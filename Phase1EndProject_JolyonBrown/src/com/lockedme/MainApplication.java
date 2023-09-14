package com.lockedme;

import com.lockedme.operations.BusinessOperationsMenu;
import com.lockedme.utils.ListFiles;
import com.lockedme.config.Config;
import com.lockedme.exception.MenuInputException;
import java.io.File;

public class MainApplication {

    // constants for menu options
    private static final int OPTION_LIST_FILES = 1;
    private static final int OPTION_BUSINESS_OPERATIONS = 2;
    private static final int OPTION_EXIT = 3;

    public static void main(String[] args) {
        // ensure root directory exists
        ensureDirectoryExists();
        // display welcome screen
        WelcomeScreen.display();
        // load  main menu
        mainMenu();
        // close the scanner 
        Config.SCANNER.close();
    }

    // check and create root directory if needed
    private static void ensureDirectoryExists() {
        File dir = new File(Config.ROOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // display main menu 
    private static void displayMainMenuOptions() {
        System.out.println("\n\t---------");
        System.out.println("\tMAIN MENU");
        System.out.println("\t---------");
        System.out.println("\t" + OPTION_LIST_FILES + ". List files in ascending order");
        System.out.println("\t" + OPTION_BUSINESS_OPERATIONS + ". Business-level operations");
        System.out.println("\t" + OPTION_EXIT + ". Close the application\n");
        System.out.print("	Enter your choice: ");
    }
    
    // main menu logic
    private static void mainMenu() {
        BusinessOperationsMenu businessOperations = new BusinessOperationsMenu();
         
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
                        // list files in root directory
                        ListFiles.listFiles();
                        break;
                    case OPTION_BUSINESS_OPERATIONS:
                        // go to business operations
                        businessOperations.menu();
                        break;
                    case OPTION_EXIT:
                        // exit the application
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

    // display exit message and exit the program
    private static void exitApplication() {
        System.out.println("\n\t---------------------------------");
        System.out.println("\tExiting the Application. Goodbye!");
        System.out.println("\t---------------------------------");
        System.exit(0);
    }
}
