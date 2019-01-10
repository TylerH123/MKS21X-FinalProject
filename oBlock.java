public class oBlock extends block{

    block a;
    int[][] cube = new int[4][2];
  /**constructor
    *@param maxHeight is the maximum hieght of the board
    *@param midWidth is half of the width of the board
    *creates a block. from there another will complete the shape
    **/
  public oBlock(int maxHeight, int midWidth){
    a = new block(midWidth,maxHeight);
    drawCube();
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
  public void drawCube(){

  }
  public int[][] printCube(){
    return cube;
  }
  //since a cube retains its shape after rotation, the next 2 methods will do nothing
  public void rotateRight(){
  }
  public void rotateLeft(){
  }
}
