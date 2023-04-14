package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
 * src.Cell class that extends JComponent to draw the
 * individual cells of the game board.
 * @author Jade Aidoghie
 * @version 4/12/2023
 */
public class Cell extends JComponent {
    // field
    private Color current;
    private int row;
    private int col;
    private GameOfLife game;


    /**
     * Overloaded Constructor
     * @param row row of cell
     * @param col column of cell
     * @param game src.GameOfLife object
     */
    public Cell(int row, int col, GameOfLife game) {
        // sets the size of the cell
        setSize(20,20);
        // initializing row,column, and game variables
        this.row = row;
        this.col = col;
        this.game = game;

        // creating a MouseListener to handle mouse click events for the cells
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // toggle between cell being dead or alive
                toggleState();
                // tells game instance that the cell has been clicked
                game.cellClicked(row, col);
            }
        });
    }

    /**
     * toggleState method that toggles the state of
     * the cell between dead and alive
     */
    private void toggleState() {
        // if the cell is red/alive set to dead/gray
        if (current == Color.red) {
            current = Color.lightGray;
        } else { // if it is gray/dead set to red/alive
            current = Color.red;
        }

        repaint();
    }


    /**
     * setCurrent method that sets the current state of the cell
     * @param isAlive to check the status of the cell
     */
    public void setCurrent(String isAlive){
        // if cell is "O' the cell is alive
        if (isAlive == "O"){
            current = Color.red;
        } else {
            current = Color.lightGray;
        }
        repaint();
    }


    /**
     * Renders the component
     * @param g the Graphics to draw the component
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // sets color to the current color
        g.setColor(current);
        // fills the Oval
        g.fillOval(0, 0, 20, 20);
    }

}

