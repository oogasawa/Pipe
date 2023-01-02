package com.github.oogasawa.functor;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.github.oogasawa.utility.types.string.StringUtil;


public class ColMatch implements Predicate<String> {

	int column = 0;
	String str = null;

	public ColMatch(int col, String s) {
		column = col;
		str = s;
	}

	@Override
	public boolean test(String line) {
		ArrayList<String> a = StringUtil.splitByTab(line);

		return str.equals(a.get(column));
	}

}


