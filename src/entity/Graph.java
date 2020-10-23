package entity;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static io.ReadCode.checkAt;

public class Graph {

    public int n;
    public int m;


    //1-n
    public int []hed;
    public int []phed;
    public int []nex;
    public int []mys;
    int cnt;


    public String []msg;

    public Graph(int n,int m){
        this.n=n;
        this.m=m*10;
        hed=new int[n+10];
        phed=new int[n+10];
        nex=new int[m+10];
        mys=new int[m+10];
        cnt=0;
        msg=new String[m+10];
        D=new Set[n+10];
    }
    public void addEdge(int u,int v,String msg){
        nex[++cnt]=hed[u];hed[u]=cnt;mys[cnt]=v;this.msg[cnt]=msg;
        nex[++cnt]=phed[v];hed[v]=cnt;mys[cnt]=u;this.msg[cnt]=msg;
    }
    public void addEdge(Edge e){
        addEdge(e.u,e.v,e.msg);
    }

    //

    public Set<Integer> []D;



    public void solve1(){
        //
        Queue<Integer> Q=new LinkedBlockingQueue<>();
        for(int i=1;i<=n;i++) {D[i]=new HashSet<>();D[i].add(i);Q.add(i);}

        //
        while(!Q.isEmpty()){
            int u=Q.poll();

            for(int d=1;d<=n;d++){
                //System.out.println("fuck");
                boolean flg=false;
                for(int i=phed[d];i!=0;i=nex[i]){
                    //System.out.println(u+" "+x);
                    int x=mys[i];
                    System.out.println(u+" fuck "+x);
                    if(D[x].contains(d)){
                        D[u].add(d);
                        flg=true;
                        break;
                    }
                }
                if(flg){
                    Q.add(d);
                }
            }
        }

        //

    }

    public void display1() {
        //
        for(int i=1;i<=n;i++){
            //
            System.out.print(i +" D is >");
            for(int v : D[i]){
                System.out.print(v+" ");
            }
            System.out.println();
        }
    }


    public static List<Edge> change(Tree tree){
        //
        // expr
        // statement_list
        //

        List<Edge> ret=new ArrayList<>();
        Map<Integer,Integer> rM=tree.rM;
        Map<Integer,Integer> labels=new HashMap<>(); //  id - > name
        // BFS
        Set<Integer> S=new HashSet<>();
        Queue<Integer> Q=new LinkedBlockingQueue<>();
        Node rt=tree.root.get(0);
        Q.add(rt.id);
        List<Edge> tmp=new ArrayList<>();
        while(!Q.isEmpty()){
            Node u=tree.mys[Q.poll()];

            if(S.contains(u.id)) continue;
            S.add(u.id);
            System.out.println(u);
            if(u.name.equals("label_expr")){
                //
                for(Sgm sgm :u.list){
                    if(sgm.name.equals("name")){
                        Node v=tree.getSon(sgm.value);
                        labels.put(v.id,u.id);
                        System.out.println(u.id + " -  M ->"+v.id);
                    }
                }
            }
            for(Sgm sgm :u.list){
                if(checkAt(sgm.value)){

                    //

                    //System.out.println(sgm.value+" "+checkAt(sgm.value));

                    Node v=tree.getSon(sgm.value);
                    if(u.name.equals("goto_expr")) System.out.println(v.name + v.id + "FUCKS");

                    if(v.name.endsWith("expr")||
                            v.name.equals("statement_list")){
                        Q.add(v.id);

                        if(!u.name.equals("statement_list")) {ret.add(new Edge(u.id,v.id));}
                    }else if(v.name.equals("label_decl")&&!u.name.equals("label_expr")){
                        System.out.println("fuck");
                        tmp.add(new Edge(u.id,v.id));
                    }
                }

            }if(u.name.equals("goto_expr")){
                for(Sgm sgm :u.list) System.out.println(sgm.name);
            }
            //
            if(u.name.equals("statement_list")) {
                int pre=u.id;
                for (Sgm sgm : u.list) {
                    if (checkAt(sgm.value)&&!u.name.startsWith("return")) {
                        Node v=tree.getSon(sgm.value);
                        ret.add(new Edge(pre,v.id));
                        pre=v.id;
                    }
                }
            }
        }

        //
        for(Edge edge : tmp){

            ret.add(new Edge(edge.u,labels.get(edge.v)));
            System.out.println("FUCK"+ " "+ edge);
        }
        return ret;

    }
}
