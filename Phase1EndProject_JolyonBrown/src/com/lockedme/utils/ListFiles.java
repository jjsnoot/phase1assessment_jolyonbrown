package com.lockedme.utils;

import com.lockedme.config.Config;
import java.io.File;
import java.util.Arrays;

public class ListFiles {

    // list files in root directory
    public static void listFiles() {
        File directory = new File(Config.ROOT_DIR);
        String[] files = directory.list();

        // check if directory is empty
        if (files == null || files.length == 0) {
            System.out.println("\tThe directory is empty.");
            return;
        }

        // display files 
        System.out.println("\n\tFiles in ascending order:");
        Arrays.stream(files)
              .sorted()
              .forEach(filename -> System.out.println("\t\t" + filename));
    }
}
