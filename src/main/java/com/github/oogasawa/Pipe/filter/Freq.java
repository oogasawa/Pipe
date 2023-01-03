package com.github.oogasawa.Pipe.filter;

import java.util.Set;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.collection.Frequency;


public class Freq extends Filter {

    protected static Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");

    Frequency<String> frequency = new Frequency<String>();

    public Freq(In in, Out out) {
        super(in, out);
    }

    public Freq() {
        super(null, null);
    }

    public void run() {

        String line = null;
        int counter = 0;
        try {
            while ((line = in.getLine()) != Pipe.END) {
                frequency.add(line);
                counter++;
                if (counter % 1000 == 0)
                    logger.fine("counter: " + counter);
            }
            logger.fine("counter (end):  " + counter);
            
            Set<String> keys = frequency.keySet();
            for (String k : keys) {
                out.putLine(k + "\t" + frequency.get(k));
            }
            out.end();
        } catch (Exception e) {
            logger.throwing("com.github.oogasawa.Pipe.filter.Freq", "run", e);
        }

    }

}
