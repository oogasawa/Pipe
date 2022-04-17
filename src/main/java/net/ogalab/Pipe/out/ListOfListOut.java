package net.ogalab.Pipe.out;

import java.util.ArrayList;

import net.ogalab.microutil.container.ListOfList;
import net.ogalab.Pipe.Out;
import net.ogalab.microutil.type.StringUtil;

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
