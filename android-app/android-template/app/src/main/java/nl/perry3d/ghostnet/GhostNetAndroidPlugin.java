package nl.perry3d.ghostnet;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Native boundary for the offline GHOSTNET game.
 *
 * Play Games is optional: accounts are not required for guest play.
 * Cloud backups are explicitly requested from JS, never automatically uploaded.
 * AdMob uses Google's official Android rewarded TEST ad unit ID. No live advertising.
 * No Play Billing checkout is exposed until product catalog and idempotent grants are tested.
 */
@CapacitorPlugin(name = "GhostNetAndroid")
public class GhostNetAndroidPlugin extends Plugin {
    private static final String SLOT = "ghostnet_main";
    private static final int MAX_SAVE_BYTES = 4 * 1024 * 1024;
    private static final String TEST_REWARDED_ID = "ca-app-pub-3940256099942544/5224354917";

    @Override
    public void load() {
        try {
            PlayGamesSdk.initialize(getActivity());
        } catch (Exception ignored) {
            // An unconfigured Play Games application must not prevent guest mode.
        }
        try {
            MobileAds.initialize(getContext(), initializationStatus -> {});
        } catch (Exception ignored) {
            // Missing Google services must not prevent offline gameplay.
        }
    }

    @PluginMethod
    public void accountStatus(PluginCall call) {
        try {
            PlayGames.getGamesSignInClient(getActivity()).isAuthenticated()
                .addOnSuccessListener(result -> {
                    JSObject value = new JSObject();
                    value.put("connected", result.isAuthenticated());
                    call.resolve(value);
                })
                .addOnFailureListener(error -> guest(call));
        } catch (Exception e) {
            guest(call);
        }
    }

    @PluginMethod
    public void signIn(PluginCall call) {
        try {
            PlayGames.getGamesSignInClient(getActivity()).signIn()
                .addOnSuccessListener(result -> {
                    JSObject value = new JSObject();
                    value.put("connected", result.isAuthenticated());
                    call.resolve(value);
                })
                .addOnFailureListener(error -> call.reject("Play Games sign-in failed: " + error.getMessage()));
        } catch (Exception e) {
            call.reject("Play Games is not configured: " + e.getMessage());
        }
    }

    private void guest(PluginCall call) {
        JSObject value = new JSObject();
        value.put("connected", false);
        call.resolve(value);
    }

    private void authenticated(PluginCall call, Runnable action) {
        try {
            PlayGames.getGamesSignInClient(getActivity()).isAuthenticated()
                .addOnSuccessListener(result -> {
                    if (!result.isAuthenticated()) {
                        call.reject("Connect Play Games first; local save is unchanged.");
                    } else {
                        action.run();
                    }
                })
                .addOnFailureListener(error -> call.reject("Play Games unavailable: " + error.getMessage()));
        } catch (Exception e) {
            call.reject("Play Games unavailable: " + e.getMessage());
        }
    }

    @PluginMethod
    public void cloudSave(PluginCall call) {
        String payload = call.getString("payload", "");
        byte[] bytes = payload.getBytes(StandardCharsets.UTF_8);
        if (bytes.length == 0 || bytes.length > MAX_SAVE_BYTES) {
            call.reject("Invalid save size");
            return;
        }
        authenticated(call, () -> {
            try {
                SnapshotsClient client = PlayGames.getSnapshotsClient(getActivity());
                client.open(SLOT, true, SnapshotsClient.RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED)
                    .addOnSuccessListener(result -> {
                        try {
                            Snapshot snapshot = result.getData();
                            if (snapshot == null) { call.reject("Cloud save could not be opened"); return; }
                            if (!snapshot.getSnapshotContents().writeBytes(bytes)) {
                                call.reject("Unable to write snapshot contents");
                                return;
                            }
                            client.commitAndClose(snapshot, new SnapshotMetadataChange.Builder()
                                    .setDescription("GHOSTNET local backup")
                                    .build())
                                .addOnSuccessListener(metadata -> {
                                    JSObject response = new JSObject();
                                    response.put("saved", true);
                                    call.resolve(response);
                                })
                                .addOnFailureListener(error -> call.reject("Cloud commit failed: " + error.getMessage()));
                        } catch (Exception error) {
                            call.reject("Cloud write failed: " + error.getMessage());
                        }
                    })
                    .addOnFailureListener(error -> call.reject("Cloud open failed: " + error.getMessage()));
            } catch (Exception e) {
                call.reject("Cloud service unavailable: " + e.getMessage());
            }
        });
    }

    @PluginMethod
    public void cloudLoad(PluginCall call) {
        authenticated(call, () -> {
            try {
                SnapshotsClient client = PlayGames.getSnapshotsClient(getActivity());
                client.open(SLOT, false, SnapshotsClient.RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED)
                    .addOnSuccessListener(result -> {
                        Snapshot snapshot = result.getData();
                        if (snapshot == null) { call.reject("No cloud backup found"); return; }
                        try {
                            byte[] bytes = snapshot.getSnapshotContents().readFully();
                            if (bytes.length > MAX_SAVE_BYTES) {
                                call.reject("Cloud save too large");
                                return;
                            }
                            JSObject response = new JSObject();
                            response.put("payload", new String(bytes, StandardCharsets.UTF_8));
                            call.resolve(response);
                        } catch (IOException e) {
                            call.reject("Cloud read failed: " + e.getMessage());
                        } finally {
                            // Read-only snapshot: no commit; request never overwrites remote data.
                        }
                    })
                    .addOnFailureListener(error -> call.reject("Cloud backup unavailable: " + error.getMessage()));
            } catch (Exception e) {
                call.reject("Cloud service unavailable: " + e.getMessage());
            }
        });
    }

    @PluginMethod
    public void showRewardedAd(PluginCall call) {
        String boost = call.getString("boost", "");
        if (!(boost.equals("ram") || boost.equals("vpnad") || boost.equals("crypto") || boost.equals("energy"))) {
            call.reject("Unknown boost");
            return;
        }
        // Google sample ads only. Replace test IDs only after AdMob setup and consent review.
        try {
            RewardedAd.load(getContext(), TEST_REWARDED_ID, new AdRequest.Builder().build(),
                new RewardedAdLoadCallback() {
                    @Override
                    public void onAdLoaded(RewardedAd ad) {
                        getActivity().runOnUiThread(() -> ad.show(getActivity(), rewardItem -> {
                            JSObject reply = new JSObject();
                            reply.put("rewarded", true);
                            reply.put("boost", boost);
                            call.resolve(reply);
                        }));
                    }
                    @Override
                    public void onAdFailedToLoad(LoadAdError error) {
                        call.reject("Test ad unavailable: " + error.getMessage());
                    }
                });
        } catch (Exception e) {
            call.reject("AdMob unavailable: " + e.getMessage());
        }
    }

    @PluginMethod
    public void billingStatus(PluginCall call) {
        JSObject result = new JSObject();
        result.put("available", false);
        result.put("reason", "Google Play Billing checkout is disabled until products and fulfillment are configured.");
        call.resolve(result);
    }
}
