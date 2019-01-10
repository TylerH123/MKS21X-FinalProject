public class oBlock extends block{

    private block a,b,c,d;
  /**constructor
    *@param maxHeight is the maximum hieght of the board
    *@param midWidth is half of the width of the board
    *creates a block. from there another will complete the shape
    **/
  public oBlock(int maxHeight, int midWidth){
    block a = new block(midWidth, maxHeight);
    block b = new block(midWidth+1, maxHeight);
    block c = new block(midWidth+1, maxHeight+1);
    block d = new block(midWidth, maxHeight+1);
  }
  //makes all the blocks in the O-shaped piece move right
  public void moveRight(){
    a.moveRight();
  }
  //makes all the blocks in the O-shaped piece move left
  public void moveLeft(){
    a.moveLeft();
  }
  //makes all the blocks in the O-shaped piece move down
  public void descend(){
    a.moveDown();
  }
  //since a cube retains its shape after rotation, the next 2 methods will do nothing
  public void rotateRight(){
  }
  public void rotateLeft(){
  }
}
