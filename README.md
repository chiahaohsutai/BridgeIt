## BridgeIt 

### Project Description:

The game consists of a two-dimensional grid of cells, with each cell being assigned one of three colors. The players’ goal is to create an uninterrupted path of yellow/orange cells from one side of the grid to the opposite side of the grid. The game is played on an n-by-n grid, where n is an odd number greater than or equal to 3. The initial board should have blue cells in the leftmost and rightmost columns, but only in every other row, starting at the second row. The initial board should also have yellow cells in the topmost and bottommost rows, but only in every other column, starting at the second column. All the other cells should be white. Each player then alternates clicking white cells, changing the cell to the player’s color. Once a white cell has been changed, it can never be changed again. White cells along the edges of the board cannot be changed. (Yellow always starts). If the set-up of the board is not clear, you can find a picture of the game at the end of the readme file. In the image, the players have already clicked some of the white cells. The original board should look like a chess board but with 3 colors. 

### How to play:

To play please do the following:
- Download all the files in the repository
- Add the .java files to your project folder
- Add the .jar files as external libraries to the project's classpath
- In your run configurations:
  - Under main, for your main class write: tester.Main
  - Under arguments, for your program arguments write: RunBridgeIt
- If you want to run all the tests, write the following for your program arguments:
  - RunBridgeIt
  - UtilsTests
  - GameTests
  - CellTests

Image of the game:

![Screen Shot 2021-12-23 at 3 13 34 PM](https://user-images.githubusercontent.com/89400862/147290770-2ad8105b-1061-44ec-a2ff-f5785549ccf6.png)
![image](https://user-images.githubusercontent.com/89400862/210663962-13e84788-145c-4510-a100-d7ef4683aedf.png)
