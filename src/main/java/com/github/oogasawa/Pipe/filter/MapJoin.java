package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;
import java.util.Map;
import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.collection.ListUtil;
import com.github.oogasawa.utility.types.string.StringUtil;


/**
 *
 * @author oogasawa
 */
public class MapJoin extends Filter {

    public final static int COLLAPSED = 1;
    public final static int REVERSED = 2;

    Map<String, String> map = null;

    int targetColumn = 0;
    String delimiter = ";";
    String notAvailable = "\\N";
    boolean collapsed = false;
    boolean removeNewLines = true;
    boolean removeTabs = true;

    public MapJoin(In in, Out out, Map<String, String> map) {
        super(in, out);
        this.map = map;
    }

    public MapJoin(Map<String, String> map) {
        super(null, null);
        this.map = map;
    }

    public void run() {

        try {
            join();

        } catch (Exception e) {
            System.err.println("Runtime error in the MapJoin.run() ");
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public void join() throws InterruptedException {
        String line = Pipe.END;
        ArrayList<String> col = null;
        while ((line = in.getLine()) != Pipe.END) {
            col = StringUtil.splitByTab(line);
            
            if (map.containsKey(col.get(this.getTargetColumn()))) {
                col.add(map.get(col.get(this.getTargetColumn())));
            }
            else {
                col.add(notAvailable);
            }
                    
            out.putLine(ListUtil.join("\t", col));

        }
        out.end();
    }


    /*
     public boolean isReversed() {
     return reversed;
     }

     public DbJoin setReversed(boolean reversed) {
     this.reversed = reversed;
     return this;
     }
     */
    public int getTargetColumn() {
        return targetColumn;
    }

    public MapJoin setTargetColumn(int targetColumn) {
        this.targetColumn = targetColumn;

        return this;
    }

    public boolean isRemoveNewLines() {
        return removeNewLines;
    }

    public MapJoin setRemoveNewLines(boolean removeNewLine) {
        this.removeNewLines = removeNewLine;

        return this;
    }

    public boolean isRemoveTabs() {
        return removeNewLines;
    }

    public MapJoin setRemoveTabs(boolean removeTabs) {
        this.removeTabs = removeTabs;

        return this;
    }
}
