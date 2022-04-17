package net.ogalab.Pipe.out;

import net.ogalab.Pipe.Out;

public class StringOut implements Out {
	
	private StringBuffer sb = null;
	
	public StringOut() {
		sb = new StringBuffer();
	}
	
	public void putLine(String line) {
		sb.append(line);
	}

	public void end() {
		// nothing to do ...
	}

	public Object get() {
		return sb.toString();
	}
	
}
