package jsh.boggle.util;

/**
 * @author Joël Hoekstra
 */
public class Config {
    public static final String APP_NAME = "Boggle Solver Made Visual";
    public static final String DICTIONARY_FILENAME = "dict.txt";
    public static final int GRID_SIZE = 4;

    public static final char[][] DIE_FACES = {
            {'a', 'a', 'e', 'e', 'g', 'n'}, // [A A E E G N]
            {'a', 'b', 'b', 'j', 'o', 'o'}, // [A B B J O O]
            {'a', 'c', 'h', 'o', 'p', 's'}, // [A C H O P S]
            {'a', 'f', 'f', 'k', 'p', 's'}, // [A F F K P S]

            {'a', 'o', 'o', 't', 't', 'w'}, // [A O O T T W]
            {'c', 'i', 'm', 'o', 't', 'u'}, // [C I M O T U]
            {'d', 'e', 'i', 'l', 'r', 'x'}, // [D E I L R X]
            {'d', 'e', 'l', 'r', 'v', 'y'}, // [D E L R V Y]

            {'d', 'i', 's', 't', 't', 'y'}, // [D I S T T Y]
            {'e', 'e', 'g', 'h', 'n', 'w'}, // [E E G H N W]
            {'e', 'e', 'i', 'n', 's', 'u'}, // [E E I N S U]
            {'e', 'h', 'r', 't', 'v', 'w'}, // [E H R T V W]

            {'e', 'i', 'o', 's', 's', 't'}, // [E I O S S T]
            {'e', 'l', 'r', 't', 't', 'y'}, // [E L R T T Y]
            {'h', 'i', 'm', 'n', 'u', 'q'}, // [H I M N U Q]
            {'h', 'l', 'n', 'n', 'r', 'z'}  // [H L N N R Z]
    };

    // private constructor <3 'cause this is not an singleton or anything-
    // else just a storage class for static use.
    private Config() { }
}
