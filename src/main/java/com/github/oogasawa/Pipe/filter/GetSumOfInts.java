package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
//import com.github.oogasawa.microutil.container.MapOfList;
//import com.github.oogasawa.microutil.exception.RuntimeExceptionUtil;
//import com.github.oogasawa.microutil.type.StringUtil;
//import com.github.oogasawa.microutil.type.Type;
import com.github.oogasawa.utility.types.collection.MapOfList;
import com.github.oogasawa.utility.types.string.StringUtil;

public class GetSumOfInts extends Filter {
	//In     in = null;
	//Out    out = null;
	int idCol    = 0;
	int valueCol = 1;
	
	MapOfList<String, Integer> data = new MapOfList<String, Integer>();
	
	public GetSumOfInts(In in, Out out, int idCol, int valueCol) {
		super(in, out);
		this.idCol    = idCol;
		this.valueCol = valueCol;
	}
	
	public GetSumOfInts(int idCol, int valueCol) {
		super(null, null);
		this.idCol    = idCol;
		this.valueCol = valueCol;
	}

	
	@Override
	public void run() {
		String line = null;
		
		try {
		ArrayList<String> inCols = null;
		while ((line = in.getLine()) != Pipe.END) {
			inCols = StringUtil.splitByTab(line);
			data.add(inCols.get(idCol), Integer.valueOf(inCols.get(valueCol)));
		}
		
		ArrayList<String> ids = data.getKeys();
		for (String id : ids) {
			ArrayList<Integer> values = data.getValues(id);
			int s = 0;
			for (int n : values) {
				s += n;
			}
			out.putLine(id + "\t" + s);
		}
		out.end();
		}
		catch (Exception e) {
			System.err.println("Runtime exception in GetSumOfInts.run()");
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
