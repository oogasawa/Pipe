package com.github.oogasawa.Pipe.in;

/**
 *
 * @author oogasawa
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Pipe;

public class FileIn implements In {

    private BufferedReader reader;

    public FileIn(String filename) throws FileNotFoundException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
    }

    public String getLine() {
        String line = Pipe.END;
        try {
            line = reader.readLine();
            if (line == null)
                line = Pipe.END;

        } catch (IOException e) {
            System.err.println("Runtime exception in FileIn.getLine() ");
            e.printStackTrace();
            System.exit(-1);
        }
        return line;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Runtime exception in FileIn.close() ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
