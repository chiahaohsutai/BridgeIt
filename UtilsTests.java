import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javalib.worldimages.Posn;
import tester.Tester;

// tests for the utils class
class UtilsTests {

  // examples of cells
  Cell c1_1;
  Cell c1_2;
  Cell c1_3;
  Cell c2_1;
  Cell c2_2;
  Cell c2_3;
  Cell c3_1;
  Cell c3_2;
  Cell c3_3;

  // examples of array lists
  ArrayList<Cell> row1;
  ArrayList<Cell> row2;
  ArrayList<Cell> row3;

  // examples of 2d array list
  ArrayList<ArrayList<Cell>> board1;

  // examples of Positions
  Posn cell_r0_c0 = new Posn(20, 20);
  Posn cell_r1_c2 = new Posn(140, 80);

  // initialize values for examples
  void init() {
    this.c1_1 = new Cell();
    this.c1_2 = new Cell(Color.ORANGE);
    this.c1_3 = new Cell();
    this.c2_1 = new Cell(Color.BLUE);
    this.c2_2 = new Cell();
    this.c2_3 = new Cell(Color.BLUE);
    this.c3_1 = new Cell();
    this.c3_2 = new Cell(Color.ORANGE);
    this.c3_3 = new Cell();
    this.row1 = new ArrayList<Cell>(Arrays.asList(this.c1_1, 
        this.c1_2, this.c1_3));
    this.row2 = new ArrayList<Cell>(Arrays.asList(this.c2_1, 
        this.c2_2, this.c2_3));
    this.row3 = new ArrayList<Cell>(Arrays.asList(this.c3_1, 
        this.c3_2, this.c3_3));
    this.board1 = new ArrayList<ArrayList<Cell>>(Arrays.asList(this.row1,
        this.row2, this.row3));
  }

  // test for checkSize()
  void testCheckSize(Tester t) {
    t.checkExpect(new Utils().checkSize(5), 5);
    t.checkException(new IllegalArgumentException("Size is not valid"), 
        new Utils(), "checkSize", 0);
    t.checkException(new IllegalArgumentException("Size is not valid"), 
        new Utils(), "checkSize", 8);
  }

  // test for genPos()
  void testGenPos(Tester t) {
    // if the cell size is changed, this test needs to be modified
    t.checkExpect(new Utils().genPos(0), 30);
    t.checkExpect(new Utils().genPos(4), 270);
  }

  // test for makeBoard()
  void testMakeBoard(Tester t) {
    this.init();
    t.checkExpect(new Utils().makeBoard(3), this.board1);
  }

  // test for linkBoardHorizontal
  void testLinkBoardHorizontal(Tester t) {
    this.init();
    new Utils().linkBoardHorizontal(this.board1);
    t.checkExpect(this.c1_1.right, this.c1_2);
    t.checkExpect(this.c1_2.left, this.c1_1);
    t.checkExpect(this.c1_2.right, this.c1_3);
    t.checkExpect(this.c1_3.left, this.c1_2);
    t.checkExpect(this.c2_1.right, this.c2_2);
    t.checkExpect(this.c2_2.left, this.c2_1);
    t.checkExpect(this.c2_2.right, this.c2_3);
    t.checkExpect(this.c2_3.left, this.c2_2);
    t.checkExpect(this.c3_1.right, this.c3_2);
    t.checkExpect(this.c3_2.left, this.c3_1);
    t.checkExpect(this.c3_2.right, this.c3_3);
    t.checkExpect(this.c3_3.left, this.c3_2);
  }

  // test for linkBoardVertical
  void testLinkBoardVertical(Tester t) {
    this.init();
    new Utils().linkBoardVertical(board1);
    t.checkExpect(this.c1_1.below, this.c2_1);
    t.checkExpect(this.c2_1.above, this.c1_1);
    t.checkExpect(this.c2_1.below, this.c3_1);
    t.checkExpect(this.c3_1.above, this.c2_1);
    t.checkExpect(this.c1_2.below, this.c2_2);
    t.checkExpect(this.c2_2.above, this.c1_2);
    t.checkExpect(this.c2_2.below, this.c3_2);
    t.checkExpect(this.c3_2.above, this.c2_2);
    t.checkExpect(this.c1_3.below, this.c2_3);
    t.checkExpect(this.c2_3.above, this.c1_3);
    t.checkExpect(this.c2_3.below, this.c3_3);
    t.checkExpect(this.c3_3.above, this.c2_3);
  }

  //test for inRange
  void testInRange(Tester t) {
    t.checkExpect(new Utils().inRange(0, 0, this.cell_r0_c0), true);
    t.checkExpect(new Utils().inRange(1, 2, this.cell_r1_c2), true);
    t.checkExpect(new Utils().inRange(1, 6, this.cell_r0_c0), false);
  }
  
  // test for setBorders
  void testSetBorders(Tester t) {
    this.init();
    new Utils().linkBoardHorizontal(this.board1);
    new Utils().linkBoardVertical(this.board1);
    new Utils().setBorders(this.board1);
    t.checkExpect(this.c1_1.border, true);
    t.checkExpect(this.c1_2.border, true);
    t.checkExpect(this.c1_3.border, true);
    t.checkExpect(this.c2_1.border, true);
    t.checkExpect(this.c2_2.border, false);
    t.checkExpect(this.c2_3.border, true);
    t.checkExpect(this.c3_1.border, true);
    t.checkExpect(this.c3_2.border, true);
    t.checkExpect(this.c3_3.border, true); 
    t.checkExpect(this.c1_1.set, true);
    t.checkExpect(this.c1_2.set, true);
    t.checkExpect(this.c1_3.set, true);
    t.checkExpect(this.c2_1.set, true);
    t.checkExpect(this.c2_2.set, false);
    t.checkExpect(this.c2_3.set, true);
    t.checkExpect(this.c3_1.set, true);
    t.checkExpect(this.c3_2.set, true);
    t.checkExpect(this.c3_3.set, true);
  }
  
  // test for winPath
  void testWinPath(Tester t) {
    this.init();
    new Utils().linkBoardHorizontal(this.board1);
    new Utils().linkBoardVertical(this.board1);
    new Utils().setBorders(this.board1);
    t.checkExpect(new Utils().winPath(this.c2_2, new ArrayList<Cell>()), false);
    this.c2_2.setColor(Color.BLUE);
    t.checkExpect(new Utils().winPath(this.c2_2, new ArrayList<Cell>()), true);
    this.c2_2.setColor(Color.ORANGE);
    t.checkExpect(new Utils().winPath(this.c2_2, new ArrayList<Cell>()), true);
  }
  
  // test for winningPath
  void testWinningPath(Tester t) {
    this.init();
    new Utils().linkBoardHorizontal(this.board1);
    new Utils().linkBoardVertical(this.board1);
    new Utils().setBorders(this.board1);
    ArrayList<Cell> testPath = new ArrayList<Cell>();
    testPath.add(this.c2_2);
    t.checkExpect(new Utils().winningPath(testPath), false);
    testPath.add(this.c1_2);
    testPath.add(this.c3_2);
    t.checkExpect(new Utils().winningPath(testPath), true);
  }
}