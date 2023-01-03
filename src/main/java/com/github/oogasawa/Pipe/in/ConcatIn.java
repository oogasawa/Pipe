package com.github.oogasawa.Pipe.in;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Pipe;


public class ConcatIn implements In {

    
    private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");

    ArrayList<In> entity = new ArrayList<In>();
    int current = 0;
    int lineno  = 0;

    public ConcatIn(In... ins) {
        for (In i : ins) {
            entity.add(i);
        }
    }

    @Override
    public String getLine() throws InterruptedException {
        String line = entity.get(current).getLine();
        
        if (lineno++ % 1000 == 0)
            logger.fine(String.format("%d",lineno));
        
        if (line.equals(Pipe.END)) {
            current++;
            if (current >= entity.size()) {
                return Pipe.END;
            }
            else {
                line = entity.get(current).getLine();
            }   
            
        }
 

        return line;
    }

    @Override
    public void close() {
        for (In i : entity) {
            i.close();
        }
    }

}
