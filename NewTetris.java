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

  //This use
  public static void main(String[] args) throws InterruptedException{
    Screen screen = TerminalFacade.createScreen();
    int score = 0;
    boolean running = true;
    //initiate new screen for terminal

    screen.startScreen();
    screen.putString(20, 0, "Score: " + score, Terminal.Color.WHITE, Terminal.Color.BLACK);
    //Putting the score at the top
    screen.refresh();

    //setting initial coordinates for the setCursorVisibleint c = 0;
    int r = 0;
    int c = 0;
    while(running){

      screen.setCursorPosition(c, r);
      Key key = screen.readInput();


      while(key == null){
        key = screen.readInput();

      }


      switch(key.getKind()){
        case Escape:
        screen.putString(5, 10, "You have exited the game, your score is: " + score, Terminal.Color.BLACK, Terminal.Color.WHITE);
        screen.refresh();
        Thread.sleep(3000);
        running = false;
        break;
        case ArrowRight:

        break;
      }
    }
    Thread.sleep(3000);
    System.exit(0);

  }
}
