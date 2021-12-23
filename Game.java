import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

// represent the Bridgit game
class Bridgit extends World {
  static int cellSize = 60;
  int size;
  ArrayList<ArrayList<Cell>> board;
  boolean player1turn;
  int winCond;

  // constructor takes size as an argument, if it's an odd integer
  Bridgit(int size) {
    this.size = new Utils().checkSize(size);
    this.board = new Utils().makeBoard(size);
    this.player1turn = true;
    this.winCond = 0;
    // links cells together
    new Utils().linkBoardHorizontal(this.board);
    new Utils().linkBoardVertical(this.board);
    new Utils().setBorders(this.board);
  }

  /* TEMPLATE:
   * fields:
   * ... this.size ... - int
   * ... this.board ... - ArrayList<ArrayList<Cell>>
   * ... this.player1turn ... - boolean
   * ... this.winCond ... - int
   * methods:
   * ... this.makeScene() ... - WorldScene
   * ... this.onTick() ...    - void
   * ... this.onKeyEvent() ... - void
   * ... this.onMouseClicked(Posn) ... - void
   * ... this.lastScene(String) ... - WorldScene
   */

  // draws the scene
  public WorldScene makeScene() {
    Utils u = new Utils();
    // makes an empty scene where the cells are being drawn onto
    WorldScene result = new WorldScene(cellSize * this.size, 
        cellSize * this.size);

    // loops through the board and adds cells to it
    for (int i = 0; i < this.board.size(); i += 1) {
      // temporary value to store the current row we are iterating through
      ArrayList<Cell> row = this.board.get(i);
      for (int j = 0; j < row.size(); j += 1) {
        result.placeImageXY(row.get(j).drawCell(), u.genPos(j), 
            u.genPos(i));
      }
    }
    return result;
  }

  // checks if the player has won on each tick
  public void onTick() {
    if (this.winCond == 1) {
      this.endOfWorld("PLAYER 1 WINS");
    }
    if (this.winCond == 2) {
      this.endOfWorld("PLAYER 2 WINS");
    }
  }

  // resets the game when the user hits the 'r' key
  public void onKeyEvent(String key) { 
    if (key.equals("r")) {
      this.board = new Utils().makeBoard(size);
      new Utils().linkBoardHorizontal(this.board);
      new Utils().linkBoardVertical(this.board);
      new Utils().setBorders(this.board);
      this.player1turn = true;
      this.winCond = 0;
    }
  }

  //makes a cell change color based on the players turn
  public void onMouseClicked(Posn pos) {
    for (int row = 0; row < this.board.size(); row += 1) {
      ArrayList<Cell> currRow = this.board.get(row);
      for (int col = 0; col < currRow.size(); col += 1) {
        Cell selected = currRow.get(col);
        if (this.player1turn && new Utils().inRange(row, col, pos)
            && !selected.set) {
          selected.setColor(Color.ORANGE);
          if (new Utils().winPath(selected, new ArrayList<Cell>())) {
            this.winCond = 1;
          }
          this.player1turn = false;
        }
        else if (!(this.player1turn) && new Utils().inRange(row, col, pos)
            && !selected.set) {
          selected.setColor(Color.BLUE);
          if (new Utils().winPath(selected, new ArrayList<Cell>())) {
            this.winCond = 2;
          }
          this.player1turn = true;
        }
      }
    }
  }

  // displays last scene
  public WorldScene lastScene(String s) {
    WorldScene result = new WorldScene(cellSize * this.size, cellSize * this.size);
    result.placeImageXY(new OverlayImage(new TextImage(s, Color.BLACK),
        new RectangleImage(400, 80, OutlineMode.SOLID, Color.GREEN)), 
        (cellSize * this.size) / 2, (cellSize * this.size) / 2);
    return result;
  }
}

// to run BridgeItGame
class RunBridgeIt {
  static int cellSize = 60;
  //runs Bridgit 
  void testBigBang(Tester t) {
    int sizeBoard = 11;
    Bridgit world = new Bridgit(sizeBoard);
    int worldWidth = (cellSize * sizeBoard);
    int worldHeight = (cellSize * sizeBoard);
    double tickRate = 0.5;
    world.bigBang(worldWidth, worldHeight, tickRate);
  }
}