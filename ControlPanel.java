package src;

import javax.swing.*;
import java.awt.event.ActionListener;

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
 * src.ControlPanel class extending JPanel
 *
 * @author Jade Aidoghie
 * 4/12/2023
 */
public class ControlPanel extends JPanel {
    // fields
    private JButton btn1;
    public JTextField gensPerMin;

    /**
     * Overloaded Constructor
     * @param e the action listener for the button
     */
    public ControlPanel(ActionListener e) {
        // instance of the start stop button
        btn1 = new JButton("Start");
        // add button to panel
        this.add(btn1);
        // adding action listener to button
        btn1.addActionListener(e);

        // creating text field instance with 5 column width
        gensPerMin = new JTextField(5);
        // setting default text to 10
        gensPerMin.setText("10");
        // adding a label for the text field
        this.add(new JLabel("Generations per minute:"));
        // add text field to panel
        this.add(gensPerMin);

    }

    /**
     * setText method
     * @param text the default text
     */
    public void setText(String text) {
        // changes button text
        btn1.setText(text);
    }

    /**
     * getGensPerMin method
     * @return value the parsed text
     */
    public int getGensPerMin(){
        try {
            // parse textfield as an integer
            int value = Integer.parseInt(gensPerMin.getText());
            // check if value is within range if not then show exception
            if (value < 1 || value > 250) {
                throw new IllegalArgumentException("Generations per minute must be between 1 and 250.");
            }
            return value;
        } catch (NumberFormatException e) {
            // exception for if a non-integer is input
            throw new IllegalArgumentException("Please enter an integer value.");
        }
    }
}