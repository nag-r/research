import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * NewUserProfile is a JFrame subclass that creates a user interface for
 * entering and saving new user profiles. It includes text fields for entering 
 * personal information and fitness level, along with buttons to save, reset,
 * and exit the form. The class also provides functionality to load existing
 * user data from a file.
 * 
 * @author Nag Rajendran 
 * @version Dec 11 2023
 */
public class NewUserProfile extends JFrame {
    // Swing components as instance variables
    private JLabel firstName, lastName, age, weight, fitnessLevel;    
    private JTextField firstNameField, lastNameField, ageField, weightField;
    private JRadioButton beginnerButton, noviceButton, intermediateButton, advancedButton, expertButton;
    private ButtonGroup fitnessLevelGroup;        
    private JButton saveButton, resetButton, exitButton;

    // Constants for layout insets
    private static final int LABEL_INSET_LEFT = 20; 
    private static final int FIELD_INSET_LEFT = 10;
    private static final int INSET_TOP_BOTTOM = 10;
    private static final int INSET_RIGHT = 10;

    /**
     * Constructor for NewUserProfile.
     * Initializes the layout of the components and adds listeners to widgets.
     */
    public NewUserProfile() {
        layoutComponents();
        addListeners();
    }


    /**
     * Sets up the layout of the program's graphical user interface.
     * It defines the placement of labels, text fields, and buttons.
     * The layout is organized for clear readability and ease of use.
     */
    private void layoutComponents() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Profile Summary");

        this.setLayout(new GridBagLayout()); // Use GridBagLayout for flexibility
        GridBagConstraints gbc = new GridBagConstraints(); // Create a GridBagConstraints object

        // Create labels
        firstName = new JLabel("First Name: ");
        lastName = new JLabel("Last Name: ");
        age = new JLabel("Age: ");
        weight = new JLabel("Weight (lbs): ");
        fitnessLevel = new JLabel("Fitness Level: ");

        // Create text fields
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(3);
        weightField = new JTextField(6);

        // Create radio buttons
        beginnerButton = new JRadioButton("Beginner");
        noviceButton = new JRadioButton("Novice");
        intermediateButton = new JRadioButton("Intermediate");
        advancedButton = new JRadioButton("Advanced");
        expertButton = new JRadioButton("Expert");

        // Group the radio buttons
        fitnessLevelGroup = new ButtonGroup();
        fitnessLevelGroup.add(beginnerButton);
        fitnessLevelGroup.add(noviceButton);
        fitnessLevelGroup.add(intermediateButton);
        fitnessLevelGroup.add(advancedButton);
        fitnessLevelGroup.add(expertButton);

        // Create buttons
        saveButton = new JButton("Save");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        Insets labelInsets = new Insets(10, 20, 10, 10); // Regular insets for labels
        Insets fieldInsets = new Insets(10, 10, 10, 10); // Regular insets for text fields

        // Add labels and text fields to the main layout
        addLabelAndField(firstName, firstNameField, 0,
                new Insets(INSET_TOP_BOTTOM, LABEL_INSET_LEFT, INSET_TOP_BOTTOM, INSET_RIGHT),
                new Insets(INSET_TOP_BOTTOM, FIELD_INSET_LEFT, INSET_TOP_BOTTOM, INSET_RIGHT));
        addLabelAndField(lastName, lastNameField, 1,
                new Insets(INSET_TOP_BOTTOM, LABEL_INSET_LEFT, INSET_TOP_BOTTOM, INSET_RIGHT),
                new Insets(INSET_TOP_BOTTOM, FIELD_INSET_LEFT, INSET_TOP_BOTTOM, INSET_RIGHT));
        addLabelAndField(age, ageField, 2,
                new Insets(INSET_TOP_BOTTOM, LABEL_INSET_LEFT, INSET_TOP_BOTTOM, INSET_RIGHT),
                new Insets(INSET_TOP_BOTTOM, FIELD_INSET_LEFT, INSET_TOP_BOTTOM, INSET_RIGHT));
        addLabelAndField(weight, weightField, 3,
                new Insets(INSET_TOP_BOTTOM, LABEL_INSET_LEFT, INSET_TOP_BOTTOM, INSET_RIGHT),
                new Insets(INSET_TOP_BOTTOM, FIELD_INSET_LEFT, INSET_TOP_BOTTOM, INSET_RIGHT));

        // Add fitness level label to the main layout
        gbc.gridx = 0; // Column 0
        gbc.gridy = 5; // Row 5
        gbc.insets = labelInsets; // Use regular insets for labels
        this.add(fitnessLevel, gbc); // Add the label to the main layout
        gbc.gridx = 1; // Column 1
        gbc.insets = fieldInsets; // Use regular insets for radio buttons

        JPanel fitnessLevelPanel = new JPanel();
        fitnessLevelPanel.setLayout(new GridLayout(3, 2, 5, 5)); // 3 rows, 2 columns, 5px horizontal and vertical gaps

        // Add radio buttons to the panel
        fitnessLevelPanel.add(beginnerButton);
        fitnessLevelPanel.add(noviceButton);
        fitnessLevelPanel.add(intermediateButton);
        fitnessLevelPanel.add(advancedButton);
        fitnessLevelPanel.add(expertButton);

        gbc.insets = new Insets(2, 2, 2, 2); // Use smaller insets for the panel
        gbc.anchor = GridBagConstraints.WEST; // Align the panel to the left
        gbc.gridwidth = 2; // Span 2 columns
        this.add(fitnessLevelPanel, gbc); // Add the panel to the main layout

        // Button panel with FlowLayout 5px horizontal and vertical gaps
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Add buttons to the panel
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        // Add button panel to main layout
        gbc.gridx = 0; // Column 0
        gbc.gridy = 7; // Row 7
        gbc.gridwidth = 4; // Span 4 columns
        gbc.insets = new Insets(2, 2, 2, 2); // Use smaller insets for the panel
        this.add(buttonPanel, gbc); // Add the panel to the main layout

        this.pack();
        this.setLocationRelativeTo(null); // Center the window
        this.setVisible(true);
    }

    
    /**
     * Adds a label and text field to the main layout.
     * 
     * @param label the label to be added
     * @param field the text field to be added
     * @param gridy the row number of the main layout
     * @param labelInsets the insets for the label
     * @param fieldInsets the insets for the text field
     */    
    private void addLabelAndField(JLabel label, JTextField field, int gridy, Insets labelInsets, Insets fieldInsets) {
        GridBagConstraints gbc = new GridBagConstraints(); // Create a GridBagConstraints object
        gbc.anchor = GridBagConstraints.WEST; // Align the label and text field to the left

        gbc.gridx = 0; // Column 0
        gbc.gridy = gridy; // Row gridy
        gbc.insets = labelInsets; // Use the provided insets for the label
        this.add(label, gbc); // Add the label to the main layout

        gbc.gridx = 1; // Column 1
        gbc.insets = fieldInsets; // Use the provided insets for the text field
        this.add(field, gbc); // Add the text field to the main layout
    }


    /**
     * Returns the first name text field.
     * 
     * @return the first name text field
     */
    public JTextField getFirstNameField() {
        return firstNameField;
    }


    /**
     * Sets the first name text field.
     * 
     * @param firstNameField the first name text field
     */
    public void setFirstNameField(JTextField firstNameField) {
        this.firstNameField = firstNameField;
    }


    /**
     * Returns the last name text field.
     * 
     * @return the last name text field
     */
    public JTextField getLastNameField() {
        return lastNameField;
    }


    /**
     * Sets the last name text field.
     * 
     * @param lastNameField the last name text field
     */
    public void setLastNameField(JTextField lastNameField) {
        this.lastNameField = lastNameField;
    }


    /**
     * Returns the age text field.
     * 
     * @return the age text field
     */
    public JTextField getAgeField() {
        return ageField;
    }


    /**
     * Sets the age text field.
     * 
     * @param ageField the age text field
     */
    public void setAgeField(JTextField ageField) {
        this.ageField = ageField;
    }


    /**
     * Returns the weight text field.
     * 
     * @return the weight text field
     */
    public JTextField getWeightField() {
        return weightField;
    }


    /**
     * Sets the weight text field.
     * 
     * @param weightField the weight text field
     */
    public void setWeightField(JTextField weightField) {
        this.weightField = weightField;
    }


    /**
     * Returns the beginner button.
     * 
     * @return the beginner button
     */
    public JRadioButton getBeginnerButton() {
        return beginnerButton;
    }


    /**
     * Sets the beginner button.
     * 
     * @param beginnerButton the beginner button
     */
    public void setBeginnerButton(JRadioButton beginnerButton) {
        this.beginnerButton = beginnerButton;
    }


    /**
     * Returns the novice button.
     * 
     * @return the novice button
     */
    public JRadioButton getNoviceButton() {
        return noviceButton;
    }


    /**
     * Sets the novice button.
     * 
     * @param noviceButton the novice button
     */
    public void setNoviceButton(JRadioButton noviceButton) {
        this.noviceButton = noviceButton;
    }


    /**
     * Returns the intermediate button.
     * 
     * @return the intermediate button
     */
    public JRadioButton getIntermediateButton() {
        return intermediateButton;
    }


    /**
     * Sets the intermediate button.
     * 
     * @param intermediateButton the intermediate button
     */
    public void setIntermediateButton(JRadioButton intermediateButton) {
        this.intermediateButton = intermediateButton;
    }


    /**
     * Returns the advanced button.
     * 
     * @return the advanced button
     */
    public JRadioButton getAdvancedButton() {
        return advancedButton;
    }


    /**
     * Sets the advanced button.
     * 
     * @param advancedButton the advanced button
     */
    public void setAdvancedButton(JRadioButton advancedButton) {
        this.advancedButton = advancedButton;
    }


    /**
     * Returns the expert button.
     * 
     * @return the expert button
     */
    public JRadioButton getExpertButton() {
        return expertButton;
    }


    /**
     * Sets the expert button.
     * 
     * @param expertButton the expert button
     */
    public void setExpertButton(JRadioButton expertButton) {
        this.expertButton = expertButton;
    }


    /**
     * Returns the fitness level group.
     * 
     * @return the fitness level group
     */
    public ButtonGroup getFitnessLevelGroup() {
        return fitnessLevelGroup;
    }


    /**
     * Sets the fitness level group.
     * 
     * @param fitnessLevelGroup the fitness level group
     */
    public void setFitnessLevelGroup(ButtonGroup fitnessLevelGroup) {
        this.fitnessLevelGroup = fitnessLevelGroup; // Set the fitness level group
    }


    /**
     * Adds action listeners to the buttons in the form for handling user
     * interactions. Includes listeners for saving data, resetting fields, and exiting the form.
     */
    private void addListeners() {
        // Save button listener
        saveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the save button is clicked.
             * Validates the user inputs and saves the data to a file.
             * 
             * @param e the action event
             */
            public void actionPerformed(ActionEvent e) {
                if (validateUserInputs()) {
                    saveUserProfile();
                }
            }
        });

        // Reset button listener
        resetButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the reset button is clicked.
             * Clears the text fields and radio button selection.
             * 
             * @param e the action event
             */
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        // Exit button listener
        exitButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the exit button is clicked.
             * Disposes the current frame and creates a new instance of MainGUIFrame.
             * 
             * @param e the action event
             */
            public void actionPerformed(ActionEvent e) {
                exitWindow();
            }
        });
    }
    
    
    /**
     * Validates the user inputs and displays an error message if any of the
     * inputs are invalid.
     * 
     * @return true if the inputs are valid, false otherwise
     */
    private boolean validateUserInputs() {
        // Validate if the first name is empty
        if (getFirstNameField().getText().trim().isEmpty()) {
            // Display an error message if the first name is empty
            JOptionPane.showMessageDialog(null, "First name must not be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate if the last name is empty
        if (getLastNameField().getText().trim().isEmpty()) {
            // Display an error message if the last name is empty
            JOptionPane.showMessageDialog(null, "Last name must not be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate age input
        try {
            // Parse the age input as an integer
            int age = Integer.parseInt(getAgeField().getText().trim());

            // Check if the age is between 1 and 100
            if (age < 1 || age > 100) {
                // Display an error message if the age is not between 1 and 100
                JOptionPane.showMessageDialog(null, "Age must be a valid integer between 1 and 100", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            // Display an error message if the age is not a valid integer
            JOptionPane.showMessageDialog(null, "Age must be a valid integer", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate weight input
        try {
            // Parse the weight input as a double
            double weight = Double.parseDouble(getWeightField().getText().trim());

            // Check if the weight is greater than 0
            if (weight <= 0) {
                // Display an error message if the weight is not greater than 0
                JOptionPane.showMessageDialog(null, "Weight must be a positive number", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            // Display an error message if the weight is not a valid number
            JOptionPane.showMessageDialog(null, "Weight must be a valid number", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate if a fitness level is selected
        if (getFitnessLevelGroup().getSelection() == null) {
            // Display an error message if a fitness level is not selected
            JOptionPane.showMessageDialog(null, "Please select a fitness level", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Return true if all inputs are valid
    }

    
    /**
     * Saves the user profile data to a file.
     * The method collects data from form fields and writes it to a text file,
     * handling any I/O exceptions.
     */
    private void saveUserProfile() {
        // Create an ArrayList to hold the data
        ArrayList<String> userData = new ArrayList<>();

        try {
            userData.add(getFirstNameField().getText().trim()); // Add the first name
            userData.add(getLastNameField().getText().trim()); // Add the last name
            userData.add(getAgeField().getText().trim()); // Add the age
            userData.add(getWeightField().getText().trim()); // Add the weight

            // Get the selected radio button
            Enumeration<AbstractButton> buttons = getFitnessLevelGroup().getElements();

            // Add the selected radio button to the ArrayList
            while (buttons.hasMoreElements()) {

                // Get the next radio button
                AbstractButton button = buttons.nextElement();

                // Check if the button is selected
                if (button.isSelected()) {
                    userData.add(button.getText()); // Add the selected radio button
                    break;
                }
            }

            // Write the data to a file
            File f = new File("userProfile.txt"); // Create a new file
            PrintWriter writer = new PrintWriter(f); // Create a PrintWriter object

            // Write each line of data to the file
            for (String line : userData) {
                writer.println(line); // Write the line to the file
            }
            writer.close(); // Close the PrintWriter

            // Display a success message
            JOptionPane.showMessageDialog(null, "Data saved successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            // Display an error message if an I/O exception occurs
            JOptionPane.showMessageDialog(null, "An error occurred while saving the data", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * Clears the text fields and radio button selection.
     */    
    private void resetFields() {
        // Clear the text fields
        getFirstNameField().setText("");
        getLastNameField().setText("");
        getAgeField().setText("");
        getWeightField().setText("");

        // Clear the radio button selection
        getFitnessLevelGroup().clearSelection();
    }


    /**
     * Disposes the current frame and creates a new instance of MainGUIFrame.
     */
    private void exitWindow() {
        // Dispose the current frame
        dispose();

        // Create a new instance of MainGUIFrame and make it visible
        MainGUIFrame mainGUIFrame = new MainGUIFrame();
        mainGUIFrame.setVisible(true);
    }


    /**
     * Loads user data from a text file and updates the form fields with this data.
     * This method is used to populate the user profile form with previously saved
     * data, facilitating the viewing or editing of existing user profiles. It reads data
     * from 'userProfile.txt', expecting each line to correspond to a different piece 
     * of user information (first name, last name, etc.). 
     * 
     * The method handles I/O exceptions and provides user feedback if data loading
     * fails.
     */
    public void loadUserData() {
        try {
            File f = new File("userProfile.txt"); // Create a new file
            Scanner scanner = new Scanner(f); // Create a Scanner object

            // Create an ArrayList to hold the data
            ArrayList<String> userData = new ArrayList<>();

            // Read each line of data from the file
            while (scanner.hasNextLine()) {
                userData.add(scanner.nextLine()); // Add the line to the ArrayList
            }

            scanner.close(); // Close the Scanner

            // Set the text fields with the data
            getFirstNameField().setText(userData.get(0));   // set the first name
            getLastNameField().setText(userData.get(1));    // set the last name
            getAgeField().setText(userData.get(2));         // set the age
            getWeightField().setText(userData.get(3));      // set the weight

            // Update the selection of the fitness level radio buttons
            String fitnessLevel = userData.get(4); // Get the fitness level

            // Check the fitness level and select the corresponding radio button
            switch (fitnessLevel) {
                case "Beginner":
                    getBeginnerButton().setSelected(true);
                    break;
                case "Novice":
                    getNoviceButton().setSelected(true);
                    break;
                case "Intermediate":
                    getIntermediateButton().setSelected(true);
                    break;
                case "Advanced":
                    getAdvancedButton().setSelected(true);
                    break;
                case "Expert":
                    getExpertButton().setSelected(true);
                    break;
            }

        } catch (IOException ex) {
            // Display an error message if an issue occurs during file reading
            JOptionPane.showMessageDialog(null, "An error occurred while loading the data", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}