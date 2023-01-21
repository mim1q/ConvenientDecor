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
    }''').replace("${model}", model)


def parented_model(model: str) -> str:
    return textwrap.dedent('''\
    {
      "parent": "${model}"
    }
    ''').replace("${model}", model)


def frontlight(model: str) -> str:
    return model[:-3] + ',\n  "gui_light": "front"\n}\n'
