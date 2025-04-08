import tester.*; // The tester library
import javalib.worldimages.*; // images, like RectangleImage or OverlayImages
import javalib.impworld.*; // the abstract World class and the big-bang library
import java.awt.Color; // general colors (as triples of red,green,blue values)
// and predefined colors (Red, Green, Yellow, Blue, Black, White)
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

//represents constants used in the game
interface Constants {

  int fontSize = 20;
  int width = 600;
  int height = 600;
  int boxWidth = 120;
  int boxHeight = 80;
  int gap = 10;
  Color green = new Color(51, 204, 51);
  Color blue = new Color(0, 153, 255);
  Color purple = new Color(153, 102, 255);
  Color yellow = new Color(255, 255, 102);

}

// represents the words
class Word implements Constants {
  String data;
  Category category;
  Position pos;
  int fontSize = 13;

  // initializes the words with its category
  Word(String data, Category category) {
    this.data = data;
    this.category = category;
    this.pos = new Position(0, 0);
  }

  // tester constructor
  Word(String data, Category category, Position pos) {
    this.data = data;
    this.category = category;
    this.pos = pos;
  }

  // gives position to this word
  public void givePositions(Position pos) {
    this.pos = pos;
  }

  // draws the this data into boxes
  public void drawBoxes(WorldScene ws) {
    WorldImage rect = new RectangleImage(boxWidth, boxHeight, OutlineMode.SOLID, Color.LIGHT_GRAY);
    WorldImage text = new TextImage(this.data, this.fontSize, FontStyle.BOLD, Color.BLACK);
    // we get another getter lol
    ws.placeImageXY(rect, pos.x, pos.y);
    ws.placeImageXY(text, pos.x, pos.y);

  }

  // draws completed boxes with category
  public WorldImage drawCompleted(WorldScene ws) {
    WorldImage rect = new RectangleImage(4 * (boxWidth + gap) - gap, boxHeight, OutlineMode.SOLID,
        category.color);
    WorldImage categoryName = new TextImage(this.category.category, 20, FontStyle.BOLD,
        Color.BLACK);
    // we get another getter lol
    WorldImage completedBox = new OverlayImage(categoryName, rect);
    return completedBox;
  }

  // compares the words position with the given position
  public boolean comparePos(Posn pos) {
    return this.pos.comparePos(pos);
  }

  // determines if words are same category
  public boolean sameCategory(Category cat) {
    return this.category.sameCat(cat);
  }

  // draws selected words
  public void drawSelected(WorldScene ws) {
    WorldImage rect = new RectangleImage(boxWidth, boxHeight, OutlineMode.SOLID, Color.DARK_GRAY);
    WorldImage text = new TextImage(this.data, this.fontSize, FontStyle.BOLD, Color.WHITE);
    // we get another getter lol
    ws.placeImageXY(rect, pos.x, pos.y);
    ws.placeImageXY(text, pos.x, pos.y);
  }

}

// the category and color of the words
class Category implements Constants {
  String category;
  Color color;

  Category(String category, Color color) {
    this.category = category;
    this.color = color;
  }

  // determines if same category
  public boolean sameCat(Category cat) {
    return this.category.equals(cat.category) && this.color.equals(cat.color);
  }

}

//represents a position
class Position implements Constants {
  int x;
  int y;

  Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // compares positions of boxes
  public boolean comparePos(Posn pos) {
    return (pos.x >= this.x - boxWidth / 2) && (pos.x <= this.x + boxWidth / 2)
        && (pos.y >= this.y - boxHeight / 2) && (pos.y <= this.y + boxHeight / 2);

  }
}

//represents all world sets in this connections world
class TotalWordSets implements Constants {
  Random rand = new Random();
  ArrayList<ArrayList<Word>> set;
  ArrayList<ArrayList<ArrayList<Word>>> allSets;

  // default constructor with all word sets for connections game
  TotalWordSets() {
    Category cat11 = new Category("WET WEATHER", yellow);
    Category cat12 = new Category("PALINDROMES", purple);
    Category cat13 = new Category("KEYBOARD KEYS", blue);
    Category cat14 = new Category("NBA TEAMS", green);
    ArrayList<Word> row11 = new ArrayList<Word>(Arrays.asList(new Word("HAIL", cat11),
        new Word("RAIN", cat11), new Word("SLEET", cat11), new Word("SNOW", cat11)));
    ArrayList<Word> row12 = new ArrayList<Word>(Arrays.asList(new Word("KAYAK", cat12),
        new Word("LEVEL", cat12), new Word("MOM", cat12), new Word("RACECAR", cat12)));
    ArrayList<Word> row13 = new ArrayList<Word>(Arrays.asList(new Word("OPTION", cat13),
        new Word("RETURN", cat13), new Word("SHIFT", cat13), new Word("TAB", cat13)));
    ArrayList<Word> row14 = new ArrayList<Word>(Arrays.asList(new Word("BUCKS", cat14),
        new Word("HEAT", cat14), new Word("JAZZ", cat14), new Word("NETS", cat14)));
    ArrayList<ArrayList<Word>> set1 = new ArrayList<ArrayList<Word>>(
        Arrays.asList(row11, row12, row13, row14));

    // set 2
    Category cat21 = new Category("OUTSPOKE", yellow);
    Category cat22 = new Category("BODIES OF WATER", green);
    Category cat23 = new Category("KINDS OF CORDS", blue);
    Category cat24 = new Category("THINGS IN BOTTLES", purple);
    ArrayList<Word> row21 = new ArrayList<Word>(Arrays.asList(new Word("DIRECT", cat21),
        new Word("LOUD", cat21), new Word("FRANK", cat21), new Word("VOCAL", cat21)));
    ArrayList<Word> row22 = new ArrayList<Word>(Arrays.asList(new Word("BAY", cat22),
        new Word("CHANNEL", cat22), new Word("SOUND", cat22), new Word("STRAIT", cat22)));
    ArrayList<Word> row23 = new ArrayList<Word>(Arrays.asList(new Word("BUNGEE", cat23),
        new Word("EXTENSION", cat23), new Word("SPINAL", cat23), new Word("UMBILICAL", cat23)));
    ArrayList<Word> row24 = new ArrayList<Word>(Arrays.asList(new Word("GENIE", cat24),
        new Word("LIGHTNING", cat24), new Word("MESSAGE", cat24), new Word("SHIP", cat24)));
    ArrayList<ArrayList<Word>> set2 = new ArrayList<ArrayList<Word>>(
        Arrays.asList(row21, row22, row23, row24));

    // set 3
    Category cat31 = new Category("TRUST AS REAL", Color.YELLOW);
    Category cat32 = new Category("POWER ISSUES", Color.GREEN);
    Category cat33 = new Category("SUMMARY", Color.BLUE);
    Category cat34 = new Category("NAME HOMOPHONES", purple);
    ArrayList<Word> row31 = new ArrayList<Word>(Arrays.asList(new Word("ACCEPT", cat31),
        new Word("BELIEVE", cat31), new Word("BUY", cat31), new Word("SWALLOW", cat31)));
    ArrayList<Word> row32 = new ArrayList<Word>(Arrays.asList(new Word("OUTAGE", cat32),
        new Word("SHORT", cat32), new Word("SPIKE", cat32), new Word("SURGE", cat32)));
    ArrayList<Word> row33 = new ArrayList<Word>(Arrays.asList(new Word("ABSTRACT", cat33),
        new Word("BRIEF", cat33), new Word("DIGEST", cat33), new Word("OUTLINE", cat33)));
    ArrayList<Word> row34 = new ArrayList<Word>(Arrays.asList(new Word("CURT", cat34),
        new Word("HAIRY", cat34), new Word("KNEEL", cat34), new Word("WANE", cat34)));
    ArrayList<ArrayList<Word>> set3 = new ArrayList<ArrayList<Word>>(
        Arrays.asList(row31, row32, row33, row34));
    // set 4

    Category cat41 = new Category("CAPTIVATE", yellow);
    Category cat42 = new Category("THINGS WITH WINGS", green);
    Category cat43 = new Category("WORDS THAT MODIFY 'WATCH' ", blue);
    Category cat44 = new Category("THINGS REPEATED IN MISS MARY MACK", purple);
    ArrayList<Word> row41 = new ArrayList<Word>(Arrays.asList(new Word("ABSORb", cat41),
        new Word("ENTRANCE", cat41), new Word("GRAB", cat41), new Word("RIVET", cat41)));
    ArrayList<Word> row42 = new ArrayList<Word>(Arrays.asList(new Word("AIRPLANE", cat42),
        new Word("FAIRY", cat42), new Word("FLY", cat22), new Word("HOSPITAL", cat42)));
    ArrayList<Word> row43 = new ArrayList<Word>(Arrays.asList(new Word("POCKET", cat43),
        new Word("WRIST", cat43), new Word("STOP", cat43), new Word("SMART", cat43)));
    ArrayList<Word> row44 = new ArrayList<Word>(Arrays.asList(new Word("BACK", cat44),
        new Word("BLACK", cat44), new Word("BUTTONS", cat44), new Word("MACK", cat44)));
    ArrayList<ArrayList<Word>> set4 = new ArrayList<ArrayList<Word>>(
        Arrays.asList(row41, row42, row43, row44));

    // set5
    Category cat51 = new Category("MOVE TO ACTION", Color.YELLOW);
    Category cat52 = new Category("UNO CARDS", Color.GREEN);
    Category cat53 = new Category("HAIL SIZE COMPARISONS", Color.BLUE);
    Category cat54 = new Category("PLACES WITH BENCHES", purple);
    ArrayList<Word> row51 = new ArrayList<Word>(Arrays.asList(new Word("DRIVE", cat51),
        new Word("PROMPT", cat51), new Word("PROPEL", cat51), new Word("PUSH", cat51)));
    ArrayList<Word> row52 = new ArrayList<Word>(Arrays.asList(new Word("DRAW", cat52),
        new Word("REVERSE", cat52), new Word("SKIP", cat52), new Word("WILD", cat52)));
    ArrayList<Word> row53 = new ArrayList<Word>(Arrays.asList(new Word("BASEBALL", cat53),
        new Word("GRAPEFRUIT", cat53), new Word("MARBLE", cat53), new Word("PEA", cat53)));
    ArrayList<Word> row54 = new ArrayList<Word>(Arrays.asList(new Word("COURTROOM", cat54),
        new Word("DUGOUT", cat54), new Word("GYM", cat54), new Word("PARK", cat54)));
    ArrayList<ArrayList<Word>> set5 = new ArrayList<ArrayList<Word>>(
        Arrays.asList(row51, row52, row53, row54));

    // all sets
    allSets = new ArrayList<ArrayList<ArrayList<Word>>>(
        Arrays.asList(set1, set2, set3, set4, set5));
  }

  // tester constructor with any word set
  TotalWordSets(ArrayList<ArrayList<Word>> set) {
    this.set = set;
    allSets = new ArrayList<ArrayList<ArrayList<Word>>>(Arrays.asList(set));
  }

  // chooses a random word set out of the 5
  ArrayList<ArrayList<Word>> chooseWordSet() {
    int index = rand.nextInt(5);
    return allSets.get(index);
  }

  // returns a specific word set for testing
  ArrayList<ArrayList<Word>> chooseWordSet(int index) {
    return allSets.get(index);
  }
}

//represents the connections world
class ConnectionsWorld extends World implements Constants {

  int test = 0;
  ArrayList<ArrayList<Word>> words; // words not completed yet
  ArrayList<ArrayList<Word>> completedWords;
  ArrayList<Word> selectedWords;
  TotalWordSets allWords = new TotalWordSets();
  int lives;
  Random rand = new Random(6);

  ConnectionsWorld() {
    // returns a random set of 16 words out of the 5 created word sets
    this.words = allWords.chooseWordSet();
    this.lives = 4;
    this.selectedWords = new ArrayList<Word>();
    this.completedWords = new ArrayList<ArrayList<Word>>();
    this.givePositions();

  }

  ConnectionsWorld(int setIndex, Random rand, ArrayList<ArrayList<Word>> words) {
    // chooses the given set of words for testing
    this.words = allWords.chooseWordSet(setIndex);
    this.lives = 4;
    this.selectedWords = new ArrayList<Word>();
    this.completedWords = new ArrayList<ArrayList<Word>>();
    this.givePositions();
    this.rand = rand;

  }

  // tester for draw, can choose specific sets to use
  ConnectionsWorld(int test, Random rand, TotalWordSets allWords) {
    this.test = test;
    this.rand = rand;
    this.lives = 4;
    this.allWords = allWords;
    this.selectedWords = new ArrayList<Word>();
    this.completedWords = new ArrayList<ArrayList<Word>>();
    this.words = allWords.chooseWordSet(test);
  }

  ConnectionsWorld(ArrayList<ArrayList<Word>> words, int lives, ArrayList<Word> selectedWords,
      ArrayList<ArrayList<Word>> completedWords) {
    // chooses the given set of words for testing
    this.words = words;
    this.lives = lives;
    this.selectedWords = selectedWords;
    this.completedWords = completedWords;
    this.givePositions();

  }

  void givePositions() {
    // generates all possible positions in an ArrayList
    ArrayList<Position> allPos = new ArrayList<Position>();
    int x = -30;
    for (int i = 0; i < 4; i += 1) {
      int y = 400;
      x += (boxWidth + gap);
      for (int j = 0; j < this.words.size(); j += 1) {
        allPos.add(new Position(x, y));
        y -= (boxHeight + gap);
      }
    }

    // randomly assigns a unique position to each word in the arraylist
    for (int i = 0; i < this.words.size(); i += 1) {
      // ArrayList<Integer> positionsX = new ArrayList<Integer>(Arrays.asList(150,
      // 250, 350, 450));
      // ArrayList<Integer> positionsY = new ArrayList<Integer>(Arrays.asList(150,
      // 250, 350, 450));
      for (int j = 0; j < this.words.get(i).size(); j += 1) {
        Position pos = this.getPos(allPos);
        this.words.get(i).get(j).givePositions(pos);
      }
    }
  }

  // gets a random position
  Position getPos(ArrayList<Position> al) {
    int index = rand.nextInt(al.size());
    Position pos = al.get(index);
    al.remove(index);
    return pos;
  }

  // draws the current state of the world
  public WorldScene makeScene() {
    WorldScene ws = new WorldScene(width, height);
    this.draw(ws);
    return ws;
  }

  // draws world
  public void draw(WorldScene ws) {
    this.drawBoxes(ws);
    this.drawLives(ws);
    this.drawSubmit(ws);
    this.drawSelected(ws);
    this.drawDeselect(ws);
    this.drawCompleted(ws);
  }

  // draws connection boxes with words in side
  public void drawBoxes(WorldScene ws) {
    for (int i = 0; i < this.words.size(); i += 1) {
      for (int j = 0; j < this.words.get(i).size(); j += 1) {
        this.words.get(i).get(j).drawBoxes(ws);
      }
    }
  }

  // indicates what words are selected
  public void drawSelected(WorldScene ws) {
    for (Word selectedWord : this.selectedWords) {
      selectedWord.drawSelected(ws);
    }
  }

  // draws the lives that the player has left
  public void drawLives(WorldScene ws) {
    WorldImage text = new TextImage("Lives: " + this.lives, fontSize, FontStyle.REGULAR,
        Color.BLACK);
    ws.placeImageXY(text, width / 3, height - 100);
  }

  // draws a submit button
  public void drawSubmit(WorldScene ws) {
    WorldImage text = new TextImage("Submit", fontSize, FontStyle.REGULAR, Color.BLACK);
    WorldImage rect = new RectangleImage(80, 40, OutlineMode.SOLID, Color.LIGHT_GRAY);
    ws.placeImageXY(rect, width * 1 / 2, height - 100);
    ws.placeImageXY(text, width * 1 / 2, height - 100);
  }

  // draws de-select button
  public void drawDeselect(WorldScene ws) {
    WorldImage text = new TextImage("Deselect All", fontSize, FontStyle.REGULAR, Color.BLACK);
    WorldImage rect = new RectangleImage(150, 40, OutlineMode.OUTLINE, Color.BLACK);
    ws.placeImageXY(rect, width * 13 / 18, height - 100);
    ws.placeImageXY(text, width * 13 / 18, height - 100);
  }

  // draws completed words with category
  public void drawCompleted(WorldScene ws) {
    if (!this.completedWords.isEmpty()) {
      for (int i = 0; i < this.completedWords.size(); i += 1) {
        if (this.completedWords.get(i).size() == 4) {
          WorldImage completedBox = this.completedWords.get(i).get(0).drawCompleted(ws);
          ws.placeImageXY(completedBox, width / 2, 220 + (boxHeight + gap) * (i - 1));
        }
      }
    }
  }

  public void onTick() {
    test += 0;
  }

  // adds the word that is clicked to selected words
  public void onMouseClicked(Posn pos) {
    // selecting the words
    for (int i = 0; i < this.words.size(); i += 1) {
      for (int j = 0; j < this.words.get(i).size(); j += 1) {
        Word currentWord = this.words.get(i).get(j);

        if (currentWord.comparePos(pos)) {
          if (this.selectedWords.contains(currentWord)) {
            this.selectedWords.remove(currentWord);
          }
          else {
            if (selectedWords.size() < 4) {
              this.selectedWords.add(currentWord);
            }
          }
        }
      }
    }

    // the submit button
    if (pos.x >= width * 1 / 2 - 40 && pos.x <= width * 1 / 2 + 40 && pos.y >= height - 100 - 40
        && pos.y <= height - 100 + 40) {
      this.checkSubmission();
    }

    // the de-selecting button
    if (pos.x >= width * 13 / 18 - 75 && pos.x <= width * 13 / 18 + 75 && pos.y >= height - 100 - 40
        && pos.y <= height - 100 + 40) {
      this.deselect();
    }

    // the shuffle button

    // this.givePositions();
  }

  // checks if the selected words are in the same category
  public void checkSubmission() {
    if (this.selectedWords.size() > 0 && this.selectedWords.size() == 4) {
      if (!this.sameCategory(this.selectedWords.get(0).category)) {
        this.lives -= 1;
      }
      else if (this.sameCategory(this.selectedWords.get(0).category)
          && this.selectedWords.size() == 4) {
        this.completedWords.add(this.selectedWords);
        for (int j = 0; j < words.size(); j += 1) {
          for (int i = 0; i < selectedWords.size(); i += 1) {
            this.words.get(j).remove(selectedWords.get(i));
          }
        }
        this.removeEmpty();
        this.givePositions();
      }
    }
    this.selectedWords = new ArrayList<Word>();

  }

  // removes any empty lists from the arraylist words
  public void removeEmpty() {
    for (int i = 0; i < this.words.size(); i += 1) {
      if (this.words.get(i).isEmpty()) {
        this.words.remove(this.words.get(i));
      }
    }
  }

  // un-selects selected words
  public void deselect() {
    this.selectedWords = new ArrayList<Word>();
  }

  // world ends
  public WorldEnd worldEnds() {
    if (this.lives <= 0) {
      return new WorldEnd(true, this.losingScene());
    }
    if (completedWords.size() >= 4 && this.lives > 0) {
      return new WorldEnd(true, this.winningScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  // returns a losing scene if you lose
  public WorldScene losingScene() {
    WorldScene ws = new WorldScene(width, height);
    WorldImage text = new TextImage("Sorry, you failed :(", 40, Color.BLUE);
    ws.placeImageXY(text, width / 2, height / 2);
    return ws;
  }

  // returns a winning scene if you win
  public WorldScene winningScene() {
    WorldScene ws = new WorldScene(width, height);
    WorldImage text = new TextImage("You Win! Congratulations!", 40, Color.BLACK);
    ws.placeImageXY(text, width / 2, height / 2);
    return ws;

  }

  // determines if selected words are in the same category
  public boolean sameCategory(Category cat) {
    boolean sameCat = true;
    for (Word word : this.selectedWords) {
      sameCat = sameCat && word.sameCategory(cat);
    }
    return sameCat;
  }

}

class ExamplesConnections {

  // constants
  int fontSize = 20;
  int width = 600;
  int height = 600;
  int boxWidth = 120;
  int boxHeight = 80;
  int gap = 10;
  Color green = new Color(51, 204, 51);
  Color blue = new Color(0, 153, 255);
  Color purple = new Color(153, 102, 255);
  Color yellow = new Color(255, 255, 102);

  // tests world
  void testBigBang(Tester t) {
    // this.initData();
    ConnectionsWorld w = new ConnectionsWorld();
    int worldWidth = 600;
    int worldHeight = 600;
    double tickRate = 0.5;
    w.bigBang(worldWidth, worldHeight, tickRate);
  }

  Random rand = new Random(6);

  // small World Example
  Category cat;
  Word hi;
  Position pos;
  ArrayList<Word> rowSmall;
  ArrayList<ArrayList<Word>> setSmall;
  TotalWordSets totalWordSmall;
  ConnectionsWorld smallWorld;
  ArrayList<Word> rowEmpty;
  ArrayList<ArrayList<Word>> setSmallEmpty;
  ConnectionsWorld smallWorldEmpty;
  TotalWordSets totalEmptySmall;

  // empty worlds for testing
  WorldScene empty1;
  WorldScene empty2;

  // actual world example
  ConnectionsWorld world;
  WorldScene scene;
  Category cat11;
  Category cat12;
  Category cat13;
  Category cat14;
  ArrayList<Word> row11;
  ArrayList<Word> row12;
  ArrayList<Word> row13;
  ArrayList<Word> row14;

  // sets for rand
  ArrayList<Word> row1rand;
  ArrayList<Word> row2rand;
  ArrayList<Word> row3rand;
  ArrayList<Word> row4rand;
  ArrayList<ArrayList<Word>> set1rand;

  ArrayList<ArrayList<Word>> set1;
  ArrayList<ArrayList<Word>> set2;
  ArrayList<ArrayList<Word>> set3;
  ArrayList<ArrayList<Word>> set4;
  ArrayList<ArrayList<Word>> set5;
  ArrayList<ArrayList<ArrayList<Word>>> allSets;
  ArrayList<ArrayList<Word>> completed;
  ConnectionsWorld comWorld;

  void initData() {
    // examples
    Random rand = new Random(6);
    // empty worlds for testing
    empty1 = new WorldScene(600, 600);
    empty2 = new WorldScene(600, 600);

    // small world example
    cat = new Category("this", blue);
    hi = new Word("hi", cat);
    pos = new Position(350, 400);
    rowSmall = new ArrayList<Word>(Arrays.asList(hi));
    setSmall = new ArrayList<ArrayList<Word>>(Arrays.asList(rowSmall));
    totalWordSmall = new TotalWordSets(setSmall);
    smallWorld = new ConnectionsWorld(0, rand, totalWordSmall);
    // with empty
    rowEmpty = new ArrayList<Word>();
    setSmallEmpty = new ArrayList<ArrayList<Word>>(Arrays.asList(rowSmall, rowEmpty));
    totalEmptySmall = new TotalWordSets(setSmallEmpty);
    smallWorldEmpty = new ConnectionsWorld(0, rand, totalEmptySmall);

    // actual example
    world = new ConnectionsWorld(0, rand, this.set1);
    scene = world.makeScene();
    cat11 = new Category("WET WEATHER", yellow);
    cat12 = new Category("PALINDROMES", purple);
    cat13 = new Category("KEYBOARD KEYS", blue);
    cat14 = new Category("NBA TEAMS", green);
    row11 = new ArrayList<Word>(Arrays.asList(new Word("HAIL", cat11), new Word("RAIN", cat11),
        new Word("SLEET", cat11), new Word("SNOW", cat11)));
    row12 = new ArrayList<Word>(Arrays.asList(new Word("KAYAK", cat12), new Word("LEVEL", cat12),
        new Word("MOM", cat12), new Word("RACECAR", cat12)));
    row13 = new ArrayList<Word>(Arrays.asList(new Word("OPTION", cat13), new Word("RETURN", cat13),
        new Word("SHIFT", cat13), new Word("TAB", cat13)));
    row14 = new ArrayList<Word>(Arrays.asList(new Word("BUCKS", cat14), new Word("HEAT", cat14),
        new Word("JAZZ", cat14), new Word("NETS", cat14)));
    set1 = new ArrayList<ArrayList<Word>>(Arrays.asList(row11, row12, row13, row14));
    set2 = new ArrayList<ArrayList<Word>>(Arrays.asList(row11, row12, row13, row14));
    allSets = new ArrayList<ArrayList<ArrayList<Word>>>(Arrays.asList(set1, set2));

    // example with completed words
    completed = new ArrayList<ArrayList<Word>>(Arrays.asList(this.row14));
    comWorld = new ConnectionsWorld(this.set1, 3, this.row12, this.completed);

    // sets for rand
    row1rand = new ArrayList<Word>(Arrays.asList(new Word("HAIL", cat11, new Position(350, 400)),
        new Word("RAIN", cat11, new Position(250, 300)),
        new Word("SLEET", cat11, new Position(250, 400)),
        new Word("SNOW", cat11, new Position(450, 100))));
    row2rand = new ArrayList<Word>(Arrays.asList(new Word("KAYAK", cat12, new Position(150, 200)),
        new Word("LEVEL", cat12, new Position(250, 200)),
        new Word("MOM", cat12, new Position(450, 200)),
        new Word("RACECAR", cat12, new Position(450, 300))));
    row3rand = new ArrayList<Word>(Arrays.asList(new Word("OPTION", cat13, new Position(150, 400)),
        new Word("RETURN", cat13, new Position(250, 100)),
        new Word("SHIFT", cat13, new Position(350, 300)),
        new Word("TAB", cat13, new Position(350, 200))));
    row4rand = new ArrayList<Word>(Arrays.asList(new Word("BUCKS", cat14, new Position(350, 100)),
        new Word("HEAT", cat14, new Position(150, 100)),
        new Word("JAZZ", cat14, new Position(450, 400)),
        new Word("NETS", cat14, new Position(150, 300))));
    set1rand = new ArrayList<ArrayList<Word>>(
        Arrays.asList(row1rand, row2rand, row3rand, row4rand));
  }

  // tests for give positions
  void testgivePositions(Tester t) {
    this.initData();
    hi.givePositions(pos);
    t.checkExpect(hi.pos, pos);

    // connections worlds class test GO TO OH
    world.givePositions();
    Random rand = new Random(6);
    t.checkExpect(world, new ConnectionsWorld(0, rand, set1rand));

  }

  // tests for get positions
  void testgetPos(Tester t) {
    this.initData();
    ArrayList<Position> testPos = new ArrayList<Position>();
    testPos.add(new Position(100, 100));
    world.getPos(testPos);
    ConnectionsWorld testpos = new ConnectionsWorld(0, rand, set1rand);
    t.checkExpect(world, testpos);

  }

  // tests for choose word set
  void testchooseWordSet(Tester t) {
    this.initData();
    TotalWordSets totalWords = new TotalWordSets(set2);
    t.checkExpect(totalWords.chooseWordSet(0), set2);
  }

  // tests for makeScene
  boolean testmakeScene(Tester t) {
    this.initData();
    empty1.placeImageXY(new RectangleImage(120, 80, OutlineMode.SOLID, Color.LIGHT_GRAY), 0, 0);
    empty1.placeImageXY(new TextImage("hi", 13, FontStyle.BOLD, Color.BLACK), 0, 0);
    empty1.placeImageXY(new TextImage("Lives: " + 4, 20, FontStyle.REGULAR, Color.BLACK), 200, 500);
    empty1.placeImageXY(new RectangleImage(80, 40, OutlineMode.SOLID, Color.LIGHT_GRAY), 300, 500);
    empty1.placeImageXY(new TextImage("Submit", 20, FontStyle.REGULAR, Color.BLACK), 300, 500);
    empty1.placeImageXY(new RectangleImage(150, 40, OutlineMode.OUTLINE, Color.BLACK), 433, 500);
    empty1.placeImageXY(new TextImage("Deselect All", 20, FontStyle.REGULAR, Color.BLACK), 433,
        500);

    return t.checkExpect(smallWorld.makeScene(), empty1);
  }

  // tests for drawBoxes
  void testdrawBoxes(Tester t) {
    this.initData();
    TotalWordSets total = new TotalWordSets(setSmall);
    ConnectionsWorld world1 = new ConnectionsWorld(0, rand, total);
    world1.drawBoxes(empty2);
    empty1.placeImageXY(new RectangleImage(120, 80, OutlineMode.SOLID, Color.LIGHT_GRAY), 0, 0);
    empty1.placeImageXY(new TextImage("hi", 13, FontStyle.BOLD, Color.BLACK), 0, 0);
    t.checkExpect(empty2, empty1);
  }

  // tests for draw completed words
  boolean testdrawCompleted(Tester t) {
    this.initData();
    empty1.placeImageXY(
        new RectangleImage(4 * (boxWidth + gap) - gap, boxHeight, OutlineMode.SOLID, purple),
        boxWidth, boxHeight);
    empty1.placeImageXY(new TextImage("NBA TEAMS", 20, FontStyle.BOLD, Color.BLACK), boxWidth,
        boxHeight);
    Word heat = new Word("HEAT", cat14, new Position(150, 100));
    return t.checkExpect(heat.drawCompleted(empty2),
        new OverlayImage(new TextImage("NBA TEAMS", 20, FontStyle.BOLD, Color.BLACK),
            new RectangleImage(4 * (boxWidth + gap) - gap, boxHeight, OutlineMode.SOLID, green)));
    // may need to include another one for word?
  }

  // compare positions tests
  Posn posn1 = new Posn(350, 400);
  Posn posn2 = new Posn(350, 500);

  boolean testcomparePos(Tester t) {
    return t.checkExpect(pos.comparePos(posn1), true)
        && t.checkExpect(pos.comparePos(posn2), false);
  }

  // same Category tests
  ConnectionsWorld catWorld = new ConnectionsWorld(0, rand, this.set1);
  Category cat22 = new Category("BODIES OF WATER", green);

  boolean testsameCateogry(Tester t) {
    this.initData();
    // word class
    return t.checkExpect(this.hi.sameCategory(cat), true)
        && t.checkExpect(this.hi.sameCategory(cat12), false)
        // world class
        && t.checkExpect(this.catWorld.sameCategory(cat11), true)
        // true bc the selected words is empty
        && t.checkExpect(this.catWorld.sameCategory(cat22), true);
  }

  // tests for draw selected words
  void testdrawSelected(Tester t) {
    this.initData();
    Word kayak = new Word("KAYAK", cat12);
    kayak.drawSelected(empty1);
    empty2.placeImageXY(new RectangleImage(boxWidth, boxHeight, OutlineMode.SOLID, Color.DARK_GRAY),
        0, 0);
    empty2.placeImageXY(new TextImage("KAYAK", 13, FontStyle.BOLD, Color.WHITE), 0, 0);
    t.checkExpect(empty1, empty2);

  }

  // tests for draw lives
  void testdrawLives(Tester t) {
    this.initData();
    world.drawLives(empty2);
    empty1.placeImageXY(new TextImage("Lives: " + "4", fontSize, FontStyle.REGULAR, Color.BLACK),
        200, 500);
    t.checkExpect(empty2, empty1);
  }

  // tests for draw submit
  void testdrawSubmit(Tester t) {
    this.initData();
    world.drawSubmit(empty1);
    empty2.placeImageXY(new RectangleImage(80, 40, OutlineMode.SOLID, Color.LIGHT_GRAY), 300, 500);
    empty2.placeImageXY(new TextImage("Submit", fontSize, FontStyle.REGULAR, Color.BLACK), 300,
        500);
    t.checkExpect(empty1, empty2);
  }

  // tests for draw de-select
  void testdrawDeselect(Tester t) {
    this.initData();
    world.drawDeselect(empty1);
    empty2.placeImageXY(new RectangleImage(150, 40, OutlineMode.OUTLINE, Color.BLACK), 433, 500);
    empty2.placeImageXY(new TextImage("Deselect All", fontSize, FontStyle.REGULAR, Color.BLACK),
        433, 500);
    t.checkExpect(empty1, empty2);
  }

  // tests for onTick
  void testonTick(Tester t) {
    this.initData();
    world.onTick();
    Random rand = new Random(6);
    ConnectionsWorld testWorld = new ConnectionsWorld(0, rand, new TotalWordSets());
    testWorld.givePositions();
    testWorld.draw(empty1);
    t.checkExpect(world, testWorld);
  }

  // tests for remove empty
  void testremoveEmpty(Tester t) {
    this.initData();
    smallWorld.removeEmpty();
    ConnectionsWorld smallWorld2 = new ConnectionsWorld(0, rand, totalWordSmall);
    // no empty
    t.checkExpect(smallWorld, smallWorld2);
    smallWorldEmpty.removeEmpty();
    // with empty
    t.checkExpect(smallWorldEmpty, smallWorld);

  }

  // tests for check submission
  void testcheckSubmission(Tester t) {
    this.initData();
    // wrong submission
    ArrayList<Word> wrong = new ArrayList<Word>(Arrays.asList(new Word("HAIL", cat11),
        new Word("RAIN", cat11), new Word("SLEET", cat11), new Word("JAZZ", cat14)));
    ConnectionsWorld wrongSub = new ConnectionsWorld(this.set1, 4, wrong, this.completed);
    wrongSub.checkSubmission();
    ConnectionsWorld liveDown = new ConnectionsWorld(this.set1, 3, new ArrayList<Word>(),
        this.completed);
    t.checkExpect(wrongSub, liveDown);
  }

  // tests for de-select method
  void testdeselect(Tester t) {
    this.initData();
    ConnectionsWorld selected = new ConnectionsWorld(this.set1, 2, this.row12, this.completed);
    selected.deselect();
    ConnectionsWorld deselected = new ConnectionsWorld(this.set1, 2, new ArrayList<Word>(),
        this.completed);
    t.checkExpect(selected, deselected);
  }

  // tests for worldEnds
  boolean testworldEnds(Tester t) {
    this.initData();
    ConnectionsWorld end = new ConnectionsWorld(this.set1, 0, this.row11, this.completed);
    ArrayList<ArrayList<Word>> all = new ArrayList<ArrayList<Word>>(
        Arrays.asList(row11, row12, row13, row14));
    ConnectionsWorld win = new ConnectionsWorld(this.set1, 3, new ArrayList<Word>(), all);
    return t.checkExpect(world.worldEnds(), new WorldEnd(false, world.makeScene()))
        // world done test
        && t.checkExpect(end.worldEnds(), new WorldEnd(true, end.losingScene()))
        && t.checkExpect(win.worldEnds(), new WorldEnd(true, end.winningScene()));
  }

  // tests for losing scene
  boolean testlosingScene(Tester t) {
    this.initData();
    empty1.placeImageXY(new TextImage("Sorry, you failed :(", 40, Color.BLUE), 300, 300);
    return t.checkExpect(world.losingScene(), empty1);
  }

  // tests for winning scene
  boolean testwinningScene(Tester t) {
    this.initData();
    empty1.placeImageXY(new TextImage("You Win! Congratulations!", 40, Color.BLACK), 300, 300);
    return t.checkExpect(world.winningScene(), empty1);
  }

  // tests for draw
  void testDraw(Tester t) {
    this.initData();
    TotalWordSets total = new TotalWordSets(setSmall);
    ConnectionsWorld world = new ConnectionsWorld(0, rand, total);
    world.drawBoxes(empty2);
    empty1.placeImageXY(new RectangleImage(120, 80, OutlineMode.SOLID, Color.LIGHT_GRAY), 0, 0);
    empty1.placeImageXY(new TextImage("hi", 13, FontStyle.BOLD, Color.BLACK), 0, 0);
    t.checkExpect(empty2, empty1);
    world.drawLives(empty2);
    empty1.placeImageXY(new TextImage("Lives: " + "4", fontSize, FontStyle.REGULAR, Color.BLACK),
        200, 500);
    t.checkExpect(empty2, empty1);
    world.drawSubmit(empty1);
    empty2.placeImageXY(new RectangleImage(80, 40, OutlineMode.SOLID, Color.LIGHT_GRAY), 300, 500);
    empty2.placeImageXY(new TextImage("Submit", fontSize, FontStyle.REGULAR, Color.BLACK), 300,
        500);
    t.checkExpect(empty1, empty2);
    world.drawDeselect(empty1);
    empty2.placeImageXY(new RectangleImage(150, 40, OutlineMode.OUTLINE, Color.BLACK), 433, 500);
    empty2.placeImageXY(new TextImage("Deselect All", fontSize, FontStyle.REGULAR, Color.BLACK),
        433, 500);
    t.checkExpect(empty1, empty2);
  }

  void testMouseClick(Tester t) {
    initData();
    TotalWordSets total = new TotalWordSets(setSmall);
    ConnectionsWorld world1 = new ConnectionsWorld(0, rand, total);
    Posn pos = new Posn(0, 0);
    world1.onMouseClicked(pos);
    world1.drawBoxes(empty2);
    world1.drawSelected(empty2);
    empty1.placeImageXY(new RectangleImage(120, 80, OutlineMode.SOLID, Color.DARK_GRAY), 0, 0);
    empty1.placeImageXY(new TextImage("hi", 13, FontStyle.BOLD, Color.WHITE), 0, 0);
    t.checkExpect(empty2, empty1);
  }

}