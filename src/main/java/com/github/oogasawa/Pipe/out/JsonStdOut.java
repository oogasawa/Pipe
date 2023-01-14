package com.github.oogasawa.Pipe.out;

import java.util.ArrayList;

import com.github.oogasawa.Pipe.Out;
import com.github.oogasawa.utility.types.string.StringUtil;

import org.json.JSONObject;

public class JsonStdOut implements Out {

    ArrayList<String> fieldNames = null;

    public JsonStdOut setFieldNames(ArrayList<String> names) {
        this.fieldNames = names;
        return this;
    }
    
    public void putLine(String line) {

        if (fieldNames == null) {
            System.out.println(line);
        }
        else {
            // Here's how to use "Json in Java":
            // https://www.baeldung.com/java-org-json
            // https://mvnrepository.com/artifact/org.json/json

            JSONObject jo = new JSONObject();
            ArrayList<String> cols = StringUtil.splitByTab(line);
            for (int i=0; i<fieldNames.size(); i++) {
                jo.put(fieldNames.get(i), StringUtil.asMultiLines(cols.get(i)));
            }
            System.out.println(jo.toString(4)); // indent width = 4.
        }
        
    }

    public void end() {
        System.out.flush();
    }

    public Object get() {
        return null;
    }
}
