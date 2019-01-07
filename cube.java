public class cube implements fourBlock {

  /**constructor
    *@param block a,b,c,d are used to create the cube
    **/
  public cube(int maxHeight, int midWidth){
    block a = new block(midWidth,maxHeight);
    block b = new block(midWidth+1,maxHeight-1);
    block c = new block(midWidth,maxHeight-1);
    block d = new block(midWidth+1,maxHeight);
  }
  public void moveRight(){};
  public void moveLeft(){};
  public void descend(){};
  public void rotateRight(){};
  public void rotateLeft(){};
  public String toString(){
    return "";
  };

}
