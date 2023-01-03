package com.github.oogasawa.Pipe.filter;

import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;



/**
 *
 * @author oogasawa
 */
public class Log extends Filter {

    private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
    
    String comment = "log message not specified (in com.github.oogasawa.Pipe.filter.Log)";
    
    public Log(In in, Out out, Logger logger, String comment) {
        super(in, out);
        this.comment = comment;
    }

    public Log(Logger logger, String comment) {
        super(null, null);
        this.comment = comment;
    }
    
    public Log(Logger logger) {
        super(null, null);
    }

    public void run() {
        String line = null;
        int counter = 0;
        try {
            logger.fine(comment);
            while ((line = in.getLine()) != Pipe.END) {
                if (counter++ < 5)
                    logger.fine(line);
                
                if (counter % 10000 == 0)
                    logger.fine(String.format("%d", counter) + " : " + line);
                
                out.putLine(line);
            }
            out.end();
        } catch (Exception e) {
            logger.throwing("com.github.oogasawa.Pipe.filter.Log", "run", e);
        }
    }

}
