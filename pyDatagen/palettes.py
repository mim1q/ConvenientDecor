import os

from PIL import Image, PyAccess

import colors


def load_palettes(path: str, color: str = None) -> [dict]:
    if color is None:
        return load_palettes_idx(path, -1)
    return load_palettes_idx(path, colors.index(color))


# noinspection PyTypeChecker
def load_palettes_idx(path: str, from_idx: int) -> [dict]:
    img = Image.open(get_path(path))
    pixels = img.load()
    w = img.width
    h = img.height

    palettes = []
    base_colors = row_colors(pixels, from_idx, w)

    for i in range(h):
        row = row_colors(pixels, i, w)
        palettes.append(dict(map(lambda k, v: (k, v), base_colors, row)))

    return palettes


def row_colors(pixels: PyAccess, idx: int, width: int) -> [(int, int, int)]:
    row = []
    for i in range(width):
        row.append(pixels[i, idx])
    return row


def save_img(path: str, template_path: str, palettes: [dict], color: str):
    save_img_idx(path, template_path, palettes, colors.index(color))


# noinspection PyUnresolvedReferences
def save_img_idx(path: str, template_path: str, palettes: [dict], to_idx: int):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    img = Image.open(get_path(template_path))
    pixels = img.load()
    palette = palettes[to_idx]

    for y in range(img.height):
        for x in range(img.width):
            key = tuple(list(pixels[x, y]))
            if key in palette:
                pixels[x, y] = palette[key]

    img.save(path)


def get_path(path: str):
    return os.path.dirname(__file__) + f'/img/{path}.png'
