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
  public static void generateBlock(ArrayList<block> Pieces, int[][] blocks){
    String[] blockTypes = new String[]{"o", "z", "i", "s", "t", "l", "j"};
    Random rand = new Random();
    int w = rand.nextInt(7);
    String type = blockTypes[w];
    block B = new block(5, 1, type);
    for(int i = 0; i < 4; i++){
      blocks[B.location[i][0]][B.location[i][1]] = 1;
    }
    Pieces.add(B);
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

  public static void gravity(int[][] blocks, ArrayList<block> Pieces){
    //to fix potential errors lets only have one block floating on at a time
    if(Pieces.size() > 0){
      for(int i = Pieces.size() - 1; i < Pieces.size(); i++){
        boolean willFall = true;
        block b = Pieces.get(i);
        int[][] c = b.location;
    //first find the lowest blocks in each column of the piece
    //if there is nothing below them then we can move the block down
        for(int k = 0; k < 4; k ++){
          if (!(NewTetris.contains(c, c[k][0], c[k][1] - 1))) {
            //All the blocks here are lowest block, now we want to see if they can moveDown
            //if even one of them can't we will set willFall equal to false
            if(blocks[ (c[k][0]) + b.getX()  ]//the x coordinate of the block on the board
              [ c[k][1] + b.getY() - 1  ] != 0) willFall = false;//the y coordinate of the block 1 less than our piece \
              //also rember that blocks has backwards coords
            }
          }
        //now move down if willfall says so
        if(willFall) (Pieces.get(i)).moveDown();
      }
    }
  }

  //keep in mind, the pieces array list contains all the blocks that ever formed, but the user only influences the last block

  //This use
  public static void main(String[] args) throws InterruptedException{
    int[][] blocks = new int[24][10];
    ArrayList<block> Pieces = new ArrayList<block>();
    int counter = 0;
    boolean canMove = true;
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
    NewTetris.generateBlock(Pieces, blocks);
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
         //System.out.println("Test" + (ro * 24 + co));
         //System.out.println(blocks[ro][co]);
          String g = "1";
          if (blocks[ro][co] == 0) g = "0";
          if(g == "0"){
            screen.putString(co * 2 + 5, ro, g, Terminal.Color.WHITE, Terminal.Color.BLACK);
            screen.putString(co * 2 + 5, ro + 1, " ", Terminal.Color.WHITE, Terminal.Color.BLACK);
            screen.putString(co * 2 + 6, ro, " ", Terminal.Color.WHITE, Terminal.Color.BLACK);
            screen.putString(co * 2 + 6, ro + 1, " ", Terminal.Color.WHITE, Terminal.Color.BLACK);
          }
          if(g == "1"){
            screen.putString(co * 2 + 5, ro, g, Terminal.Color.WHITE, Terminal.Color.RED);
            screen.putString(co * 2 + 5, ro + 1, " ", Terminal.Color.WHITE, Terminal.Color.RED);
            screen.putString(co * 2 + 6, ro, " ", Terminal.Color.WHITE, Terminal.Color.RED);
            screen.putString(co * 2 + 6,ro + 1, " ", Terminal.Color.WHITE, Terminal.Color.RED);
          }
        }
      }
       screen.refresh();
       //PUT GRAVITY HERE - CUZ IT MUST go after we fill in blocks
       //NewTetris.gravity(blocks, Pieces);


       if (counter % 1000 == 0){
         int redSquaresOld = 0;
         for(int ro = 0; ro < blocks.length; ro++){
          for(int co = 0; co < blocks[ro].length; co++){
            redSquaresOld += blocks[ro][co];
          }
        }

         block B = Pieces.get(Pieces.size() - 1);
         if (canMove){
           for(int i = 0; i < 4; i++){
             blocks[B.location[i][0]][B.location[i][1]] = 0;
           }
           B.moveDown();
           for(int i = 0; i < 4; i++){
             blocks[B.location[i][0]][B.location[i][1]] = 1;
           }
         }
         //check if too low
         for (int j = 0; j < B.location.length; j++){
           if (B.location[j][0] > 22){
             canMove = false;
             NewTetris.generateBlock(Pieces, blocks);
             canMove = true;
           }
         }
         //check if block is covering
         int redSquaresNew = 0;
         for(int ro = 0; ro < blocks.length; ro++){
          for(int co = 0; co < blocks[ro].length; co++){
            redSquaresNew += blocks[ro][co];
          }
        }
        if(redSquaresNew < redSquaresOld){
          B.moveUp();
          for(int i = 0; i < 4; i++){
            blocks[B.location[i][0]][B.location[i][1]] = 1;
          }

        canMove = false;
        NewTetris.generateBlock(Pieces, blocks);
        canMove = true;
      }

   }

       counter = counter % 10000;

       screen.setCursorPosition(c, r);
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
             for(int i = 0; i < 4; i++){
               blocks[B.location[i][0]][B.location[i][1]] = 0;
             }
             B.moveRight();
             try{
             for(int i = 0; i < 4; i++){
               blocks[B.location[i][0]][B.location[i][1]] = 1;
             }
             } catch (ArrayIndexOutOfBoundsException e){
               B.moveLeft();
               for(int i = 0; i < 4; i++){
                 blocks[B.location[i][0]][B.location[i][1]] = 1;
               }
             }
            }


           break;

           case ArrowLeft:

           if(counter > -1){
             block B = Pieces.get(Pieces.size() - 1);
             for(int i = 0; i < 4; i++){
               blocks[B.location[i][0]][B.location[i][1]] = 0;

             }
             B.moveLeft();
             try{ for(int i = 0; i < 4; i++){
               blocks[B.location[i][0]][B.location[i][1]] = 1;
             }

             } catch(ArrayIndexOutOfBoundsException e){
             B.moveRight();
             for(int i = 0; i < 4; i++){
               blocks[B.location[i][0]][B.location[i][1]] = 1;
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
             if(counter > -1){
               block B = Pieces.get(Pieces.size() - 1);
               for(int i = 0; i < 4; i++){
                 blocks[B.location[i][0]][B.location[i][1]] = 0;

               }
               B.rotateLeft();
               for(int i = 0; i < 4; i++){
                 blocks[B.location[i][0]][B.location[i][1]] = 1;
               }
             }
           }
           if(key.getCharacter() == 'x'){
             if(counter > -1){
               block B = Pieces.get(Pieces.size() - 1);
               for(int i = 0; i < 4; i++){
                 blocks[B.location[i][0]][B.location[i][1]] = 0;

               }
               B.rotateRight();
               for(int i = 0; i < 4; i++){
                 blocks[B.location[i][0]][B.location[i][1]] = 1;
               }
             }
           }
         }
         }
       Thread.sleep(1000);
       System.exit(0);
     }
   }
