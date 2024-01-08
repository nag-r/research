import java.io.*;
import javax.swing.*;

/**
 * Represents the data for a workout, including the day, completion status, and
 * time taken. This class encapsulates all relevant information about a workout and provides
 * functionality to save and load this data to and from a file.
 * 
 * @author Nag Rajendran 
 * @version Dec 11 2023
 */
public class WorkoutData {

    private String day;         // The specific day of the workout
    private boolean completed;  // Whether the workout was completed
    private String timeTaken;   // The time taken for the workout
    
    /**
     * Constructor to initialize a WorkoutData object.
     *
     * @param day       The day of the workout.
     * @param completed Whether the workout was completed.
     * @param timeTaken The time taken to complete the workout.
     */
    public WorkoutData(String day, boolean completed, String timeTaken) {
        this.day = day;
        this.completed = completed;
        this.timeTaken = timeTaken;
    }
    

    /**
     * Returns the day of the workout.
     *
     * @return The day of the workout.
     */
    public String getDay() {
        return day;
    }


    /**
     * Sets the day of the workout.
     *
     * @param day The day of the workout.
     */
    public void setDay(String day) {
        this.day = day;
    }


    /**
     * Returns whether the workout was completed.
     *
     * @return Whether the workout was completed.
     */
    public boolean isCompleted() {
        return completed;
    }


    /**
     * Sets whether the workout was completed.
     *
     * @param completed Whether the workout was completed.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    /**
     * Returns the time taken for the workout.
     *
     * @return The time taken for the workout.
     */
    public String getTimeTaken() {
        return timeTaken;
    }


    /**
     * Sets the time taken for the workout.
     *
     * @param timeTaken The time taken for the workout.
     */
    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }


    /**
     * Returns a string representation of the workout data.
     *
     * @return A string representation of the workout data.
     */
    public String toString() {
        return "Day: " + day + ", Completed: " + completed + ", Time Taken: " + timeTaken;
    }


    /**
     * Saves the workout data to a text file.
     * The file is named based on the day number (e.g., "day1workout.txt") and
     * contains the workout data.
     *
     * @param workout The WorkoutData object to save.
     * @param day     The day number of the workout.
     */
    public static void saveWorkout(WorkoutData workout, int day) {

        // Create the filename based on the day number
        String filename = "day" + day + "workout.txt";

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.println(workout); // Write the workout data to the file
            writer.close(); // Close the file
        } catch (IOException e) {
            // Show error dialog if an error occurred while saving the data
            JOptionPane.showMessageDialog(null, "An error occurred while saving the data", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Loads workout data from a text file.
     * Reads the workout data for a specific day from a file named (e.g.,
     * "day1workout.txt"). If the file cannot be read, it returns default 
     * workout data indicating the workout was not completed.
     *
     * @param day The day number for which to load the workout data.
     * @return A WorkoutData object containing the loaded or default data.
     */
    public static WorkoutData loadWorkouts(int day) {

        // Create the filename based on the day number
        String filename = "day" + day + "workout.txt";
        WorkoutData defaultWorkoutData = new WorkoutData("Day " + day, false, "0");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line; // The current line in the file
            boolean parsedCompleted = false; // Whether the workout was completed
            String parsedTimeTaken = ""; // The time taken for the workout

            // Read the file line by line            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // Split the line into parts
                parsedCompleted = Boolean.parseBoolean(parts[1].split(": ")[1]); // Parse the completed status
                parsedTimeTaken = parts[2].split(": ")[1]; // Parse the time taken
            }

            return new WorkoutData("Day " + day, parsedCompleted, parsedTimeTaken); // Return the loaded workout data

        } catch (IOException e) {
            // Return default data if file cannot be read
            return defaultWorkoutData;
        }
    }
}