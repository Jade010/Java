package src;

/************************************************
 * Name: Jade Aidoghie; WSU ID: 11775092
 * Date: 04/11/2023
 * Course: CPT_S 132 Section 01, Spring 23
 * Assignment: HW11 src.Life - Controller
 * Level: N/A
 * Description: This project adds a controller, threads and mouse clicking events to the
 * GUI version of the game of life.
 * Grade Level: N/A
 ************************************************/


/**
 * Class to represent src.Life game board
 * @author Jade Aidoghie
 * @version 4/12/2023
 */
public class Life {
    // fields
    public final static String ALIVE = "O";
    public final static String DEAD = ".";
    // 2D array current generation
    private String [][] currentGen;
    // 2D array next generation
    private String [][] nextGen;


    /**
     * Default Constructor
     */
    public Life() {
        // 19x19 game board with current generation
        currentGen = new String[19][19];

        // loops through array initializing the state of all cells as dead
        for (int i = 0; i < currentGen.length; i++){
            for (int j = 0; j < currentGen[i].length; j++){
                currentGen[i][j] = DEAD;
            }
        }

        // the patterns that the board starts off as
        // traffic light pattern
        currentGen[6][1] = ALIVE;
        currentGen[6][2] = ALIVE;
        currentGen[6][3] = ALIVE;
        currentGen[6][7] = ALIVE;
        currentGen[6][8] = ALIVE;
        currentGen[6][9] = ALIVE;
        currentGen[2][5] = ALIVE;
        currentGen[3][5] = ALIVE;
        currentGen[4][5] = ALIVE;
        currentGen[8][5] = ALIVE;
        currentGen[9][5] = ALIVE;
        currentGen[10][5] = ALIVE;

        // beehive pattern
        currentGen[12][9] = ALIVE;
        currentGen[12][10] = ALIVE;
        currentGen[13][8] = ALIVE;
        currentGen[13][11] = ALIVE;
        currentGen[14][9] = ALIVE;
        currentGen[14][10] = ALIVE;

        // blinker pattern
        currentGen[17][11] = ALIVE;
        currentGen[17][12] = ALIVE;
        currentGen[17][13] = ALIVE;

        // glider pattern
        currentGen[15][2] = ALIVE;
        currentGen[16][3] = ALIVE;
        currentGen[16][4] = ALIVE;
        currentGen[15][4] = ALIVE;
        currentGen[14][4] = ALIVE;

        // programmed to die 1
        currentGen[8][16] = ALIVE;
        currentGen[9][16] = ALIVE;
        currentGen[10][14] = ALIVE;
        currentGen[10][15] = ALIVE;

        // 19x19 game board with next generation
        nextGen = new String[19][19];

    }


    /**
     * getData method that returns a String of arrays
     * @return currentGen
     */
    public String [][] getData(){
        return currentGen;
    }

    /**
     * toString() method for the string representation of the board
     * @return the gameboard as a String
     */
    public String toString() {
        String gameboard = "";

        // loops through array storing array board as a string with spacing between cells
        for (int i = 0; i < currentGen.length; i++) {
            for (int j = 0; j < currentGen[i].length; j++) {
                gameboard += currentGen[i][j] + " ";
            }
            // inserts a newline in the text for the rows
            gameboard += "\n";
        }

        return gameboard;
    }

    // helper methods

    /**
     * nextGeneration method to create the next generation board
     */
    public void nextGeneration() {
        // loop through array
        for (int i = 0; i < currentGen.length; i++) {
            for (int j = 0; j < currentGen[i].length; j++) {
                // creating next generation by checking previous generations living cell status
                nextGen[i][j] = isCellAlive(getNeighbors(i, j), currentGen[i][j]);
            }
        }
        // loop to store next generation into current generation
        for (int i = 0; i < nextGen.length; i++) {
            for (int j = 0; j < nextGen[i].length; j++) {
                currentGen[i][j] = nextGen[i][j];
            }
        }
    }

    /**
     * isCellAlive method to check which cells survive to the
     * next generation.
     * @param livingNeighbors the number of living neighbor cells
     * @param currAlive the cells that are currently alive
     * @return true/ALIVE if the cell will live to the next generation
     * @return false/DEAD if the cell does not survive to the next generation
     */
    private String isCellAlive(int livingNeighbors, String currAlive) {
        // check if the current cell is alive
        if (currAlive == ALIVE) {
            // check if the cell has 2 or 3 live neighbors = survival
            if (livingNeighbors == 2 || livingNeighbors == 3) {
                return ALIVE;
            } else {
                // live cell has < 2 neighbors = dies from loneliness/ has > 3 neighbors = dies from overcrowding
                return DEAD;
            }
        } else {
            // if dead cell has 3 live neighbors = birth
            if (livingNeighbors == 3){
                return ALIVE;
            }
        }
        // none of these cases are true/ cell remains dead
        return DEAD;
    }


    /**
     * aliveNeighbors method that evaluates the state of the neighbor cell
     * @param row the row on the board
     * @param col the column on the board
     * @return true if cell is alive, false otherwise.
     */
    private boolean aliveNeighbors(int row, int col) {
        // if cell is within the bounds of the board
        if (row < 19 && row >= 0 && col < 19 && col >= 0) {
            // if current generations cell is alive return true
            if (currentGen[row][col] == ALIVE) {
                return true;
            }
        }
        return false;
    }

    /**
     * get Neighbors method that gets number of neighbors for
     * each row and column
     * @param row the row in the matrix
     * @param col the column in the matrix
     * @return neighbor cells
     */
    private int getNeighbors(int row, int col) {
        int neighbors = 0;

        // each cell has 8 neighbors
        // check if statements alternating through the columns and rows around the cell
        // to see if neighbor is alive
        if (aliveNeighbors(row - 1, col - 1)) {
            neighbors++;
        }
        if (aliveNeighbors(row - 1, col)) {
            neighbors++;
        }
        if (aliveNeighbors(row - 1, col + 1)) {
            neighbors++;
        }
        if (aliveNeighbors(row, col - 1)) {
            neighbors++;
        }
        if (aliveNeighbors(row, col + 1)) {
            neighbors++;
        }
        if (aliveNeighbors(row + 1, col - 1)) {
            neighbors++;
        }
        if (aliveNeighbors(row + 1, col)) {
            neighbors++;
        }
        if (aliveNeighbors(row + 1, col + 1)) {
            neighbors++;
        }

        return neighbors;
    }

    /**
     * the application method
     * @param args the command-line argument
     */
    public static void main(String[] args){
        // instantiates the src.Life game
        Life gameboard = new Life();

        // prints out the initial gameboard
        System.out.println(gameboard);

        // loop to generate 7 generations
       for(int i = 0; i < 7; i++) {
            gameboard.nextGeneration();
            System.out.println("\n");
            System.out.println(gameboard);
       }
    }

    public void toggleCellState(int row, int col) {
        if (currentGen[row][col] == ALIVE) {
            currentGen[row][col] = DEAD;
        } else {
            currentGen[row][col] = ALIVE;
        }
    }
}

