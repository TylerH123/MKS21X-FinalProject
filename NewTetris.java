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


  public static void main(String[] args) throws InterruptedException{
    Screen screen = TerminalFacade.createScreen(10, 24);
    int score = 0;
    boolean running = true;
    //initiate new screen for terminal

    screen.startScreen();
    screen.putString(10, 5, "Score: " + score, Terminal.Color.BLACK, Terminal.Color.WHITE);
    //Putting the score at the top
    screen.refresh();

    //setting initial coordinates for the setCursorVisibleint c = 0;
    int r = 0;
    int c = 0;
    while(running){

      screen.moveCursor(c, r)
      Key key = screen.readInput();
      screen.setCursorVisible(true);

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
