package net.ogalab.Pipe;

public interface Out {
	void putLine(String line) throws InterruptedException;
	void end() throws InterruptedException;
	
	Object get();
}
