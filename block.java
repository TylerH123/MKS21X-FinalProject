import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.TerminalSize;

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
    xcor -= 1;
  }

  public int getX(){
    return xcor;
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
}
