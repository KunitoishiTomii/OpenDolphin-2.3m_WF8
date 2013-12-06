package open.dolphin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Kazushi Minagawa, Digital Globe, Inc.
 */
public class HashUtil {

    private static final String MD5 = "MD5";
    private static final String ISO_8859_1 = "iso-8859-1";

    public static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String MD5(String text) {
        
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(text.getBytes(ISO_8859_1), 0, text.length());
            byte[] md5hash = md.digest();
            
            return convertToHex(md5hash);
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
            System.err.println(e1);
        }

        return null;
    }
}
