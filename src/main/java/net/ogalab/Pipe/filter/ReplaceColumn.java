package net.ogalab.Pipe.filter;

import java.util.ArrayList;

import net.ogalab.microutil.container.ListUtil;
import net.ogalab.microutil.functor.Functor;
import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.type.StringUtil;

public class ReplaceColumn extends Filter {
	
	int             colIdx = 0;
	Functor<String> funcObj = null;
	
	
	public ReplaceColumn(int colIdx, Functor<String> funcObj) {
		super(null, null);
		this.colIdx  = colIdx;
		this.funcObj = funcObj;
	}
	
	public ReplaceColumn(In in, Out out, int colIdx, Functor<String> funcObj) {
		super(in, out);
		
		this.colIdx  = colIdx;
		this.funcObj = funcObj;
	}
	

	public void run() {
		String line = null;
		try {
		ArrayList<String> inCols = null;
		ArrayList<String> outCols = null;
		while ((line = in.getLine()) != Pipe.END) {
			inCols  = StringUtil.splitByTab(line);
			outCols = new ArrayList<String>();
			for (int i=0; i<inCols.size(); i++) {
				
				if (i==colIdx) {
					String out = funcObj.func(inCols.get(i));
					outCols.add(out);
				}
				else
					outCols.add(inCols.get(i));
			}
			
			out.putLine(ListUtil.join("\t", outCols));
		}
		out.end();
		}
		catch (Exception e) {
			RuntimeExceptionUtil.invoke(e, "Runtime error in ReplaceColumn.run()");
		}
	}

}
