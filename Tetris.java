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

public class Tetris {

  public static boolean gameOver(ArrayList<block> Leon){
    if(Leon.size() > 3){
      for(int i = 1; i < Leon.size() - 1; i++){
        block leeeon = Leon.get(i);
        for(int j = 0; j < 10; j++){
          if (Tetris.contains(3,j, leeeon.location)) return true;
        }
      }
    }
    return false;
  }

  //if a full row is filled, then it gets cleared and all the blocks fall down
  public static void clearRows(int[][] blocks, int score){
    for(int r = 0; r < blocks.length; r++){
      boolean filledIn = true;
      for(int c = 0; c < blocks[r].length; c++){
        //checks if the row the entire row is filled
        if(blocks[r][c] == 0) filledIn = false;
      }
      //if the row is filled in, clear it, then move all the blocks above that row down by 1
      if (filledIn){
        score += 100;
        for(int c = 0; c < blocks[r].length; c++){
          blocks[r][c] = 0;
        }
        for (int col = 0; col < blocks[r].length; col++){
          for (int row = r; row > 0; row--){
            int old = blocks[row-1][col];
            blocks[row][col] = old;
          }
        }
      }

    }
  }

  //wipes the entire board
  public static void clear(int[][] blocks){
    for(int r = 0; r < blocks.length; r++){
      for(int c = 0; c < blocks[r].length; c++){
        blocks[r][c] = 0;
      }
    }
  }

  //checks through the left and right location arrays to make sure each coordinate is inside the board
  public static boolean canRotate(block b, String dir, int[][] blocks){
    int[][] ary = new int[4][2];
    int[][] now = b.location;
    if (dir.equals("left")){
      ary = b.locationLeft;
    }
    if (dir.equals("right")){
      ary = b.locationRight;
    }
    for (int i = 0; i < ary.length; i++){

      if (ary[i][0] < 0 || ary[i][0] > 23 || ary[i][1] < 0 || ary[i][1] > 9){
        return false;
      }

      if (blocks[ary[i][0]][ary[i][1]] > 0 && blocks[now[i][0]][now[i][1]] == 0){
        return false;
      }

    }
    return true;
  }

  //Generating Block Function
  public static int generateBlock(ArrayList<block> Pieces, int[][] blocks){
    //string containing all the different piece types
    String[] blockTypes = new String[]{"o", "z", "i", "j", "t", "l", "s"};
    Random rand = new Random();
    //rand is used to determine what type of piece gets created
    int w = rand.nextInt(7);
    String type = blockTypes[w];
    //color is based on the type of piece, each piece has its own color
    int color = w + 1;
    block B = new block(5, 2, type);
    for(int i = 0; i < 4; i++){
      blocks[B.location[i][0]][B.location[i][1]] = color;
    }
    Pieces.add(B);
    return color;
  }

  //helper function to find if a space is occupied
  public static boolean contains(int num1,int num2, int[][] stuff){
    for(int i = 0; i < stuff.length; i++){
      if(stuff[i][1] == num2 && stuff[i][0] == num1) return true;
    }
    return false;
  }

  //checks if the spaces underneath current block is occupied or not
  public static boolean blockBelow(ArrayList<block> Pieces, int[][]blocks){
    block b = Pieces.get(Pieces.size() - 1);
    for(int i = 0; i < 4; i++){
      //make sure spaces underneath is not occupied
      if(!Tetris.contains(b.location[i][0]+1, b.location[i][1], b.location)){
        //make sure block is above the board limits
        if(b.location[i][0]+1 < 24){
          if(blocks[b.location[i][0]+1][b.location[i][1]] > 0) return false;
        }
      }
    }
    return true;
  }

  //creates a border around the board
  public static void createBorder(Screen x){
    //creates the vertical parts of the border
    for (int i = 0; i < 25; i++){
      x.putString(4, i, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
      x.putString(25, i, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
      x.putString(3, i, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
      x.putString(26, i, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
    }
    //creates the horizontal parts of the border
    for (int j = 3; j < 27; j++){
      x.putString(j, 0, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
      x.putString(j, 25, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
    }
  }

  //fill in board Function
  public static void fillBoard(Screen x, int co, int ro, int colour){
    //array list with all the colors to color pieces
    Terminal.Color colors = Terminal.Color.BLACK;
    if(colour == 0) colors = Terminal.Color.BLACK;
    if(colour == 1) colors = Terminal.Color.YELLOW;
    if(colour == 2) colors = Terminal.Color.RED;
    if(colour == 3) colors = Terminal.Color.CYAN;
    if(colour == 4) colors = Terminal.Color.WHITE;
    if(colour == 5) colors = Terminal.Color.MAGENTA;
    if(colour == 6) colors = Terminal.Color.BLUE;
    if(colour == 7) colors = Terminal.Color.RED;
    //the piece determines which color is used
    //creates the board
    x.putString(co * 2 + 5, ro, " ", Terminal.Color.WHITE, colors);
    x.putString(co * 2 + 6, ro, " ", Terminal.Color.WHITE, colors);
  }

  //method to check if a block can move right
  public static boolean checkRight(ArrayList<block> Pieces, int[][] blocks){
    block b = Pieces.get(Pieces.size() - 1);
    for (int i = 0; i < b.location.length; i++){
      //checking if the coordinates of the space to the right is outside the boundary of the board
      if (b.location[i][1] + 1 > 9){
        return false;
      } else{
        if(b.location[i][1] + 1 < 10){
          //checking if there's a block to the left using the same algorithm as moveDown
          if(!Tetris.contains(b.location[i][0], b.location[i][1] + 1, b.location)){
            if(blocks[b.location[i][0]][b.location[i][1] + 1] > 0) return false;
          }
        }
      }
    }
    return true;
  }

  //method to check if a block can move down
  public static boolean canMoveDown(block b, ArrayList<block> Pieces, int[][]blocks){
    for (int i = 0; i < b.location.length; i++){
      //checking if the space under the block is outside of the board
      if (b.location[i][0] + 1 > 23){
        return false;
      } else if (b.location[i][0] < 23){
        //checks if there is a block underneath
        if(!Tetris.blockBelow(Pieces, blocks)) return false;
      }
    }
    return true;
  }

  //method to check if a block can move left
  public static boolean checkLeft(ArrayList<block>Pieces, int[][]blocks){
    block b = Pieces.get(Pieces.size() - 1);
    for (int i = 0; i < b.location.length; i++){
      //checking if the space to the left of the block is outside of the board
      if (b.location[i][1] - 1 < 0){
        return false;
      } else{
        if(b.location[i][1] - 1 >= 0){
          //checking if there's a block to the left using the same algorithm as moveDown
          if(!Tetris.contains(b.location[i][0], b.location[i][1] - 1, b.location)){
            if(blocks[b.location[i][0]][b.location[i][1] - 1] > 1) return false;
          }
        }
      }
    }
    return true;
  }

  public static void main(String[] args) throws InterruptedException{
    //dimensions of the board
    int[][] blocks = new int[24][10];
    //array list containing all the pieces generated
    ArrayList<block> Pieces = new ArrayList<block>();
    //counter used to make blocks auto drop
    int counter = 0;
    //setting movement in all directions possible
    boolean canMove = true;
    boolean canMoveRight = true;
    boolean canMoveLeft = true;
    boolean canRotateRight = true;
    boolean canRotateLeft = true;
    Screen screen = TerminalFacade.createScreen();
    int score = 0;
    boolean running = true;
    //initiate new screen for terminal
    screen.startScreen();
    clear(blocks);
    //create the first piece
    int color = Tetris.generateBlock(Pieces, blocks);
    //starting the game
    while(running){

      //Putting the score at the bottom middle
      screen.putString(40, 20, "Score: " + score, Terminal.Color.WHITE, Terminal.Color.DEFAULT);

      //setting the current block to the most recent piece created
      //this block will be the player controlled piece
      block current = Pieces.get(Pieces.size() - 1);
      counter++;
      //filling the board
      for(int ro = 0; ro < blocks.length; ro++){
        for(int co = 0; co < blocks[ro].length; co++){
          fillBoard(screen, co, ro+1, blocks[ro][co]);
        }
      }
      //adding the border
      createBorder(screen);
      screen.refresh();
      //auto falling code for the current piece
      if (counter % 5000 == 0){
        score += 1;
        canMove = canMoveDown(current, Pieces, blocks);
        if (canMove){
          for(int i = 0; i < 4; i++){
            blocks[current.location[i][0]][current.location[i][1]] = 0;
          }
          current.moveDown();
          for(int i = 0; i < 4; i++){
            blocks[current.location[i][0]][current.location[i][1]] = color;
          }
        }
        else{
          //if piece can no longer move down, new piece will be created
          //current piece will be set to the new piece created
          //all movement will be allowed again
          Tetris.clearRows(blocks, score);
          color = Tetris.generateBlock(Pieces, blocks);
          current = Pieces.get(Pieces.size() - 1);
          canMove = true;
          canMoveLeft = true;
          canMoveRight = true;
          canRotateLeft = true;
          canRotateRight = true;
        }
      }



      //
      if (gameOver(Pieces)) running = false;
      Key key = screen.readInput();
      //used for arrows
      if(key == null){
        key = screen.readInput();
      } else {
        switch(key.getKind()){
          //stops the game and exits the game
          case Escape:
          screen.putString(5, 30, "You have exited the game, your score is: " + score, Terminal.Color.WHITE, Terminal.Color.YELLOW);
          screen.refresh();
          Thread.sleep(1000);
          running = false;
          break;
          case ArrowRight:
          //move right by clearing its current position, adding 1 to its xcor, then updating location
          canMoveRight = checkRight(Pieces, blocks);
          if (canMoveRight){
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = 0;
            }
            current.moveRight();
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = color;
            }
          }
          break;
          case ArrowLeft:
          //move left by clearing its current position, subtracting 1 to its xcor, then updating location
          canMoveLeft = checkLeft(Pieces, blocks);
          if (canMoveLeft){
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = 0;
            }
            current.moveLeft();
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = color;
            }
          }
          break;
          case ArrowDown:
          //manual move down by clearing its current position, subtracting 1 to its ycor, then updating location
          //this is very similar to the auto fall code
          canMove = canMoveDown(current, Pieces, blocks);
          if (canMove){
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = 0;
            }
            current.moveDown();
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = color;
            }
          }
          else{
            Tetris.clearRows(blocks, score);
            color = Tetris.generateBlock(Pieces, blocks);
            canMove = true;
            canMoveLeft = true;
            canMoveRight = true;
            canRotateLeft = true;
            canRotateRight = true;
          }
          break;
          default:
          break;
        }
        if(key.getCharacter() == 'z'){
          //rotate right by clearing its current position, changing the direction the block is facing, then updating location
          canRotateLeft = canRotate(current, "left", blocks) && checkLeft(Pieces, blocks);

          if(canRotateLeft){
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = 0;
            }
            current.rotateLeft();
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = color;
            }
          }
        }
        if(key.getCharacter() == 'x'){
          //rotate left by clearing its current position, changing the direction the block is facing, then updating location
          canRotateRight = canRotate(current, "right", blocks) && checkRight(Pieces, blocks);
          if(canRotateRight){
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = 0;
            }
            current.rotateRight();
            for(int i = 0; i < 4; i++){
              blocks[current.location[i][0]][current.location[i][1]] = color;
            }
          }
        }
      }
      screen.refresh();
    }
    screen.clear();

    Thread.sleep(1000);
    System.exit(0);
  }
}
