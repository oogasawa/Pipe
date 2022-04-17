package net.ogalab.Pipe.filter;

import java.io.PrintWriter;

import net.ogalab.microutil.functor.Predicate;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.file.FileIO;
import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;

public class FilterOutMatchedRows extends Filter {

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
                if (pred.is(line)) {
                    pw.println(line);
                } else {
                    out.putLine(line);
                }
            }
            pw.close();
            out.end();
        } catch (Exception e) {
            RuntimeExceptionUtil.invoke(e, "Runtime error in FilterOutMatchedRows.run() ");
        }
    }

}
