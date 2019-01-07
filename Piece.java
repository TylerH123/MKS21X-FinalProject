public class Piece{

  private int xcor, ycor;
  private boolean hasBlock;

  /**constructor
    *@param x,y are the x-coordinates and y-coordinates of the block
    **/
  public Piece(int x, int y){
    xcor = x;
    ycor = y;
  }
  //Implementing the bastic methods movdown getx and gety

  public int getX(){
    return xcor;
  }

  public int getY(){
    return ycor;
  }

//Basic ToString PieceShape
  public String toString(){
    String ans = "_\n";
   if(xcor == 0){
      ans += "| |";
    } else{
      ans += " |";
  }
  if(ycor == 0) ans += "\n_";
  return ans;
}
}
