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
    //uptade when arrayList of fourblocks in implemented
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




  //This use
  public static void main(String[] args) throws InterruptedException{
    int[][] blocks = new int[10][24];
    ArrayList<block> Pieces = new ArrayList<block>();
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
    System.out.println(blocks.length);
     for(int ro = 0; ro < blocks.length; ro++){
      for(int co = 0; co < blocks[ro].length; co++){
       System.out.println("Test" + (ro * 24 + co));
       System.out.println(blocks[ro][co]);
        String g = "1";
        if (blocks[ro][co] == 0) g = "0";
        System.out.println(g);
        screen.putString(co,ro, g, Terminal.Color.WHITE, Terminal.Color.BLACK);
        screen.refresh();
     }
     }


    screen.refresh();
    /*while(running){


      //Filling the board

      screen.refresh();

      screen.setCursorPosition(c, r);
      Key key = screen.readInput();


      while(key == null){
        key = screen.readInput();

      }


      switch(key.getKind()){
        case Escape:
        screen.putString(5, 10, "You have exited the game, your score is: " + score, Terminal.Color.WHITE, Terminal.Color.BLACK);
        screen.refresh();
        Thread.sleep(1000);
        running = false;
        break;

        case ArrowRight:

        //move cursor to block
        //(hasBlock (getTerminalPosition.x, getTerminalPosition.y)) --> check screen documentation
        //  blockAt(terminalpostion).rotateRight
        break;

        case ArrowLeft:

      //same as ArrowRight
        break;
        case ArrowDown:
        //fourBlockAt.movedDown()
        break;


      }
    }*/
    Thread.sleep(3000);
    System.exit(0);

  }
}
