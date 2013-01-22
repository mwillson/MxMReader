package mxmreader;

import java.io.*;
import java.util.*;

/**
 *
 * @author mwillson
 * 
 * @description reads a file containing MxM (Musical Exploration Machine) output
 * , translates it, and writes it into standard LEABRA input file for Emergent.
 */
public class MxMReader {

	private static ArrayList<Note> notes = null;
	private static ArrayList<Timbre> timbres = null;
        private static int numRows = 0;
        
    /**
     * @param args the command line arguments
     */
	
    public static void main(String[] args) throws IOException {
        Scanner s = null;
        PrintWriter out = null;
	Token current = new Token("");
		
        try {
            s = new Scanner 
                    (new BufferedReader (new FileReader
                    ("/home/mwillson/Desktop/gamelan.txt"))
                    );
            out = new PrintWriter (new FileWriter("out_test.txt"));

            while ( s.hasNext() ) {
		// set text variable of token for further processing
                current.setText(s.next());
		//embody token in a class instance and assign it to a list
                classify(current);
            }
            // the number of rows for the input matrices is the total 
            // number of "notes" played
            numRows = notes.size();
            
            //Write out note info to file
            out.write("_D: ");
            for(int i = 0; i < notes.size(); i++) {
                out.write(notes.get(i).pitchMatrix());
            }
            out.println();
            
            //Write out duration info to file
            
            //Write out timbre info to file
			
        } finally {
            if (s != null) {
                s.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
	
    /*
     * Classify token as Note or Timbre
     */
    
    public static void classify (Token t) {
		if(t.isTimbre()) {
			timbres.add(new Timbre(t.getText()));
		}else if(t.isNote()) {
                        Note tempNote = t.parseNote();
			notes.add(tempNote);
		}
    }
    
    /**
     * create the three matrices to be written to the file for pitch,
     * duration, and timbre. (for now...possibly more dimensions later)
     */
    public static void constructMatrices() {
        
    }
  
}
