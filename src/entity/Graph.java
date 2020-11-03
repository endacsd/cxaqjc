package entity;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static io.ReadCode.checkAt;

public class Graph {


    final static HashSet<String> Mast=new HashSet<String>(Arrays.asList(
            "function_decl","statement_list","label_decl","body",
            "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
            "op 1","op 2","op 0"
    ));
    final static HashSet<String> Go=new HashSet<String>(Arrays.asList(
            "body"
    ));

    public int n;
    public int m;
    //1-n
    public int []hed;
    public int []phed;
    public int []nex;
    public int []mys;
    public int []dfsvis;
    int cnt;
    public String []msg;
    Tree tree;
    public Graph(int n,int m,Tree tree){
        m*=10;

        this.n=n;
        this.m=m;
        hed=new int[n+10];
        dfsvis=new int[n+10];
        phed=new int[n+10];
        nex=new int[m+10];
        mys=new int[m+10];
        cnt=0;
        msg=new String[m+10];
        D=new Set[n+10];
        this.tree=tree;
        mpcnt=0;
        Mp=new TreeMap<>();
        M=new TreeMap<>();
    }
    public void addEdge(int u,int v,String msg){
        nex[++cnt]=hed[u];hed[u]=cnt;mys[cnt]=v;this.msg[cnt]=msg;
        nex[++cnt]=phed[v];hed[v]=cnt;mys[cnt]=u;this.msg[cnt]=msg;
    }
    public void addEdge(Edge e){
        addEdge(e.u,e.v,e.msg);
    }

    int mpcnt=0;
    Map<Integer,Node> Mp;
    Map<Integer,Integer> M;
    Map<Integer,Integer> Mr;
    public void addEdge(Node u,Node v){

        if(!M.containsKey(u.id)){
            Mp.put(++mpcnt,u);
            M.put(u.id,mpcnt);
        }
        if(!M.containsKey(v.id)){
            Mp.put(++mpcnt,v);
            M.put(v.id,mpcnt);
        }
        if(u.id==4715) {
            System.out.println(M.get(u.id));
        }
        if(v.id==4715) {
            System.out.println(M.get(v.id));
        }
        //System.out.println("DEB:" + M.containsKey(u.id) + " "+ M.containsKey(v.id));
        //System.out.println("DEB:" + u.id + " "+ v.id);
        //System.out.println(mpcnt);
        addEdge(M.get(u.id),M.get(v.id),"");

    }
    //

    public Set<Integer> []D;


    public void before(){
        // 规范化
        // 1. 让编号变为1 开始 这个在创建的时候就已经完成了
        // 2. 解决 label 的问题
        // 添加结束以后 BFS 规范化一次

        Queue<Integer> Q=new LinkedBlockingQueue<>();

        int vis[]=new int[mpcnt+10];
        Q.add(1);
        int clkcnt=0;
        Node []nodeu=new Node[m];
        Node []nodev=new Node[m];
        while(!Q.isEmpty()){


            int uid=Q.poll();
            if(vis[uid]==1) continue;
            vis[uid]=1;
            Node u=Mp.get(uid);

            for(int i=hed[uid];i!=0;i=nex[i]){
                //
                int vid=mys[i];

                Node v= Mp.get(vid);
                //
                if(v.name.equals("label_decl")){
                    nodeu[++clkcnt]=u;
                    nodev[clkcnt]=v;
                    addEdge(v,u);
                    //System.out.println(u +" "+ v);
                    break;
                }else{
                    Q.add(vid);
                }

            }

        }
        for(int i=1;i<=clkcnt;i++){
           // addEdge(nodeu[i],nodev[i]);
        }

        System.out.println(clkcnt);


    }


    /*


        f   ->  uidp1   -> end
        f   ->  uidp2  -> end
     */

    public String getTest(int id){
        return Mp.containsKey(id) ? Mp.get(id).toTest() : "\"" + id + "\"";
    }
    public int DFS1(int f,int uid,int end){


        Node u=Mp.get(uid);
        int flg=0;
        String fs;
        String uids;
        fs= Mp.containsKey(f)  ? Mp.get(f).toTest():"\""+f+"\"";
        uids=Mp.containsKey(uid)  ? Mp.get(uid).toTest():"\""+uid+"\"";
       // if(!u.name.equals("label_expr"))

        Node fnode=Mp.get(f);
        Node unode=Mp.get(uid);
        if(end!=0) System.out.println(fs +" -> " +uids);


        dfsvis[uid]=1;
        int ret=uid;
        //System.out.println("DEB: " + u.name );
        if(u.name.equals("function_decl")){

            for(int i=hed[uid];i!=0;i=nex[i]){
                int vid=mys[i];

                Node v=Mp.get(vid);
                //System.out.println(vid +" "+ v);
                if(v.name.equals("statement_list")||v.name.endsWith("expr")){
                    //System.out.println(v);
                    ret=DFS1(uid,vid,1);
                    flg=1;
                    //ret=end;
                }
            }
        }else if(u.name.equals("statement_list")){
            // 0 -> 1 -> 2 -> 3 ->4
            int preid=uid;
            Node preNode=u;
            for(int i=0;i<u.list.size();i++){
                Sgm sgm = u.list.get(i);
                //int nex= i==u.list.size()-1 ?  end: M.get(u.list.get(i+1).getSon());

                if(Mast.contains(sgm.name)){

                    int vid=sgm.getSon();
                    if(vid==-1) continue;
                    vid=M.get(vid);
                    Node v=Mp.get(vid);


                    //
                    //vid
                    // 循环的也要hide

                    int check=0;

                    if(preNode.name.equals("cond_expr")){

                            for(Sgm dc : preNode.list){
                                //System.out.println("ffff");

                                int son=dc.getSon();
                                if(son==-1) continue;
                                if(M.get(son)==null) continue;
                                Node tmp = Mp.get(M.get(son));
                                if(tmp!=null && tmp.name.equals("goto_expr")) check=1;
                            }

                    }



                    //if(check==1)
                    //System.out.println(check);
                    if(preNode.name.equals("goto_expr")||check==1){
                        //System.out.println("???");
                        preid=DFS1(preid,vid,0);

                    }else{
                        preid=DFS1(preid,vid,1);
                    }
                    ret=preid;
                    preNode=v;

                }


            }

        }else if(u.name.equals("cond_expr")){
            //       -> op 1
            //op 0
            //       -> op 2
            //
            int newEnd=++mpcnt;
            int newEndret=-1;
            //System.out.println(newEndret);
            int check=0;

            for(Sgm sgm : u.list){
                if(!M.containsKey(sgm.getSon())) continue;
                int vid = M.get(sgm.getSon());
                Node v=Mp.get(vid);
                //if(v.name.equals("op 0")) continue;
                if(v.name.equals("goto_expr")) {
                    check=1;

                }
            }
            for(int i=0;i<u.list.size();i++){
                Sgm sgm = u.list.get(i);
                if(!M.containsKey(sgm.getSon())) continue;
                int vid = M.get(sgm.getSon());
                Node v=Mp.get(vid);


                if(sgm.name.equals("op 0")){
                    int curret=DFS1(uid,vid,1);

                        System.out.println(getTest(curret)+" -> "+ getTest(newEnd));
                }else{

                    if(newEndret==-1) newEndret=++mpcnt;
                    int curend=DFS1(newEnd,vid,1);
                    //System.out.println(newEndret);
                    if(check!=1)
                        System.out.println(getTest(curend)+" -> "+getTest(newEndret));
                }
                flg=1;
            }

            ret=newEndret;
            //System.out.println("id:"+ret);


        }else if(u.name.equals("goto_expr")){
            for(int i=0;i<u.list.size();i++){
                Sgm sgm = u.list.get(i);
                if(!M.containsKey(sgm.getSon())) continue;
                int vid = M.get(sgm.getSon());
                Node v=Mp.get(vid);
                if(v.name.equals("label_decl")){

                    int d=mys[hed[vid]];
                    //System.out.println("???" +d);
                    // u -> v ->d
                    // u -> v ->d
                    ret=DFS1(uid,d,1);

                    //System.out.println(v.id);
                    flg=1;
                }
            }


        }else if(u.name.equals("label_expr")) {
           // System.out.println(u + " "+ end);
        }else if(u.name.endsWith("expr")){
            for(int i=0;i<u.list.size();i++){
                Sgm sgm = u.list.get(i);
                //System.out.println(sgm.getSon());
                if(!M.containsKey(sgm.getSon())) continue;
                int vid = M.get(sgm.getSon());
                Node v=Mp.get(vid);
                if(sgm.name.equals("body")){
                   ret=DFS1(uid,vid,1);
                   flg=1;
                }
            }
        }
        if(flg==0){

           // fs= Mp.containsKey(uid)  ? Mp.get(uid).toTest():"\""+uid+"\"";
           // uids=Mp.containsKey(end)  ? Mp.get(end).toTest():"\""+ end+"\"";
           // System.out.println(fs +" -> " +uids);
        }
        return ret;

    }
    public void solve1(){
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


    public static List<Edge> change(Tree tree) {
        //
        // expr
        // statement_list
        //

        //



        //DFS


        // 分支    拆出两个节点
        // A-> B ->E_A
        // A-> C ->E_A
        // E_A -> D
        // A->A_E -> B
        // 标明当前块 ?
        // ++cnt
        // 回去的问题
        //

        return null;
    }
}
