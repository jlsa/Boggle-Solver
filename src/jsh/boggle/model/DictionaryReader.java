package jsh.boggle.model;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.nio.charset.Charset;

/**
 * @author Joël Hoekstra
 */
public class DictionaryReader {
    public static String[] read(String filename) {
        ArrayList<String> words = new ArrayList<>();

        long startTimeNano = System.nanoTime();
        long startTime = System.currentTimeMillis();
        Charset charset = Charset.forName("ISO-8859-1"); // The file is not "UTF-8" @todo check the file type
        //ISO-8859-1

        int n = 0;
        Path path = Paths.get(Model.class.getResource(filename).getPath());
        try {
            String line;
            try (BufferedReader reader = Files.newBufferedReader(path,charset)) {
                while ((line = reader.readLine()) != null) {
                    words.add(line.toLowerCase());
                    n++;
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch(Exception e) {
            System.err.println(e);
        }
        long endTime = System.currentTimeMillis();
        long endTimeNano = System.nanoTime();

        System.out.println("Reading word file and adding it to the dictionary took " + (endTime - startTime) + " ms");
        System.out.println("Reading word file and adding it to the dictionary took " + (endTimeNano - startTimeNano) + " nano seconds");
        System.out.println("There are " + n + " words added to the dictionary.");
        return words.toArray(new String[words.size()]);
    }
}
