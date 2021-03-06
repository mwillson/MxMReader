###MxMReader

A program which encodes MxM (Musical Exploration Machine) output data
as activation matrices for use with the Emergent neural network modeling
program. 


###How it Works

Musical tokens are represented as 3 distinct arrays, or "matrices", as they
are referred to often in the code. 1 array represents the pitch of the token,
another represents its duration, and the final array represents the timbre of
the particular "note".


###Pitch

Pitch is the simplest to represent, although this is likely due to the fact
that (admittedly important) things such as scale and key are not currently taken
into account. As of this writing, pitch information is encoded as one of twelve
note values along the chromatic scale. Associated is a vibration frequency in hertz,
although I have yet to make a real use for that. The BIG assumption here is that
these are just encodings of specific frequencies along a certain spectrum that
we deem to be important to music.

Example pitch encodings (where A is the base frequency of 440 hz):

the note C	  => 000100000000
the note A        => 100000000000 
the note F# or Gb => 000000000100 


###Duration

Duration is a little bit trickier. It must be encoded so as to take into account
fractions of beats as well as multiples. This is done with simple division. One half
of the representation matrix stands for the numerator of a fraction, the other half
stands for the denominator. 

#####Side note
A future implementation of the duration encoding function may want 
to recursively subdivide each half of the array, allowing more complex fractional 
notation and any possibility for a length, for example:
12/(2/3) = 12*(3/2) = 36/2 = 18 beats, or
(1/2)/12 = 1/24 of a beat
#####End of side note

So, on to examples. The matrix for now is set at a size 24, which means a numerator with
a possible 12 options, and a denominator with the same. Keep in mind that there are mult-
iple possibilities for any duration based on this model. Whole beats are always represented
in the simplest way, with a denominator of 1.

Duration of 8 beats         => 000000010000100000000000
Duration of .5 beats        => 100000000000010000000000
Also a duration of .5 beats => 010000000000000100000000


###Timbre

As there are a limited number of timbre options, this seems to be pretty much a free choice
as to how to encode it. So as to not have a huge array with a bunch of 0's and one 1, this is
going to be encoded in binary. My current count says there are exactly 128 possibilities, 
which is awfully convenient, so, we represent this as an 7 bit array based on the location
in the timbres array of our particular timbre.

Examples:
"cello"   = timbres(127) => 1111111
"gunshot" = timbres(51)  => 0110011
