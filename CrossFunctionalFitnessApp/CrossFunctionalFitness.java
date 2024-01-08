import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * The CrossFunctionalFitness class is the main class of the application that
 * sets up the user interface. It initializes the primary window (MainGUIFrame) 
 * of the Cross-Functional Fitness program.
 * 
 * @author Nag Rajendran 
 * @version Dec 11 2023
 */
public class CrossFunctionalFitness {
    /**
     * The main method is the entry point for the Java application.
     * It creates and displays the main GUI frame.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create and display the main GUI frame
        MainGUIFrame mainGUIFrame = new MainGUIFrame();

        // Make the main GUI frame visible
        mainGUIFrame.setVisible(true);
    }
}


/**
 * MainGUIFrame extends JFrame to create a custom window for the application.
 * It contains various UI components like buttons, labels, and text panes,
 * arranged to provide a user-friendly experience for interacting with the fitness program.
 */
class MainGUIFrame extends JFrame {
    
    // Declaration of UI components. Each component serves a specific role in the GUI.
    private JLabel titleLabel;
    private JTextPane programDescriptionPane;
    private JButton newUserButton, loadUserButton, fitnessSummaryButton, launchWorkoutButton;

    /**
     * Constructor of MainGUIFrame. It initializes the GUI components and their layout,
     * and sets up event listeners for user interaction.
     */            
    public MainGUIFrame() {
        layoutComponents(); // Set up the layout
        addListeners();     // Add event listeners
    }

    /**
     * Set up the program's layout.
     */
    private void layoutComponents() {
        welcomeForm();
    }


    /**
     * Sets up the initial form that users see upon opening the application.
     * This includes setting the title, configuring the layout manager,
     * and adding various UI elements like labels, buttons, and text panes.
     * This method is crucial for initial user engagement.
     */    
    private void welcomeForm() {
        // Configuration of the frame's basic properties, layout, and UI components.
        this.setTitle("Main");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout()); // Use GridBagLayout for the frame
        GridBagConstraints gbc = new GridBagConstraints(); // Create a GridBagConstraints object

        // Title label
        titleLabel = new JLabel("Introduction to Cross-Functional Fitness!", SwingConstants.CENTER); // Center the text
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.gridwidth = 3; // Span across 3 columns
        gbc.insets = new Insets(10, 10, 10, 10); // 10px padding on all sides
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        this.add(titleLabel, gbc);

        // Program Description
        String programDescriptionText = "Welcome to Cross-Functional Fitness, your 30-day journey to a healthier, "
                + "more active lifestyle! This program is your gateway to discovering the joys and benefits "
                + "of functional fitness, suitable for anyone, anywhere. Designed to combat the inactivity "
                + "of desk jobs and remote work, it dispels the myth that high-intensity training is only "
                + "for the fit. Whether you have access to a gym or not, this program adapts to your needs, "
                + "making exercise an enjoyable part of your daily life. Join us to transform your health "
                + "and vitality!\n\n"
                + "How to Use the App:\n\n"
                + "[1] Get Started: Create your profile and embark on your fitness journey.\n\n"
                + "[2] Launch Your Journey: Begin your 30-day workout program.\n\n"
                + "[3] Explore & Engage: Get to know your workout Program window. It's your daily fitness hub.\n\n"
                + "[4] Track Your Progress: After each workout, check \"Mark as Completed\" and note your finish time. "
                + "Remember, every step counts!\n\n"
                + "[5] Celebrate Your Achievements: Visit the Fitness Summary screen anytime to see how far you've come.\n\n"
                + "Every workout is a step towards a healthier you. Embrace the challenge, enjoy the journey, "
                + "and celebrate your progress. You've got this!\n\n";

        // Program Description text pane
        programDescriptionPane = new JTextPane();
        programDescriptionPane.setText(programDescriptionText);
        programDescriptionPane.setEditable(false);
        programDescriptionPane.setOpaque(false);
        programDescriptionPane.setFont(new Font("Arial", Font.PLAIN, 12));

        // Set the alignment of the text to justify
        StyledDocument doc = programDescriptionPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet(); // Create an AttributeSet for the text
        StyleConstants.setFontFamily(attr, "Arial"); // Set the font family
        StyleConstants.setFontSize(attr, 14); // Set the font size
        StyleConstants.setAlignment(attr, StyleConstants.ALIGN_JUSTIFIED); // Set the alignment
        doc.setParagraphAttributes(0, doc.getLength(), attr, false); // Apply the attributes to the text

        SimpleAttributeSet boldAttr = new SimpleAttributeSet(); // Create an AttributeSet for bold text
        StyleConstants.setBold(boldAttr, true); // Set the bold attribute

        // Find the position of "How to Use the App:" in the text
        int start = programDescriptionText.indexOf("How to Use the App:"); // Start position
        int end = start + "How to Use the App:".length(); // End position

        // Apply the bold attributes to "How to Use the App:"
        doc.setCharacterAttributes(start, end - start, boldAttr, false);

        // Program Description
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        gbc.gridwidth = 3; // Span across 3 columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill the width of the column
        this.add(programDescriptionPane, gbc); // Add the text pane to the frame

        // Buttons
        newUserButton = new JButton("Create New User");
        loadUserButton = new JButton("Load Existing User Profile");
        fitnessSummaryButton = new JButton("Fitness Summary");
        launchWorkoutButton = new JButton("Launch Workout Program");

        // Layout for the buttons
        gbc.gridwidth = 1; // Reset the grid width
        gbc.fill = GridBagConstraints.NONE; // Reset the fill

        // New User button
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Row 2
        gbc.anchor = GridBagConstraints.LINE_START; // Align to the left
        this.add(newUserButton, gbc); // Add the button to the frame

        // Load User button
        gbc.gridx = 1; // Column 1
        gbc.gridy = 2; // Row 2
        gbc.anchor = GridBagConstraints.CENTER; // Align to the center
        this.add(loadUserButton, gbc); // Add the button to the frame

        // Summary Stats button
        gbc.gridx = 2; // Column 2
        gbc.gridy = 2; // Row 2
        gbc.anchor = GridBagConstraints.LINE_END; // Align to the right
        this.add(fitnessSummaryButton, gbc); // Add the button to the frame

        // Launch Workout Program button
        gbc.gridx = 0; // Column 0
        gbc.gridy = 3; // Row 3
        gbc.gridwidth = 3; // Span across 3 columns
        gbc.fill = GridBagConstraints.BOTH; // Fill the width and height of the column
        launchWorkoutButton.setFont(new Font("Arial", Font.BOLD, 16));  // Set the font
        launchWorkoutButton.setForeground(Color.BLUE);  // Set the font color
        launchWorkoutButton.setPreferredSize(new Dimension(50, 50)); // Set the preferred size
        launchWorkoutButton.setMinimumSize(new Dimension(50, 50));  // Set the minimum size
        this.add(launchWorkoutButton, gbc); // Add the button to the frame

        // Display the window
        this.pack(); // Optimize the frame's size
        this.setSize(600, 700); 
        this.setLocationRelativeTo(null); // Center the window
        this.setVisible(true);
    }


    /**
     * Adds action listeners to the buttons in the GUI.
     * Each listener is responsible for handling user 
     * interactions with the respective buttons, triggering 
     * appropriate actions and handling any exceptions that 
     * occur during the process.
     */    
    private void addListeners() {
        // Listener for 'New User' button
        newUserButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the 'New User' button is clicked.
             * This method attempts to display the form for creating a new user.
             * If an exception occurs during the process, it is caught, logged,
             * and a user-friendly error message is displayed.
             *
             * @param e The ActionEvent object generated by the button click.
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    newUserForm(); // Attempt to open the new user form
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log the exception for debugging purposes
                    JOptionPane.showMessageDialog(null,
                            "An error occurred while creating a new user. Please try again.");
                }
            }
        });


        // Listener for 'Load User' button
        loadUserButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the 'Load User' button is clicked.
             * This method attempts to display the form for loading an existing user profile.
             * Similar to the 'New User' button, it handles exceptions and informs the user
             * if an issue arises during the process.
             *
             * @param e The ActionEvent object generated by the button click.
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    loadUserForm(); // Attempt to open the form to load an existing user profile
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log the exception for debugging purposes
                    JOptionPane.showMessageDialog(null,
                            "An error occurred while creating a new user. Please try again.");
                }
            }
        });
        

        // Listener for 'Fitness Summary' button
        fitnessSummaryButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the 'Fitness Summary' button is clicked.
             * This method calculates and displays fitness metrics based on user workout data.
             * It handles the creation of a fitness summary report and updates it with calculated metrics.
             *
             * @param e The ActionEvent object generated by the button click.
             */
            public void actionPerformed(ActionEvent e) {
                // Create a new fitness summary window
                FitnessSummary fitnessSummary = new FitnessSummary();
                fitnessSummary.setVisible(true); // Make the fitness summary window visible

                // Create a list to store workout data
                List<WorkoutData> workoutDataList = new ArrayList<>();

                // Load workout data for each day and add it to the list
                for (int i = 1; i <= 30; i++) {
                    WorkoutData data = WorkoutData.loadWorkouts(i);
                    if (data != null) {
                        workoutDataList.add(data);
                    }
                }

                // Calculate the metrics and update the fitness summary window
                String averageTime = String.format("%.2f",
                        FitnessMetricsCalculator.calculateAverageTime(workoutDataList));
                String totalTime = String.valueOf(FitnessMetricsCalculator.calculateTotalTime(workoutDataList));
                String consistencyScore = String.format("%.2f", FitnessMetricsCalculator
                        .calculateConsistencyScore(workoutDataList, FitnessMetricsCalculator.TOTAL_PLANNED_WORKOUTS));
                String personalBests = FitnessMetricsCalculator.calculatePersonalBests(workoutDataList);

                // Update the fitness summary window with the calculated metrics
                fitnessSummary.updateMetrics(averageTime, totalTime, consistencyScore, personalBests);
            }
        });


        // Listener for 'Launch Workout Program' button
        launchWorkoutButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the 'Launch Workout' button is clicked.
             * This method initializes and displays a workout plan for the user.
             * It is the entry point for users starting their workout routines.
             *
             * @param e The ActionEvent object generated by the button click.
             */
            public void actionPerformed(ActionEvent e) {
                // Create a new workout plan window
                WorkoutPlanGenerator newWorkoutPlan = new WorkoutPlanGenerator();
                newWorkoutPlan.setVisible(true); // Make the workout plan window visible
                newWorkoutPlan.loadDayOneWorkout(); // Load the workout plan for Day 1
            }
        });
    }
    

    /**
     * This method is responsible for initializing and displaying the form for creating a new user profile.
     * It is typically invoked when the user clicks the 'New User' button. The method creates an instance
     * of the NewUserProfile class, which represents the new user registration form, and makes it visible
     * to the user. This form allows users to enter their details to create a new profile in the application.
     */
    private void newUserForm() {
        // Creation of the new user profile form.
        NewUserProfile newUserProfile = new NewUserProfile();

        // Making the new user profile form visible to the user.
        newUserProfile.setVisible(true);
    }


    /**
     * This method is designed to handle the loading of an existing user's profile.
     * It is triggered when a user clicks the 'Load User' button. The method creates an instance
     * of the NewUserProfile class, similar to the newUserForm method, but instead of creating a new profile,
     * it initiates the process of loading existing user data. This functionality is essential for users
     * who want to return to the application and access their previously saved profile and fitness data.
     */    
    private void loadUserForm() {
        // Creation of a user profile form, identical in appearance to the new user form.
        NewUserProfile existingUserProfile = new NewUserProfile();

        // Calling the method to load existing user data.
        existingUserProfile.loadUserData();
    }
}