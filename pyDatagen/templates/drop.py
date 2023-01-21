import textwrap


def single_item(item: str, count: int = 1):
    return textwrap.dedent('''\
    {
      "type": "minecraft:block",
      "pools": [
        {
          "bonus_rolls": 0,
          "conditions": [
            {
              "condition": "minecraft:survives_explosion"
            }
          ],
          "entries": [
            {
              "type": "minecraft:item",
              "name": "${item}"
            }
          ],
          "rolls": ${count}
        }
      ]
    }
    ''').replace('${item}', item).replace('${count}', str(count))
