package com.lockedme.operations;

import com.lockedme.config.Config;
import com.lockedme.exception.MenuInputException;

public class BusinessOperationsMenu {

    private FileOperations fileOperation = new FileOperations(Config.ROOT_DIR);

    private void displayBusinessOperationsMenu() {
    	System.out.println("\n\t\t--------------------");
        System.out.println("\t\tBUSINESS OPERATIONS");
        System.out.println("\t\t--------------------");
        System.out.println("\t\t1. Add a file");
        System.out.println("\t\t2. Delete a file");
        System.out.println("\t\t3. Search for a file");
        System.out.println("\t\t4. Return to main menu\n");
        System.out.print("\t\tEnter your choice: ");
    }

    
    public void menu() {
        while (true) {
            displayBusinessOperationsMenu();

            String input = Config.SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("\t\t❌ Error: Please insert a valid input.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        fileOperation.addFile();
                        break;
                    case 2:
                        fileOperation.deleteFile();
                        break;
                    case 3:
                        fileOperation.searchFile();
                        break;
                    case 4:
                        return;
                    default:
                        throw new MenuInputException("\t\t❌ Error: Invalid choice. Please choose a number between 1-4.");
                }
            } catch (MenuInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("\t\t❌ Error: Please enter a valid number.");
            }
        }
    }
}