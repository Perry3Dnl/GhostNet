#!/usr/bin/env python3
"""Convert committed vector designs into Google Play PNG assets.

Requirements: pip install cairosvg pillow
Run from repository root: python3 play-store/assets/render-assets.py
"""
from pathlib import Path

try:
    import cairosvg
    from PIL import Image
except ImportError as exc:
    raise SystemExit("Install dependencies: pip install cairosvg pillow") from exc

ROOT = Path(__file__).resolve().parent
OUT = ROOT / "generated"
OUT.mkdir(parents=True, exist_ok=True)
for src, dst, size in [
    ("app-icon.svg", "app-icon-512.png", (512, 512)),
    ("feature-graphic.svg", "feature-graphic-1024x500.png", (1024, 500)),
]:
    infile = ROOT / "source" / src
    outfile = OUT / dst
    cairosvg.svg2png(url=str(infile), write_to=str(outfile), output_width=size[0], output_height=size[1])
    with Image.open(outfile) as im:
        assert im.size == size, (outfile.name, im.size)
        assert im.mode in ("RGB", "RGBA"), im.mode
    print(f"OK: {outfile.relative_to(ROOT)} {size[0]}x{size[1]} ({outfile.stat().st_size:,} bytes)")
