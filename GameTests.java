import java.awt.Color;
import javalib.impworld.WorldScene;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;
import tester.Tester;

// to represents the tests for the game
class GameTests {
  static int cellSize = 60;
  Utils u = new Utils();

  // examples of rectangles
  WorldImage rBlue = new RectangleImage(cellSize, cellSize, 
      OutlineMode.SOLID, Color.BLUE);
  WorldImage rOrange = new RectangleImage(cellSize, cellSize, 
      OutlineMode.SOLID, Color.ORANGE);
  WorldImage rWhite = new RectangleImage(cellSize, cellSize, 
      OutlineMode.SOLID, Color.WHITE);

  // example board 
  WorldScene world1;

  // example last scene
  WorldScene lastScene = new WorldScene(cellSize * 3, cellSize * 3);

  // adds the last scene images to the example last scene
  void makeLastScene() {
    this.lastScene.placeImageXY(
        new OverlayImage(new TextImage("PLAYER 1 WINS", Color.BLACK),
            new RectangleImage(400, 80, OutlineMode.SOLID, Color.GREEN)), 
        (cellSize * 3) / 2, (cellSize * 3) / 2);
  }

  // initialize value
  void init() {
    this.world1 = new WorldScene(cellSize * 3, cellSize * 3);
    this.world1.placeImageXY(this.rWhite, u.genPos(0), u.genPos(0));
    this.world1.placeImageXY(this.rOrange, u.genPos(1), u.genPos(0));
    this.world1.placeImageXY(this.rWhite, u.genPos(2), u.genPos(0));
    this.world1.placeImageXY(this.rBlue, u.genPos(0), u.genPos(1));
    this.world1.placeImageXY(this.rWhite, u.genPos(1), u.genPos(1));
    this.world1.placeImageXY(this.rBlue, u.genPos(2), u.genPos(1));
    this.world1.placeImageXY(this.rWhite, u.genPos(0), u.genPos(2));
    this.world1.placeImageXY(this.rOrange, u.genPos(1), u.genPos(2));
    this.world1.placeImageXY(this.rWhite, u.genPos(2), u.genPos(2));
  }

  // creates a worldscene where player 1 clicked the middle cell
  void clickMiddle() {
    this.world1 = new WorldScene(cellSize * 3, cellSize * 3);
    this.world1.placeImageXY(this.rWhite, u.genPos(0), u.genPos(0));
    this.world1.placeImageXY(this.rOrange, u.genPos(1), u.genPos(0));
    this.world1.placeImageXY(this.rWhite, u.genPos(2), u.genPos(0));
    this.world1.placeImageXY(this.rBlue, u.genPos(0), u.genPos(1));
    this.world1.placeImageXY(this.rOrange, u.genPos(1), u.genPos(1));
    this.world1.placeImageXY(this.rBlue, u.genPos(2), u.genPos(1));
    this.world1.placeImageXY(this.rWhite, u.genPos(0), u.genPos(2));
    this.world1.placeImageXY(this.rOrange, u.genPos(1), u.genPos(2));
    this.world1.placeImageXY(this.rWhite, u.genPos(2), u.genPos(2));
  }

  // example world
  Bridgit exworld = new Bridgit(3);

  // test for makeScene()
  void testMakeScene(Tester t) {
    this.init();
    t.checkExpect(new Bridgit(3).makeScene(), this.world1);
  }

  // test for lastScene()
  void testLastScene(Tester t) {
    this.makeLastScene();
    t.checkExpect(this.exworld.lastScene("PLAYER 1 WINS"), this.lastScene);
  }

  // Mouse Positions
  Posn cell1 = new Posn(10, 10);
  Posn cell4 = new Posn(75, 75);

  // test for onMouseClicked()
  void testOnMouseClicked(Tester t) {
    this.init();
    this.exworld.onMouseClicked(cell1);
    t.checkExpect(this.exworld.makeScene(), this.world1);
    this.exworld.onMouseClicked(cell4);
    this.clickMiddle();
    t.checkExpect(this.exworld.makeScene(), this.world1);
    t.checkExpect(this.exworld.winCond, 1);
  }

  // test for onKeyEvent()
  void testOnKeyEvent(Tester t) {
    this.init();
    this.exworld.onMouseClicked(cell4);
    this.exworld.onKeyEvent("r");
    t.checkExpect(this.exworld.makeScene(), this.world1);
  }
}