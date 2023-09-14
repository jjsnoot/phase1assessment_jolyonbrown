package com.lockedme.operations;

import com.lockedme.config.Config;

import java.io.File;

public class FileOperations {

    private String rootDir;

    // initialise rootDir
    public FileOperations(String rootDir) {
        this.rootDir = rootDir;
    }

    // prompt for file name
    private String promptForFileName(String promptMessage) {
        System.out.print("\t\t" + promptMessage);
        return Config.SCANNER.nextLine().trim();
    }

    // return a File object from file name
    private File getFile(String fileName) {
        return new File(rootDir + fileName);
    }

    // add a file to root directory
    public void addFile() {
        String fileName = promptForFileName("Enter the name of the file to add: ");
        File directory = new File(rootDir);
        File[] fileList = directory.listFiles();
        
        // check if file exists
        if (fileList != null) {
            for (File file : fileList) {
                if (file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("\t\t❌ File already exists.");
                    return;
                }
            }
        }

        // create new file
        File newFile = getFile(fileName);
        try {
            if (newFile.createNewFile()) {
                System.out.println("\t\t✔ File added successfully.");
            }
        } catch (Exception e) {
            System.out.println("\t\t❌ An error occurred: " + e.getMessage());
        }
    }

    // delete file from directory
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

    // search for file in root directory
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
