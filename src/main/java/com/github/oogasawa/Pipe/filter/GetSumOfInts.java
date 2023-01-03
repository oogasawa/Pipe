package com.github.oogasawa.Pipe.filter;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.github.oogasawa.Pipe.In;
import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.Pipe.Pipe;
import com.github.oogasawa.utility.types.collection.MapOfList;
import com.github.oogasawa.utility.types.string.StringUtil;

public class GetSumOfInts extends Filter {

	private static final Logger logger = Logger.getLogger("com.github.oogasawa.Pipe");
	
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
			logger.throwing("com.github.oogasawa.Pipe.filter.GetSumOfInts", "run", e);
		}
	}

}
