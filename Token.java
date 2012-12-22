package mxmreader;

/**
 *
 * @author mwillson
 */
 
public class Token {
    
    private String text = "";
    public String[] timbres = 
        {"PIANO", "BRIGHT_ACOUSTIC", "ELECTRIC_GRAND", "HONKEY_TONK", 
	"ELECTRIC_PIANO1", "ELECTRIC_PIANO2", "HARPSICHORD", "CLAVINET",
	"DRAWBAR_ORGAN", "PERCUSSIVE_ORGAN", "ROCK_ORGAN", "CHURCH_ORGAN",
	"REED_ORGAN", "ACCORDIAN", "HARMONICA", "TANGO_ACCORDIAN", "ACOUSTIC_BASS",
	"ELECTRIC_BASS_FINGER", "ELECTRIC_BASS_PICK", "FRETLESS_BASS",
	"SLAP_BASS_1", "SLAP_BASS_2", "SYNTH_BASS_1", "SYNTH_BASS_2",
	"STRING_ENSEMBLE_1", "STRING_ENSEMBLE_2", "SYNTH_STRINGS_1",
	"SYNTH_STRINGS_2", "CHOIR_AAHS", "VOICE_OOHS", "SYNTH_VOICE", 
	"ORCHESTRA_HIT", "CELESTA", "GLOCKENSPIEL", "MUSIC_BOX", "VIBRAPHONE",
	"MARIMBA", "XYLOPHONE", "TUBULAR_BELLS", "DULCIMER", "TIMPANI",
	"GUITAR", "STEEL_STRING_GUITAR", "ELECTRIC_JAZZ_GUITAR", 
	"ELECTRIC_CLEAN_GUITAR", "ELECTRIC_MUTED_GUITAR", "OVERDRIVEN_GUITAR",
	"DISTORTION_GUITAR", "GUITAR_HARMONICS", "VIOLIN", "VIOLA", "CELLO",
	"CONTRABASS", "TREMOLO_STRINGS", "PIZZICATO_STRINGS", "ORCHESTRAL_STRINGS",
	"TRUMPET", "TROMBONE", "TUBA", "MUTED_TRUMPET", "FRENCH_HORN", 
	"BRASS_SELECTION", "SYNTH_BRASS_1", "SYNTH_BRASS_2", "SOPRANO_SAX", 
	"TENOR_SAX", "ALTO_SAX", "BARITONE_SAX", "OBOE", "ENGLISH_HORN",
	"BASSOON", "CLARINET", "SQUARE", "SAWTOOTH", "CALLIOPE", "CHIFF", 
	"CHARANG", "VOICE", "FIFTHS", "BASSLEAD", "RAIN", "SOUNDTRACK", "CRYSTAL",
	"ATMOSPHERE", "BRIGHTNESS", "GOBLINS", "ECHOES", "SCI-FI", "TINKLE_BELL",
	"AGOGO", "STEEL_DRUMS", "TAIKO_DRUM", "MELODIC_TOM", "SYNTH_DRUM", 
	"WOODBLOCK", "REVERSE_CYMBAL", "PICCOLO", "FLUTE", "RECORDER", "PAN_FLUTE",
	"BLOWN_BOTTLE", "SKAKUHACHI", "WHISTLE", "OCARINA", "NEW_AGE", "WARM", 
	"POLYSYNTH", "CHOIR", "BOWED", "METALLIC", "HALO", "SWEEP", "SITAR", 
	"BANJO", "SHAMISEN", "KOTO", "KALIMBA", "BAGPIPE", "FIDDLE", "SHANAI",
	"GUITAR_FRET_NOISE", "BREATH_NOISE", "SEASHORE", "BIRD_TWEET",
	"TELEPHONE_RING", "HELICOPTER", "APPLAUSE", "GUNSHOT"};
    
    public Token (String t) {
        text = t;
    }
    
    public void setText(String t) {
        text = t;
    }
    
    public String getText() {
        return text;
    }
    
    /*
     * returns true if the token represents a note with a duration
     */
    
    public boolean isNote() {
      return (!(isTimbre()) && !(text.equals("||")) && !(pitchChange()));
    } 

	/*
	 * returns true if it is a timbre change (instrument) token
	 */
	 
    public boolean isTimbre() {
		for(int i = 0; i < timbres.length; i++) {
		  if(text.equals(timbres[i])) return true;
		}
		return false;
	}	
    
	/*
	 * returns true if it is a pitch raise or lower token
	 */
	 
	public boolean pitchChange (){
        return ( text.equals("/") || text.equals("\\") ); 
    }
	
	/*
	 * If the token represents a note, returns an int representing its
	 * duration, otherwise returns 0.
	 */
	 
	public Note parseNote () {
          double noteDuration = 0.0;
          String notePitch = "";
	  int start = 0;
	  String durString = null;
	  char[] first, second;
	    char[] ptext = text.toCharArray();
		if((new Character(ptext[1])).equals('b') || 
                   (new Character(ptext[1])).equals('#')) {
			start = 2;
		}else {
			start = 1;
		}
		char[] durCharArray = new char[ptext.length - start];
		for(int i = start; i < ptext.length; i++) {
			durCharArray[i - start] = ptext[i];
		}
		durString = new String(durCharArray);
		//duration is a fraction
		if(durString.split("/").length > 1) {
			//divLoc - location of div sign
			int divLoc = 0;
			for(int i = 0; i < durCharArray.length; i++) {
			  if((new Character(durCharArray[i])).equals('/')) {
                              divLoc = i;
                          } 
			}
			first = new char[divLoc+1];
			second = new char[durCharArray.length - (divLoc + 1)];
			for(int i = 0; i < divLoc; i++) {
				first[i] = durCharArray[i];
			}
			for(int i = divLoc + 1; i < durCharArray.length; i++) {
				second[i] = durCharArray[i];
			}
			noteDuration = Integer.parseInt(new String(first)) / 
				   Integer.parseInt(new String(second));
		}else {
                    //duration is not a fraction
                    noteDuration = Integer.parseInt(durString);
                }
                
                return new Note(notePitch, noteDuration);
	}//parseDuration
	
}