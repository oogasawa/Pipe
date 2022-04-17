package net.ogalab.Pipe.filter;

import java.util.ArrayList;
import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.container.ListUtil;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.type.StringUtil;
import org.slf4j.LoggerFactory;

public class GetColumn extends Filter {

    protected static org.slf4j.Logger log = LoggerFactory.getLogger(GetColumn.class);
    
    int[] col = null;

    public GetColumn(In in, Out out, int[] col) {
        super(in, out);
        this.col = col;
    }

    public GetColumn(In in, Out out, int col) {
        super(in, out);
        this.col = new int[]{col};
    }

    public GetColumn(int[] col) {
        super(null, null);
        this.col = col;
    }

    public GetColumn(int col) {
        super(null, null);
        this.col = new int[]{col};
    }

    public void run() {
        ArrayList<String> inCols = null;
        ArrayList<String> outCols = ListUtil.sequence(new String(), col.length);
        String line = null;
        String prevOut = null;
        String prevIn  = null;
        int counter = 0;
        try {

            while ((line = in.getLine()) != Pipe.END) {
                inCols = StringUtil.splitByTab(line);

                //if (counter > 461000 && counter < 461040) {
                //    log.debug(line);
                //} 
                
                
                for (int i = 0; i < col.length; i++) {
                    outCols.set(i, inCols.get(col[i]));
                }

                out.putLine(ListUtil.join("\t", outCols));
                prevOut = ListUtil.join("\t", outCols);
                prevIn  = ListUtil.join("\t", inCols);
                counter++;
            }
            out.end();
        } catch (Exception e) {
            RuntimeExceptionUtil.invoke(e, "Runtime exception in GetColumn.run() : " + counter + "\t" + line + "\n" 
                    + ListUtil.join("\t", inCols) + "\n"
                    + ListUtil.join("\t", outCols) + "\n"
                    + prevIn +"\n" + prevOut);
        }
    }
}
