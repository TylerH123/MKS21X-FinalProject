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
  //This used in the arrayList of ints, if the coordinate has a block on the coordinate
  // - since the color is a thing and not just 0, then it uses returns true
  public static boolean hasBlock(int[][] blocks, int col, int row){
    if(blocks[row][col] > 0) return true;
    return false;
  }

  public static boolean gameOver(ArrayList<block> Leon){
    if(Leon.size() > 3){
    for(int i = 1; i < Leon.size() - 1; i++){
      block leeeon = Leon.get(i);
      for(int j = 0; j < 10; j++){
      if (Tetris.contains(3,j, leeeon.location)) return true;
    }
    }
  } return false;
  }

  public static void clearRows(int[][] blocks, int score){
    int startY = 0;
    for(int r = 0; r < blocks.length; r++){
      boolean filledIn = true;
      for(int c = 0; c < blocks[r].length; c++){
        if(!hasBlock(blocks, c, r)) filledIn = false;
      }
      if (filledIn){
        for(int c = 0; c < blocks[r].length; c++){
          blocks[r][c] = 0;
          startY = r;
        }
      }
      score += 10;
    }
    //slides the thin up to the top
    for(int i = 0; startY > 0; startY--){
      for(int x = 0; x < 10; x++){
      blocks[startY][x] = blocks[startY - 1][x];

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

  //checks through the left arrays to make sure each coordinate is inside the board
  public static boolean canRotate(block b, String dir){
    int[][] ary = new int[4][2];
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
    }
    return true;
  }

  //Generating Block Function
  public static int generateBlock(ArrayList<block> Pieces, int[][] blocks){
    String[] blockTypes = new String[]{"o", "z", "i", "s", "t", "l", "j"};
    Random rand = new Random();
    int w = rand.nextInt(7);
    String type = blockTypes[w];
    int color = w + 1;
    block B = new block(5, 2, type);
    for(int i = 0; i < 4; i++){
      blocks[B.location[i][0]][B.location[i][1]] = color;
    }
    Pieces.add(B);
    return color;
  }

  public static boolean contains(int num1,int num2, int[][] stuff){
    for(int i = 0; i < stuff.length; i++){
        if(stuff[i][1] == num2 && stuff[i][0] == num1) return true;
    } return false;
  }

  public static boolean blockBelow(ArrayList<block> Pieces, int[][]blocks){
    block b = Pieces.get(Pieces.size() - 1);
    for(int i = 0; i < 4; i++){

        if(!Tetris.contains(b.location[i][0]+1, b.location[i][1], b.location)){
          //^roots out all the blocks that are above another block in the 4block
        if(b.location[i][0]+1 < 24){
          if(blocks[b.location[i][0]+1][b.location[i][1]] > 0) return false;
        }
        }

    } return true;
  }

  //Move down all the pieces - ONLY worry about actual pieces and not empty spa
  //helper function for gravity


  //creates a border around the board
  public static void createBorder(Screen x){
    for (int i = 0; i < 25; i++){
      x.putString(4, i, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
      x.putString(25, i, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
      x.putString(3, i, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
      x.putString(26, i, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
    }
    for (int j = 3; j < 27; j++){
      x.putString(j, 0, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
      x.putString(j, 25, " ", Terminal.Color.GREEN, Terminal.Color.GREEN);
    }
  }

  //fill in board Function
  public static void fillBoard(Screen x, int co, int ro, String g){
    Terminal.Color color = Terminal.Color.YELLOW;
    if (g.equals("0")) color = Terminal.Color.BLACK;
    if (g.equals("1")) color = Terminal.Color.YELLOW;
    if (g.equals("2")) color = Terminal.Color.RED;
    if (g.equals("3")) color = Terminal.Color.CYAN;
    if (g.equals("4")) color = Terminal.Color.RED;
    if (g.equals("5")) color = Terminal.Color.MAGENTA;
    if (g.equals("6")) color = Terminal.Color.BLUE;
    if (g.equals("7")) color = Terminal.Color.WHITE;
    x.putString(co * 2 + 5, ro, " ", Terminal.Color.WHITE, color);
    x.putString(co * 2 + 6, ro, " ", Terminal.Color.WHITE, color);
  }

  //method to check if a block can move right
  public static boolean checkRight(ArrayList<block> Pieces, int[][] blocks){
    block b = Pieces.get(Pieces.size() - 1);
    for (int i = 0; i < b.location.length; i++){
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

  public static boolean canMoveDown(block b, ArrayList<block> Pieces, int[][]blocks){
    for (int i = 0; i < b.location.length; i++){
      if (b.location[i][0] + 1 > 23){
        return false;
      } else if (b.location[i][0] < 23){
        if(!Tetris.blockBelow(Pieces, blocks)) return false;
      }

      //checks if theres a block below
    }
    return true;
  }

  //method to check if a block cana move left
  public static boolean checkLeft(ArrayList<block>Pieces, int[][]blocks){
    block b = Pieces.get(Pieces.size() - 1);
    for (int i = 0; i < b.location.length; i++){
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

  //keep in mind, the pieces array list contains all the blocks that ever formed, but the user only influences the last block
  //This use
  public static void main(String[] args) throws InterruptedException{
    int[][] blocks = new int[24][10];
    ArrayList<block> Pieces = new ArrayList<block>();
    int counter = 0;
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

    //screen.setCursorVisible(false);
    //setting initial coordinates for the setCursorVisibleint c = 0;
    Tetris.clear(blocks);
    int color = Tetris.generateBlock(Pieces, blocks);
    while(running){
      screen.putString(40, 20, "Score: " + score, Terminal.Color.WHITE, Terminal.Color.DEFAULT);
      //Putting the score at the top
      screen.refresh();
      block current = Pieces.get(Pieces.size() - 1);
      counter++;
      //filling the board
      for(int ro = 0; ro < blocks.length; ro++){
        for(int co = 0; co < blocks[ro].length; co++){
          String g = Integer.toString(blocks[ro][co]);
          fillBoard(screen, co, ro+1, g);
        }
      }
      createBorder(screen);
      screen.refresh();
      if (counter % 5000 == 0){
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
          System.out.println(score);
        }
      }

      //
      if (gameOver(Pieces)) running = false;
      Key key = screen.readInput();
      int totalshift = 0;
      //used for arrows
      if(key == null){
        key = screen.readInput();
      } else {
        switch(key.getKind()){
          case Escape:
            screen.putString(5, 30, "You have exited the game, your score is: " + score, Terminal.Color.WHITE, Terminal.Color.YELLOW);
            screen.refresh();
            Thread.sleep(1);
            running = false;
          break;
          case ArrowRight:
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
            canRotateLeft = canRotate(current, "left");
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
            canRotateRight = canRotate(current, "right");
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
      }
      Thread.sleep(1000);
      System.exit(0);
    }
  }
