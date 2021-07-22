Conway's Game of Life
=====================
*Rewritten in Java by Tricia McMillan*
- - - 
Simplified rules
---
*Taken directly from [Wikipedia](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Rules).*

The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, live or dead [...]. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:


1. Any live cell with two or three live neighbours survives.
2. Any dead cell with three live neighbours becomes a live cell.
3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.



---
This has been rewritten in Java, with the biggest difference being that it is not on an infinite grid, but instead on a grid as defined by the user.

The starting seed can be randomly generated or determined by the user, if selected. To continue with each step, the user must press the enter key, or type "exit" to exit.

TODO:
- Make the grid infinitely loop
- Create a graphical interface