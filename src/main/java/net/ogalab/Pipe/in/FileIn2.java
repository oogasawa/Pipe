package net.ogalab.Pipe.in;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;

public class FileIn2 implements In {

    private BufferedReader reader = null;
    StringBuilder sb = new StringBuilder();
    
    public FileIn2(String filename) throws FileNotFoundException {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileIn2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public String getLine() {
        
        if (reader == null)
            return Pipe.END;
        
        StringBuffer buffer = new StringBuffer();
        
        try {
            int state = 0;
            int ch;
            while ((ch = reader.read()) > -1) {
                char ch2 = (char)ch;
                if (ch2 < ' ' && ch2 != '\n' && ch2 != '\t') // control character.
                    buffer.append(' ');
                else if (ch2 == '\n') { 
                    state = 1;
                    break;
                }
                else
                    buffer.append((char)ch);
            }
            
            String line = buffer.toString();
            if (ch > -1) { // break by newline.
                
            }
            else {
                
            }
            if (state == 0 && line.length() == 0) {
                reader.close();
                reader = null;
                return Pipe.END;
            } 
                
            else if (ch < 0) {
                reader.close();
                reader = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileIn2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return buffer.toString();            
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            RuntimeExceptionUtil.invoke(e, "Runtime exception in FileIn.close() ");
        }
    }
}
