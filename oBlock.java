public class oBlock extends block{

    private block a,b,c,d;
    //dist is a 2-D array containing locations of blocks b,c,d respectively relative to block a
    //first value is the direction in x-axis and how many blocks to travel
    //second value is the direction in the y-axis and how many blocks to move
    private int[][] dist = new dist[][]{{0,1},{1,0},{1,1}};
    public int[][] cube;
  /**constructor
    *@param maxHeight is the maximum hieght of the board
    *@param midWidth is half of the width of the board
    *creates a block. from there another will complete the shape
    **/
  public oBlock(int maxHeight, int midWidth){
    drawCube(midWidth, maxHeight);
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
  public void drawCube(int x, int y){
    cube = new int[][]{{x,y},{x+1,y+1},{x,y+1},{x+1,y}};
  }
  //since a cube retains its shape after rotation, the next 2 methods will do nothing
  public void rotateRight(){
  }
  public void rotateLeft(){
  }
}
