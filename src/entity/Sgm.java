package entity;

public class Sgm {


    public String name;
    public String value;

    public Sgm(String name,String value){
        this.name=name;
        this.value=value;
    }
    public Sgm(){}

    @Override
    public String toString() {
        return name+":"+value+"";
    }

    public String toGraph() {
        return name + (value.startsWith("@") ? "": ":"+value);
    }

    public int getSon(){

        if(value.startsWith("@")){
            return new Integer(value.substring(1));
        }else{
            return -1;
        }
    }
}
