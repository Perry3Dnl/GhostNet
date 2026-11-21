# GHOSTNET — Google Play release readiness checklist

**Project stage:** pre-release preparation, not submitted to Play. Repository is for source/artifact storage only; **no GitHub Pages deployment and no automatic Play Store publishing**.

## Already prepared in GitHub

- [x] Android Capacitor source under `android-app/`.
- [x] Debug APK build GitHub Action (`android-debug.yml`).
- [x] Manual signed AAB GitHub Action (`android-release-aab.yml`) using **Secrets** (no keys committed).
- [x] Listing copy in English within Google character limits.
- [x] Six provisional in-app products and NL price points.
- [x] Original icon and feature-art vector sources plus PNG renderer workflow.
- [x] Privacy draft and Data safety evidence checklist **clearly labeled as unfinished**.
- [x] Release notes draft and genuine-screenshot capture instructions.

## Required user / Play Console actions

- [ ] Create/verify Google Play Console developer account and legal/publisher name.
- [ ] Confirm permanent Android application ID `nl.perry3d.ghostnet` **before release**.
- [ ] Choose release countries, support contact email, privacy contact and stable **public HTTPS** privacy policy URL. Repository storage is **not** publishing the policy.
- [ ] Review/approve final English description; optionally localize Dutch.
- [ ] Complete Google Play content rating, target audience, ads, Data safety, and policy declarations truthfully from final installed SDK inventory.
- [ ] Configure Play Games Services app ID, Saved Games, testers, and app-signing SHA fingerprints. Test guest → cloud and cloud → device conflicts.
- [ ] Configure AdMob application and rewarded unit IDs, user consent requirements, test devices. Do not click real ads during development.
- [ ] Create products in Play Console and test Billing 8 grants/consume/acknowledge/refunds/purchase restoration. **Currently checkout disabled, not ready for real charging.**
- [ ] Set GitHub Actions signing secrets (instructions below) and run manual signed-AAB workflow.
- [ ] Capture actual 2–8 Android screenshots from the built app; review accuracy and text legibility.
- [ ] Upload signed AAB to **Internal testing** track, recruit license testers and test on physical devices.
- [ ] If personal Play account created after Nov 13, 2023, complete required **12 opted-in closed testers for 14 continuous days**, then apply for production access. Verify account-specific requirement in Console.
- [ ] Review rollout, payments profile, tax/payout settings in Play Console (do not share financial credentials in chat or GitHub).

## GitHub Actions secrets for signed AAB

In GitHub repo **Settings → Secrets and variables → Actions**, create:

| Secret | Description |
|---|---|
| `GHOSTNET_UPLOAD_KEYSTORE_BASE64` | Base64-encoded contents of the Android **upload** keystore |
| `GHOSTNET_UPLOAD_KEY_ALIAS` | Alias in keystore |
| `GHOSTNET_UPLOAD_STORE_PASSWORD` | Keystore password |
| `GHOSTNET_UPLOAD_KEY_PASSWORD` | Private key password |

Generate and safely back up your upload key **outside the repository**. Never paste key/passwords into source, logs or chat. Do not overwrite keystore unless following Google's upload-key reset/recovery procedure. The workflow generates `bundleRelease` and stores an AAB artifact in GitHub Actions; it **never** publishes to Google Play by itself.

## Important engineering gates

- [ ] Verify a successful native CI compile (debug APK first, then signed AAB). Merely committing a workflow is not proof it builds.
- [ ] Test on actual devices: frame pacing, scroll stability, Android back behavior, game saves, offline use, interruptions, and portrait/small screens.
- [ ] Verify Android target SDK **36** (Capacitor 8 default) in the actual generated AAB before submission. Since Aug 31, 2026 Google Play requires API 36 for new standard mobile apps.
- [ ] Verify monetization behavior: billing return codes, PENDING→PURCHASED, duplicate callbacks, consumption/acknowledgement, app restart before granting PC, and permanent purchases restored.
- [ ] Verify cloud save updates do not silently erase guest saves.
- [ ] Verify SDK consent and advertising policies, including EEA/UK users.
- [ ] Approve public policy and Data safety answers.

## Official references (verified Nov 2026)

- Target API level: https://developer.android.com/google/play/requirements/target-sdk
- Publishing and title limits: https://support.google.com/googleplay/android-developer/answer/9859152
- Assets: https://support.google.com/googleplay/android-developer/answer/9866151
- New personal-account testing: https://support.google.com/googleplay/android-developer/answer/14151465
- Data safety: https://support.google.com/googleplay/android-developer/answer/10787469
- Billing: https://developer.android.com/google/play/billing/integrate
