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

public class NewTetris{
  private int[][] blocks;
  private ArrayList pieces;

  //Stuff from the terminal demo
  public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}



  public static void main(String args[]){
    int x = 10;
		int y = 10;

		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();


		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);
    size.setColumns(10);
    size.setRows(24);

		boolean running = true;
    while(running){
      for(int i = 0; i > -10; i --){
        for(int j = 0; j > -24; j--){
          terminal.moveCursor(i, j );
          terminal.applyBackgroundColor(Terminal.Color.BLACK);
          terminal.applyForegroundColor(Terminal.Color.YELLOW);
          terminal.applySGR(Terminal.SGR.ENTER_BOLD);
          terminal.putCharacter(' ');
          terminal.putCharacter(' ');
          terminal.putCharacter(' ');
          terminal.putCharacter(' ');
          terminal.moveCursor(i,j);
          terminal.putCharacter(' ');
          terminal.putCharacter(' ');
          terminal.putCharacter(' ');
          terminal.putCharacter(' ');
          terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
          terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        }
      }
      Key key = terminal.readInput();

      if (key != null)
      {

        if (key.getKind() == Key.Kind.Escape) {

          terminal.exitPrivateMode();
          running = false;
        }
      }

  }
  }
}
