/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ogalab.Pipe.filter;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.type.Type;
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
                    logger.debug(Type.toString(counter) + " : " + line);
                
                out.putLine(line);
            }
            out.end();
        } catch (Exception e) {
            RuntimeExceptionUtil.invoke(e, "Runtime exception in Trivial.run() ");
        }
    }

}
