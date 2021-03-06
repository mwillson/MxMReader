/**
 *
 * @author mwillson
 */

public class Timbre {
    
    private String name;
    private int position;

    public Timbre (String n, int p){
        name = n;
        position = p;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPosition() {
        return position;
    }

    /*
     * Return a usable matrix representation of the timbre of the note.
     * this is just a binary number representing the timbre's location
     * in the Token class's "timbres" array.
     */
    
    public char[] timbreMatrix () {
        //get binary representation
        char[] m = new char[7];
        char[] temp = (Integer.toBinaryString(position)).toCharArray();
        int size = temp.length;
        //add leading 0's to make it length 7
        for(int i = size; i < 7; i++) m[i-size] = '0';
        for(int i = (7-size); i < 7; i++) m[i] = temp[i-(7-size)];
        return m;
    }
    
}
