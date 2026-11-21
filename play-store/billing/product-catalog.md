# GHOSTNET Google Play Billing product catalog (draft)

Prices below are **proposed Netherlands EUR prices**, not configured store offers. Google Play Console manages final prices, tax and regional conversion. Never hardcode checkout prices in the app; query Play Billing product details and display the localized price.

| Product ID | Type | NL list price | Grant | Repeatable? |
|---|---|---:|---|---|
| `ghostnet_starter` | One-time non-consumable | €1.99 | 75 Premium Credits **once** + exclusive terminal cosmetic | No |
| `ghostnet_pc_100` | Consumable | €2.99 | 100 PC | Yes |
| `ghostnet_pc_250` | Consumable | €5.99 | 250 PC | Yes |
| `ghostnet_pc_550` | Consumable | €11.99 | 550 PC | Yes |
| `ghostnet_supporter` | One-time non-consumable | €4.99 | Supporter theme + permanent badge | No |
| `ghostnet_ad_free` | One-time non-consumable | €5.99 | Eligible ad-gated boosts can be claimed without ad views, **same cooldown and reward** | No |

## Delivery & restore rules

- **Payment setup is not yet active.** Billing dependency alone is not a working checkout; do not enable Buy until product discovery, checkout, acknowledgement, recovery and tests succeed.
- Enable real-money checkout only for players connected through the game's chosen Play Games identity gate. A Play Games identity is not proof of ownership of a Play Billing purchase; query Google Play Billing separately.
- After **PURCHASED** state only (not PENDING), grant entitlement. Do not grant on cancel/error.
- For consumables, record token and applied grant locally in a durable pending transaction journal, add PC once, save atomically where possible, then consume token with `consumeAsync()`; retry safely on restart. Backup/recovery edge cases still require testing.
- For non-consumables, record ownership, grant associated entitlement once, and acknowledge via `acknowledgePurchase()` when needed. Restore by querying purchases and reapplying permanent entitlements. The starter PC allotment is **not repeatable** on restore; it must be recorded as granted.
- **Consumed PC packs are not automatically replayable from Google Play's purchase history after reinstall.** Without a server, their spent/unspent PC balances rely on the local save or voluntary cloud save. Make this limitation clear before enabling payments and keep a Save Vault export option.
- Respect refunds, cancellations, price changes, and any Google policy/consumer rights obligations. Backend verification remains an optional later improvement, not an anti-cheat requirement for offline single-player gameplay.
- Never use test-ad completion as proof that a real ad was watched.

## Play Console setup

1. Create the app with package ID `nl.perry3d.ghostnet` (confirm this permanent identifier before first publication).
2. Under **Monetize with Play → Products → In-app products**, create exactly these six IDs.
3. Mark products as consumable/non-consumable through app fulfillment logic and use the correct Google Play product model.
4. Configure Netherlands EUR pricing and any regional prices in Play Console.
5. Add license tester accounts; test purchases with Google Play's license-testing environment, including pending orders, restarts, duplicate callbacks, cancellations, refunds, and restores.
6. Use client-side Billing 8 flow according to Google documentation when there is no backend: https://developer.android.com/google/play/billing/integrate

**No passwords, payment credentials, or signing keys belong in the repository.**
