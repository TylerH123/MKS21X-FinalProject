import java.util.*;
public class Tetris{
  private int height, width;
  private boolean[][] pieces;
  private ArrayList blocks;
  //This can be a 1D version of a 2D array of all the blocks. The coordinates is x = index% width y = index/width
  private int score;
  private boolean running = true
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
  }

    //Note that these coordinates are (row column), which are (y, x), not (x, y)






  public static void main(String args[]){
    Tetris ans = new Tetris();


  }
}
