package com.lockedme.utils;

import com.lockedme.config.Config;
import java.io.File;
import java.util.Arrays;

public class FileUtility {

    public static void listFiles() {
        File directory = new File(Config.ROOT_DIR);
        String[] files = directory.list();

        if (files == null || files.length == 0) {
            System.out.println("\tThe directory is empty.");
            return;
        }

        System.out.println("\n\tFiles in ascending order:");
        Arrays.stream(files)
              .sorted()
              .forEach(filename -> System.out.println("\t\t" + filename));
    }
}
