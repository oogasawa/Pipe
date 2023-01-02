package com.github.oogasawa.Pipe.filter;


import java.util.function.Function;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
//import com.github.oogasawa.microutil.exception.RuntimeExceptionUtil;

public class AddColumn  extends Filter {
	
	Function<String, String> functor = null;
	
	public AddColumn(In in, Out out, Function<String, String> functor) {
		super(in, out);
		this.functor = functor;
	}


	public AddColumn(Function<String, String> functor) {
		super(null, null);
		this.functor = functor;
	}	

	public void run() {
		String line = null;
		try {
			while ((line = in.getLine()) != Pipe.END) {
				String newColumn = functor.apply(line);
				out.putLine(line + "\t" + newColumn);
			}
			out.end();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
