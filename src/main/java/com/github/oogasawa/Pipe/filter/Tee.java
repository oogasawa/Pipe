package com.github.oogasawa.Pipe.filter;

import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;


public class Tee extends Filter {

	private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
	
	public Tee(In in, Out out) {
		super(in, out);
	}
	
	public Tee() {
		super(null, null);
	}

	public void run() {
		String line = null;
		try {
		while ((line = in.getLine()) != Pipe.END) {
			System.out.println(line);
			out.putLine(line);
		}
		out.end();
		}
		catch (Exception e) {
			logger.throwing("com.github.oogasawa.Pipe.filter.Tee", "run", e);
		}
	}


}

