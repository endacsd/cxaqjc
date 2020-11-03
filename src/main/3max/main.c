int main(){
    int a=1,b=2,c=3;
    int max=0;
    if(a>=b){
        if(a>=c)    max=a;
        else    max=c;
    }
    else{
        if(b>=c)    max=b;
        else     max=c;
    }
    return 0;
}