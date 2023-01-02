package com.github.oogasawa.Pipe.filter;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;


public class Tee extends Filter {

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
			System.out.println("Runtime exception in Tee.run() ");
			e.printStackTrace();
			System.exit(-1);
		}
	}


}

