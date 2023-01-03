package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.collection.ListUtil;
import com.github.oogasawa.utility.types.string.StringUtil;


public class GetColumn extends Filter {

    private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");    

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
            logger.throwing("com.github.oogasawa.Pipe.filter.GetColumn", "run", e);
            logger.fine(String.format("%d\t%s", counter, line));
            logger.fine(ListUtil.join("\t", inCols));
            logger.fine(ListUtil.join("\t", outCols));
            logger.fine(prevIn);
            logger.fine(prevOut);
        }
    }
}
