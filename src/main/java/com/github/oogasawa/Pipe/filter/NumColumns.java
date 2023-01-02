package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.string.StringUtil;


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
			out.putLine(String.format("%d", cols.size()));
		}
		out.end();
		} catch (Exception e) {
			System.err.println("Runtime exception in NumColumns.run() ");
			e.printStackTrace();
			System.exit(-1);
		}
	}

}

