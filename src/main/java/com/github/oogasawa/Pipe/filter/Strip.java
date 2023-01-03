package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.collection.ListUtil;
import com.github.oogasawa.utility.types.string.StringUtil;


/**
 *
 * @author oogasawa
 */
public class Strip extends Filter {

    private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
    
    public Strip() {
        super(null,null);
    }
    
    public Strip(In in, Out out) {
        super(in, out);
    }

    public void run() {
        String line = null;
        try {
            ArrayList<String> inCols = null;
             while ((line = in.getLine()) != Pipe.END) {
                inCols = StringUtil.splitByTab(line);

                for (int i = 0; i < inCols.size(); i++) {
                    String s = StringUtil.asMultiLines(inCols.get(i));
                    s = s.strip();
                    s = StringUtil.asOneLine(s);
                    inCols.set(i, s);
                }

                out.putLine(ListUtil.join("\t", inCols));
            }
            out.end();
        } catch (Exception e) {
            logger.throwing("com.github.oogasawa.Pipe.filter.Strip", "run", e);
        }
    }

}
