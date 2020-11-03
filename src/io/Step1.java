package io;

import entity.Edge;

import java.util.List;
import java.util.Set;

public class Step1 {



    public static String printGraph(Set<String> S, List<Edge> edges){
        String ret=
                "digraph G{\n" +
                "compound=true\n" +
                "\tcompound=true\n" +
                "\tfontsize=20\n" +
                "\tmargin=\"0,0\"\n" +
                "\tranksep =1\n" +
                "\tnodesep =0.25\n" +
                "    ordering=out;\n" +
                "    node [shape =record, charset = \"UTF-8\" fontname = \"Microsoft Yahei\", fontsize = 14] \n";
        for(String str : S){
            //System.out.println(str);
            ret= ret + str + "\n";
        }
        for (Edge edge : edges) {
            //System.out.println(edge);
            ret= ret+ edge + "\n";
        }
        ret += "}";
        return ret;
    }
}
