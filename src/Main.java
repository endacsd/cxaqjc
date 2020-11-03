import entity.*;
import io.ReadCode;
import io.Step1;

import java.util.*;

public class Main {



    private static void Deb(String str){
        if(debug) System.out.println(" "+str);
    }

    private static final boolean debug=true;

    public static void main(String[] args) throws Exception {


        List<Node> list = ReadCode.read();

        if (list == null) {
            System.out.println("error");
            return;
        }

        //

        if (debug && false) {
            System.out.println(list.size());
            for (Node node : list) {
                System.out.println(node);
            }
        }

        Node[] arr = new Node[list.size() + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = list.get(i - 1);
        }
        Tree tree = new Tree(arr);



        List<Edge> edges = new ArrayList<>();
        Set<String> S = new TreeSet<>();
        tree.DFS(tree.root.get(0), ">", edges, S);
        //tree.DFS(tree.root.get(1),">");

        int[] tvis = tree.vis;
        int all = 0;
        for (int i = 1; i < tvis.length; i++) {
            //System.out.println(i+" "+tvis[i]);
            all += tvis[i];
        }

        //OUT1

        if(false){
            System.out.println(Step1.printGraph(S,edges));
            return;
        }


        //System.out.println(all+" " +(tvis.length-1));

        //

        if(false)
        for(int i=1;i<arr.length;i++){
            //
            int id=arr[i].id;
            for(Sgm sgm :arr[i].list){
                Node v=tree.getSon(sgm.value);
                if(v!=null){
                    System.out.println("    "+id+ " -> "+ v.id);
                }
            }
        }

        boolean k=true;

        if(k){
            Graph G=new Graph(all,all*10,tree);

            for(Edge edge :edges){
                G.addEdge(arr[edge.u],arr[edge.v]);
            }
            G.before();
            G.DFS1(0,1,1);
        }







    }
}



