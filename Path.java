public class Path {
    private int q;
    private String a;
    private int r;
    private String b;
    private String x;

    public Path(int q, String a, int r, String b, String x){
        this.q=q;
        // if(a.equals("_")){
        //     this.a=" ";
        // }else{
        //     this.a=a;
        // }
        this.a=a;
        this.r=r;
        this.b=b;
        this.x=x;

    }

    public int getQ(){
        return q;
    }
    
    public String getA(){
        return a;
    }
    
    public int getR(){
        return r;
    }

    public String getB(){
        return b;
    }

    public String getX(){
        return x;
    }

    
}
