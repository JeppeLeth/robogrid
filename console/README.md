# Example of usage
Run the file **com/jleth/projects/robogrid/console/Main.java**
or via commandline downloading the [JAR-file](console-all-1.0.jar)
```
...\Download>java -jar console-all-1.0.jar
```

### Console input/output

```
============================
|    ROBOT GRID CONSOLE    |
============================

============================
|        Welcome           |
============================

============================
|  How do you prefer to    |
|  enter values in the     |
|  console ?               |
|   S. One value per line  |
|   B. More values per line|
============================

 Enter 'B' or 'S' :  B
============================
| First we need to setup   |
| The size of the grid     |
============================
 Enter number of columns and rows (e.g. '8 9'):  10 10

============================
| Now set the initial      |
| values for start point   |
============================
 Enter X and Y coordinate and direction
 X coordinate (0-9) - Y coordinate (0-9) - Direction (N, S, E or W): 
 Please enter values (e.g. '1 2 W'):  2 3 E

============================
|  Now you are ready  !!!  |
============================
| Command options:         |
|        R. Turn right     |
|        L. Turn left      |
|        F. Move forward   |
============================
 Enter series of commands:  RRFLRLFLFF
 The current position is: 3 4 E
```