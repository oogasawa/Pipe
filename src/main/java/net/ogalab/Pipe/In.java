package net.ogalab.Pipe;

public interface In {
	String getLine() throws InterruptedException; // null means EOF
	
	void close();
}


