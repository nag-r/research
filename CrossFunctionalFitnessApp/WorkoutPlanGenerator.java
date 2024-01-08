import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * WorkoutPlanGenerator is a JFrame subclass that creates a user interface for
 * generating and displaying a workout plan. It includes a panel for each day 
 * of the workout plan and another panel for detailed workout instructions.
 * This class allows users to navigate through different days of the workout
 * plan and track their progress.
 * 
 * @author Nag Rajendran 
 * @version Dec 11 2023
 */
public class WorkoutPlanGenerator extends JFrame {

    // Widgets for displaying workout plan
    private JPanel daysPanel, detailsPanel; // Panels for displaying workout days and details
    private CardLayout cardLayout; // Layout to switch between workout details
    private ArrayList<JButton> dayButtons; // Buttons for each day of the workout plan
    private ArrayList<String> workoutDescriptions; // Workout descriptions for each day
    private WorkoutDetails[] workoutDetailsArray; // Array to store workout details for each day


    /**
     * Constructor for WorkoutPlanGenerator.
     * Initializes the workout details array, sets up workout descriptions, and
     * configures the layout and listeners.
     */
    public WorkoutPlanGenerator() {
        workoutDetailsArray = new WorkoutDetails[FitnessMetricsCalculator.TOTAL_PLANNED_WORKOUTS]; // Initialize array for 30 days
        initializeWorkoutDescriptions(); // Setup workout descriptions
        layoutComponents(); // Setup layout
        addListeners(); // Setup listeners
    }


    /**
     * Lays out the components of the workout plan generator.
     * This includes setting up panels for the days and details of workouts and
     * adding them to the frame.
     */
    private void layoutComponents() {

        this.setTitle(FitnessMetricsCalculator.TOTAL_PLANNED_WORKOUTS + " Day Workout Plan"); // Set the title of the frame

        setLayout(new GridBagLayout()); // Use GridBagLayout for layout
        GridBagConstraints gbc = new GridBagConstraints(); // Use GridBagConstraints for layout

        // Left section (workout days)
        daysPanel = new JPanel(new GridBagLayout());
        JScrollPane dayScrollPane = new JScrollPane(daysPanel); // Wrap the days panel in a scroll pane
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.fill = GridBagConstraints.BOTH; // Fill the entire space
        gbc.weightx = 0.1; // // Assigns 10% of extra horizontal space to this component
        gbc.weighty = 1; // Assigns a high priority to allocate extra vertical space to this component
        add(dayScrollPane, gbc); // Add the scroll pane to the frame

        // Right section (workout details)
        detailsPanel = new JPanel();
        cardLayout = new CardLayout();
        detailsPanel.setLayout(cardLayout);
        JScrollPane scrollPane = new JScrollPane(detailsPanel); // Wrap the details panel in a scroll pane
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        gbc.weightx = 0.7; // Assigns 70% of extra horizontal space to this component
        gbc.weighty = 1; // Assigns a high priority to allocate extra vertical space to this component
        add(scrollPane, gbc); // Add the scroll pane to the frame

        // Add workout days and details
        addWorkoutDaysAndDetails();

        // Display the window
        pack();
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
    

    /**
     * Populates the workout plan generator with buttons for each workout day and
     * corresponding detail panels. This method dynamically creates a JButton for 
     * each day in the workout plan and a detail panel with workout information for 
     * that day. The buttons are added to the daysPanel, and the detail panels are
     * added to the detailsPanel with a CardLayout, allowing users to switch between
     * different days' details.
     */    
    private void addWorkoutDaysAndDetails() {

        dayButtons = new ArrayList<>(); // Initialize the list to hold buttons for each day
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout management

        gbc.gridx = 0; // Column 0
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        gbc.weightx = 1; // Assigns 100% of extra horizontal space to this component

        // Iterate through the total number of planned workouts
        for (int i = 1; i <= FitnessMetricsCalculator.TOTAL_PLANNED_WORKOUTS; i++) {

            String day = "Day " + i; // Create a string representing the day
            JButton dayButton = new JButton(day); // Create a button for the day
            dayButtons.add(dayButton); // Add the button to the list of day buttons
            gbc.gridy = i - 1; // Set the vertical position for the button
            daysPanel.add(dayButton, gbc); // Add the button to the daysPanel with specified GridBagConstraints

            // Create and add a detail panel for each day
            JPanel details = createWorkoutDetailsPanel(i, day); // Create a details panel for the day
            detailsPanel.add(details, day); // Add the details panel to the detailsPanel with CardLayout
        }
    }
    

    /**
     * Creates and returns a JPanel containing the workout details for a specific
     * day.
     * This panel includes a text area with the workout description and a bottom
     * panel with controls
     * for marking the workout as complete and recording the time taken.
     *
     * @param dayNumber The specific day number for which the details panel is being
     *                  created.
     * @param day       The string representation of the day (e.g., "Day 1").
     * @return A JPanel containing the workout details for the specified day.
     */
    private JPanel createWorkoutDetailsPanel(int dayNumber, String day) {

        // Create a new JPanel with GridBagLayout for flexible component arrangement.
        JPanel workoutDetailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1; // Allocate any extra horizontal space to this component.
        gbc.weighty = 1; // Allocate any extra vertical space to this component.

        // Determine the workout program description for the specified day.
        String workoutDescription;

        // Check if the day number is within the range of the workout descriptions.
        if (workoutDescriptions.size() > dayNumber - 1) {
            workoutDescription = workoutDescriptions.get(dayNumber - 1); // Get the workout description for the day.
        } else {
            // Default description if none available.
            workoutDescription = "No workout description available.";
        }

        // Create a text area for displaying the workout description.
        JTextArea workoutProgram = new JTextArea(workoutDescription);
        workoutProgram.setEditable(false); // Make the text area read-only.
        workoutDetailsPanel.add(workoutProgram, gbc); // Add the text area to the workout details panel.

        // Create a bottom panel for additional controls like 'mark as complete'
        // checkbox and time input fields.
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.gridx = 0; // Column 0
        gbcBottom.gridy = 0; // Row 0
        gbcBottom.anchor = GridBagConstraints.WEST; // Align components to the left

        JCheckBox markAsComplete = new JCheckBox("Mark as complete");
        JTextField hours = new JTextField(3);
        JTextField minutes = new JTextField(3);
        JButton saveButton = createSaveButton(dayNumber, day, markAsComplete, hours, minutes); // Create a save button.

        // Setup the bottom panel with the created components.
        setupBottomPanel(bottomPanel, markAsComplete, hours, minutes, saveButton);

        // Store workout details in an array for later retrieval or manipulation.
        workoutDetailsArray[dayNumber - 1] = new WorkoutDetails(markAsComplete, hours, minutes);

        // Adjust layout constraints and add the bottom panel to the workout details panel.
        gbc.gridy = 1; // Row 1
        gbc.weighty = 0; // Do not allocate any extra vertical space to this component.
        gbc.anchor = GridBagConstraints.NORTH; // Align components to the top
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        workoutDetailsPanel.add(bottomPanel, gbc); // Add the bottom panel to the workout details panel.

        return workoutDetailsPanel;
    }


    /**
     * Creates and returns a 'Save' button with an attached ActionListener.
     * This button, when clicked, will save the workout data entered in the form
     * fields for the specified day.
     * It handles the extraction and validation of time inputs and updates the
     * workout data accordingly.
     *
     * @param dayNumber      The specific day number for which the save button is
     *                       being created.
     * @param day            The string representation of the day (e.g., "Day 1").
     * @param markAsComplete Checkbox indicating whether the workout is completed.
     * @param hours          TextField for entering the number of hours taken for
     *                       the workout.
     * @param minutes        TextField for entering the number of minutes taken for
     *                       the workout.
     * @return A JButton configured with an ActionListener for saving workout data.
     */
    private JButton createSaveButton(int dayNumber, String day, JCheckBox markAsComplete, JTextField hours,
            JTextField minutes) {

        JButton saveButton = new JButton("Save"); // Create a new 'Save' button

        // Add an ActionListener to the button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract and validate workout completion status
                    boolean completed = markAsComplete.isSelected();

                    // Parse the time taken in hours and minutes, handling empty or invalid inputs
                    int timeHour;
                    if (hours.getText().isEmpty()) {
                        timeHour = 0;
                    } else {
                        timeHour = Integer.parseInt(hours.getText());
                    }

                    int timeMins;
                    if (minutes.getText().isEmpty()) {
                        timeMins = 0;
                    } else {
                        timeMins = Integer.parseInt(minutes.getText());
                    }

                    // Calculate the total time taken in minutes
                    int totalMinutes = timeHour * 60 + timeMins;
                    String timeTaken = String.valueOf(totalMinutes);

                    // Create a new WorkoutData object and save it using static method saveWorkout
                    WorkoutData workoutData = new WorkoutData(day, completed, timeTaken);
                    WorkoutData.saveWorkout(workoutData, dayNumber);

                    if (completed) {
                        // Show a confirmation message if the workout is marked as completed
                        JOptionPane.showMessageDialog(null,
                                "Awesome job! You've completed the workout for " + day + ". Keep up the great work!");
                    }
                } catch (NumberFormatException ex) {
                    // Show an error message if the time inputs are not valid numbers
                    JOptionPane.showMessageDialog(null,
                            "Invalid number format. Please enter a valid number for hours and minutes.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return saveButton; // Return the configured 'Save' button
    }
    

    /**
     * Sets up the bottom panel of the workout details section.
     * This method configures the layout and adds components such as a checkbox for
     * marking completion, text fields for hours and minutes, and a save button to the given panel.
     * 
     * @param bottomPanel    The JPanel to which the components are added.
     * @param markAsComplete A JCheckBox for the user to mark the workout as
     *                       complete.
     * @param hours          A JTextField for entering the number of hours spent on
     *                       the workout.
     * @param minutes        A JTextField for entering the number of minutes spent
     *                       on the workout.
     * @param saveButton     A JButton that, when clicked, saves the workout data.
     */
    private void setupBottomPanel(JPanel bottomPanel, JCheckBox markAsComplete, JTextField hours, JTextField minutes,
            JButton saveButton) {
        
        GridBagConstraints gbc = new GridBagConstraints(); // Create GridBagConstraints for layout management
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        bottomPanel.add(markAsComplete, gbc); // Add the 'mark as complete' checkbox to the bottom panel

        // Configure and add label for the hours input field
        gbc.insets = new Insets(10, 20, 10, 0); // Add padding
        gbc.gridx = 1; // Column 1
        bottomPanel.add(new JLabel("Time taken (hours): "), gbc); // Add the label to the bottom panel

        // Configure and add the hours input field
        gbc.insets = new Insets(10, 0, 10, 0); // Add padding
        gbc.gridx = 2; // Column 2
        bottomPanel.add(hours, gbc); // Add the hours input field to the bottom panel

        // Configure and add label for the minutes input field
        gbc.insets = new Insets(10, 20, 10, 0); // Add padding
        gbc.gridx = 3; // Column 3
        bottomPanel.add(new JLabel("Minutes: "), gbc); // Add the label to the bottom panel

        // Configure and add the minutes input field
        gbc.insets = new Insets(10, 0, 10, 0); // Add padding
        gbc.gridx = 4; // Column 4
        bottomPanel.add(minutes, gbc); // Add the minutes input field to the bottom panel

        // Configure and add the save button
        gbc.insets = new Insets(10, 20, 10, 0); // Add padding
        gbc.gridx = 5; // Column 5
        bottomPanel.add(saveButton, gbc); // Add the save button to the bottom panel
    }


    /**
     * Adds action listeners to each day button in the workout plan.
     * This method iterates through the list of day buttons and attaches an
     * ActionListener to each.
     * When a day button is clicked, it changes the appearance of the button to
     * indicate it's selected, shows the corresponding workout details in the details 
     * panel, and loads any existing workout data for that day.
     */    
    private void addListeners() {

        // Iterate through all day buttons
        for (int i = 0; i < dayButtons.size(); i++) {
            // Get the day button at the current index
            JButton dayButton = dayButtons.get(i);

            // Calculate the day number (1-based index)
            int dayNumber = i + 1;

            // Add an ActionListener to the day button
            dayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resetButtonColors(); // Reset the color of all day buttons to default
                    dayButton.setFont(new Font("Arial", Font.BOLD, 14)); // Make the font bold
                    dayButton.setForeground(Color.BLUE); // Change the font color to blue

                    // Switch the card layout to show the panel corresponding to the selected day
                    cardLayout.show(detailsPanel, dayButton.getText());

                    // Load workout details for the selected day
                    loadWorkoutDetails(dayNumber);
                }
            });
        }
    }
    

    /**
     * Loads the workout details for a specified day number.
     * This method retrieves the workout details for a given day from the
     * workoutDetailsArray and updates the UI accordingly.
     * It is typically called when a day button is clicked to display the stored or
     * previously entered workout data.
     * 
     * @param dayNumber The day number for which to load the workout details.
     */
    private void loadWorkoutDetails(int dayNumber) {

        // Check if the day number is within the valid range
        if (dayNumber > 0 && dayNumber <= workoutDetailsArray.length) {
            // Retrieve the WorkoutDetails object for the specified day
            WorkoutDetails details = workoutDetailsArray[dayNumber - 1];

            // Load the workout details for the specified day
            updateWorkoutDetails(details, WorkoutData.loadWorkouts(dayNumber));
        }
    }


    /**
     * Updates the workout details UI components based on the provided workout data.
     * This method is responsible for setting the state of the workout details
     * components (like checkboxes and text fields)
     * to reflect the data of a specific workout. It sets the 'mark as complete'
     * checkbox based on the workout's completion
     * status and parses the time taken for the workout to set the hours and minutes
     * fields. If the workout data or details
     * are not available, it resets the details to their default state.
     * 
     * @param details     The WorkoutDetails object containing the UI components for
     *                    a specific day's workout details.
     * @param workoutData The WorkoutData object containing data for a specific
     *                    day's workout (e.g., completion status, time taken).
     */
    private void updateWorkoutDetails(WorkoutDetails details, WorkoutData workoutData) {

        // Check if both details and workoutData are available
        if (details != null && workoutData != null) {
            // Set the completion status in the 'mark as complete' checkbox
            details.markAsComplete.setSelected(workoutData.isCompleted());

            // Parse the time taken from the workout data and set the hours and minutes fields
            parseAndSetTime(workoutData.getTimeTaken(), details);
        } else {
            // If details or workoutData are missing, reset the workout details to default state
            resetWorkoutDetails(details);
        }
    }


    /**
     * Parses the time taken for a workout and sets the hours and minutes in the
     * workout details. This method takes the total time taken for a workout in 
     * minutes, converts it to hours and remaining minutes, and updates the respective 
     * text fields in the WorkoutDetails object. If the timeTaken string is not properly 
     * formatted, an error message is displayed to the user.
     *
     * @param timeTaken The string representing the total time taken for the workout
     *                  in minutes.
     * @param details   The WorkoutDetails object containing the UI components
     *                  (hours and minutes fields) to be updated.
     */
    private void parseAndSetTime(String timeTaken, WorkoutDetails details) {

        // Check if the timeTaken string is not null
        if (timeTaken != null) {
            try {
                // Convert the timeTaken string to an integer representing total minutes
                int totalMinutes = Integer.parseInt(timeTaken);

                // Calculate hours and remaining minutes from total minutes.
                // Set the calculated hours and minutes in the workout details.
                details.hours.setText(String.valueOf(totalMinutes / 60));
                details.minutes.setText(String.valueOf(totalMinutes % 60));
            } catch (NumberFormatException e) {
                // Display an error dialog if timeTaken is not a valid number
                JOptionPane.showMessageDialog(null, "Invalid number format in time taken", "Number Format Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Resets the workout details in the UI to their default state.
     * This method is called to clear the workout information from the UI components
     * in the WorkoutDetails object. It unchecks the 'mark as complete' checkbox 
     * and clears the text fields for hours and minutes. This is typically used 
     * when there is no workout data to display, or when initializing the UI for a new workout day.
     *
     * @param details The WorkoutDetails object containing the UI components
     *                (checkbox and text fields) to be reset.
     */
    private void resetWorkoutDetails(WorkoutDetails details) {

        // Check if the details object is not null
        if (details != null) {
            // Uncheck the 'mark as complete' checkbox
            details.markAsComplete.setSelected(false);

            // Clear the text fields for hours and minutes
            details.hours.setText("");
            details.minutes.setText("");
        }
    }


    /**
     * Loads the workout details for the first day of the workout plan.
     * This method is a convenience function that specifically targets the first day
     * of the workout plan. It calls the loadWorkoutDetails method with a parameter of 1, 
     * indicating the first day. This can be useful for initial setup or for quickly 
     * navigating back to the first day's details.
     */
    public void loadDayOneWorkout() {

        loadWorkoutDetails(1); // Load the workout details for day one
    }


    /**
     * Inner class representing the details of a workout session for a specific day.
     * This class encapsulates the UI components related to workout details, such as
     * a checkbox for marking the workout as complete and text fields for entering the hours
     * and minutes spent on the workout. It acts as a container for these components, 
     * making it easier to manage and access them as a single unit.
     */
    private class WorkoutDetails {

        JCheckBox markAsComplete;   // Checkbox to indicate if the workout is completed
        JTextField hours;           // TextField for entering the number of hours spent on the workout
        JTextField minutes;         // TextField for entering the number of minutes spent on the workout

        /**
         * Constructor for WorkoutDetails.
         * Initializes the WorkoutDetails object with the provided UI components.
         *
         * @param markAsComplete The checkbox component representing workout completion
         *                       status.
         * @param hours          The text field component for entering the number of
         *                       hours.
         * @param minutes        The text field component for entering the number of
         *                       minutes.
         */
        public WorkoutDetails(JCheckBox markAsComplete, JTextField hours, JTextField minutes) {
            this.markAsComplete = markAsComplete;   // Set the checkbox for marking completion
            this.hours = hours;                     // Set the text field for hours
            this.minutes = minutes;                 // Set the text field for minutes
        }
    }
    

    /**
     * Resets the visual appearance of all day buttons to their default state.
     * This method is used to standardize the look of the day buttons in the workout
     * plan,
     * ensuring that no button appears selected or highlighted. It iterates through
     * each button in the dayButtons list,
     * resetting its font, text color, and background color. This method is
     * typically called before highlighting
     * a newly selected day button to ensure that only one button appears as
     * selected at any given time.
     */
    private void resetButtonColors() {

        for (JButton button : dayButtons) {
            // Set the font of the button to Arial, bold, with a size of 14
            button.setFont(new Font("Arial", Font.BOLD, 14));

            // Change the text color of the button to light gray
            button.setForeground(Color.LIGHT_GRAY);

            // Reset the background color of the button to its default color
            button.setBackground(null);
        }
    }


    /**
     * Initializes the list of workout descriptions for each day of the workout
     * plan. This method populates an ArrayList with detailed descriptions of the 
     * workouts for each day. The descriptions include various components of the workouts, 
     * such as warm-up routines, specific exercises, the number of repetitions or duration 
     * for each exercise, and post-workout stretching instructions.
     * 
     * These descriptions are used to provide users with clear guidance on what each
     * day's workout entails.
     */
    private void initializeWorkoutDescriptions() {

        // Initialize the ArrayList to hold workout descriptions
        workoutDescriptions = new ArrayList<>();

        // Add workout descriptions for each day of the workout plan
        // Day 1
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "Workout of the Day (WOD):" +
                "(Repeat 2 times)\n\n" +
                "   Exercise 1: 200m Jumping Jacks\n" +
                "   Exercise 2: 40 air squats\n" +
                "   Exercise 3: 200m Jumping Jacks\n" +
                "   Exercise 4: 30 sit-ups\n" +
                "   Exercise 5: 200m Jumping Jacks\n" +
                "   Exercise 6: 20 jump squats\n" +
                "   Exercise 7: 200m Jumping Jacks\n" +
                "   Exercise 8: 10 burpees\n\n" +
                "Post-workout stretching: 10 mins.");
        
        // Day 2
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "EMOM (Every minute on the minute) 35 mins (Workout for 45 sec, 15 sec rest).\n\n" +
                "   Exercise 1: Air Squat: 20 to 35 reps\n" +
                "   Exercise 2: Push Up: 10 to 20 reps\n" +
                "   Exercise 3: Reverse lunge: 20 reps (10 reps per side)\n" +
                "   Exercise 4: Burpee: 10 to 20 reps\n" +
                "   Exercise 5: Russian Twist: 10 to 20 reps\n" +
                "   Exercise 6: Jump rope\n" +
                "   Exercise 7: V-up: 10 to 20 reps\n\n" +
                "Stretching: 10 mins.");
        
        // Day 3
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "Workout Of the Day (WOD):\n" +
                "Sixteen 2-minute AMRAP(AS MANY ROUNDS AS POSSIBLE) in 32 minutes\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) from 0:00-4:00\n" +
                "       20 Jumping Jacks, 16 Burpees\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) from 4:00-8:00:\n" +
                "       20 Jumping Jacks, 16 Push-Ups\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) from 8:00-12:00:\n" +
                "       20 Jumping Jacks, 16 Air Squats\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) from 12:00-16:00:\n" +
                "       20 Jumping Jacks, 16 Mountain Climbers\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) from 16:00-20:00:\n" +
                "       20 Jumping Jacks. 16 Jumping Jacks\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) from 20:00-24:00:\n" +
                "       20 Jumping Jacks, 16 Jumping Lunges\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) from 24:00-28:00:\n" +
                "       20 Jumping Jacks, 16 High Knees\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) from 28:00-32:00:\n" +
                "       20 Jumping Jacks, 16 Tuck Jumps\n\n" +
                "Stretching: 10 mins.");

        // Day 4
        workoutDescriptions.add("Rest or yoga 30 mins. Thats it. You deserved it!");

        // Day 5
        workoutDescriptions.add("Warm Up: 10 mins\n\n\n" +
                "Workout Of the Day (WOD):\n\n" +
                "   1X: 1mile run, 50 burpees.\n\n" +
                "   2X: 800 m run, 25 air squats.\n\n" +
                "   3X: 400m run, 15 pushups.\n\n\n" +
                "Stretching: 10 mins.");

        // Day 6
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "Practice Wall Walks for 15 mins\n\n" +
                "Workout Of the Day:\n\n" +
                "   AMRAP(AS MANY ROUNDS AS POSSIBLE) in 30 mins: Burpees\n\n" +
                "Stretching: 10 mins.");

        // Day 7
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "Yoga: 20 mins\n\n" +
                "Benchmark Test:\n" +
                "   400m Run\n" +
                "   25 Pushups\n" +
                "   50 Air Squat\n" +
                "   75 Sit-Ups\n" +
                "   400m Run\n\n" +
                "Stretching: 10 mins.");

        // Day 8
        workoutDescriptions.add("Rest or Yoga 30 mins");

        // Day 9
        workoutDescriptions.add("Warm Up: Yoga 10 mins\n\n" +
                "5K Run Goal: 40 mins or less\n\n" +
                "Stretching: 10 mins.");

        // Day 10
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "Workout Of the Day:\n\n" +
                "Buy In: \n" +
                "   Quarter mile run\n" +
                "   40 air squats\n" +
                "   30 sit-ups\n" +
                "   20 burpees\n" +
                "   10 pull-ups\n\n" +
                "Cash Out: \n" +
                "   Another quarter-mile run\n\n" +
                "Stretching: 10 mins.");

        // Day 11
        workoutDescriptions.add("Warm Up: 10 mins\n\n\n" +
                "Practice Wall Walks for 15 mins\n\n" +
                "Workout Of the Day:\n\n" +
                "   100 lunges\n" +
                "   100 jumping squats\n" +
                "   150 sit-ups\n" +
                "   50 air-squats\n" +
                "   50 lunges\n\n\n" +
                "Stretching: 10 mins.");

        // Day 12
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "Workout Of the Day:\n" +
                "2 rounds:\n\n" +
                "   10 push-ups\n" +
                "   1 mile run\n" +
                "   17 air squats\n\n" +
                "Buy out : 58 burpees\n\n" +
                "Stretching: 10 mins.");

        // Day 13
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "Workout Of the Day:\n\n" +
                "   100 Double-Under\n" +
                "       or\n" +
                "   300 Single-Under\n" +
                "       or\n" +
                "   300 Jumping Jacks, 60 squats (with or without weights)\n\n" +
                "   100 Double-Under\n" +
                "       or\n" +
                "   300 Single-Under\n" +
                "       or\n" +
                "   300 jumping jacks, 60 Push-Ups\n" +
                "       or\n" +
                "   Hand Release Push-Ups\n\n" +
                "Goal: Hard effort\n\n" +
                "Stretching: 10 mins.");

        // Day 14
        workoutDescriptions.add("Warm Up: 10 mins\n\n" +
                "Yoga: 15 mins\n\n" +
                "Benchmark Test:\n" +
                "   800m Run\n" +
                "   50 Pushups\n" +
                "   75 Air Squat\n" +
                "   100 Sit-Ups\n" +
                "   800m Run\n\n" +
                "Stretching: 10 mins.");

        // Day 15
        workoutDescriptions.add("Rest or Yoga 30 mins");

        // Day 16
        workoutDescriptions.add(
                "Warm Up: 10 mins\n\n" +
                        "Practice wall walks: 15 mins\n\n" +
                        "Workout Of the Day:\n\n" +
                        "Buy-in: 400m run\n" +
                        "Then (88-66-44)\n\n" +
                        "   88 Push-ups or hand release push-ups\n" +
                        "   88 Sit-Ups\n" +
                        "   66 Push-ups or hand release push-ups\n" +
                        "   66 Sit-Ups\n" +
                        "   44 Push-ups or hand release push-ups\n" +
                        "   44 Sit-Ups\n\n" +
                        "Cash Out: 400m run\n\n" +
                        "Stretching: 10 mins");

        // Day 17
        workoutDescriptions.add(
                "Warm Up: 10 mins\n\n" +
                        "Workout Of the Day:\n\n" +
                        "   150 push-ups\n" +
                        "       Or\n" +
                        "   75 handstand push-ups\n\n" +
                        "Every time you break, perform 5 burpees.\n\n" +
                        "Stretching: 10 mins");

        // Day 18
        workoutDescriptions.add(
                "Repeat Day 2 and try to increase counts.\n\n" +
                        "Warm Up: 10 mins\n\n" +
                        "EMOM (Every minute on the minute) 35 mins (Workout for 45 sec, 15 sec rest)\n" +
                        "   Exercise 1: Air Squat: 20 to 35 reps\n" +
                        "   Exercise 2: Push Up: 10 to 20 reps\n" +
                        "   Exercise 3: Reverse lunge: 20 reps (10 reps per side)\n" +
                        "   Exercise 4: Burpee: 10 to 20 reps\n" +
                        "   Exercise 5: Russian Twist: 10 to 20 reps\n" +
                        "   Exercise 6: Jump rope\n" +
                        "   Exercise 7: V-up: 10 to 20 reps\n\n" +
                        "Stretching: 10 mins");

        // Day 19
        workoutDescriptions.add("60 min jogging or running");

        // Day 20
        workoutDescriptions.add("Rest or Yoga 30 mins");

        // Day 21
        workoutDescriptions.add(
                "Warm Up: 10 Min Yoga\n\n" +
                        "Benchmark Test:\n" +
                        "   1200m Run\n" +
                        "   75 Pushups\n" +
                        "   150 Air Squat\n" +
                        "   200 Sit-Ups\n" +
                        "   1200m Run\n\n" +
                        "Stretching: 10 mins");

        // Day 22
        workoutDescriptions.add(
                "Warm Up: 10 mins\n\n" +
                        "Workout Of the Day:\n\n" +
                        "AMRAP(AS MANY ROUNDS AS POSSIBLE) in 40 mins\n" +
                        "   4 Wall Walks\n" +
                        "   14 jumping air squats\n" +
                        "   24 mountain climbers (each side)\n" +
                        "   34 jumping jacks\n\n" +
                        "Stretching: 10 mins");

        // Day 23
        workoutDescriptions.add(
                "Warm Up: 10 mins\n\n" +
                        "Workout Of the Day:\n\n" +
                        "   10 rounds for time\n" +
                        "   25 air squats\n" +
                        "   50 jumping jacks\n" +
                        "   25 V-ups\n\n" +
                        "Stretching: 10 mins");

        // Day 24
        workoutDescriptions.add(
                "Warm Up: 10 mins\n\n" +
                        "Practice wall walks: 15 min\n\n" +
                        "Workout Of the Day:\n\n" +
                        "   7 rounds for time:\n" +
                        "   7 push-ups\n" +
                        "   7 V-ups\n" +
                        "   7 mountain climbers (each side)\n" +
                        "   7 burpees\n" +
                        "   7 squats\n" +
                        "   7 Sit-ups\n" +
                        "   7 jumping jacks\n\n" +
                        "Stretching: 10 mins");

        // Day 25
        workoutDescriptions.add("Run 5 miles- (Goal within 60 mins)");

        // Day 26
        workoutDescriptions.add(
                "Warm Up: 10 mins\n\n" +
                        "Workout Of the Day:\n\n" +
                        "AMRAP(AS MANY ROUNDS AS POSSIBLE) in 30 mins\n" +
                        "   150 jumping jacks\n" +
                        "   60 plank shoulder taps\n" +
                        "   15 V-ups\n" +
                        "   40 plank shoulder taps\n" +
                        "   15 V-ups\n" +
                        "   20 plank shoulder taps\n" +
                        "   15 V-ups\n\n" +
                        "Stretching: 10 mins");

        // Day 27
        workoutDescriptions.add(
                "Repeat Day 2 and try to increase counts.\n\n" +
                        "Warm Up: 10 mins\n\n" +
                        "EMOM (Every minute on the minute) 35 mins (Workout for 45 sec, 15 sec rest)\n" +
                        "   Exercise 1: Air Squat: 20 to 35 reps\n" +
                        "   Exercise 2: Push Up: 10 to 20 reps\n" +
                        "   Exercise 3: Reverse lunge: 20 reps (10 reps per side)\n" +
                        "   Exercise 4: Burpee: 10 to 20 reps\n" +
                        "   Exercise 5: Russian Twist: 10 to 20 reps\n" +
                        "   Exercise 6: Jump rope\n" +
                        "   Exercise 7: V-up: 10 to 20 reps\n\n" +
                        "Stretching: 10 mins");

        // Day 28
        workoutDescriptions.add("Jogging or running for 60 mins");

        // Day 29
        workoutDescriptions.add("Rest or Yoga for 30 mins");

        // Day 30
        workoutDescriptions.add(
                "Warm Up: 10 Min Yoga\n\n" +
                        "Benchmark Test:\n" +
                        "   1 mile Run\n" +
                        "   100 Pushups\n" +
                        "   200 Air Squat\n" +
                        "   300 Sit-Ups\n" +
                        "   1 mile run\n\n" +
                        "Stretching: 10 mins");
    }
}