#!/usr/bin/env python3
from pathlib import Path
p=Path('android/app/build.gradle')
s=p.read_text()
deps="\n    implementation 'com.google.android.gms:play-services-games-v2:21.0.0'\n    implementation 'com.google.android.gms:play-services-ads:24.0.0'\n    implementation 'com.android.billingclient:billing:8.0.0'\n"
if 'play-services-games-v2' not in s:
 idx=s.index('dependencies {')+len('dependencies {')
 s=s[:idx]+deps+s[idx:]
 p.write_text(s)
p=Path('android/app/src/main/AndroidManifest.xml')
s=p.read_text()
if 'com.google.android.gms.ads.APPLICATION_ID' not in s:
 s=s.replace('<application', '<application', 1)
 marker='</application>'
 # Google's official sample AdMob app ID; never use production ad IDs in debug builds.
 s=s.replace(marker,'<meta-data android:name="com.google.android.gms.ads.APPLICATION_ID" android:value="ca-app-pub-3940256099942544~3347511713" />\n'+marker)
 p.write_text(s)
print('Native Android dependencies and AdMob test app ID configured')

# Register the Play Games Services application identifier as a resource.
# Set a real PGS ID in Play Console before signing/testing cloud saves.
p=Path('android/app/src/main/res/values/strings.xml')
s=p.read_text()
if 'game_services_project_id' not in s:
 s=s.replace('</resources>', '<string name="game_services_project_id">000000000000</string>\n</resources>')
 p.write_text(s)
p=Path('android/app/src/main/AndroidManifest.xml')
s=p.read_text()
if 'com.google.android.gms.games.APP_ID' not in s:
 s=s.replace('</application>', '<meta-data android:name="com.google.android.gms.games.APP_ID" android:value="@string/game_services_project_id" />\n</application>')
 p.write_text(s)
