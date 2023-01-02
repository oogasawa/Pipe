package com.github.oogasawa.Pipe.out;

import java.io.IOException;
import java.io.PrintWriter;

import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.utility.files.FileIO;

public class FileOut implements Out {

	private String      outfile = null;
	private PrintWriter pw = null;
	
	public FileOut(String outfile) throws IOException {
		this.outfile = outfile;
		pw = FileIO.getPrintWriter(outfile);
	}
	
	public void putLine(String line) {
		pw.println(line);
	}

	public void end() {
		pw.flush();
		pw.close();
	}

	public Object get() {
		return outfile;
	}

}
