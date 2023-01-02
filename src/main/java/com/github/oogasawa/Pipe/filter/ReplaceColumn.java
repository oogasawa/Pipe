package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;
import java.util.function.Function;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.collection.ListUtil;
import com.github.oogasawa.utility.types.string.StringUtil;


public class ReplaceColumn extends Filter {
	
	int             colIdx = 0;
	Function<String, String> funcObj = null;
	
	
	public ReplaceColumn(int colIdx, Function<String, String> funcObj) {
		super(null, null);
		this.colIdx  = colIdx;
		this.funcObj = funcObj;
	}
	
	public ReplaceColumn(In in, Out out, int colIdx, Function<String, String> funcObj) {
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
					String out = funcObj.apply(inCols.get(i));
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
			System.err.println("Runtime error in ReplaceColumn.run()");
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
