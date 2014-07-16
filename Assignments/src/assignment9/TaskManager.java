package assignment9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Represents an arbitrary task management infrastructure
 *
 * @author Paymon saebi
 * @author
 * @author
 */
public class TaskManager {
    private Task currentTask;
    private DeviceManager comms;
    private PriorityQueueHEAP<Task> PQ;

    /**
     * @param tasks - contains the tasks with priority groups and levels
     */
    public TaskManager(File tasks) {
        PQ = new PriorityQueueHEAP<Task>(new TaskComparator());
        populateTasks(tasks);
    }

    /**
     * @param command
     */
    public void sendComm(String command) {
        comms.sendCommand(command);
    }

    /**
     * @return
     */
    public boolean isDone() {
        return this.PQ.size() == 0;
    }

    /**
     * @return
     */
    public Task getTask() {
        return this.currentTask;
    }

    /**
     * @return
     */
    public Task nextTask() {
        return this.PQ.findMin();
    }

    /**
     * @return
     */
    public Task runTask() {
        currentTask = this.PQ.deleteMin();

        System.out.println("\nRunning prioritized task: " + currentTask);

        try //Emulating task execution delay
        {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        if (currentTask.getTaskName().equals("user_init")) {
            comms = new DeviceManager();
            System.out.println("User command connection has been initialized...");
        } else if (currentTask.getTaskName().equals("user_comm")) {
            System.out.println("Please enter new device command to be sent...");
        } else
            System.out.println("System Task \"" + currentTask.getTaskName() + "\" has been executed...");

        return currentTask;
    }

    /**
     * @param tasks
     */
    public void populateTasks(File tasks) {
        try {
            Scanner fileInput = new Scanner(tasks);

            int level = 0;
            char group = 0;
            String name = "";
            String token = "";

            while (fileInput.hasNextLine()) {
                token = fileInput.next();

                if (!token.equals(""))
                    name = token;

                token = fileInput.next();

                if (!token.equals(""))
                    group = token.charAt(0);

                token = fileInput.next();

                if (!token.equals(""))
                    level = Integer.parseInt(token);

                this.PQ.add(new Task(name, group, level));
            }

            fileInput.close();
            //this.PQ.generateDotFile("tasks.dot");
        } catch (FileNotFoundException e) {
            System.err.println("File " + tasks + " cannot be found.");
        }
    }

    /**
     * Represents the information to define a system task
     * Each task has name, a priority group A-Z, and a priority level 1-100
     * Lower values on group and level denote a higher priority
     *
     * @author Paymon Saebi
     */
    public class Task {
        private String taskName;
        private char priorityGroup;
        private int priorityLevel;

        /**
         * @param _taskName
         * @param _priorityGroup
         * @param _priorityLevel
         */
        public Task(String _taskName, char _priorityGroup, int _priorityLevel) {
            this.taskName = _taskName;
            this.priorityGroup = _priorityGroup;
            this.priorityLevel = _priorityLevel;
        }

        /**
         *
         */
        public String toString() {
            return "(Task: " + taskName + ", Group: " + priorityGroup + ", Level: " + priorityLevel + ")";
        }

        /**
         * @return
         */
        public String getTaskName() {
            return this.taskName;
        }

        /**
         * @return
         */
        public char getPriorityGroup() {
            return this.priorityGroup;
        }

        /**
         * @return
         */
        public int getPriorityLevel() {
            return this.priorityLevel;
        }
    }

    /**
     * Comparator class for TaskManager that compares objects of type Task.
     * Implements the compare method.
     */
    protected class TaskComparator implements Comparator<Task> {
        // implement compare method
        public int compare(Task lhs, Task rhs) {
            char leftGroup = lhs.getPriorityGroup(), rightGroup = rhs.getPriorityGroup();
            if (leftGroup != rightGroup) {
                // if the lhs is less than rhs, mean rhs is closer to Z, return -1
                if (leftGroup < rightGroup)
                    return -1;
                else if (leftGroup > rightGroup)
                    return 1;
            }
            // here leftGroup & rightGroup must be equal, tiebreaker is the task's priority level
            int leftLevel = lhs.getPriorityLevel(), rightLevel = rhs.getPriorityLevel();
            if (leftLevel < rightLevel)
                return -1;
            else if (leftLevel > rightLevel)
                return 1;
            // if this point is reached than lhs & rhs must be equal, return 0
            return 0;
        }
    }

}
