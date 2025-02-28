import java.util.HashSet;

// CS108 HW1 -- String static methods

public class StringCode {

    /**
     * Given a string, returns the length of the largest run.
     * A run is a series of adjacent chars that are the same.
     *
     * @param str input
     * @return max run length
     */
    public static int maxRun(String str) {
        int result = 1;
        int length = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                length++;
            } else {
                length = 1;
            }
            result = Math.max(result, length);
        }
        return result;
    }


    /**
     * Given a string, for each digit in the original string,
     * replaces the digit with that many occurrences of the character
     * following. So the string "a3tx2z" yields "attttxzzz".
     *
     * @param str input
     * @return blown up string
     */
    public static String blowup(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) && i != str.length() - 1) {
                result.append(String.valueOf(str.charAt(i + 1)).repeat(Character.getNumericValue(str.charAt(i) - '0')));
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * Given 2 strings, consider all the substrings within them
     * of length len. Returns true if there are any such substrings
     * which appear in both strings.
     * Compute this in linear time using a HashSet. Len will be 1 or more.
     * wtf rolling hash
     */
    public static boolean stringIntersect(String a, String b, int len) {

        final int base = 31;
        final int mod = 1000000009;

        long hashA = 0;
        for (int i = 0; i < len; i++) {
            hashA = (hashA * base + (a.charAt(i) - 'a' + 1)) % mod;
        }

        HashSet<Long> hashesA = new HashSet<>();
        hashesA.add(hashA);

        long power = 1;
        for (int i = 0; i < len - 1; i++) {
            power = (power * base) % mod;
        }
        for (int i = 1; i <= a.length() - len; i++) {
            hashA = (hashA - (a.charAt(i - 1) - 'a' + 1) * power) % mod;
            if (hashA < 0) {
                hashA += mod;
            }
            hashA = (hashA * base + (a.charAt(i + len - 1) - 'a' + 1)) % mod;
            hashesA.add(hashA);
        }

        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + (b.charAt(i) - 'a' + 1)) % mod;
        }

        if (hashesA.contains(hashB)) {
            return true;
        }
        for (int i = 1; i <= b.length() - len; i++) {
            hashB = (hashB - (b.charAt(i - 1) - 'a' + 1) * power) % mod;
            if (hashB < 0) {
                hashB += mod;
            }
            hashB = (hashB * base + (b.charAt(i + len - 1) - 'a' + 1)) % mod;
            if (hashesA.contains(hashB)) {
                return true;
            }
        }
        return false;
    }
}
