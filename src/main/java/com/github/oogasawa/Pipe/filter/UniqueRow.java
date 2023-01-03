package com.github.oogasawa.Pipe.filter;

import java.util.TreeSet;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;


/**
 *
 * @author oogasawa
 */
public class UniqueRow extends Filter {

    private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
    
    TreeSet<String> rows = new TreeSet<>();

    public UniqueRow(In in, Out out) {
        super(in, out);
    }

    public UniqueRow() {
        super(null, null);
    }

    public void run() {
        String line = null;
        try {
            while ((line = in.getLine()) != Pipe.END) {
                rows.add(line);
            }
            for (String r : rows) {
                out.putLine(r);
            }
            out.end();
        } catch (Exception e) {
            logger.throwing("com.github.oogasawa.Pipe.filter.UniqueRow", "run", e);
        }
    }

}
