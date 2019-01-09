public class Lblock implements fourBlock{
  block a,b,c,d;
  /**constructor
    *@param maxHeight is the maximum hieght of the board
    *@param midWidth is half of the width of the board
    *creates the L-shaped piece using 4 blocks placed accordingly
  **/
  public Lblock(int maxHeight, int midWidth){
    a = new block(midWidth,maxHeight);
    b = new block(midWidth,maxHeight-1);
    c = new block(midWidth-1,maxHeight-1);
    d = new block(midWidth-2,maxHeight-1);
  }
  //makes all the blocks in the L-shaped piece move right
  public void moveRight(){
    a.moveRight();
    b.moveRight();
    c.moveRight();
    d.moveRight();
  }
  //makes all the blocks in the L-shaped piece move left
  public void moveLeft(){
    a.moveLeft();
    b.moveLeft();
    c.moveLeft();
    d.moveLeft();
  }
  //makes all the blocks in the L-shaped piece move down
  public void descend(){
    a.moveDown();
    b.moveDown();
    c.moveDown();
    d.moveDown();
  }
  //
  public void rotateRight(){
  }
  public void rotateLeft(){
  }
}
