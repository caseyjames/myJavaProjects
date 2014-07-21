package assignment9;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * represents the user controller software for remote device utilization
 *
 * @author Paymon Saebi
 * @author Casey Nordgran
 * @author Cody Cortello
 * @version 7/16/2014
 */
public class UserController {
    /**
     * @param args
     */
    public static void main(String[] args) {
        args = new String[10];
        args[0] = "tasks.txt";

        // check that atleast one argument was passed
        if (args.length == 0) {
            System.out.println("You must give atleast one parameter!");
            return;
        }
        // task list file
        File taskList = new File(args[0]);
        //Ensure that the args[0] parameter given is a valid file
        if (!taskList.isFile()) {
            System.out.println("Unable to use the file " + args[0] + "!");
            return;
        }

        // here the file must be good, invoke run_task_manager method below
        run_task_manager(taskList);

        //TO DO:
        //Ensure that the user has given at least one parameter
        //If not, print an error message about it and return

        //TO DO:
        //Create a file object from args[0] parameter
        //Ensure that the parameter given is a valid file

        //TO DO:
        //If the file is valid and everything is good, run the method below
        //If not, then print an error message about it and return
    }

    /**
     *
     */
    public static void run_task_manager(File tasks) {
        System.out.println("System task manager is initialized ...");

        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        TaskManager manager = new TaskManager(tasks);

        manager.runTask();

        while (true) {
            if (manager.isDone()) {
                System.out.println("\nAll tasks are done, Have a nice day...");
                scanner.close();
                return;
            }

            String input = scanner.nextLine();

            if (input.equals("task")) {
                System.out.println("\nCurrent task: " + manager.getTask());
            } else if (input.equals("run")) {
                manager.runTask();
            } else if (input.equals("next")) {
                System.out.println("\nNext task: " + manager.nextTask());
            } else if (input.equals("exit")) {
                System.out.println("\nSystem task manager has exited ...");
                scanner.close();
                return;
            }

            while (manager.getTask().getTaskName().equals("user_comm")) {
                input = scanner.nextLine();

                if (input.equals("done")) {
                    System.out.println("\nUser command connection has been shut down...");
                    break;
                } else
                    manager.sendComm(input);
            }
        }
    }
}
