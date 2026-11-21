# GHOSTNET Android (v3.8 development source)

A single-player Android wrapper for GHOSTNET v3.7. It preserves the four-tab UI, shop, single-player simulation and existing browser storage save format. No server or public website is required.

## Build locally

Requirements: Node 22+, Android Studio 2025.2.1+, Android SDK, JDK 21 (see Capacitor v8 docs), and network access to npm/Gradle the first time.

```bash
npm run android:init    # install Capacitor, generate Android Studio project, add native Google services plugin
npm test
npm run android:open    # use Android Studio for emulator/device and Play signing
# or: npm run android:build
```

The current source repository intentionally contains **the generation template**, not downloaded dependencies or an unsigned APK. Run the initialization script before the Android build. A non-debug Play Store build needs a real signing key and Google Play Console setup.

## Player modes

- **Guest (default):** play offline with device-local saves, daily rewards, Zero Day and shop upgrades. No login and no checkout.
- **Google Play Games player (optional):** user connects from ZERO DAY, then may explicitly upload the current save or explicitly restore the cloud snapshot. No silent overwrite. Upload/download requires Android Play Games Services setup.
- **Rewarded ads:** Android plugin loads Google's **TEST rewarded ads**; rewards are granted only when the native SDK reports earning a reward. Browser edition retains demo boosts. No production advertising is enabled.
- **Payments:** price previews are intentionally disabled until the exact in-app products are configured and Billing is tested. A Java Billing 8 dependency is declared for the next phase; *no money can be charged by this build*. This avoids pretending that a purchase is processed when no Play Console account/products exist.

## Required Play Console configuration

1. Create an Android app with application ID `nl.perry3d.ghostnet`. **Decide the permanent package ID before publishing.**
2. Configure Play Games Services v2 for the app: enable Saved Games, add SHA-1 for debug/release signing certificates, publish game configuration to test users, and configure required Play Games app ID in Android resources/manifest per the current Google instructions.
3. Replace Google's sample AdMob app ID and ad unit with your own configured production IDs **only after** setting up AdMob consent and test-device settings. Never click production ads during development.
4. Create Play Billing one-time products for `ghostnet_starter`, `ghostnet_pc_100`, `ghostnet_pc_250`, `ghostnet_pc_550`, `ghostnet_supporter`, `ghostnet_ad_free`. Define regional prices in Play Console. Do not enable purchasing until the purchase fulfillment/recovery flow has been implemented and tested.
5. Build a signed AAB, upload to Google Play internal testing, and test cloud saves/rewarded ads on a real Android device.

## Storage and risk model

Game saves are local. Cloud snapshots are an optional backup for connected users. Free gameplay is not protected against cheating. For purchased consumable PC, Google does not automatically provide a durable, restorable balance after consumption: a client-only implementation needs a clear policy for lost saves/refunds, and must implement idempotent grants and purchase recovery before enabling checkout.

This repository is for development/storage; do **not** enable GitHub Pages.
