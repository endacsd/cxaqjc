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
}
