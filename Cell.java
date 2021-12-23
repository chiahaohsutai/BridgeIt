import java.awt.Color;
import tester.*;
import javalib.worldimages.*;

// to represent a cell for the game
class Cell {
  static int cellSize = 60;
  Color c;
  int size;
  Cell above;
  Cell below;
  Cell right;
  Cell left;
  boolean set;
  boolean border;

  // constructor for white cells
  Cell() {
    this.c = Color.WHITE;
    this.size = cellSize;
    this.above = null;
    this.below = null;
    this.right = null;
    this.left = null;
    this.set = false;
    this.border = false;
  }

  // constructor for colored cells
  Cell(Color c) {
    this.c = c;
    this.size = cellSize;
    this.above = null;
    this.below = null;
    this.right = null;
    this.left = null;
    this.set = true;
    this.border = false;
  }

  /* TEMPLATE:
   * fields:
   * ... this.c ... - Color
   * ... this.size ... - int
   * ... this.above ... - Cell
   * ... this.below ... - Cell
   * ... this.right ... - Cell
   * ... this.left ... - Cell
   * ... this.set ... - boolean
   * methods:
   * ... this.drawCell() ... - WorldImage
   * ... this.linkVertical(Cell) ... - void
   * ... this.linkHorizontal(Cell) ... - void
   * ... this.setColor(Color) ...    - void
   * ... this.checkColor(Cell) ...   - boolean
   */

  // draws the given Cell in the scene
  WorldImage drawCell() {
    return new RectangleImage(this.size, this.size, OutlineMode.SOLID, 
        this.c);
  }

  // links cells together in vertical direction
  void linkVertical(Cell c) {
    /* methods:
     * ... this.drawCell() ... - WorldImage
     * ... this.linkVertical(Cell) ... - void
     * ... this.linkHorizontal(Cell) ... - void
     */
    this.below = c;
    c.above = this;
  }

  //links cells together in horizontal direction
  void linkHorizontal(Cell c) {
    /* methods:
     * ... this.drawCell() ... - WorldImage
     * ... this.linkVertical(Cell) ... - void
     * ... this.linkHorizontal(Cell) ... - void
     */
    this.right = c;
    c.left = this;
  }

  // EFFECT: sets the cells color to the given color
  void setColor(Color c) {
    this.c = c;
    this.set = true;
  }

  // compares two cells to see if they are the same color
  boolean checkColor(Cell selected) {
    return selected.c.equals(this.c);
  }

}

// Tests for the cell class
class CellTests {
  static int cellSize = 60;

  // examples of cells
  Cell cBlue = new Cell(Color.BLUE);
  Cell cOrange = new Cell(Color.ORANGE);
  Cell cWhite = new Cell();

  // examples of rectangles
  WorldImage rBlue = new RectangleImage(cellSize, cellSize, 
      OutlineMode.SOLID, Color.BLUE);
  WorldImage rOrange = new RectangleImage(cellSize, cellSize, 
      OutlineMode.SOLID, Color.ORANGE);
  WorldImage rWhite = new RectangleImage(cellSize, cellSize, 
      OutlineMode.SOLID, Color.WHITE);

  // initializes the values
  void init() {
    this.cBlue = new Cell(Color.BLUE);
    this.cOrange = new Cell(Color.ORANGE);
    this.cWhite = new Cell();
  }

  // test for drawCell
  void testDrawCell(Tester t) {
    this.init();
    t.checkExpect(this.cBlue.drawCell(), this.rBlue);
    t.checkExpect(this.cOrange.drawCell(), this.rOrange);
    t.checkExpect(this.cWhite.drawCell(), this.rWhite);
  }

  // test for linkHorizontal
  void testLinkHorizontal(Tester t) {
    this.init();
    this.cBlue.linkHorizontal(cOrange);
    t.checkExpect(this.cBlue.right, this.cOrange);
    t.checkExpect(this.cOrange.left, this.cBlue);
  }

  // test for linkVertical
  void testLinkVertical(Tester t) {
    this.init();
    this.cWhite.linkVertical(cOrange);
    t.checkExpect(this.cWhite.below, this.cOrange);
    t.checkExpect(this.cOrange.above, this.cWhite);
  }

  // test for setColor
  void testSetColor(Tester t) {
    this.init();
    t.checkExpect(this.cWhite.c, Color.WHITE);
    this.cWhite.setColor(Color.black);
    t.checkExpect(this.cWhite.c, Color.black);
    this.cOrange.setColor(Color.red);
    t.checkExpect(this.cOrange.c, Color.red);
  }

  // test for checkColor
  void testCheckColor(Tester t) {
    this.init();
    t.checkExpect(this.cBlue.checkColor(this.cBlue), true);
    t.checkExpect(this.cBlue.checkColor(this.cOrange), false);
    t.checkExpect(this.cBlue.checkColor(this.cWhite), false);
  }
}