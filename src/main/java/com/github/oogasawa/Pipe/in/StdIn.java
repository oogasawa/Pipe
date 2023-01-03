package com.github.oogasawa.Pipe.in;

/**
 *
 * @author oogasawa
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Pipe;


public class StdIn implements In {

    private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
    
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
            logger.throwing("com.github.oogasawa.Pipe.in.StdIn", "getLine", e);
        }
        return line;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            logger.throwing("com.github.oogasawa.Pipe.in.StdIn", "close", e);
        }
    }
}
