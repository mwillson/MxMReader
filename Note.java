
import java.util.*;

/**
 *
 * @author mwillson
 */
public class Note {
    
    private int pitch, freq = 440;
    private int durnumerator, durdenominator;
    
    public Note (String p, int durnum, int durden){
        switch(p) {
            case "A": pitch = 0;
                      break;
            case "B": pitch = 2;
                      break;
            case "C": pitch = 3;
                      break;
            case "D": pitch = 5;
                      break;
            case "E": pitch = 7;
                      break;
            case "F": pitch = 8;
                      break;
            case "G": pitch = 10;
                      break;
            case "A#": pitch = 1;
                      break;
            case "Bb": pitch = 1;
                      break;
            case "C#": pitch = 4;
                      break;
            case "Db": pitch = 4;
                      break;
            case "D#": pitch = 6;
                      break;
            case "Eb": pitch = 6;
                      break;
            case "F#": pitch = 9;
                      break;
            case "Gb": pitch = 9;
                      break;
            case "G#": pitch = 11;
                      break;
            case "Ab": pitch = 11;
                      break;
        }
        durnumerator = durnum;
        durdenominator = durden;
    }
    
    public int getPitch(){
        return pitch;
    }
    
    public double getDurationNumerator(){
        return durnumerator;
    }

    public int getDurationDenominator(){
        return durdenominator;
    }
    
    /*
     * return a usable matrix representation of the pitch of the note
     */
    
    public char[] pitchMatrix () {
      char[] m = new char[12];
      for(int i = 0; i < 12; i++){
          if(i == pitch){
              m[i] = '1';
          }else {
              m[i] = '0';
          }
      }
      return m;
    }

    /*
     * return a usable matrix representation of the duration of the note
     */

    public char[] durationMatrix () {
      System.out.println("numerator: " + durnumerator);
      System.out.println("denom: " + durdenominator);
      char[] m = new char[24];
      for(int i = 0; i < 12; i++) {
        if((i+1) == durnumerator) m[i] = '1';
        else m[i] = '0';
      }
      for(int i = 12; i < 24; i++) {
        if((i-11) == durdenominator) m[i] = '1';
        else m[i] = '0';
      }
      return m;
    }
   
}
