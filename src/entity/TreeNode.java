package entity;

import java.util.List;

public class TreeNode {
    public int id;
    public String val;
    public List<TreeNode> lis;


    public TreeNode(int id,String val,List<TreeNode> lis){
        this.id=id;
        this.val=val;
        this.lis=lis;
    }
    public boolean isLeaf(){
        return lis.size()==0;
    }

    //

}
