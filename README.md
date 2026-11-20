# GHOSTNET — Incremental Hacker Clicker

A fictional, offline-playable incremental game for mobile browsers. Hack simulated targets, grow a botnet, unlock upgrades, prestige with **ZERO DAY**, and ascend items to **Level 9,999**.

## Project repository — source and version archive

**This repository is for development and storage only. GitHub Pages is not enabled or intended.** To play, download `index.html` and open it locally in a browser. No deployment, build, API, or account is needed.

## Current experimental build

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
