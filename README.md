# Tetris  

## How to Use

**To compile:**   
```
bash compile.sh
```
**To run:**
```
bash run.sh
```
**To use:**
```
←,→ keys to move the piece left and right
```
```
z to rotate left
```
```
x to rotate right
```
```
↓ key to speed up the rate at which the pieces fall
```

## Developmental Log

### Tyler
- 1/3/19 : created repo, block class, fourBlock class, piece class, and tetris class
- 1/4/19 : worked on block class and fourBlock class. Added the methods of block and fourBlock. Made fourBlock abstract because the different tetris pieces were going to be extend the fourBlock class.
- 1/5/19 : tried to do block toString, couldn't get it to work because tried to print out a terminal.
- 1/7/19 : finished cube class, started on line class, got stuck on rotation methods, deleted piece class because it was useless. Also, deleted toString for block because toString should only be in tetris class.
- 1/8/19 : finished adding all the piece classes, still need to work on the rotation algorithm
- 1/9/19 : changed how the shapes are created. shapes are now created using direction and relative locations. also added
clear method. using the new way to create shapes helped solve the rotation problem. fourBlock class was also deleted since it played no evident role in our code.
- 1/10/19 : worked on a border check method to make sure pieces don't go off the board. Also, deleted all the piece classes because I made a new way to create the pieces. However, border check does not belong in block class, and needs to be moved into NewTetris class. worked on styling readme. also added how to use code in readme.
- 1/13/19: added a rotational border check. to do this I added 2 new arrays in to block which contained the locations if it were to rotate. also worked on debugging NewTetris. added ways for blocks to fall and move left and right. added a way to rotate blocks.
- 1/14/19: fixed indentation. got rid of the bug that created an extra line at the bottom of the board.
- 1/15/19: added different colors to each of the pieces. made code more modular. got rid of move left and right bugs. added check for rotation.
- 1/16/19: added a border around the board. also fixed rotation check.
- 1/18/19: fixed a rotation bug that made pieces rotate incorrectly. fixed clear rows so that multiple rows can be cleared at once. clearRows method is not a time efficient code. we need to find a better way to speed up the way clearRows works.

### Jude
- 1/4/19 : worked on block methods
- 1/5/19 : learned about lanterna and its methods, like get() and set() as well as moveDown
- 1/7/19 : made the board for tetris
- 1/8/19 : Made a new terminal window with score variable and message when the program runs, need to print blocks on board
- 1/9/19 : added more methods to the new terminal window and added a switch statement
-1/13/19: I updated the entire newBoard as well as made it possible to print the array on the scrren. Then I made the Pieces Arraylist and worked on the switch statement for moving pieces left and right. As a result some bugs were caused, so I had to make a checker. I did that with a try catch method, and used the same thing to check the Rotitional errors that Tyler had made
-1/15/19: I created a gameover method for when the user stacks tetris too high as well as worked on fixing up some of the bugs with rotation. I made a new algorithm for moveDown checker that doesn't destroy my computer by only seeing if the lower blocks have a block below them instead of counting all the blocks in the whole board and seeing if that got smaller.
-1/19/19: I fixed game over as well as debugged a lot of the rotations that came with changing the algorithms from the previous few days
-1/21/19: I fixed score to finalize the game and made a gameOver screen
