package mxmreader;

/**
 *
 * @author mwillson
 */
public class Timbre {
    
    private String name;
    
    public Timbre (String n){
        name = n;
    }
    
    public String getName(){
        return name;
    }
    
    /*
     * return a usable matrix representation of the pitch of the note
     */
    
    public int[] timbreMatrix (){
      int[] m = new int[12];
      for(int i = 0; i < 12; i++){
          if(i == Integer.parseInt(name)){
              m[i] = 1;
          }else {
              m[i] = 0;
          }
      }
      return m;
    }    
    
}