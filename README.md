# Tetris  

## How to Use

**To compile:**   
``` javac -cp lanterna.jar:. NewTetris.java
```
**To run:**
``` java -cp lanterna.jar:. NewTetris
```
**To use:**
``` ←,→ keys to move the piece left and right
```
```z to rotate left
```
```x to rotate right
```
```↓ key to speed up the rate at which the piece drops
```

## Developmental Log

### Tyler
- 1/3/19 : created repo, block class, fourBlock class, piece class, and tetris class
- 1/4/19 : worked on block class and fourBlock class. Added the methods of block and fourBlock
- 1/5/19 : tried to do block toString, couldn't get it to work
- 1/7/19 : finished cube class, started on line class, got stuck on rotation methods, deleted piece class because it was useless. Also, deleted toString for block because toString should only be in tetris class.
- 1/8/19 : finished adding all the piece classes, still need to work on the rotation algorithm
- 1/9/19 : changed how the shapes are created. shapes are now created using direction and relative locations. also added
         clear method
- 1/10/19 : worked on a border check method to make sure pieces don't go off the board. Also, deleted all the piece classes because I made a new way to create the pieces.

### Jude
- 1/4/19 : worked on block methods
- 1/5/19 : earned about lanterna and its methods
- 1/7/19 : made the board for tetris
- 1/8/19 : Made a new terminal window with score variable and message when the program runs, need to print blocks on board
- 1/9/19 : added more methods to the new terminal window and added a switch statement
