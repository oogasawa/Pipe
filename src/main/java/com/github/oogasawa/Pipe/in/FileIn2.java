package com.github.oogasawa.Pipe.in;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Pipe;


public class FileIn2 implements In {

    private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
    
    private BufferedReader reader = null;
    StringBuilder sb = new StringBuilder();
    
    public FileIn2(String filename) throws FileNotFoundException {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8"));
        } catch (UnsupportedEncodingException e) {
            logger.throwing("com.github.oogasawa.Pipe.in.FileIn2", "constructor", e);
            //Logger.getLogger(FileIn2.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception e) {
            logger.throwing("com.github.oogasawa.Pipe.in.FileIn2", "getLine", e);
        }

        return buffer.toString();            
    }

    public void close() {
        try {
            reader.close();
        } catch (Exception e) {
            logger.throwing("com.github.oogasawa.Pipe.in.FileIn2", "close", e);
        }
    }
}
