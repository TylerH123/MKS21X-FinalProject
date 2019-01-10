public class block{

  private int xcor, ycor;

  /**constructor
    *@param x,y are the x-coordinates and y-coordinates of the block
    **/
  public block(int x, int y){
    xcor = x;
    ycor = y;
  }
  //Implementing the bastic methods movedown getx and gety
  public void moveDown(){
    ycor--;
  }
  //return xcor
  public int getX(){
    return xcor;
  }
  //return ycor
  public int getY(){
    return ycor;
  }
  //set the ycor
  public void setY(int g){
    ycor = g;
  }
  //set xcor
  public void setX(int g){
    xcor = g;
  }
  //add to the xcor to move right
  public void moveRight(){
    xcor++;
  }
  //subtract from the xcor to move left
  public void moveLeft(){
    xcor--;
  }
}
