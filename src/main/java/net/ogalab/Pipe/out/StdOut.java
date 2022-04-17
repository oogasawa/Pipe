package net.ogalab.Pipe.out;

import net.ogalab.Pipe.Out;


public class StdOut implements Out {
	
	public void putLine(String line) {
		System.out.println(line);
	}

	public void end() {
		System.out.flush();
	}

	public Object get() {
		return null;
	}
}
