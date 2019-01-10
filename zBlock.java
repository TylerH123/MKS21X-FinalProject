public class zBlock extends block{
  block a,b,c,d;
  //dist is a 2d array containing the locations of block b,c,d respectively relative to block a
  private int[][] dist = new int[][]{{-1,-1},{-1,0},{0,1}};
  /**constructor
    *@param maxHeight is the maximum hieght of the board
    *@param midWidth is half of the width of the board
    *creates the Z-block piece using 4 blocks placed accordingly
  **/
  public zBlock(int maxHeight, int midWidth){
    a = new block(midWidth,maxHeight);
  }
  //makes all the blocks in the Z-block piece move right
  public void moveRight(){
    a.moveRight();
    b.moveRight();
    c.moveRight();
    d.moveRight();
  }
  //makes all the blocks in the Z-block piece move left
  public void moveLeft(){
    a.moveLeft();
    b.moveLeft();
    c.moveLeft();
    d.moveLeft();
  }
  //makes all the blocks in the Z-block piece move down
  public void descend(){
    a.moveDown();
    b.moveDown();
    c.moveDown();
    d.moveDown();
  }
  public void createZBlock(){
     
  }
  //
  public void rotateLeft(){
  }
  public void rotateLeft(){
  }
}
