public class block{

  //dist variables are 2d arrays containing the locations of the other 3 blocks relative to one block
  //each of these varaibles are for different pieces
  private int[][] zDist = new int[][]{{-1,0},{1,0},{1,1}};
  private int[][] iDist = new int[][]{{0,1},{0,2},{0,3},{0,4}};
  private int[][] sDist = new int[][]{{1,0},{1,0},{-1,1}};
  private int[][] lDist = new int[][]{{0,1},{1,1},{2,1}};
  private int[][] jDist = new int[][]{{0,1},{-1,1},{-2,1}};
  private int[][] tDist = new int[][]{{0,1},{1,1},{-1,1}};
  //rightIdx is the index in direction thatright variable uses
  private int rightIdx = 1;
  //leftIdx is the index of direction that left variable uses
  private int leftIdx = 3;
  private int xcor, ycor;
  //left shows the direction if turnLeft happens
  //right shows the direction if turnRight happens
  //current is the current direction
  //piece stores the shape of the piece for later uses
  private String left,right, current, piece;
  private String[] direction = new String[]{"down", "right", "up", "left"};
  //location is a 2d array which contain the coordinates of each block
  public int[][] location = new int[4][2];

  /**constructor
    *@param x,y are the x-coordinates and y-coordinates of the block
    **/
  public block(int x, int y, String type){
    xcor = x;
    ycor = y;
    current = "down";
    left = direction[leftIdx];
    right = direction[rightIdx];
    createBlock(type);
  }
  //clears the piece by replacing all locations with null
  public void clear(){
    for (int i = 0; i < location.length; i++){
      for (int j = 0; j < location[i].length; j++){
        location[i][j] = null;
      }
    }
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
  //changes the current block location to the location of the right string
  //right string gets replaced by the next direction in the array
  //loops to the beginnning if at the end
  //after rotation, clear the piece and then redraw using new location
  public void turnRight(){
    current = right;
    if (rightIdx == 3){
      rightIdx = 0;
    }
    else{
      rightIdx++;
    }
    right = direction[rightIdx];
    clear();
    createBlock(piece);
  }
  //changes the current block location to the location of the left string
  //left string gets replaced by the previous direction in the array
  //loops to the end if at the beginning
  //after rotation, clear the piece and then redraw using new location
  public void rotateLeft(){
    current = left;
    if (leftIdx == 0){
      leftIdx = 3;
    }
    else{
      leftIdx++;
    }
    left = direction[leftIdx];
    clear();
    createBlock(piece);
  }

  /******************************************************************************
  test code
  ******************************************************************************/
  //this creates the different shaped blocks
  //the first element of the array is the first block
  //the next elements are determined using the position of the first block and adding to the xcor and ycor depending on direction
  public void createblock(String shape){
    if (shape.equals("o")){
      createOBlock();
    }
    else{
      int[][] whichAry = new int[4][2];
      if (shape.equals("z")){
        whichAry = zDist;
      }
      if (shape.equals("i")){
        whichAry = iDist;
      }
      if (shape.equals("s")){
        whichAry = sDist;
      }
      location[0][0] = xcor;
      location[0][1] = ycor;
      if (current.equals("down")){
        for (int i = 1; i < location.length; i++){
          location[i][0] = xcor + whichAry[i-1][0];
          location[i][1] = ycor + whichAry[i-1][1];
        }
      }
      if (current.equals("right")){
        for (int i = 1; i < location.length; i++){
          location[i][0] = xcor + whichAry[i-1][1];
          location[i][1] = ycor + whichAry[i-1][0];
        }
      }
      if (current.equals("up")){
        for (int i = 1; i < location.length; i++){
          location[i][0] = xcor + whichAry[i-1][0] * -1;
          location[i][1] = ycor + whichAry[i-1][1] * -1;
        }
      }
      if (current.equals("left")){
        for (int i = 1; i < location.length; i++){
          location[i][0] = xcor + whichAry[i-1][1] * -1;
          location[i][1] = ycor + whichAry[i-1][0] * -1;
        }
      }
    }
  }
  //since cube does not change shape no matter how you rotate it, the location is not as dependent on direction
  public void createOBlock(){
    location[0][0] = xcor;
    location[0][1] = ycor;
    location[1][0] = xcor+1;
    location[1][1] = ycor;
    location[2][0] = xcor;
    location[2][1] = ycor+1;
    location[3][0] = xcor+1;
    location[3][1] = ycor+1;
  }
}
