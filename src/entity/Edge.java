package entity;

public class Edge {


    public int u;
    public int v;
    public String msg;
    public  Edge(int u,int v,String msg){
        this.u=u;
        this.v=v;
        this.msg=msg;
    }
    public Edge(int u,int v){
        this.u=u;
        this.v=v;
        msg="";
    }

    @Override
    public String toString() {
        return u + ":"+ msg +" -> " + v;
    }
}
