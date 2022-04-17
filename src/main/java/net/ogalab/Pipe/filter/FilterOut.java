/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.ogalab.Pipe.filter;

import java.util.ArrayList;
import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.type.StringUtil;


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
            RuntimeExceptionUtil.invoke(e, "Runtime error in FilterOut.run() ");
        }
    }
    
}
