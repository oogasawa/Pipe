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


public class StdIn implements In {

    private BufferedReader reader;

    public StdIn() throws FileNotFoundException {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getLine() {
        String line = Pipe.END;
        try {
            line = reader.readLine();
            if (line == null) {
                line = Pipe.END;
            }

        } catch (IOException e) {
            System.err.println("Runtime exception in StdIn.getLine() ");
            e.printStackTrace();
            System.exit(-1);
        }
        return line;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Runtime exception in StdIn.close() ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
