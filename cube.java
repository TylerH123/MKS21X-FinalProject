public class cube implements fourBlock {

    block a,b,c,d;
  /**constructor
    *@param maxHeight is the maximum hieght of the board
    *@param midWidth is half of the width of the board
    *creates the cube piece using 4 blocks placed accordingly
    **/
  public cube(int maxHeight, int midWidth){
    a = new block(midWidth,maxHeight);
    b = new block(midWidth+1,maxHeight-1);
    c = new block(midWidth,maxHeight-1);
    d = new block(midWidth+1,maxHeight);
  }
  public void moveRight(){
    a.
  }
  public void moveLeft(){}
  public void descend(){}
  public void rotateRight(){}
  public void rotateLeft(){}
}
