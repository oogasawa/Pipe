package net.ogalab.Pipe.filter;

import java.util.ArrayList;

import net.ogalab.Pipe.In;
import net.ogalab.Pipe.Out;
import net.ogalab.Pipe.Pipe;
import net.ogalab.microutil.container.MapOfList;
import net.ogalab.microutil.exception.RuntimeExceptionUtil;
import net.ogalab.microutil.type.StringUtil;
import net.ogalab.microutil.type.Type;

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
			data.add(inCols.get(idCol), Type.toInteger(inCols.get(valueCol)));
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
			RuntimeExceptionUtil.invoke(e, "Runtime exception in GetSumOfInts.run()");
		}
	}

}
