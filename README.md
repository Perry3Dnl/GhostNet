# GHOSTNET — Incremental Hacker Clicker

## v3.7 — Daily Operations (current development build)

- [Play locally with index.html](index.html) or use the [versioned v3.7 snapshot](releases/ghostnet_v3_7_daily_operations.html). The repository is for source/version storage; **do not deploy GitHub Pages**.
- **TARGETS → DAILY:** seven-step login reward cycle (3, 5, 8, 10, 15, 20, 30 PC), three daily quests, one advanced quest, and a rotating free daily offer.
- Daily quest counters track upgrades, successful breaches, contract completions, simulated ad-button activations, and ZERO DAY resets. Tasks reset at **00:00 UTC**. Missing days does not erase the login reward cycle.
- **◆ PC:** preview of the six agreed euro-priced products. **These are NOT real transactions**: checkout is intentionally disabled and no money, credits, cosmetic ownership, or ad-free privileges can be purchased yet.
- The visible +PC debug-grant buttons were removed. Current ad boosts are **simulated activations, not advertisements**; the daily quest labels reflect that.
- v3.7 uses a new save key, attempts migration from v3.4 and previous builds, and maintains portable JSON import/export.
- Browser checks: 320px/360px/430px no horizontal overflow, daily login claims, ad quest progression, repeated-claim prevention after a restored save, and store previews. Longer-term pacing and clock manipulation resistance have not been validated.
- **Security caveat:** locally stored daily claims and rewards can be modified by players. Before connecting payments or real ad networks, move purchased PC, ownership entitlements, and reward claim validation to a server.


A fictional, offline-playable incremental game for mobile browsers. Hack simulated targets, grow a botnet, unlock upgrades, prestige with **ZERO DAY**, and ascend items to **Level 9,999**.

## Project repository — source and version archive

**This repository is for development and storage only. GitHub Pages is not enabled or intended.** To play, download `index.html` and open it locally in a browser. No deployment, build, API, or account is needed.

## Current experimental build

**v3.6 — Stable Touch UI** is the latest version: [index.html](index.html) / [archived v3.6](releases/ghostnet_v3_6_stable_touch_ui.html). It fixes layout jumps and animates bars independently of the simulation.

- **v3.4 — Boost Balance:** [index.html](index.html) / [release snapshot](releases/ghostnet_v3_4_boost_balance.html).
- **v3.3 — Progression Balance:** [release snapshot](releases/ghostnet_v3_3_progression_balance.html).
- **v3.2 — Prestige Pacing:** [release snapshot](releases/ghostnet_v3_2_prestige_pacing.html).

Progress is saved locally in your browser. Use **ZERO DAY → Save Vault → Export Save** before changing browsers or moving to another device. v3.4 keeps its own save key and attempts migration from v3.3 and earlier saves.

## v3.4 balance changes

- Base BTC payouts reduced by **18%**.
- Wallet Extraction's reward multiplier has a lower soft cap.
- Ad buttons: simulated Crypto Booster now provides **×3 BTC for five minutes**; other ad abilities retain their documented bonuses.
- Premium Credits: **BTC Surge ×2.2 / 10 min**, **Turbo Scan ×2 / 5 min**. Permanent CPU and Magnet upgrades remain available as PC unlocks.
- **TARGETS → INTEL → BOOST COMPARISON** tests free, ad-boosted, and premium-loadout scenarios without touching the actual save.

## Automated balance audit

[Download 102-run report](reports/v3_4_boost_simulation_102_runs.csv): 17 isolated buying strategies × two prestige runs × three boost modes.

The test is a **deterministic expected-value approximation**: one shop item is repeatedly purchased with BUY MAX, automatic target progression is enabled, and random payouts are averaged. The first run represents around 30 minutes; the second represents around six hours.

Mode assumptions used for a directly comparable audit:
- **Free:** no optional boosts.
- **Ads:** all four ad effects reactivated every 15 minutes, with normal 5-minute / 3-minute durations.
- **PC:** assumes access to Premium CPU and Bitcoin Magnet permanent items, plus sufficient PC to reactivate BTC Surge for 10 minutes and Turbo Scan for 5 minutes every 20 minutes. Credits are assumed supplied by the scenario rather than generated or purchased in-game. No real payment integration exists.

These modeled premium scenarios show the upper potential of PC upgrades, **not** a claim about typical spending or player earnings. Ads are simulated buttons, not real ad placements. Neither mode accelerates ZERO DAY's separate bounded research timer; stronger payouts accelerate network progress instead.

## Balance targets

- First ZERO DAY: approximately **30 minutes**.
- Second ZERO DAY: approximately **six additional hours**.
- Regular upgrades, tier milestones and rewards throughout the run.

Open bugs, balance observations and future versions should be tracked as GitHub issues and commits. The current build is a balance-test build, not a certified release.

This is a fictional simulation. It does not interact with real networks.
