package entity;

import java.util.*;

import static io.ReadCode.checkAt;

public class Tree {

    Node []mys;

    Map<Integer,Integer> M;
    Map<Integer,Integer> rM;
    int cnt=0;
    final static HashSet<String> Mast=new HashSet<String>(Arrays.asList(new String[]{"body","0","1","op 0","op 1","op 2","size","name"}));
    //root  srcp:
    String roottype="srcp";
    String rootname="main.c";
    public List<Node> root;
    public int []vis;
    public Node getSon(String value){
        //
        if(checkAt(value)){

            try{
                int id=Integer.parseInt(value.substring(1));
                if(id<mys.length) return mys[id];
                else return null;
            }catch (Exception e){
                System.out.println(value);
            }



        }else{
            return null;
        }
        return null;
    }
    public Tree(Node []arr){
        //
        int n=arr.length;
        mys=arr;
        vis=new int[n];
        root=new ArrayList<>();
        for(int i=1;i<n;i++){
            //
            for(Sgm sgm : mys[i].list){
                //System.out.println(sgm.name);
                if(sgm.name.equals(roottype)&&sgm.value.startsWith(rootname)){
                    //System.out.println("find at"+ i);
                    root.add(mys[i]);
                }
            }
        }
        M=new HashMap<>();
        rM=new HashMap<>();
        cnt=0;
    }

    public Node get(int id){
        //
        if(id>=0 && id <mys.length) return mys[id];
        else return null;
    }



    public void DFS(Node u,String dep,List<Edge> lis,Set<String> S){
        //

        //System.out.println(dep+u+" "+u.id);
        //if(u==null) return ;
        if(vis[u.id]==1) return;
        vis[u.id]=1;
        //System.out.println(dep+u.id);
        for(Sgm sgm :u.list){
            if(checkAt(sgm.value)){
                //System.out.println(sgm.value+" "+checkAt(sgm.value));
                Node v=getSon(sgm.value);
                //if(v==null) System.out.println("@fuck "+u+"@sgm: " +sgm);
                if(v!=null&&Mast.contains(sgm.name)||u.name.equals("statement_list")){
                    //System.out.println(u.toGraphNode());
                    //System.out.println(v.toGraphNode());
                    S.add(u.toGraphNode());
                    S.add(v.toGraphNode());
                    if(!M.containsKey(u.id)) {M.put(u.id,++cnt);rM.put(cnt,u.id);}
                    if(!M.containsKey(v.id)) {M.put(v.id,++cnt);rM.put(cnt,v.id);}

                    int nu=M.get(u.id);
                    int nv=M.get(v.id);
                    lis.add(new Edge(u.id,v.id,"\""+sgm.name+"\""));

                    DFS(v,dep,lis,S);
                }

            }
        }
    }


}
