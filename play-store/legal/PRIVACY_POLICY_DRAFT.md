# GHOSTNET privacy policy — REVIEW DRAFT (NOT READY FOR PUBLICATION)

> **Do not publish this draft as a final legal statement until the developer name, support contact, SDK integrations, data declarations and retention practices have been confirmed.** No public privacy-policy URL exists yet. Google Play requires a public URL and policy text/link within the released app.

**Effective date:** [RELEASE DATE]

**Developer / data controller:** [LEGAL DEVELOPER OR ORGANIZATION NAME]

**Contact:** [SUPPORT/PRIVACY EMAIL]

**Game:** GHOSTNET: Hacker Clicker for Android (`nl.perry3d.ghostnet`)

## How gameplay works

GHOSTNET is a single-player fictional simulation. When used as a guest, gameplay, progress, virtual currencies, achievements, daily rewards and settings are stored locally on the device. The user may export or delete their local save data using available game controls, or remove device storage by uninstalling the game. Whether Android system backups include app data must be checked for the final package.

## Optional Google Play Games

If a player chooses to connect Google Play Games Services, Google may process the player's Play Games profile identifier and saved-game data to authenticate and synchronize game progress. Cloud save upload and restore should be user-initiated where the final app offers those actions. Google's terms and privacy practices apply to its services.

## Rewarded advertising

If live rewarded ads are enabled, Google AdMob and its partners may collect or process device identifiers, advertising data and other information needed to serve, measure and protect ads. Depending on location and settings, applicable consent disclosures and controls will be provided. **Verify the actual AdMob SDK behavior and consent flow before publication.** Test ads are not production advertising.

## In-app purchases

If purchases are enabled, transactions are processed by Google Play Billing. Google handles payment details; the game receives transaction status and product entitlement data to grant purchased content, restore permanent purchases and provide support. Whether transaction identifiers are saved locally and for how long must be confirmed after implementation. We do not intend to collect payment card numbers directly.

## Information the game may process

- Local gameplay progress, preferences, achievement and reward state.
- Play Games profile and cloud save information **only when linked**.
- In-app purchase records and entitlement information **when purchasing**.
- Ad-related identifiers and diagnostic data **when live AdMob is enabled**.
- Technical crash or diagnostic data **if those services are installed and enabled**; verify SDK inventory first.

## Why data is used

To operate the game, persist saves, provide optional backup, deliver permitted advertising and purchases, restore eligible entitlements, prevent accidental duplicate transaction grants and respond to player support requests.

## Sharing and retention

Local gameplay data is stored on the player's device until removed or overwritten. Optional connected services may store and process information under Google's policies. The developer does not plan to sell gameplay saves as personal information. Exact service retention periods, legal bases and sharing disclosures depend on the final SDKs and must be confirmed before publication.

## User choices, access, deletion

Guest players may play without a developer account and may delete local game storage. Connected players can disconnect from Play Games in the final app and may manage their Google data via Google's settings. For inquiries about developer-held data or rights, contact [SUPPORT/PRIVACY EMAIL].

## Children and age suitability

GHOSTNET's intended audience and content rating must be accurately declared in Google Play Console. The final privacy notice, consent experience and audience selection must be checked against any applicable children's privacy requirements.

## Updates

We may update this privacy policy to reflect the final game features, legal requirements and SDK practices. Material changes will be communicated as required.

---

### Mandatory review before publishing

- Replace ALL brackets with confirmed, truthful information.
- Inventory packaged permissions and SDKs (Capacitor, Play Games, AdMob, Billing and any crash reporting).
- Verify Google Play Data safety declarations against the actual release binary and SDK disclosures.
- Review consent requirements, especially for EEA/UK and advertising.
- Confirm policy text can be opened within the app and is hosted at a stable **public HTTPS URL**; GitHub repository storage itself is not a policy deployment.
- Validate support/data deletion procedure, Play Games data handling, and purchase history behavior.

Sources: https://support.google.com/googleplay/android-developer/answer/10144311 and https://support.google.com/googleplay/android-developer/answer/10787469
