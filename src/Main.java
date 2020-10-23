import entity.*;
import io.ReadCode;

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

        //System.out.println(tree.root.get(0));

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

        {

            for(String str : S){
                System.out.println(str);
            }
            for (Edge edge : edges) {
                System.out.println(edge);
            }

        }

        if(true) return;
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

        boolean k=false;

        if(k){
            Graph G=new Graph(all,all*10);
            for(Edge e : edges){
                if(k)System.out.println(e);
                G.addEdge(e);
            }
            G.solve1();
            G.display1();
        }

        List<Edge> edges1=Graph.change(tree);
        for(Edge e : edges1){
            System.out.println(e);
        }





    }
}



