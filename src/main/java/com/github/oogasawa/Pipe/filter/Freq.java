package com.github.oogasawa.Pipe.filter;

import java.util.Set;

//import com.github.oogasawa.microutil.container.Frequency;
//import com.github.oogasawa.microutil.exception.RuntimeExceptionUtil;
import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.collection.Frequency;

import org.slf4j.LoggerFactory;

public class Freq extends Filter {

    protected static org.slf4j.Logger log = LoggerFactory.getLogger(Freq.class);

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
                    log.debug("counter: " + counter);
            }
            log.debug("counter (end):  " + counter);
            
            Set<String> keys = frequency.keySet();
            for (String k : keys) {
                out.putLine(k + "\t" + frequency.get(k));
            }
            out.end();
        } catch (Exception e) {
            System.err.println("Runtime error in Freq.run() ");
            e.printStackTrace();
            System.exit(-1);
        }

    }

}
