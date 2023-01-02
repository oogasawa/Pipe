package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;
import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.string.StringUtil;



/**
 *
 * @author oogasawa
 */
public class FilterOut extends Filter {

    //Predicate<String> pred = null;
    //String outfile = "/dev/null";
    int colIdx = 0;
    String str = null;

    public FilterOut(In in, Out out, int col, String str) {
        super(in, out);
        this.colIdx = col;
        this.str    = str;
    }

    public FilterOut(int col, String str) {
        super(null, null);
        this.colIdx = col;
        this.str    = str;
    }



    @Override
    public void run() {
        try {
            String line = null;
            while ((line = in.getLine()) != Pipe.END) {
                
                ArrayList<String> cols = StringUtil.splitByTab(line);
                if (!cols.get(colIdx).equals(str)) {
                    out.putLine(line);
                }
            }
            out.end();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
}
