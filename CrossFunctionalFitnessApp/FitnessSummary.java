import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * FitnessSummary is a JFrame subclass that provides a graphical user interface
 * to display various fitness-related metrics. It showcases the user's workout
 * performance in terms of average time per workout, total time spent exercising,
 * consistency score, and personal bests in terms of Longest Workout. The class is 
 * designed to be user-friendly, providing tooltips and a help button for additional 
 * information.
 * 
 * @author Nag Rajendran 
 * @version Dec 11 2023
 */
public class FitnessSummary extends JFrame {

    // Widgets for displaying fitness metrics
    private JLabel lblAverageTimePerWorkout = new JLabel("Average Time Per Workout (minutes): ");
    private JLabel lblTotalTimeSpentExercising = new JLabel("Total Time Spent Exercising (minutes): ");
    private JLabel lblConsistencyScore = new JLabel("Consistency Score (%): ");
    private JLabel lblPersonalBests = new JLabel("Longest Workout (minutes): ");

    private JTextField txtAverageTimePerWorkout = new JTextField(10);
    private JTextField txtTotalTimeSpentExercising = new JTextField(10);
    private JTextField txtConsistencyScore = new JTextField(10);
    private JTextField txtPersonalBests = new JTextField(10);

    private JButton btnHelp = new JButton("Help");
    
    /**
     * Constructor for FitnessSummary.
     * Initializes the layout of the components and adds listeners to widgets.
     */
    public FitnessSummary() {
        layoutComponents();
        addListeners();
    }


    /**
     * Sets up the layout of the program's graphical user interface.
     * It defines the placement of labels, text fields, and the help button.
     * The layout is organized for clear readability and ease of use.
     */
    private void layoutComponents() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Fitness Summary");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned
        this.add(topPanel, BorderLayout.NORTH); // Add to the top of the JFrame
        topPanel.add(btnHelp); // Add the help button to the top panel

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns, 10px hgap and vgap
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10px padding on all sides

        // Tooltips provide additional information on what each metric represents
        lblAverageTimePerWorkout.setToolTipText("Average duration of each completed workout.");
        lblTotalTimeSpentExercising.setToolTipText("Total time spent on all workouts.");
        lblConsistencyScore.setToolTipText("Percentage of planned workouts that were completed.");
        lblPersonalBests.setToolTipText("Longest time spent on a single workout.");

        // Add the labels and text fields to the panel
        panel.add(lblAverageTimePerWorkout);
        panel.add(txtAverageTimePerWorkout);
        panel.add(lblTotalTimeSpentExercising);
        panel.add(txtTotalTimeSpentExercising);
        panel.add(lblConsistencyScore);
        panel.add(txtConsistencyScore);
        panel.add(lblPersonalBests);
        panel.add(txtPersonalBests);

        this.add(panel, BorderLayout.CENTER); // Add the panel to the center of the JFrame

        // Set the text fields to read-only
        txtAverageTimePerWorkout.setEditable(false);
        txtTotalTimeSpentExercising.setEditable(false);
        txtConsistencyScore.setEditable(false);
        txtPersonalBests.setEditable(false);

        // Optimize JFrame's size
        this.setPreferredSize(new Dimension(600, 300));
        this.pack();
        this.setLocationRelativeTo(null); // Center the window
    }
    

    /**
     * Updates the text fields with the provided fitness metrics.
     * This method is called to refresh the UI with new or updated data.
     *
     * @param averageTime      The average time per workout.
     * @param totalTime        The total time spent exercising.
     * @param consistencyScore The consistency score as a percentage.
     * @param personalBests    The record for the longest workout.
     */
    public void updateMetrics(String averageTime, String totalTime, String consistencyScore, String personalBests) {
        // Validate and update the text fields with the provided metrics                       
        try {
            // Update average time if valid, otherwise set to "0"
            if (averageTime != null) {
                Double.parseDouble(averageTime);
                txtAverageTimePerWorkout.setText(averageTime);
            } else {
                txtAverageTimePerWorkout.setText("0");
            }

            // Update total time if valid, otherwise set to "0"
            if (totalTime != null) {
                Double.parseDouble(totalTime);
                txtTotalTimeSpentExercising.setText(totalTime);
            } else {
                txtTotalTimeSpentExercising.setText("0");
            }

            // Update consistency score if valid, otherwise set to "0"
            if (consistencyScore != null) {
                Double.parseDouble(consistencyScore);
                txtConsistencyScore.setText(consistencyScore);
            } else {
                txtConsistencyScore.setText("0");
            }
        } catch (NumberFormatException e) {
            // Show error dialog if provided metrics are not valid numbers
            JOptionPane.showMessageDialog(null, "Average time, total time, and consistency score must be valid numbers",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Update personal bests if valid, otherwise set to empty string
        if (personalBests == null) {
            personalBests = "";
        } else {
            txtPersonalBests.setText(personalBests);
        }
    }
    

    /**
     * Adds listeners to the widgets, particularly the help button.
     * The help button provides additional information about the fitness metrics.
     */
    private void addListeners() {
        btnHelp.addActionListener(new ActionListener() {
            /**
             * Invoked when the help button is clicked.
             * Displays a message dialog with information about the fitness summary metrics.
             *
             * @param e The ActionEvent object generated by the button click.
             */
            public void actionPerformed(ActionEvent e) {
                // Display help information for understanding fitness metrics
                JOptionPane.showMessageDialog(null,
                        "Get to know your fitness summary metrics:\n" +
                        "----------------------------------\n\n" +
                        "[1] Average Time Per Workout: Average duration of each completed workout.\n" +
                        "[2] Total Time Spent Exercising: Total time spent on all workouts.\n" +
                        "[3] Consistency Score: Percentage of planned workouts that were completed.\n" +
                        "[4] Longest Workout: Longest time spent on a single workout.\n\n" +
                        "Note: The metrics are calculated based on the workouts that have been marked as completed.");
            }
        });
    }
}