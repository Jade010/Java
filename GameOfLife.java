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
 * Game of src.Life class to represent the game of life board/
 * the frame
 * @author Jade Aidoghie
 * @version 4/12/2023
 */
public class GameOfLife extends JFrame {
    // fields
    private Output GUI;
    private Life game;
    private ControlPanel controlPanel;
    private Thread animation;
    private boolean running = false;

    /**
     * Constructor
     */
    public GameOfLife() {
        super("Game of src.Life");
        // set the window size
        setSize(500, 500);
        // set the window location
        setLocation(40, 80);

        // instance of src.Life gameboard
        game = new Life();
        // instance of src.Output object for displaying game
        GUI = new Output(game.getData(), this);
        this.add(GUI);

        // creates and adds the control panel with an ActionListener for the button
        controlPanel = new ControlPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleAnimation();
            }
        });
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * cellClicked method to toggle the cells state when clicked
     * @param row the row
     * @param col the column
     */
    public void cellClicked(int row, int col) {
        game.toggleCellState(row, col);
    }

    /**
     * toggleAnimation method to start/ stop the animation
     */
    private void toggleAnimation() {
        synchronized(this) {
            // if the animation is not running then start it
            if(!running) {
                int gensPerMin;
                try {
                    // get the generations per minute from the control panel
                    gensPerMin = controlPanel.getGensPerMin();
                } catch (IllegalArgumentException e) {
                    // error message if value is not valid and return
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    controlPanel.gensPerMin.requestFocus();
                    return;
                }
                // calculates the time between generations
                int time = 60000 / gensPerMin;
                running = true;

                // change control panel button text to "Stop"
                controlPanel.setText("Stop");
                // creates and starts a new animation thread
                animation = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true) {
                            synchronized(this) {
                                if(!running) {
                                    break;
                                }
                            }
                            // start next generation and update the output
                            game.nextGeneration();
                            GUI.cellColor(game.getData());
                            // sleep for the calculated time
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                break;
                            }
                        }
                    }
                });
                // start the animation
                animation.start();
            } else {
                // if the animation is running then stop it
                running = false;
                // change the control panel button back to "Start"
                controlPanel.setText("Start");
                // interrupt the animation thread to stop what it is doing
                animation.interrupt();
            }
        }
    }

    /**
     * The application method
     * @param args the command-line argument
     */
    public static void main(String[] args) {
        // create instance of the entire game
        new GameOfLife();
    }
}