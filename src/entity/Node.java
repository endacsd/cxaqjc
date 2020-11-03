package entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Node {

    //@4      type_decl        name: @8       type: @9       chain: @10
    final static HashSet<String> Mast=new HashSet<String>(Arrays.asList("body",
            "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
            "op 0","op 1","op 2",
            "size","name","type","low","srcp","strg","lngt","labl"

            ));
    public int id;
    public String name;

    public List<Sgm> list;

    public Node(){}
    public Node(int id,String name,List<Sgm> list){
        this.id=id;
        this.name=name;
        this.list=list;
    }


    @Override
    public String toString() {
        String string= "@"+Integer.toString(id)+" "+name;
        for(Sgm sgm : list){
            string=string+" "+sgm;
        }
        return string;
    }
    public String toTest(){
        return "\""  + id + " "+ name + "\"";
    }
    public String toGraphNode(){


        String string=id+"[label=\""+id+"|"+name;

        for(Sgm sgm: list){
            if(Mast.contains(sgm.name)){
                string+= "|<"+sgm.toGraph()+">"+sgm.toGraph();
            }
        }
        return string+"\"]";
    }
}
