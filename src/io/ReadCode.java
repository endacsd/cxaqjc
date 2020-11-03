package io;

import entity.Node;
import entity.Sgm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCode {





    private static void Deb(String str){
        if(debug) System.out.println(" "+str);
    }

    public static boolean checkAt(String str){
        return str.length()>1 ? str.charAt(0)=='@' : false;
    }

    public static boolean checkIs(String str){
        return str.length()==1 ? str.charAt(0)==':' : false;
    }

    public static boolean checkEnd(String str){
        return str.length()>1 ? str.charAt(str.length()-1)==':' : false;
    }
    private static final boolean debug=false;


    //static final String filename="src/io/main.c.001t.tu";
    static final String filename="src/main/99/main.c.001t.tu";
    public static List<Node> read() throws Exception{


        List<Node> ret=new ArrayList<>();


        //


        //

        File file=new File(filename);
        //

        //
        if(!file.exists()){
            System.out.println("file is not exists!");
            throw new Exception("file is not exists");
        }

        //

        String text=readfile(file);
        String []pstr=text.split("\\s+");

        //change

        //
        List<String> stringList=new ArrayList<>();
        //
        for(String s: pstr){

            //
            if(checkIs(s)){
                stringList.set(stringList.size()-1,stringList.get(stringList.size()-1)+":");
            }else{
                stringList.add(s);
            }
        }

        //
        String []str=new String[stringList.size()];

        for(int i=0;i<str.length;i++){
            str[i]=stringList.get(i);
        }
        if(debug){


            System.out.println(text.length());
            System.out.println(str.length);
            //
            for(int i=0;i<Math.min(200,str.length);i++){
                System.out.println(str[i]);
            }
        }
        //

        //
        //
        // @id  name   {name:value}

        //
        // x begin

        int id=0;
        int begin=0;

        Deb(id+" "+begin);
        int cur=0;
        //if(true)return null;

        while(cur<str.length){


            // @id


            String ids;
            String types;
            if(!checkAt(str[cur])){
                System.out.println("ids error: "+ str[cur]);
                return null;
            }
            ids=str[cur];cur++;
            if(checkAt(str[cur])){
                System.out.println("type error: "+ str[cur]);
                return null;
            }
            types=str[cur];cur++;
            Deb(ids+" "+types+" >");
            //read Sgment

            List<Sgm> list=new ArrayList<>();
            while(cur<str.length&&!checkAt(str[cur])){
                //

                //


                String names=str[cur];cur++;
                if(names.equals("op")) {names+=(" "+str[cur]);cur++;}
                String values=str[cur];cur++;

                while(cur<str.length&&!checkAt(str[cur])&&!checkEnd(str[cur])&&!checkAt(values)) {values+=(" "+str[cur]);cur++;}
                Deb("   \t"+names+values);
                list.add(new Sgm(names.substring(0,names.length()-1),values));
            }

            int idn=new Integer(ids.substring(1));
            ret.add(new Node(idn,types,list));

        }




        return ret;
    }


    private static String readfile(File file){

        //
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr+" ");
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
}
