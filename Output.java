package src;

import javax.swing.*;
import java.awt.*;

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
 * Class to represent board GUI
 * @author Jade Aidoghie
 * @version 4/12/2023
 */
public class Output extends JPanel {
    // field
    private Cell [][] cellArray = new Cell[19][19];

    /**
     * Overloaded Constructor
     * @param data String array object
     * @param game GOL object
     */
    public Output(String[][] data, GameOfLife game) {
        // creates JPanel with gridLayout
        super(new GridLayout(19, 19));
        // sets size of JPanel
        this.setSize(50 * 19, 50 * 19);
        // sets the board color to black
        this.setBackground(Color.black);

        // loops through array adding cells to panel
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                // cell object with coordinates and a reference to the game object
                cellArray[i][j] = new Cell(i, j, game);
                // sets the initial state of the cell based on the data array
                cellArray[i][j].setCurrent(data[i][j]);
                // add cells to panel
                this.add(cellArray[i][j]);
            }
        }
        setVisible(true);
    }

    /**
     * cellColor method that sets the current cell array color
     * based on the array that is passed through.
     * @param current the string array
     */
    public void cellColor(String [][] current) {
        // loops through array
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                // the current array that is being passed through determines the cell color/if it is dead or alive
                cellArray[i][j].setCurrent(current[i][j]);
            }
        }
        repaint();
    }

}
