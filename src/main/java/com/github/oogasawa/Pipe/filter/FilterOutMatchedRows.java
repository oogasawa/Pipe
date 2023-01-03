package com.github.oogasawa.Pipe.filter;

import java.io.PrintWriter;
import java.util.function.Predicate;
import java.util.logging.Logger;

//import com.github.oogasawa.microutil.file.FileIO;
import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.files.FileIO;

public class FilterOutMatchedRows extends Filter {

    private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
    
    Predicate<String> pred = null;
    String outfile = "/dev/null";

    public FilterOutMatchedRows(In in, Out out, Predicate<String> pred, String path) {
        super(in, out);
        this.pred = pred;
        outfile = path;
    }

    public FilterOutMatchedRows(Predicate<String> pred, String path) {
        super(null, null);
        this.pred = pred;
        outfile = path;
    }

    public FilterOutMatchedRows(Predicate<String> pred) {
        super(null, null);
        this.pred = pred;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = FileIO.getPrintWriter(outfile);
            String line = null;
            while ((line = in.getLine()) != Pipe.END) {
                if (pred.test(line)) {
                    pw.println(line);
                } else {
                    out.putLine(line);
                }
            }
            pw.close();
            out.end();
        } catch (Exception e) {
            logger.throwing("com.github.oogasawa.Pipe.filter.FilterOutMatchedRows", "run", e);
        }
    }

}
