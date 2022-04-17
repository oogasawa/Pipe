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
import net.ogalab.microutil.container.ListUtil;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.type.StringUtil;

/**
 *
 * @author oogasawa
 */
public class Trim extends Filter {

    public Trim() {
        super(null,null);
    }
    
    public Trim(In in, Out out) {
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
                    s = StringUtil.trim(s);
                    s = StringUtil.asOneLine(s);
                    inCols.set(i, s);
                }

                out.putLine(ListUtil.join("\t", inCols));
            }
            out.end();
        } catch (Exception e) {
            RuntimeExceptionUtil.invoke(e, "Runtime exception in GetColumn.run() ");
        }
    }

}
