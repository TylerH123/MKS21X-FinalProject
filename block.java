public class Block{

  private int xcor, ycor;

  /**constructor
    *@param x,y are the x-coordinates and y-coordinates of the block
    **/
  public Block(int x, int y){
    xcor = x;
    ycor = y;
  }
  //Implementing the bastic methods movdown getx and gety
  public void moveDown(){
    xcor -= 1;
  }

  public int getX(){
    return xcor
  }

  public int getY(){
    return ycor;
  }

  
}
