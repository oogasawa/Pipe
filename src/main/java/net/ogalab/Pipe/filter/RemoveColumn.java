package net.ogalab.Pipe.filter;

import java.util.ArrayList;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.container.ListUtil;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.type.StringUtil;

public class RemoveColumn extends Filter {
	
	int[] col = null;
	
	
	public RemoveColumn(In in, Out out, int[] col) {
		super(in, out);
		this.col = col;
	}
	
	public RemoveColumn(In in, Out out, int col) {
		super(in, out);
		this.col = new int[]{ col };
	}
	
	public RemoveColumn(int[] col) {
		super(null, null);
		this.col = col;
	}
	
	public RemoveColumn(int col) {
		super(null, null);
		this.col = new int[]{ col };
	}

	public boolean isRemoved(int c) {
		boolean result = false;
		for (int i=0; i<this.col.length; i++)
			if (col[i] == c)
				result = true;
		
		return result;
	}
	
	public void run() {
		String line = null;
		try {
			while ((line = in.getLine()) != Pipe.END) {
				ArrayList<String> inCols = StringUtil.splitByTab(line);
				ArrayList<String> outCols = new ArrayList<>(); //ListUtil.sequence(new String(), col.length);
							
				for (int i=0; i<inCols.size(); i++) {
					if (!isRemoved(i))
						outCols.add(inCols.get(i));
				}
				
				out.putLine(ListUtil.join("\t", outCols));
			}
			out.end();
		} catch (Exception e) {
			RuntimeExceptionUtil.invoke(e, "Runtime exception in GetColumn.run() ");
		}
	}

}
