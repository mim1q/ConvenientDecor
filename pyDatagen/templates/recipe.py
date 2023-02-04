import textwrap


def combine_two(first: str, second: str, result: str):
    return textwrap.dedent('''\
      {
        "type": "minecraft:crafting_shapeless",
        "ingredients": [
          { "item": "${first}" },
          { "item": "${second}" }
        ],
        "result": { "item": "${result}", "count": 1 }
      }
    ''').replace('${first}', first).replace('${second}', second).replace('${result}', result)
