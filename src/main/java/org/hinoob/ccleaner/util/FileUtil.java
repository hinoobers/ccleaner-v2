package org.hinoob.ccleaner.util;

import java.io.File;

public class FileUtil {

    public static boolean isDirectoryEmpty(File directory) {
        // Check for subfolders
        if(directory.listFiles() == null || directory.listFiles().length == 0) {
            return true;
        }

        for(File file : directory.listFiles()) {
            if(file.isDirectory()) {
                if(!isDirectoryEmpty(file)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    public static void delete(File file) {
        if(file.isDirectory()) {
            for(File f : file.listFiles()) {
                delete(f);
            }
        }

        file.delete();
    }
}
