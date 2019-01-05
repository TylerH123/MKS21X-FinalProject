import java.util;
public class Tetris{
  private int height, width;
  private boolean[][] pieces;
  private ArrayList blocks;
  //This can be a 1D version of a 2D array of all the blocks. The coordinates is x = index% width y = index/width
  private int score;
  //Blocks is an array of all the coordinates in the tetris board, each index in the 2d array representaing
  //a coordinate. False means there is no block on the piece while
  public Tetris(){
    height = 24;
    width = 10;
    blocks = new boolean[height][width];
    //at the default the board has no blocks
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j ++){
        boolean[i][j] = false;
      }
    }

    public Block blockAt(x, y){
      if(blocks[x][y])
    }
  }

  public String toString(){
    String ans = "";
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j ++){
        if(boolean[i][j]){
        ans += blocks.get(i*width + j);
      } else {
        ans += piece(j, i);
      }
      }

    }
  }
}
