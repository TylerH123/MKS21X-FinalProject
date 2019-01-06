public class Block{

  private int xcor, ycor;

  /**constructor
    *@param x,y are the x-coordinates and y-coordinates of the block
    **/
  public Block(int x, int y){
    xcor = x;
    ycor = y;
  }
  //Implementing the bastic methods movedown getx and gety
  public void moveDown(){
    xcor -= 1;
  }

  public int getX(){
    return xcor
  }

  public int getY(){
    return ycor;
  }

  public int setY(int g){
    int storage = ycor;
    ycor = g;
    return storage;
  }

  public int setX(int g){
    int storage = xcor;
    xcor = g;
    return storage;
  }
//Basic ToString BlockShape
  public String toString(){
    String ans = "_\n";
   if(x == 0){
      ans += "| |";
    } else{
      ans += " |";
  }
  if(y == 0) ans += "\n_";
  return ans;
}
