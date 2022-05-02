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

  public void walkMaze() {

    inicialPosition();
    while (!findExit()) {
      printPosition();
      System.out.print("Line: " + line + "Column: " + column);
      printMaze();
    }

  if ((maze[line.lastPosition() - 1][column.lastPosition()] == " ") || (maze[line.lastPosition() - 1][column.lastPosition()] == "Y")) {
    var printPosition = maze[line.lastPosition() - 1][column.lastPosition()];
    line.push(line.lastPosition() - 1);
    lastPosition.push(0);
  }

  else if ((maze[line.lastPosition()][column.lastPosition() + 1] == " ") || (maze[line.lastPosition()][column.lastPosition() + 1] == "Y")) {
  var printPosition = maze[line.lastPosition()][column.lastPosition() + 1];
  column.push(column.lastPosition() + 1);
  lastPosition.push(1);
  }

  else if ((maze[line.lastPosition() + 1][column.lastPosition()] == " ") || (maze[line.lastPosition() + 1][column.lastPosition()] == "Y")) {
    var printPosition = maze[line.lastPosition() + 1][column.lastPosition()];
    line.push(line.lastPosition() + 1);
    lastPosition.push(2);
  }

  else if ((maze[line.lastPosition()][column.lastPosition() - 1] == " ") || (maze[line.lastPosition()][column.lastPosition() - 1] == "B")) {
    var printPosition = maze[line.lastPosition()][column.lastPosition() - 1];
    column.push(column.lastPosition() - 1);
    lastPosition.push(3);
  }

  else {
    switch (lastPosition.lastPosition()) {

      case 0:
        line.pop();
        lastPosition.pop();
        break;

      case 1:
        lastPosition.pop();
        column.pop();
        break;

      case 2:
        lastPosition.pop();
        line.pop();
        break;

      case 3:
        column.pop();
        lastPosition.pop();
        break;
      }
    }
  }
}

public boolean findExit() {
  if (Objects.equals(maze[line.lastPosition()][column.lastPosition()], "Y")) {
    System.out.println("Exit found");
    return true;
  }
  else if (line.isEmpty() || column.isEmpty()) {
    System.out.println("Exit not found");
    return true;
  }
  return false;
}

public void inicialPosition() {
  line.push(1);
  column.push(1);
  lastPosition.push(0);
  System.out.println("Starting the Maze");
}

public void printPosition() {
  maze[line.lastPosition()][column.lastPosition()] = "@";
}

public void printMaze() {
  for (String[] strings : maze) {
    System.out.println(Arrays.toString(strings));
  }
}