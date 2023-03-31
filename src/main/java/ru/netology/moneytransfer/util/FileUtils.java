package ru.netology.moneytransfer.util;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class FileUtils {
    public static Boolean createNewFile(String path, String fileName) {
        File file = new File(path, fileName);

        try {
            if (!file.exists()) {
                return file.createNewFile();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static void writeTextToFile(String path, String file, String text) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + file, true))) {
            writer.write(text + "\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            throw new IOException(ex);
        }
    }
}
