# GHOSTNET — Incremental Hacker Clicker

A fictional, offline-playable incremental game for mobile browsers. Hack simulated targets, grow a botnet, unlock upgrades, prestige with **ZERO DAY**, and ascend shop items up to **Level 9,999**.

## Play

Open **[index.html](index.html)** in your browser after downloading the repository (or enable GitHub Pages in repository **Settings → Pages** and select **Deploy from a branch → main → / (root)**).

The game is a standalone HTML file. It needs no installation, build step, API, or account. **Game saves live in your browser**, so use **ZERO DAY → Save Vault → Export Save** before changing devices or browsers.

## Versioned builds

- **Current experimental build:** [v3.3 — Progression Balance](releases/ghostnet_v3_3_progression_balance.html), also available as [index.html](index.html).
- **Previous build:** [v3.2 — Prestige Pacing](releases/ghostnet_v3_2_prestige_pacing.html).

The v3.3 build retains the compact four-tab UI, 16 upgrades, item ascension, achievements, tier mechanics, and backup/export features. It changes progression pacing by staging target unlocks and adjusting higher-level item costs.

## Pacing goals

- **First ZERO DAY:** approximately 30 minutes of simulated progression.
- **Second ZERO DAY:** approximately six additional hours after resetting.
- **First run:** introductory targets; subsequent runs unlock later target chapters.
- **Longer runs:** keep meaningful purchases and item milestones available instead of relying only on a prestige timer.

See the [v3.3 simulation summary](reports/v3_3_progression_simulation.csv) for representative deterministic strategy runs.

### Testing limitations

The automated balance model uses expected-value income, automatic target selection, and one-item buying strategies. Its times are **simulated estimates**, not guarantees for real players. Random drops, manual taps, boosts, skill choices, and offline progression can alter pacing. Some single-item strategies have long purchase gaps even after balancing, so this is still a tuning build.

## Development workflow

1. Preserve the playable game in `index.html`.
2. Archive each notable version under `releases/`.
3. Run deterministic economy checks and compare pacing against the desired milestones.
4. Record results under `reports/`.
5. Validate browser saves, imports, bulk purchasing, and mobile layout before declaring a release stable.

This is an original fictional hacking simulation. It does not interact with real networks.
