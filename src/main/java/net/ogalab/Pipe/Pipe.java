 package net.ogalab.Pipe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;

public class Pipe implements In, Out {
	public static final String END = "\\b";
	
	  private BlockingQueue<String> queue = null;
	  private int queueLength = 1024;

	  public Pipe() {
	    queue = new ArrayBlockingQueue<String>(queueLength);
	  }

	  public String getLine() throws InterruptedException{
	    String line = queue.take();

	    return line;
	  }

	  public void putLine(String line) throws InterruptedException {
	      queue.put(line);
	  }

	  public void end() throws InterruptedException {
		  queue.put(END);
	  }


	public Object get() {
		return queue;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

}
