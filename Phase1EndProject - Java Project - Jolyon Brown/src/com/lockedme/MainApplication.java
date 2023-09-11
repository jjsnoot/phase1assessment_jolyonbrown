package com.lockedme;

import com.lockedme.operations.BusinessOperations;
import com.lockedme.utils.FileUtility;
import com.lockedme.config.Config;
import com.lockedme.exception.MenuInputException;
import java.io.File;

public class MainApplication {

    // Constants for menu options
    private static final int OPTION_LIST_FILES = 1;
    private static final int OPTION_BUSINESS_OPERATIONS = 2;
    private static final int OPTION_EXIT = 3;

    public static void main(String[] args) {
        ensureDirectoryExists();
        WelcomeScreen.display();
        mainMenu();
    }

    private static void ensureDirectoryExists() {
        File dir = new File(Config.ROOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private static void mainMenu() {
    	 BusinessOperations businessOperations = new BusinessOperations();
    	 
        while (true) {
            displayMainMenuOptions();

            String input = Config.SCANNER.nextLine().trim();

            try {
                if (input.isEmpty()) {
                    throw new MenuInputException("\t❌ Error: No option entered.");
                }

                int choice = Integer.parseInt(input);

                switch (choice) {
                    case OPTION_LIST_FILES:
                        FileUtility.listFiles();
                        break;
                    case OPTION_BUSINESS_OPERATIONS:
                        businessOperations.menu();
                        break;
                    case OPTION_EXIT:
                        exitApplication();
                        break;
                    default:
                        throw new MenuInputException("❌ Error: Invalid choice. Please choose a number between 1-3.");
                }
            } catch (MenuInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                if (input.length() > 1) {
                    System.out.println("\t❌ Error: Please enter only one character.");
                } else {
                    System.out.println("\t❌ Error: That's not a number.");
                }
            }
        }
    }



    private static void displayMainMenuOptions() {
        System.out.println("\n\t---------");
        System.out.println("\tMAIN MENU");
        System.out.println("\t---------");
        System.out.println("\t" + OPTION_LIST_FILES + ". List files in ascending order");
        System.out.println("\t" + OPTION_BUSINESS_OPERATIONS + ". Business-level operations");
        System.out.println("\t" + OPTION_EXIT + ". Close the application\n");
        System.out.print("	Enter your choice: ");
    }

    private static void exitApplication() {
        System.out.println("\n------------------------------------------");
        System.out.println("Thank you for using LockedMe.com. Goodbye!");
        System.out.println("------------------------------------------");
        System.exit(0);
    }
}
