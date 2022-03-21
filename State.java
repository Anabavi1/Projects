public class State{
    private int current;
    private int next;
    private int type;

    public State(int current, int next, int type){
        // type: 0-none, 1-start, 2-accept, 3-reject 
        // next: -1-null pointer
        this.current=current;
        this.next=next;
        this.type=type;

    }

    public int getCurrent(){
        return current;
    }
    public int getNext(){
        return next;
    }
    public int getType(){
        return type;
    }


}