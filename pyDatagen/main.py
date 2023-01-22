import os
import shutil
import sys

import colors
from templates import drop
from templates import basic

mod = 'convenientdecor'


def generate(base_path: str):
    def asset(file): return base_path + '/assets/' + mod + "/" + file + '.json'
    def data(file): return base_path + '/data/' + mod + "/" + file + '.json'
    def mod_id(name): return mod + ':' + name

    print('Executing Python Datagen Script')

    def umbrella(color: str, recipe: bool = True):
        save(basic.single_blockstate(f'minecraft:block/{color if color in colors.colors else "black"}_wool'), asset(f'blockstates/{color}_umbrella'))
        save(basic.frontlight(basic.parented_model('minecraft:builtin/entity')), asset(f'models/item/{color}_umbrella'))
        if recipe:
            save(basic.from_file('recipes/black_umbrella').replace('black', color), data(f'recipes/{color}_umbrella'))

    colors.foreach(umbrella)
    umbrella('broken', recipe=False)

    save(basic.single_blockstate(mod_id('block/umbrella_stand')), asset('blockstates/umbrella_stand'))
    save(basic.parented_model(mod_id('block/umbrella_stand')), asset('models/item/umbrella_stand'))
    save(drop.single_item(mod_id('umbrella_stand')), data('loot_tables/blocks/umbrella_stand'))


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
