public class block{

  //dist variables are 2d arrays containing the locations of the other 3 blocks relative to one block
  //each of these varaibles are for different pieces
  //first value is the change in x-cor, second value is the change in y-cor
  private int[][] zDist = new int[][]{{0,0},{0,1},{1,1},{1,2}};
  private int[][] iDist = new int[][]{{0,0},{1,0},{2,0},{3,0}};
  private int[][] sDist = new int[][]{{0,0},{0,-1},{1,-1},{1,-2}};
  private int[][] lDist = new int[][]{{0,0},{1,0},{1,1},{1,2}};
  private int[][] jDist = new int[][]{{0,0},{1,0},{1,-1},{1,-2}};
  private int[][] tDist = new int[][]{{0,0},{1,0},{1,1},{1,-1}};


  //rightIdx is the index in direction thatright variable uses
  private int rightIdx = 3;
  //leftIdx is the index of direction that left variable uses
  private int leftIdx = 1;
  private int xcor, ycor;
  //left shows the direction if turnLeft happens
  //right shows the direction if turnRight happens
  //current is the current direction
  //piece stores the shape of the piece for later uses
  private String left, right, current, piece;
  private String[] direction = new String[]{"down", "right", "up", "left"};
  //location is a 2d array which contain the coordinates of each block
  public int[][] location = new int[4][2];
  //locationRight is a 2d array which contains the coordinates of each block if it were to rotate right
  public int[][] locationRight = new int[4][2];
  //locationLeft is a 2d array which contains the coordinates of each block if it were to rotate right
  public int[][] locationLeft = new int[4][2];

  /**constructor
    *@param x,y are the x-coordinates and y-coordinates of the block
    *@param type is the type of piece it will become
    *@param maxWidth,maxHeight are the maximum dimensions of the board
    **/
  public block(int x, int y, String type){
    xcor = x;
    ycor = y;
    current = "down";
    left = direction[leftIdx];
    right = direction[rightIdx];
    piece = type;
    createBlock(piece);
  }
  //Implementing the bastic methods movedown getx and gety
  public void moveDown(){
    ycor++;
    createBlock(piece);
  }
  public void moveUp(){
    ycor--;
    createBlock(piece);
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
    createBlock(piece);
  }
  //subtract from the xcor to move left
  public void moveLeft(){
    xcor--;
    createBlock(piece);
  }
  //returns the current direction
  public String currentDirec(){
    return current;
  }
  //changes the current block location to the location of the right string
  //right string gets replaced by the next direction in the array
  //loops to the beginnning if at the end
  //after rotation, clear the piece and then redraw using new location
  //param maxWidth is the maximum width of the board
  //param maxHeight is the maximum height of the board
  public void rotateRight(){
      current = right;
      if (rightIdx == 0){
        rightIdx = 3;
      }
      else{
        rightIdx--;
      }
      right = direction[rightIdx];
      createBlock(piece);
  }
  //changes the current block location to the location of the left string
  //left string gets replaced by the previous direction in the array
  //loops to the end if at the beginning
  //after rotation, clear the piece and then redraw using new location
  public void rotateLeft(){
      current = left;
      if (leftIdx == 3){
        leftIdx = 0;
      }
      else{
        leftIdx++;
      }
      left = direction[leftIdx];
      createBlock(piece);
  }

  /******************************************************************************
  test code
  ******************************************************************************/
  //this creates the different shaped blocks
  //the first element of the array is the first block
  //the next elements are determined using the position of the first block and adding to the xcor and ycor depending on direction
  public void createBlock(String shape){
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
      if (shape.equals("t")){
        whichAry = tDist;
      }
      if (shape.equals("l")){
        whichAry = lDist;
      }
      if (shape.equals("j")){
        whichAry = jDist;
      }
      fillInLocation(left, whichAry, "left");
      fillInLocation(right, whichAry, "right");
      fillInLocation(current, whichAry, "current");
    }
  }
  //since cube does not change shape no matter how you rotate it, the location is not as dependent on direction
  public void createOBlock(){
    location[0][1] = xcor;
    location[0][0] = ycor;
    location[1][1] = xcor+1;
    location[1][0] = ycor;
    location[2][1] = xcor;
    location[2][0] = ycor+1;
    location[3][1] = xcor+1;
    location[3][0] = ycor+1;
  }
  //fills in location arrays
  //dir is the direction in which the block is facing
  //whichAry is the array of the piece that is being created
  //whichLoc is the location array that is being filled
  public void fillInLocation(String dir, int[][] whichAry, String whichLoc){
    int[][] locArray = new int[4][2];
    if (whichLoc.equals("left")){
      locArray = locationLeft;
    }
    if (whichLoc.equals("right")){
      locArray = locationRight;
    }
    if (whichLoc.equals("current")){
      locArray = location;
    }
    if (dir.equals("down")){
      for (int i = 0; i < location.length; i++){
        locArray[i][1] = xcor + whichAry[i][1];
        locArray[i][0] = ycor + whichAry[i][0];
      }
    }
    if (dir.equals("right")){
      for (int i = 0; i < location.length; i++){
        locArray[i][1] = xcor + whichAry[i][0];
        locArray[i][0] = ycor + whichAry[i][1] * -1;
      }
    }
    if (dir.equals("up")){
      for (int i = 0; i < location.length; i++){
        locArray[i][1] = xcor + whichAry[i][1] * -1;
        locArray[i][0] = ycor + whichAry[i][0] * -1;
      }
    }
    if (dir.equals("left")){
      for (int i = 0; i < location.length; i++){
        locArray[i][1] = xcor + whichAry[i][0] * -1;
        locArray[i][0] = ycor + whichAry[i][1];
      }
    }
  }
}
