package net.ogalab.Pipe;

import java.util.ArrayList;

import net.ogalab.Pipe.filter.Filter;
import net.ogalab.Pipe.filter.Trivial;
import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;



public class PipeLine extends ArrayList<Filter> {

	In  inlet  = null;
	Out outlet = null;
	
	public PipeLine() { }
	
	public PipeLine(In inObj, Out outObj) {
		inlet  = inObj;
		outlet = outObj;
	}
	
	public In getInlet() {
		return inlet;
	}
	
	public void setInlet(In inlet) {
		this.inlet = inlet;
	}
	
	public Out getOutlet() {
		return outlet;
	}
	
	public void setOutlet(Out outlet) {
		this.outlet = outlet;
	}
	
	public void start() throws InterruptedException {
		run();
	}
	
	public void run() throws InterruptedException {
		if (size() == 0) {
			run0();
		}
		else if (size() == 1) {
			run1();
		}
		else {
			run2();
		}

	}
	
	public void run0() throws InterruptedException {
		
		Filter f0 = new Trivial(inlet, outlet);
		f0.start();

		f0.join();
	}
	
	public void run1() throws InterruptedException {
		
		Filter f1 = get(0);
		f1.setInlet(inlet);
		f1.setOutlet(outlet);
		f1.start();
		
		f1.join();
	}

	
	public void run2() throws InterruptedException {
		
		ArrayList<Pipe> pList = new ArrayList<Pipe>();
		

		for (int i=0; i<size(); i++) {
			
			if (i==0) {
				get(i).setInlet(inlet);
			}
			if (i == size()-1) {
				get(i).setOutlet(outlet);
			}
			if (i < size()-1) {
				Pipe p = new Pipe();
				get(i).setOutlet(p);
				get(i+1).setInlet(p);
				pList.add(p);
			}
						
		}
		
		for (int i=0; i<size(); i++) {
			get(i).start();
		}
		
		get(size()-1).join();
		
	}
	
	
}
