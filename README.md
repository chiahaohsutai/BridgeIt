# BridgeIt

What did we make? The team designed a 2 player BridgeIt game. 

*Course Name:* Fundamentals of Computer Science 2

Team Members: Chia Hao Hsu Tai (myself) and Robert Yoo

### Summary of Technical Design:

The design of the game concentrates on the use of mutation, ArrayLists and Java's Deque data structure design. The project uses 2D ArrayLists to store and keep track of the mutatable objects. Moreover, since objects are stored in lists, the project is able to quickly cycle thorugh all objects using a simple for loop. Mutation aids in changing each state of the game. Objects are being mutated based on the user's inputs. The Deque data structure was used in order to link objects together. The project does not use a Deque, but is utilices the same concept for linking objects togther and generating cyclical data. This linking becomes fundamental when the game needs to check if a player has won. The linking allows the project to direclty check the paths that are being created by each player. The project uses Java's impworld and world images libraries to generate the front end of the game. 

### Summary of Game:

The game consists of a two-dimensional grid of cells, with each cell being assigned one of three colors. The first player’s goal is to create an uninterruped path of yellow/orange cells from the top to the bottom edge of the grid. The second player’s goal is to do the same, but from the right to left bottom edge using blue cells. The game is played on an n by n grid, where n is an odd number greater than or equal to 3. The initial board should has blue cells in the leftmost and rightmost columns, but only in every other row, starting at the second rows. The initial board should also have orange/yellow cells in the topmost and bottommost rows, but only in every other column, starting at the second column. All of the other cells should be white. Each player then alternates clicking white cells, changing the cell to the player’s color. Once a white cell has been changed, it can never be changed again. White cells along the edges of the board cannot be changed. (Orange/yellow always starts)

How to play:
To play please do the following:

To play please do the following:
- Download all the files in the repository
- Add the .java files to your project folder
- Add the .jar files as extrenal libraries to the project's classpath
- In your run configurations:
  - Under main, for your main class write: tester.Main
  - Under arguments, for your program arguments write: RunBridgeIt
- If you want to run all the tests, write the following for your program arguments:
  - RunBridgeIt
  - UtilsTests
  - GameTests
  - CellTests


Sneak Peak into the Game:

![Screen Shot 2021-12-23 at 3 13 34 PM](https://user-images.githubusercontent.com/89400862/147290770-2ad8105b-1061-44ec-a2ff-f5785549ccf6.png)
