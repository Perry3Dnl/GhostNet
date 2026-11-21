# GHOSTNET Google Play artwork and screenshots

## Ready-to-render original art

- **App icon source:** [source/app-icon.svg](source/app-icon.svg), renders to **512 × 512 PNG**.
- **Feature graphic source:** [source/feature-graphic.svg](source/feature-graphic.svg), renders to **1024 × 500 PNG**.
- Source SVGs live permanently in GitHub. The [Asset Builder GitHub Action](../../../.github/workflows/play-store-assets.yml) creates the store-ready PNGs as downloadable workflow artifacts. No website deployment is involved.
- To render locally: `pip install cairosvg pillow` then `python3 play-store/assets/render-assets.py`.

## Phone screenshots — must capture from actual game

The release must include **2–8 genuine phone screenshots**. Do **not** submit AI-generated concepts, mock gameplay, fabricated features, or composite art as actual screenshots. Suggested 1080 × 1920 captures: (1) hacking/terminal, (2) shop and upgrades, (3) TARGETS, (4) ZERO DAY and ascension, (5) daily quests, (6) Premium Credits shop **only when the functionality shown is real and enabled**.

One possible capture workflow, after installing the debug APK on an Android emulator set to a 1080 × 1920 portrait profile:

```bash
adb shell screencap -p /sdcard/ghostnet-screenshot.png
adb pull /sdcard/ghostnet-screenshot.png screenshot-01-hacking.png
```

Capture other screens manually and inspect crop, aspect ratio, UI legibility, legal compliance and accuracy. Screenshots must reflect the actual release binary.

## Brand policy

Use the same mint-on-dark branding across the game and listing. Avoid implying real hacking, real cryptocurrency earnings, guaranteed rewards, or affiliation with Google. The project is fictional single-player entertainment.

## Current Google reference

https://support.google.com/googleplay/android-developer/answer/9866151
