package com.github.oogasawa.Pipe.filter;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;

//import com.github.oogasawa.microutil.exception.RuntimeExceptionUtil;
//import com.github.oogasawa.microutil.type.Type;
import org.slf4j.Logger;

/**
 *
 * @author oogasawa
 */
public class Log extends Filter {

    Logger logger = null;
    String comment = "Pipe log.";
    
    public Log(In in, Out out, Logger logger, String comment) {
        super(in, out);
        this.logger = logger;
        this.comment = comment;
    }

    public Log(Logger logger, String comment) {
        super(null, null);
        this.logger = logger;
        this.comment = comment;
    }
    
    public Log(Logger logger) {
        super(null, null);
        this.logger = logger;
    }

    public void run() {
        String line = null;
        int counter = 0;
        try {
            logger.debug(comment);
            while ((line = in.getLine()) != Pipe.END) {
                if (counter++ < 5)
                    logger.debug(line);
                
                if (counter % 10000 == 0)
                    logger.debug(String.format("%d", counter) + " : " + line);
                
                out.putLine(line);
            }
            out.end();
        } catch (Exception e) {
            System.err.println("Runtime exception in Log.run() ");
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
