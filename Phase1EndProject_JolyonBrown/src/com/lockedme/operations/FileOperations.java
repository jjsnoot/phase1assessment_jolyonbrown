package com.lockedme.operations;

import com.lockedme.config.Config;

import java.io.File;

public class FileOperations {

    private String rootDir;

    public FileOperations(String rootDir) {
        this.rootDir = rootDir;
    }

    // Helper method to get a file name from the user
    private String promptForFileName(String promptMessage) {
        System.out.print("\t\t" + promptMessage);
        return Config.SCANNER.nextLine().trim();
    }

    // Helper method to get a File object based on the file name
    private File getFile(String fileName) {
        return new File(rootDir + fileName);
    }

    public void addFile() {
        System.out.print("\t\tEnter the name of the file to add: ");
        String fileName = Config.SCANNER.nextLine();
        File directory = new File(rootDir);
        File[] fileList = directory.listFiles();
        
        if (fileList != null) {
            for (File file : fileList) {
                if (file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("\t\t❌ File already exists.");
                    return;
                }
            }
        }

        File newFile = getFile(fileName);
        try {
            if (newFile.createNewFile()) {
                System.out.println("\t\t✔ File added successfully.");
            }
        } catch (Exception e) {
            System.out.println("\t\t❌ An error occurred: " + e.getMessage());
        }
    }


    public void deleteFile() {
        String fileName = promptForFileName("Enter the name of the file to delete: ");
        File file = getFile(fileName);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("\t\t✔ File deleted successfully.");
            } else {
                System.out.println("\t\t❌ Error: Failed to delete the file.");
            }
        } else {
            System.out.println("\t\t❌ File not found.");
        }
    }

    public void searchFile() {
        String fileName = promptForFileName("Enter the name of the file to search:");
        File file = getFile(fileName);
        if (file.exists()) {
            System.out.println("\t\t✔ File found.");
        } else {
            System.out.println("\t\t❌ File not found.");
        }
    }
}
