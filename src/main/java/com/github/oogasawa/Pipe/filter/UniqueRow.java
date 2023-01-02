package com.github.oogasawa.Pipe.filter;

import java.util.TreeSet;
import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;


/**
 *
 * @author oogasawa
 */
public class UniqueRow extends Filter {

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
            System.err.println("Runtime exception in UniqueRow.run() ");
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
