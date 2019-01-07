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




public class Tetris{
  private int height, width;
  private boolean[][] pieces;
  private ArrayList blocks;
  //This can be a 1D version of a 2D array of all the blocks. The coordinates is x = index% width y = index/width
  private int score;
  private boolean running = true;
  private TerminalSize size;
  //Blocks is an array of all the coordinates in the tetris board, each index in the 2d array representaing
  //a coordinate. False means there is no block on the piece while
  public boolean isRunning(){
    return running;
  }

  public Tetris(){
    height = 24;
    width = 10;
    pieces = new boolean[height][width];
    //at the default the board has no blocks
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j ++){
        pieces[i][j] = false;
      }
    }
    size = terminal.getTerminalSize();
    terminal.setCursorVisible(true);
    size.setColumns(10);
    size.setRows(24);
  }

  TerminalSize size = terminal.getTerminalSize();
  terminal.setCursorVisible(true);
  size.setColumns(10);
  size.setRows(24);
  public void printBoard(){

  }

    //Note that these coordinates are (row column), which are (y, x), not (x, y)






  public static void main(String args[]){
    Tetris ans = new Tetris();


  }
}
