package com.github.oogasawa.Pipe.filter;

import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;


public class Trivial extends Filter {

	private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
	
	public Trivial(In in, Out out) {
		super(in, out);
	}
	
	public Trivial() {
		super(null, null);
	}

	public void run() {
		String line = null;
		try {
		while ((line = in.getLine()) != Pipe.END) {
			out.putLine(line);
		}
		out.end();
		}
		catch (Exception e) {
			logger.throwing("com.github.oogasawa.Pipe.filter.Trivial", "run", e);
		}
	}


}
