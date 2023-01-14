package com.github.oogasawa.Pipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.github.oogasawa.pojobdd.BddUtil;

import org.json.JSONObject;

public class JsonSpec {


    public static boolean exec() {

        String docId = BddUtil.documentId("JsonSpec");
        Path mdPath = Path.of(docId, docId + ".md");
        
        try (PrintStream out = BddUtil.newPrintStream(mdPath)) {
            // Checks if all the tests are succeeded.
            List<Boolean> results = new ArrayList<Boolean>();

            out.println(BddUtil.yamlHeader(docId, "JsonSpec"));
            results.add(storyDesc(out));
            results.add(multilineStringExample(out));

            out.flush();
            return BddUtil.allTrue(results);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static boolean storyDesc(PrintStream out) {

        String description = """

            ## Conversion from TSV to JSON

            
            
            """;

        return true;
    }
    
    
    public static boolean multilineStringExample(PrintStream out) {

        // Description
        String description = """
            

            ### Example : Conversion from multiline string to JSON
            
            - Given a multi-line string
            - When convert it to JSON format
            - Then, in JSON format, all newline characters in a multi-line string are escaped.

            Code:

            ```
            {{snippet}}
            ```

            Result:
                                           
            """;



        // Reality
        // %begin snippet : multilineStringExample

        String data = """
            Today is November 26th.
            It snowed all day today.
            The snow is beautiful.
            The snow finally stopped.
            """;

        JSONObject jo = new JSONObject();
        jo.put("multiline_string", data);
        String result = jo.toString(4); // indent width = 4.

        // %end snippet : multilineStringExample


        String snippet = BddUtil.readSnippet(
                            Path.of("src/test/java/com/github/oogasawa/Pipe/JsonSpec.java"),
                            "multilineStringExample");
        description = description.replace("{{snippet}}", snippet);
        out.println(description);


        // Expectations
        String expectation = """
        {"multiline_string": "Today is November 26th.\\nIt snowed all day today.\\nThe snow is beautiful.\\nThe snow finally stopped.\\n"}""";

        // Check the answer.
        boolean passed = BddUtil.assertTrue(out, expectation, result);
        assert passed;
        return passed;


        //return true;
    }
}
