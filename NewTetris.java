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
    Screen screen = TerminalFacade.createScreen();
    int score = 0;
    boolean running = true;
    //initiate new screen for terminal

    screen.startScreen();
    screen.putString(10, 5, "Score: " + score, Terminal.Color.BLACK, Terminal.Color.WHITE);
    //Putting the score at the top
    screen.refresh();
    while(running){
      Key key = screen.readInput();
      while(key == null){
        key = screen.readInput();
      }

      switch(key.getKind()){
        case Escape:
        screen.putString(5, 10, "You have exited the game, your score is: " + score, Terminal.Color.BLACK, Terminal.Color.WHITE);
        Thread.sleep(5000);
        running = false;
      }
    }
    Thread.sleep(3000);
    System.exit(0);

  }
}
