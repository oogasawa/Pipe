/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.ogalab.Pipe.filter;

import java.util.TreeSet;
import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;

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
            RuntimeExceptionUtil.invoke(e, "Runtime exception in GetColumn.run() ");
        }
    }

}
