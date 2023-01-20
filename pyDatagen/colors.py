from typing import Callable

colors = ['black', 'gray', 'light_gray', 'white', 'brown', 'blue', 'cyan', 'light_blue', 'lime', 'green', 'yellow',
          'orange', 'red', 'pink', 'magenta', 'purple']


def foreach(consumer: Callable[[str], None]):
    for color in colors:
        consumer(color)
