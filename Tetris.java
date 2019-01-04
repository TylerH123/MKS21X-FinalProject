public class Tetris extends Piece{
  private int height, width;
  private boolean[][] blocks;
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
  }
}
