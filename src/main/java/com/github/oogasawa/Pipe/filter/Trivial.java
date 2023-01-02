package com.github.oogasawa.Pipe.filter;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;


public class Trivial extends Filter {

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
			System.err.println("Runtime exception in Trivial.run() ");
			e.printStackTrace();
			System.exit(-1);
		}
	}


}
