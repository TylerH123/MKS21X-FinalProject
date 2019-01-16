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

  public static void clearRows(int[][] blocks, int score){
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
      score += 10;
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
  public static int generateBlock(ArrayList<block> Pieces, int[][] blocks){
    String[] blockTypes = new String[]{"o", "z", "i", "s", "t", "l", "j"};
    Random rand = new Random();
    int w = rand.nextInt(7);
    String type = blockTypes[w];
    int color = w + 1;
    block B = new block(5, 1, type);
    for(int i = 0; i < 4; i++){
      blocks[B.location[i][0]][B.location[i][1]] = color;
    }
    Pieces.add(B);
    return color;
  }

  //Move down all the pieces - ONLY worry about actual pieces and not empty spa
  //helper function for gravity
  private static boolean contains(int[][] coords, int x, int y){
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 2; j++){
        if(x == i && j == y) return true;
      }
    } return false;
  }

  //fill in board Function
  public static void fillBoard(Screen x, int co, int ro, String g){
    Terminal.Color color = Terminal.Color.BLACK;
    if (g.equals("0")) color = Terminal.Color.BLACK;
    if (g.equals("1")) color = Terminal.Color.YELLOW;
    if (g.equals("2")) color = Terminal.Color.RED;
    if (g.equals("3")) color = Terminal.Color.CYAN;
    if (g.equals("4")) color = Terminal.Color.GREEN;
    if (g.equals("5")) color = Terminal.Color.MAGENTA;
    if (g.equals("6")) color = Terminal.Color.BLUE;
    if (g.equals("7")) color = Terminal.Color.WHITE;
    x.putString(co * 2 + 5, ro, g, Terminal.Color.WHITE, color);
    x.putString(co * 2 + 5, ro, g, Terminal.Color.WHITE, color);
    x.putString(co * 2 + 6, ro, g, Terminal.Color.WHITE, color);
    x.putString(co * 2 + 6, ro, g, Terminal.Color.WHITE, color);
  }

  //really bad algorithm to make sure blocks dont overlap
  public static void overlapCheck(int[][] blocks){
    int redSquaresOld = 0;
    for(int ro = 0; ro < blocks.length; ro++){
      for(int co = 0; co < blocks[ro].length; co++){
        redSquaresOld += blocks[ro][co];
      }
    }
    int[][] prevState = new int[blocks.length][blocks[1].length];
    for(int ro = 0; ro < blocks.length; ro++){
      for(int co = 0; co < blocks[ro].length; co++){
        prevState[ro][co] = blocks[ro][co];
      }
    }
  }

  //method to check if a block can move right
  public static boolean checkRight(block b){
    for (int i = 0; i < b.location.length; i++){
      if (b.location[i][1] >= 9){
        return false;
      }
    }
    return true;
  }

  //method to check if a block cana move left
  public static boolean checkLeft(block b){
    for (int i = 0; i < b.location.length; i++){
      if (b.location[i][1] <= 1){
        return false;
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
    NewTetris.clear(blocks);
    Screen screen = TerminalFacade.createScreen();
    int score = 0;
    boolean running = true;
    //initiate new screen for terminal
    screen.startScreen();
    screen.putString(40, 0, "Score: " + score, Terminal.Color.WHITE, Terminal.Color.BLACK);
    //Putting the score at the top
    screen.refresh();
    //screen.setCursorVisible(false);
    //setting initial coordinates for the setCursorVisibleint c = 0;
    NewTetris.clear(blocks);
    int color = NewTetris.generateBlock(Pieces, blocks);
    while(running){
      counter++;
      //using this as temporary timer
      if(counter % 20 == 0){
        //NewTetris.generateBlock(Pieces, blocks);
      }
      //if(Pieces.size() != 0) block B = Pieces[counter - 1];
      //filling the board
      for(int ro = 0; ro < blocks.length; ro++){
        for(int co = 0; co < blocks[ro].length; co++){
          String g = Integer.toString(blocks[ro][co]);
          fillBoard(screen, co, ro, g);
        }
      }
      screen.refresh();
      //PUT GRAVITY HERE - CUZ IT MUST go after we fill in blocks
      //NewTetris.gravity(blocks, Pieces);
      if (counter % 5000 == 0){
        overlapCheck(blocks);
        block B = Pieces.get(Pieces.size() - 1);
        if (canMove){
          for(int i = 0; i < 4; i++){
            blocks[B.location[i][0]][B.location[i][1]] = 0;
          }
          B.moveDown();
          for(int i = 0; i < 4; i++){
            blocks[B.location[i][0]][B.location[i][1]] = color;
          }
        }
        //check if too low
        for (int j = 0; j < B.location.length; j++){
          if (B.location[j][0] >= 23){
            canMove = false;
            NewTetris.clearRows(blocks, score);
            color = NewTetris.generateBlock(Pieces, blocks);
            canMove = true;
            canMoveLeft = true;
            canMoveRight = true;
          }
        }
      }
      counter = counter % 10000;
      Key key = screen.readInput();
      int totalshift = 0;
      //used for arrows
      if(key == null){
        key = screen.readInput();
      } else {
        switch(key.getKind()){
          case Escape:
            screen.putString(5, 30, "You have exited the game, your score is: " + score, Terminal.Color.WHITE, Terminal.Color.BLACK);
            screen.refresh();
            Thread.sleep(1);
            running = false;
          break;
          case ArrowRight:
            if(counter > -1){
              block B = Pieces.get(Pieces.size() - 1);
              canMoveRight = checkRight(B);
              if (canMoveRight){
              overlapCheck(blocks);
              for(int i = 0; i < 4; i++){
                blocks[B.location[i][0]][B.location[i][1]] = 0;
              }
              B.moveRight();
                for(int i = 0; i < 4; i++){
                  blocks[B.location[i][0]][B.location[i][1]] = color;
                }
              }
            }
            break;
            case ArrowLeft:
              if(counter > -1){
                block B = Pieces.get(Pieces.size() - 1);
                canMoveLeft = checkLeft(B);
                overlapCheck(blocks);
                for(int i = 0; i < 4; i++){
                  blocks[B.location[i][0]][B.location[i][1]] = 0;
                }
                B.moveLeft();
                try{
                  for(int i = 0; i < 4; i++){
                    blocks[B.location[i][0]][B.location[i][1]] = color;
                  }
                } catch(ArrayIndexOutOfBoundsException e){
                  B.moveRight();
                  for(int i = 0; i < 4; i++){
                    blocks[B.location[i][0]][B.location[i][1]] = color;
                  }
                }
              }
            //same as ArrowRight
            break;
            case ArrowDown:
            break;
            default:
            break;
          }
          if(key.getCharacter() == 'z'){
            //algorithm to prevent going into another square
            if(counter > -1){
              block B = Pieces.get(Pieces.size() - 1);
                for(int i = 0; i < 4; i++){
                  blocks[B.location[i][0]][B.location[i][1]] = 0;
                }
                try{
                  B.rotateLeft();
                  for(int i = 0; i < 4; i++){
                    blocks[B.location[i][0]][B.location[i][1]] = color;
                  }
                } catch (Exception e){
                  B.rotateRight();
                }
              }
            }
            if(key.getCharacter() == 'x'){
              if(counter > -1){
                block B = Pieces.get(Pieces.size() - 1);
                  for(int i = 0; i < 4; i++){
                    blocks[B.location[i][0]][B.location[i][1]] = 0;
                  }
                  try{
                    B.rotateRight();
                    for(int i = 0; i < 4; i++){
                      blocks[B.location[i][0]][B.location[i][1]] = color;
                    }
                  } catch(Exception e){
                    B.rotateLeft();
                  }
                }
              }
            }
          }
          Thread.sleep(1000);
          System.exit(0);
        }
      }
