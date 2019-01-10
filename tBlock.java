public class tBlock extends block{
  block a,b,c,d;
  /**constructor
    *@param maxHeight is the maximum hieght of the board
    *@param midWidth is half of the width of the board
    *creates the T-block piece using 4 blocks placed accordingly
  **/
  public tBlock(int maxHeight, int midWidth){
    a = new block(midWidth,maxHeight);
    b = new block(midWidth,maxHeight-1);
    c = new block(midWidth+1,maxHeight-1);
    d = new block(midWidth-1,maxHeight-1);
  }
  //makes all the blocks in the T-block piece move right
  public void moveRight(){
    a.moveRight();
    b.moveRight();
    c.moveRight();
    d.moveRight();
  }
  //makes all the blocks in the T-block piece move left
  public void moveLeft(){
    a.moveLeft();
    b.moveLeft();
    c.moveLeft();
    d.moveLeft();
  }
  //makes all the blocks in the T-block piece move down
  public void descend(){
    a.moveDown();
    b.moveDown();
    c.moveDown();
    d.moveDown();
  }
  //
  public void rotateLeft(){
  }
  public void rotateLeft(){
  }
}
