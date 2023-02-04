import os
import textwrap


def from_file(filename: str) -> str:
    path = os.path.dirname(__file__) + '/../json/' + filename + '.json'
    with open(path, 'r') as file:
        return file.read()


def single_blockstate(model: str) -> str:
    return textwrap.dedent('''\
    {
      "variants": {
        "": {
          "model": "${model}"
        }
      }
    }''').replace('${model}', model)


def generated_model(texture: str) -> str:
    return textwrap.dedent('''\
    {
      "parent": "minecraft:item/generated",
      "textures": {
        "layer0": "${texture}"
      }
    }
    ''').replace('${texture}', texture)


def parented_model(model: str, textures: [(str, str)] = None) -> str:
    overwritten_textures = ''
    if textures is not None:
        overwritten_textures += ',\n  "textures": {\n'
        for texture in textures:
            overwritten_textures += f'    "{texture[0]}": "{texture[1]}",\n'
        overwritten_textures = overwritten_textures[:-2] + '\n  }'

    return textwrap.dedent('''\
    {
      "parent": "${model}"${overwritten_textures}
    }
    ''').replace('${overwritten_textures}', overwritten_textures).replace('${model}', model)


def frontlight(model: str) -> str:
    return model[:-3] + ',\n  "gui_light": "front"\n}\n'
