package com.github.oogasawa.Pipe.out;

import java.util.ArrayList;

import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.utility.types.collection.ListOfList;
import com.github.oogasawa.utility.types.string.StringUtil;

public class ListOfListOut implements Out {
	
	private ListOfList<String> lol = null;
	
	public ListOfListOut() {
		lol = new ListOfList<String>();
	}
	
	public void putLine(String line) {
		ArrayList<String> row = StringUtil.splitByTab(line);
		lol.addRow(row);
	}

	public void end() {
		// nothing to do ...
	}

	public Object get() {
		return lol;
	}

}
