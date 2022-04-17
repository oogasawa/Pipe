/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.ogalab.functor;

import java.util.ArrayList;
import net.ogalab.microutil.functor.Predicate;
import net.ogalab.microutil.type.StringUtil;

/**
 *
 * @author oogasawa
 */
public class ColMatch implements Predicate<String> {
	
	int     column = 0;
	String  str    = null;
	
	public ColMatch(int col, String s) {
		column = col;
		str    = s;
	}

	public boolean is(String line) {
            ArrayList<String> a = StringUtil.splitByTab(line);
            
            
            return str.equals(a.get(column));
	}
	
	

}

