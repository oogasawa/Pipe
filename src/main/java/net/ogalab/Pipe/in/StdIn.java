/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ogalab.Pipe.in;

/**
 *
 * @author oogasawa
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;

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
            RuntimeExceptionUtil.invoke(e, "Runtime exception in FileIn.getLine() ");
        }
        return line;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            RuntimeExceptionUtil.invoke(e, "Runtime exception in FileIn.close() ");
        }
    }
}
