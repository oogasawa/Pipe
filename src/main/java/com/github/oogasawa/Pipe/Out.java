package com.github.oogasawa.Pipe;

public interface Out {
	void putLine(String line) throws InterruptedException;
	void end() throws InterruptedException;
	
	Object get();
}
