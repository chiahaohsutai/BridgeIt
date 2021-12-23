import java.awt.Color;
import java.util.ArrayList;
import javalib.worldimages.Posn;

// to represent a utility class
class Utils {
  static int cellSize = 60;

  /* TEMPLATE:
   * methods:
   * ... this.genPos(int) ... - int
   * ... this.checkSize(int) ... - int
   * ... this.makeBoard(int) ... - ArrayList<ArrayList<Cell>>
   * ... this.linkBoardHorizontal(ArrayList<ArrayList<Cell>>) ... - void
   * ... this.linkBoardVertical(ArrayList<ArrayList<Cell>>) ... - void
   * ... this.inRange(int, int, Posn) ...       - boolean
   * ... this.winPath(Cell, ArrayList<Cell>) ...     - boolean
   * ... this.winningPath(ArrayList<Cell>) ...       - boolean
   */

  // generates the appropriate x or y position based on array index
  int genPos(int index) {
    return (cellSize / 2) + (cellSize * index);
  }

  // checks if the size of the board is valid
  int checkSize(int size) {
    if (size >= 3 && (size % 2) != 0) {
      return size;
    }
    else {
      throw new IllegalArgumentException("Size is not valid");
    }
  }

  // makes a valid board based on the input size
  ArrayList<ArrayList<Cell>> makeBoard(int n) {

    ArrayList<ArrayList<Cell>> result = new ArrayList<ArrayList<Cell>>();

    // makes the board based on if the row is even or odd
    for (int row = 0 ; row < n ; row += 1) {
      ArrayList<Cell> temp = new ArrayList<Cell>();
      if (row % 2 == 0) {
        for (int col = 0 ; col < n ; col += 1) {
          if (col % 2 == 0) {
            temp.add(new Cell());
          }
          else {
            temp.add(new Cell(Color.ORANGE));
          }
        }
        result.add(temp);
      }
      else {
        for (int col = 0 ; col < n ; col += 1) {
          if (col % 2 == 0) {
            temp.add(new Cell(Color.BLUE));
          }
          else {
            temp.add(new Cell());
          }
        }
        result.add(temp);
      }
    }
    return result;
  }

  // links the cells together horizontally
  void linkBoardHorizontal(ArrayList<ArrayList<Cell>> board) {
    for (int row = 0 ; row < board.size(); row += 1) {
      ArrayList<Cell> currRow = board.get(row); 
      for (int col = 0 ; col < currRow.size() - 1 ; col += 1) {
        currRow.get(col).linkHorizontal(currRow.get(col + 1));
      }
    }
  }

  // links the cells together vertically
  void linkBoardVertical(ArrayList<ArrayList<Cell>> board) {
    for (int row = 0 ; row < board.size() - 1 ; row += 1) {
      ArrayList<Cell> currRow = board.get(row);
      ArrayList<Cell> nextRow = board.get(row + 1);
      for (int col = 0 ; col < currRow.size() ; col += 1) {
        currRow.get(col).linkVertical(nextRow.get(col));
      }
    }
  }

  // checks if the given posn is on the cell
  boolean inRange(int row, int col, Posn pos) {
    return pos.x >= col * cellSize && pos.x <= (col + 1) * cellSize
        && pos.y >= row * cellSize && pos.y <= (row + 1) * cellSize;
  }

  // sets the cells on the border of the board 
  void setBorders(ArrayList<ArrayList<Cell>> board) {
    for (int row = 0; row < board.size() ; row += 1) {
      ArrayList<Cell> currRow = board.get(row);
      for (Cell c : currRow) {
        if (c.above == null || c.below == null || c.left == null 
            || c.right == null) {
          c.set = true;
          c.border = true;
        }
      }
    }
  }

  // creates a path and checks if it is a winning path
  boolean winPath(Cell selected, ArrayList<Cell> path) {
    path.add(selected);
    if (!selected.border) {
      if (selected.checkColor(selected.left) 
          && path.indexOf(selected.left) == -1) {
        winPath(selected.left, path);
      }
      if (selected.checkColor(selected.right) 
          && path.indexOf(selected.right) == -1) {
        winPath(selected.right, path);
      }
      if (selected.checkColor(selected.above) 
          && path.indexOf(selected.above) == -1) {
        winPath(selected.above, path);
      }
      if (selected.checkColor(selected.below) 
          && path.indexOf(selected.below) == -1) {
        winPath(selected.below, path);
      }
    }
    return winningPath(path);
  }

  // helper for winPath, checks a given path to see if it wins
  boolean winningPath(ArrayList<Cell> path) {
    int leftborders = 0;
    int aboveborders = 0;
    int rightborders = 0;
    int belowborders = 0;
    for (Cell c : path) {
      if (c.border && c.below == null) {
        belowborders = 1;
      }
      if (c.border && c.above == null) {
        aboveborders = 1;
      }
      if (c.border && c.right == null) {
        rightborders = 1;
      }
      if (c.border && c.left == null) {
        leftborders = 1;
      }
    }
    return (aboveborders + belowborders == 2) || (leftborders + rightborders == 2);
  }
}
