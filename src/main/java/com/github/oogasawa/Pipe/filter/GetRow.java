package com.github.oogasawa.Pipe.filter;

import java.util.function.Predicate;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;


public class GetRow extends Filter {
	//In     in = null;
	//Out    out = null;
	protected Predicate<String> pred = null;
	
	public GetRow(In in, Out out, Predicate<String> pred) {
		super(in, out);
		this.pred = pred;
	}
	
	public GetRow(Predicate<String> pred) {
		super(null, null);
		this.pred = pred;
	}

	@Override
	public void run() {
		String line = null;
		try {
		while ((line = in.getLine()) != Pipe.END) {
			if (pred.test(line)) {
				out.putLine(line);
			}
		}
		out.end();
		}
		catch (Exception e) {
			System.err.println("Runtime exception in GetRow.run()");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	
	
}
