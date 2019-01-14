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

  public static boolean GenerateFourBlock(ArrayList<block> Pieces){

    return true;
    //update when arrayList of fourblocks in implemented
  }
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


  //Generating Block Function
public static boolean generateBlock(ArrayList<block> Pieces){
  String[] blockTypes = new String[]{"o", "z", "i", "s", "t", "l", "j"};
  Random rand = new Random();
  String type = blockTypes[rand.nextInt()%7];
  block B = new block(0, 20, type, 10, 24);

  Pieces.add(B);

  return true;
}

//Move down all the pieces - ONLY worry about actual pieces and not empty space
public static void gravity(int[][] blocks, ArrayList<block> Pieces){
  for(int i = 0; i < Pieces.size(); i++){
    (Pieces.get(i)).moveDown();
  }
  for(int co = 0; co < blocks[0].length; co++){
    //go column by column from left to right, from down to up. Only move down blocks by swapping them with empty
    //spaces. Also, since Y coords are backwards we have to go down from 23
    //BUT, since you can't move down from the bottom we start at 22
   for(int ro = 22; ro > 0; ro--){
     //first check if coordinate has a block and coordinate below is free
     if(blocks[co][ro] > 0 && blocks[co][ro + 1] == 0){
       //Make a new storage variable
      int holder = blocks[ro][co];
       //now swapping the block and empty space below
       blocks[co][ro + 1] = holder;
       blocks[co][ro] = 0;
     }
   }
 }
}


//keep in mind, the pieces array list contains all the blocks that ever formed, but the user only influences the last block
//in the list




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
      //NewTetris.gravity(blocks, Pieces);


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
