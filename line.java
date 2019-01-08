public class line implements fourBlock{
  block a,b,c,d;
  /**constructor
    *@param maxHeight is the maximum hieght of the board
    *@param midWidth is half of the width of the board
    *creates the line piece using 4 blocks placed in a line
  **/
  public line(int maxHeight, int midWidth){
    a = new block(midWidth,maxHeight);
    b = new block(midWidth,maxHeight-1);
    c = new block(midWidth,maxHeight-2);
    d = new block(midWidth,maxHeight-3);
  }
  //makes all the blocks in the cube move right
  public void moveRight(){
    a.moveRight();
    b.moveRight();
    c.moveRight();
    d.moveRight();
  }
  //makes all the blocks in the cube move left
  public void moveLeft(){
    a.moveLeft();
    b.moveLeft();
    c.moveLeft();
    d.moveLeft();
  }
  //makes all the blocks in the cube move down
  public void descend(){
    a.moveDown();
    b.moveDown();
    c.moveDown();
    d.moveDown();
  }
  //since a cube retains its shape after rotation, the next 2 methods will do nothing
  public void rotateRight(){
  }
  public void rotateLeft(){
  }
}