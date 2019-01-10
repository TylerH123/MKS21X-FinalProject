public class block{

  //rightIdx is the index in direction thatright variable uses
  //leftIdx is the index of direction that left variable uses
  private int xcor, ycor, leftIdx, rightIdx;
  //left shows the direction if turnLeft happens
  //right shows the direction if turnRight happens
  //current is the current direction
  private String left,right, current;
  private String[] direction = new String[]{"down", "right", "up", "left"};

  /**constructor
    *@param x,y are the x-coordinates and y-coordinates of the block
    **/
  public block(int x, int y){
    xcor = x;
    ycor = y;
    current = "down";
    rightIdx = 1;
    leftIdx = 3;
    left = direction[leftIdx];
    right = direction[rightIdx];
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
  public String currentDirec(){
    return current;
  }
  public void turnRight(){
    current = right;
    if (rightIdx == 3){
      rightIdx = 0;
    }
    else{
      rightIdx++;
    }
    right = direction[rightIdx];
  }
  public void rotateLeft(){
    current = right;
    if (leftIdx == 0){
      leftIdx = 3;
    }
    else{
      leftIdx++;
    }
    left = direction[leftIdx];
  }
}
