package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.string.StringUtil;


public class NumColumns  extends Filter {

	private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
	
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
			logger.throwing("com.github.oogasawa.Pipe.filter.NumColumns", "run", e);
		}
	}

}

