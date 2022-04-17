package net.ogalab.Pipe.filter;

import java.util.Set;

import net.ogalab.microutil.container.Frequency;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
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
            RuntimeExceptionUtil.invoke(e, "Runtime error in Freq.run() ");
        }

    }

}
