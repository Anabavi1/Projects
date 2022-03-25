import java.util.*;
import java.util.Scanner;
import java.io.*;


class nabavian_p1{
  public static void main(String[] args) {
    try {
      File myObj = new File(args[0]);
      Scanner scanner = new Scanner(myObj);
      ArrayList<State> machine = new ArrayList<State>();
      ArrayList<Path> paths = new ArrayList<Path>();
      boolean repeat=true;

      while (scanner.hasNextLine()) {

        String [] data = scanner.nextLine().split("\\s+");
        int datasize= data.length;
        int name;
        
        if(data[0].equals("state")){
          

          if(datasize==2){
            name= Integer.parseInt(data[1]);
            State state = new State(name, -1, 0);
            machine.add(state);
          }else if(datasize==3){
            name= Integer.parseInt(data[1]);
            String type=data[2];
            if(type.equals("start") && repeat){
              State state = new State(name, -1, 1);
              machine.add(state);
              repeat= false;
            }else if(type.equals("accept")){
              State state = new State(name, -1, 2);
              machine.add(state);
            }else if(type.equals("reject")){
              State state = new State(name, -1, 3);
              machine.add(state);
            }else{
              System.out.println("Invalid state type");
            }
            
          }else{
            System.out.println("Too many inputs");
          }
        }else if(data[0].equals("transition")){
          if(datasize==6){  
            int q=Integer.parseInt(data[1]);
            String a=data[2];
            int r=Integer.parseInt(data[3]);
            String b=data[4];
            String x=data[5];
            Path path = new Path(q, a, r, b, x);
            paths.add(path);


          }else{
            System.out.println("Too many inputs");
          }
         
          
        }else{
          System.out.println("Invalid text file input. Try using 'state' or 'transition'.");
        }


      }


      StringBuilder input= new StringBuilder(args[1]);
      
      int max= Integer.parseInt(args[2]);
      int iteration=0;
      int position=0;
      State currentstate= new State(0,0,0);
      Path currentpath= new Path(0, "0", 0 , "0", "0");
      String s= input.substring(0, 1);

      for(int i=0; i<machine.size(); i++){
        if(machine.get(i).getType()== 1){
          currentstate= machine.get(i); 
          break;
        }
      }

      for(Path x: paths ){
        if(x.getQ()==currentstate.getCurrent() && x.getA().equals(s)){
          currentpath=x;
          break;
        }
      }
      
      String finalstatetype="quit";
      while(iteration<max){

        // ------Turn on to follow transitions------
        // System.out.println("TRANSITION " +iteration + "-------------");
        // System.out.println("INPUT STRING: " +input);
        // System.out.println("POSITION:     " + position);
        // System.out.println("PATH:         " + currentpath.getQ() + " " + currentpath.getA() + " " + currentpath.getR() + " " + currentpath.getB() + " " + currentpath.getX());
        
        
        if(currentstate.getType()==2){
          finalstatetype= "accept";
          break;
        }

        if(currentstate.getType()==3){
          finalstatetype= "reject";
          break;
        }

        if(position >= input.length()){
          s="_";
        }else{
          s= input.substring(position, position +1);
        }
        

        
        if(s.equals(currentpath.getA())){
          if(position>=input.length()){

            if(currentpath.getB().equals("_")){
              //input.append("_");
            }else{
              input.append(currentpath.getB());
            }

          }else if(currentpath.getB().equals("_")){
            input.setCharAt(position, '_');
          }else{
            input.setCharAt(position, currentpath.getB().charAt(0));
          }
        }
        

        if(currentpath.getX().equals("L")){
          position--; 
        }else if(currentpath.getX().equals("R")  ){
          position++;
        }else if(currentpath.getX().equals("S")){
          
        }

        if(position >= input.length()){
          s="_";
        }else{
          s= input.substring(position, position+1);
        }


        for(Path x: paths ){
          if((x.getQ()==currentpath.getR()) && (x.getA()).equals(s)){
            
            currentpath=x;
            break;
          }
        }
        

        for(int i=0; i<machine.size(); i++){
          if(machine.get(i).getCurrent()== currentpath.getR()){
            currentstate= machine.get(i); 
            break;
          }
        }

        
        iteration++;
      }
      
      if(iteration >= max){
        finalstatetype= "quit";

      }


      if(finalstatetype.equals("reject") || finalstatetype.equals("accept")){
        if(currentpath.getX().equals("L")){
          position--; 
        }else if(currentpath.getX().equals("R")  ){
          position++;
        }else if(currentpath.getX().equals("S")){
          
        }
      }

      String fin=input.substring(position, input.length());

      fin=fin.replaceAll("_", "");

      System.out.println(position);
      System.out.println(fin + " " + finalstatetype);


      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }
}
