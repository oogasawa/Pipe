package net.ogalab.Pipe.filter;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;

public abstract class Filter implements Runnable {
	protected In  in = null;
	protected Out out = null;
	protected Thread th = null;

	public Filter(In in, Out out) {
		this.in = in;
		this.out = out;
	}

	public abstract void run(); // runnable

	public void start() {
		th = new Thread(this);
		th.start();
	}

	public void join() throws InterruptedException {
		th.join();
	}

	public In getInlet() {
		return in;
	}

	public void setInlet(In in) {
		this.in = in;
	}

	public Out getOutlet() {
		return out;
	}

	public void setOutlet(Out out) {
		this.out = out;
	}

}
