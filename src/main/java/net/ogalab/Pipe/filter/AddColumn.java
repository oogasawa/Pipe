package net.ogalab.Pipe.filter;

import net.ogalab.microutil.functor.Functor;
import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;

public class AddColumn  extends Filter {
	
	Functor<String> functor = null;
	
	public AddColumn(In in, Out out, Functor<String> functor) {
		super(in, out);
		this.functor = functor;
	}


	public AddColumn(Functor<String> functor) {
		super(null, null);
		this.functor = functor;
	}	

	public void run() {
		String line = null;
		try {
			while ((line = in.getLine()) != Pipe.END) {
				String newColumn = functor.func(line);
				out.putLine(line + "\t" + newColumn);
			}
			out.end();
		}
		catch (Exception e) {
			RuntimeExceptionUtil.invoke(e, "Runtime error in AddColumn.run() ");
		}
	}

}
