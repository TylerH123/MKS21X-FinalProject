import java.util.*;
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import com.googlecode.lanterna.screen.Screen;

public class NewTetris {


  //This used in the arrayList of ints, if the coordinate has a block on the coordinate
  // - since the color is a thing and not just 0, then it uses returns true
  public static boolean hasBlock(int[][] blocks, int col, int row){
    if(blocks[row][col] > 0) return true;
    return false;
  }


  public static void clearRows(int[][] blocks){
    for(int r = 0; r < blocks.length; r++){
      boolean filledIn = true;
      for(int c = 0; c < blocks[r].length; c++){
        if(!hasBlock(blocks, c, r)) filledIn = false;
      }
      if (filledIn){
        for(int c = 0; c < blocks[r].length; c++){
          blocks[r][c] = 0;
        }
      }

    }
  }

  public static void clear(int[][] blocks){
    for(int r = 0; r < blocks.length; r++){
      for(int c = 0; c < blocks[r].length; c++){
        blocks[r][c] = 0;


      }

    }
  }
  //checks through the arrays to make sure each coordinate is inside the board
  public boolean canRotate(String rotate, ArrayList<block> Pieces){
    if (rotate.equals("left")){
      int[][] loc = Pieces.get(0).locationLeft;
    }
    if (rotate.equals("right")){
      int[][] loc = Pieces.get(0).locationRight;
    }
    block currentBlock = Pieces.get(0);
    int[][] loc = new int[4][2];
    for (int i = 0; i < loc.length; i++){
      if (loc[i][0] > 10 || loc[i][0] < 0){
        return false;
      }
      if (loc[i][1] < 0 || loc[i][1] > 24){
        return false;
      }
    }
    return true;
  }

  //Generating Block Function
public static void generateBlock(ArrayList<block> Pieces, int[][] blocks){
  String[] blockTypes = new String[]{"o", "z", "i", "s", "t", "l", "j"};
  Random rand = new Random();
  String type = blockTypes[rand.nextInt()%4+3];
  block B = new block(5, 5, type);
  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 2; j++){
    blocks[B.coords[i][j] + 5][B.coords[i][j] + 5] = 1;
    //in the future we can make differnt colors for diffrent blocks
  }
}

  Pieces.add(B);


}

//Move down all the pieces - ONLY worry about actual pieces and not empty space
public static void gravity(int[][] blocks){


  for(int co = 0; co < blocks[0].length; co++){
    //go column by column from left to right, from down to up. Only move down blocks by swapping them with empty
    //spaces. Also, since Y coords are backwards we have to go down from 23
    //BUT, since you can't move down from the bottom we start at 22
   for(int ro = 22; ro > 0; ro--){
     //first check if coordinate has a block and coordinate below is free
     if(blocks[ro][co] > 0 && blocks[ro][co + 1] == 0){
       //Make a new storage variable
      int holder = blocks[ro][co];
       //now swapping the block and empty space below
       blocks[ro][co + 1] = holder;
       blocks[ro][co] = 0;
      }
     }
    }
  }

//helper function for gravity
private static boolean contains(int[][] coords, int x, int y){
  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 2; j++){
      if(x == i && j == y) return true;
    }
  } return false;
}

public static void gravity(int[][] blocks, ArrayList<block> Pieces){
  //to fix potential errors lets only have one block floating on at a time
  for(int i = Pieces.size() - 2; i < Pieces.size(); i++){
    boolean willFall = true;
    block b = Pieces.get(i);
    int[][] c = b.coords;


    //first find the lowest blocks in each column of the piece
    //if there is nothing below them then we can move the block down

    for(int k = 0; k < 4; k ++){
      if (!(NewTetris.contains(c, c[k][0], c[k][1] - 1))) {
        //All the blocks here are lowest block, now we want to see if they can moveDown
        //if even one of them can't we will set willFall equal to false

        if(blocks[ (c[k][0]) + b.getX()  ]//the x coordinate of the block on the board
        [ c[k][1] + b.getY() - 1  ] != 0) willFall = false;//the y coordinate of the block 1 less than our piece \
        //also rember that blocks has backwards coords
      }

    }

    //now move down if willfall says so
    if(willFall) (Pieces.get(i)).moveDown();

  }
}


//keep in mind, the pieces array list contains all the blocks that ever formed, but the user only influences the last block

  //This use
  public static void main(String[] args) throws InterruptedException{
    int[][] blocks = new int[10][24];
    ArrayList<block> Pieces = new ArrayList<block>();
    int counter = 0;
    NewTetris.clear(blocks);
    Screen screen = TerminalFacade.createScreen();
    int score = 0;
    boolean running = true;
    //initiate new screen for terminal

    screen.startScreen();
    screen.putString(40, 0, "Score: " + score, Terminal.Color.WHITE, Terminal.Color.BLACK);
    //Putting the score at the top
    screen.refresh();

    //setting initial coordinates for the setCursorVisibleint c = 0;
    int r = 0;
    int c = 0;
    NewTetris.clear(blocks);



    while(running){
      //using this as temporary timer
      if(counter % 20 == 0){
        NewTetris.generateBlock(Pieces, blocks);
      }


      //if(Pieces.size() != 0) block B = Pieces[counter - 1];




      //filling the board
      System.out.println(blocks.length);
       for(int ro = 0; ro < blocks.length; ro++){
        for(int co = 0; co < blocks[ro].length; co++){
         //System.out.println("Test" + (ro * 24 + co));
         //System.out.println(blocks[ro][co]);
          String g = "1";
          if (blocks[ro][co] == 0) g = "0";
          if(g == "0"){
          screen.putString(ro * 2 + 5,co * 2, g, Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.putString(ro * 2 + 5,co * 2 + 1, " ", Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.putString(ro * 2 + 6,co * 2, " ", Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.putString(ro * 2 + 6,co * 2 + 1, " ", Terminal.Color.WHITE, Terminal.Color.BLACK);
        }
         if(g == "1"){
           screen.putString(ro * 2 + 5,co * 2, g, Terminal.Color.WHITE, Terminal.Color.RED);
           screen.putString(ro * 2 + 5,co * 2 + 1, " ", Terminal.Color.WHITE, Terminal.Color.RED);
           screen.putString(ro * 2 + 6,co * 2, " ", Terminal.Color.WHITE, Terminal.Color.RED);
           screen.putString(ro * 2 + 6,co * 2 + 1, " ", Terminal.Color.WHITE, Terminal.Color.RED);
         }
        }
       }
       screen.refresh();

//PUT GRAVITY HERE - CUZ IT MUST go after we fill in blocks
    NewTetris.gravity(blocks, Pieces);



      screen.setCursorPosition(c, r);
      Key key = screen.readInput();


      while(key == null){
        key = screen.readInput();
      }


      switch(key.getKind()){
        case Escape:
        screen.putString(5, 30, "You have exited the game, your score is: " + score, Terminal.Color.WHITE, Terminal.Color.BLACK);
        screen.refresh();
        Thread.sleep(1000);
        running = false;
        break;

        case ArrowRight:
        if(counter > -1){
          block b = Pieces.get(counter - 1);
          b.moveRight();
        }
        break;

        case ArrowLeft:
        if(counter > -1){
          block b = Pieces.get(counter - 1);
          b.moveLeft();
        }
      //same as ArrowRight
        break;
        case ArrowDown:

        break;



        default:
        break;


      }

      if(key.getCharacter() == 'z'){
        block b = Pieces.get(counter - 1);
        b.rotateLeft();
      }

      if(key.getCharacter() == 'z'){
        block b = Pieces.get(counter - 1);
        b.turnRight();
      }



    }
    Thread.sleep(1000);
    System.exit(0);

  }

}
