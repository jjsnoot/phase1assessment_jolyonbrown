package com.lockedme.operations;

import com.lockedme.config.Config;

import java.io.File;

public class FileOperation {

    private String rootDir;

    public FileOperation(String rootDir) {
        this.rootDir = rootDir;
    }

    public void addFile() {
        System.out.print("\t\tEnter the name of the file to add: ");
        String fileName = Config.SCANNER.nextLine();
        File file = getFile(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("\t\t✔ File added successfully.");
            } else {
                System.out.println("\t\t❌ File already exists.");
            }
        } catch (Exception e) {
            System.out.println("\t\t❌ An error occured: " + e.getMessage());
        }
    }

    public void deleteFile() {
        System.out.print("\t\tEnter the name of the file to delete: ");
        String fileName = Config.SCANNER.nextLine();
        File file = getFile(fileName);
        if (fileExists(file)) {
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
        System.out.print("\t\tEnter the name of the file to search: ");
        String fileName = Config.SCANNER.nextLine();
        File file = getFile(fileName);
        if (fileExists(file)) {
            System.out.println("\t\t✔ File found.");
        } else {
            System.out.println("\t\t❌ File not found.");
        }
    }

    private File getFile(String fileName) {
        return new File(rootDir + fileName);
    }

    private boolean fileExists(File file) {
        return file.exists();
    }
}
