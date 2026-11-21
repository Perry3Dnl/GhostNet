#!/usr/bin/env python3
"""Configure Gradle to sign a release bundle with keystore path supplied by CI.

Never store or print keystore passwords in this repository.
"""
import os
from pathlib import Path

required = (
    "GHOSTNET_KEYSTORE_PATH",
    "GHOSTNET_KEY_ALIAS",
    "GHOSTNET_STORE_PASSWORD",
    "GHOSTNET_KEY_PASSWORD",
)
missing = [name for name in required if not os.environ.get(name)]
if missing:
    raise SystemExit("Release signing unavailable: missing GitHub secrets/environment: " + ", ".join(missing))

keystore = Path(os.environ["GHOSTNET_KEYSTORE_PATH"])
if not keystore.is_file() or keystore.stat().st_size < 128:
    raise SystemExit("Release keystore file missing or invalid")

gradle = Path("android/app/build.gradle")
source = gradle.read_text(encoding="utf-8")
if "ghostnetUploadRelease" in source:
    raise SystemExit("Signing config already injected; refusing to duplicate")

# Gradle reads sensitive values from environment; none are embedded in repo source.
source += r"""

// CI-only Google Play upload signing; values provided via Actions secrets.
// Never check in a keystore or credentials.
android {
    signingConfigs {
        ghostnetUploadRelease {
            storeFile file(System.getenv("GHOSTNET_KEYSTORE_PATH"))
            storePassword System.getenv("GHOSTNET_STORE_PASSWORD")
            keyAlias System.getenv("GHOSTNET_KEY_ALIAS")
            keyPassword System.getenv("GHOSTNET_KEY_PASSWORD")
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.ghostnetUploadRelease
        }
    }
}
"""
gradle.write_text(source, encoding="utf-8")
print("CI release signing configuration enabled using environment variables")
