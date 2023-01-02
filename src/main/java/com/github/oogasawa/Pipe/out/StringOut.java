package com.github.oogasawa.Pipe.out;

import com.github.oogasawa.Pipe.Out;

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
