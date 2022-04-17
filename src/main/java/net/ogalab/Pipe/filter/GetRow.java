package net.ogalab.Pipe.filter;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.functor.Predicate;

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
			if (pred.is(line)) {
				out.putLine(line);
			}
		}
		out.end();
		}
		catch (Exception e) {
			RuntimeExceptionUtil.invoke(e, "Runtime exception in GetRow.run()");
		}
	}

	
	
}
