package net.ogalab.Pipe.filter;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;

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
			RuntimeExceptionUtil.invoke(e, "Runtime exception in Trivial.run() ");
		}
	}


}
