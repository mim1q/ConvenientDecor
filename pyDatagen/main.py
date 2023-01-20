import os
import shutil
import sys
import colors
from templates import basic

mod = 'convenientdecor'


def generate(base_path: str):
    def asset_path(file): return base_path + '/assets/' + mod + "/" + file + '.json'
    def mod_id(name): return mod + ':' + name

    print('Executing Python Datagen Script')

    def umbrellas(color: str):
        save(basic.single_blockstate('minecraft:block/' + color + '_wool'), asset_path('blockstates/' + color + '_umbrella'))
        save(basic.parented_model('minecraft:builtin/entity'), asset_path('models/item/' + color + '_umbrella'))

    colors.foreach(umbrellas)
    save(basic.single_blockstate('minecraft:block/black_wool'), asset_path('blockstates/broken_umbrella'))
    save(basic.parented_model('minecraft:block/black_wool'), asset_path('models/item/broken_umbrella'))


def save(content: str, path: str):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w') as file:
        file.write(content)


def main():
    if len(sys.argv) == 1:
        print('No output path specified')
        exit(1)
    output_path = sys.argv[1]

    if os.path.exists(output_path):
        shutil.rmtree(output_path)

    print('Running Python Data Generator Script')
    print('Output Path: ' + output_path)
    generate(output_path)


if __name__ == '__main__':
    main()
