import java.util.Arrays;
import java.util.Objects;

public class Maze {
  private final String [][] maze = {
    {"=", "=", "=", "=", "=", "=", "=", "=", "="},
    {"=", "X", " ", " ", "=", "=", " ", "=", "="},
    {"=", " ", "=", " ", "=", "=", " ", "=", "="},
    {"=", " ", "=", " ", "=", " ", " ", " ", "="},
    {"=", " ", "=", " ", " ", " ", "=", " ", "="},
    {"=", " ", " ", " ", "=", "=", "=", " ", "="},
    {"=", "=", "=", " ", "=", "=", "=", " ", "="},
    {"=", "=", "=", " ", "=", " ", " ", " ", "="},
    {"=", "=", " ", " ", "=", " ", "=", "=", "="},
    {"=", "=", " ", "=", " ", " ", " ", "=", "="},
    {"=", "=", " ", " ", "=", "=", " ", "=", "="},
    {"=", "=", "=", " ", "=", "=", " ", "=", "="},
    {"=", "=", "=", "=", "=", " ", " ", " ", "="},
    {"=", "=", "=", "Y", " ", " ", "=", " ", "="},
    {"=", "=", "=", "=", "=", "=", "=", "=", "="},
  };

  ArrayStack line = new ArrayStack(1,0);
  ArrayStack column = new ArrayStack(1,0);
  ArrayStack lastPosition = new ArrayStack(1,0);
  
  //While not finding the exit print the position
  public void walkMaze() {
    inicialPosition();
    while (!findExit()) {
      printPosition();
      System.out.print("Line: " + line + "Column: " + column);
      printMaze();
    }

  //Walk up
  if ((maze[line.lastPosition() - 1][column.lastPosition()] == " ") || (maze[line.lastPosition() - 1][column.lastPosition()] == "Y")) {
    var printPosition = maze[line.lastPosition() - 1][column.lastPosition()];
    line.push(line.lastPosition() - 1);
    lastPosition.push(0);
  }
    
  //Go right
  else if ((maze[line.lastPosition()][column.lastPosition() + 1] == " ") || (maze[line.lastPosition()][column.lastPosition() + 1] == "Y")) {
  var printPosition = maze[line.lastPosition()][column.lastPosition() + 1];
  column.push(column.lastPosition() + 1);
  lastPosition.push(1);
  }
    
  //Walk down
  else if ((maze[line.lastPosition() + 1][column.lastPosition()] == " ") || (maze[line.lastPosition() + 1][column.lastPosition()] == "Y")) {
    var printPosition = maze[line.lastPosition() + 1][column.lastPosition()];
    line.push(line.lastPosition() + 1);
    lastPosition.push(2);
  }
    
  //Go left
  else if ((maze[line.lastPosition()][column.lastPosition() - 1] == " ") || (maze[line.lastPosition()][column.lastPosition() - 1] == "B")) {
    var printPosition = maze[line.lastPosition()][column.lastPosition() - 1];
    column.push(column.lastPosition() - 1);
    lastPosition.push(3);
  }
    
  //Unstack function
  else {
    switch (lastPosition.lastPosition()) {
      //Back down
      case 0:
        line.pop();
        lastPosition.pop();
        break;
      //Go back left
      case 1:
        lastPosition.pop();
        column.pop();
        break;
      //Back up
      case 2:
        lastPosition.pop();
        line.pop();
        break;
      //Go back right
      case 3:
        column.pop();
        lastPosition.pop();
        break;
      }
    }
  }
}
//If found the exit
public boolean findExit() {
  if (Objects.equals(maze[line.lastPosition()][column.lastPosition()], "Y")) {
    System.out.println("Exit found");
    return true;
  }
  //If didn't find the exit
  else if (line.isEmpty() || column.isEmpty()) {
    System.out.println("Exit not found");
    return true;
  }
  return false;
}

//Inicial position = [1][1]
public void inicialPosition() {
  line.push(1);
  column.push(1);
  lastPosition.push(0);
  System.out.println("Starting the Maze");
}

//Print a trail through the maze
public void printPosition() {
  maze[line.lastPosition()][column.lastPosition()] = "@";
}

//Print the maze
public void printMaze() {
  for (String[] strings : maze) {
    System.out.println(Arrays.toString(strings));
  }
}
