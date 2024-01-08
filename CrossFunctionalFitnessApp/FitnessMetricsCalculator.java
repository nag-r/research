import java.util.*;
import javax.swing.*;

/**
 * FitnessMetricsCalculator is a utility class that provides static methods to
 * calculate various fitness metrics. It includes methods for calculating average 
 * workout time, total time spent exercising, consistency score,
 * and identifying personal bests in workouts. The calculations are based on a
 * list of WorkoutData objects.
 * 
 * @author Nag Rajendran 
 * @version Dec 11 2023
 */
public class FitnessMetricsCalculator {
    // Total number of workouts planned in the program
    public static final int TOTAL_PLANNED_WORKOUTS = 30;

    /**
     * Calculates the average time taken per workout.
     * It sums the time taken for each workout and divides 
     * it by the total number of workouts.
     * If the list of workouts is null or empty, it returns 0.0.
     *
     * @param workouts List of WorkoutData objects.
     * @return The average time taken for workouts as a double.
     */
    public static double calculateAverageTime(List<WorkoutData> workouts) {
        // Check if the list is null or empty
        if (workouts == null || workouts.isEmpty()) {
            return 0.0;
        }

        int totalTime = 0; // Total time taken for all workouts
        int completedWorkouts = 0; // Number of workouts completed

        // Sum the time taken for each workout
        for (WorkoutData workout : workouts) {
            if (workout.isCompleted()) { // Check if the workout was completed
                try {
                    totalTime += Integer.parseInt(workout.getTimeTaken()); // Add the time taken for each workout
                    completedWorkouts++; // Increment the number of completed workouts
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Display an error message if the time taken cannot be parsed
                    JOptionPane.showMessageDialog(null, "Error parsing time taken for workout", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        if (completedWorkouts == 0) {
            return 0.0; // Avoid division by zero if no workouts were completed
        }

        return totalTime / (double) completedWorkouts; // Return the average time for completed workouts
    }
    

    /**
     * Calculates the total time spent on workouts.
     * It adds up the time taken for each workout in the list. If the list is null
     * or empty, it returns 0.
     *
     * @param workouts List of WorkoutData objects.
     * @return The total time spent on workouts as an integer.
     */
    public static int calculateTotalTime(List<WorkoutData> workouts) {
        // Check if the list is null or empty
        if (workouts == null || workouts.isEmpty()) {
            return 0;
        }

        int totalTime = 0; // Total time taken for all workouts

        for (WorkoutData workout : workouts) {
            try {
                totalTime += Integer.parseInt(workout.getTimeTaken()); // Add the time taken for each workout
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Display an error message if the time taken cannot be parsed
                JOptionPane.showMessageDialog(null, "Error parsing time taken for workout", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return totalTime;
    }
    

    /**
     * Calculates the consistency score as a percentage.
     * The score is calculated as the ratio of completed workouts to total planned
     * workouts multiplied by 100.
     * If the list is null or empty, or if totalPlannedWorkouts is zero, the method
     * returns 0.0.
     *
     * @param workouts             List of WorkoutData objects.
     * @param totalPlannedWorkouts Total number of planned workouts.
     * @return The consistency score as a percentage.
     */
    public static double calculateConsistencyScore(List<WorkoutData> workouts, int totalPlannedWorkouts) {
        // Check if the list is null or empty
        if (workouts == null || workouts.isEmpty()) {
            return 0.0;
        }

        // Check if totalPlannedWorkouts is zero
        if (totalPlannedWorkouts == 0) {
            throw new IllegalArgumentException("Total planned workouts must not be zero");
        }

        int completedWorkouts = 0; // Number of completed workouts

        // Count the number of completed workouts        
        for (WorkoutData workout : workouts) {
            if (workout.isCompleted()) {
                completedWorkouts++;
            }
        }

        return (completedWorkouts / (double) totalPlannedWorkouts) * 100; // Return the consistency score
    }


    /**
     * Identifies the personal best workout based on the longest time taken.
     * It compares the time taken for each workout and returns the one with the
     * maximum time. If the list is null or empty, it returns a message indicating 
     * no data is available.
     *
     * @param workouts List of WorkoutData objects.
     * @return A string representing the personal best workout.
     */
    public static String calculatePersonalBests(List<WorkoutData> workouts) {
        // Check if the list is null or empty
        if (workouts == null || workouts.isEmpty()) {
            return "No data available";
        }

        WorkoutData personalBest = workouts.get(0); // Assume the first workout is the personal best
        
        for (WorkoutData workout : workouts) {
            try {
                // Compare the time taken for each workout and update the personal best
                if (Integer.parseInt(workout.getTimeTaken()) > Integer.parseInt(personalBest.getTimeTaken())) {
                    personalBest = workout; // Update the personal best
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Display an error message if the time taken cannot be parsed
                JOptionPane.showMessageDialog(null, "Error parsing time taken for workout", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }            
        }
        return String.format("%s minutes on %s", personalBest.getTimeTaken(), personalBest.getDay());
    }
}