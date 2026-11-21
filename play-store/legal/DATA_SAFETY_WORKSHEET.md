# Google Play Data safety — evidence collection worksheet

> This is **not a completed Data safety declaration**. Answer from the **final built AAB and enabled SDK features**, not from assumptions.

| Potential category | App source or SDK | Possible purpose | Need to verify |
|---|---|---|---|
| Gameplay / user-generated local progress | Game engine | Save and resume gameplay | Local-only versus Android backup/Play Games upload |
| Account or user IDs | Google Play Games Services (optional) | Sign-in, cloud saves | SDK data collected and account handling |
| Purchase history / transaction IDs | Google Play Billing | Payments, entitlement restoration | What client stores and retains |
| Device / advertising identifiers | Google AdMob (if enabled) | Ad serving, measurement, fraud prevention | Partner data disclosures, consent, collection/sharing |
| App performance / diagnostics | Android/SDK dependencies | Quality monitoring | Whether crash/analytics SDKs are shipped |
| Cloud save files | Google Play Games Saved Games | Sync | Encryption, retention, deletion process |

## Before completing Play Console form

1. Build and inspect final AAB, manifest permissions, dependency graph, and SDK data-safety guidance.
2. Determine which categories are collected, shared, encrypted in transit, optional/required, and user-deletable.
3. Prepare privacy policy, account/data deletion procedure, consent UX if needed.
4. Complete the form **even if no data is collected**.
5. Re-check after every ad, analytics, billing or login SDK change.

Reference: https://support.google.com/googleplay/android-developer/answer/10787469
