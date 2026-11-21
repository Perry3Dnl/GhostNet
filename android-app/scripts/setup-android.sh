#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
npm install
if [ ! -d android ]; then npx cap add android; fi
mkdir -p android/app/src/main/java/nl/perry3d/ghostnet
cp android-template/app/src/main/java/nl/perry3d/ghostnet/*.java android/app/src/main/java/nl/perry3d/ghostnet/
python3 scripts/configure-native.py
npx cap sync android
printf '
Project initialized. Open with: npx cap open android
'
