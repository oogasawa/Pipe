package net.ogalab.Pipe.filter;

import java.util.ArrayList;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.type.StringUtil;
import net.ogalab.microutil.type.Type;

public class NumColumns  extends Filter {
	
	public NumColumns(In in, Out out) {
		super(in, out);
	}
	
	public NumColumns() {
		super(null, null);
	}
	

	public void run() {
		String line = null;
		try {
		while ((line = in.getLine()) != Pipe.END) {
			ArrayList<String> cols = StringUtil.splitByTab(line);
			out.putLine(Type.toString(cols.size()));
		}
		out.end();
		} catch (Exception e) {
			RuntimeExceptionUtil.invoke(e, "Runtime exception in NumColumns.run() ");
		}
	}

}

