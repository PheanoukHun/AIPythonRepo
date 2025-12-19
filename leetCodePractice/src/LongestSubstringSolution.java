public class LongestSubstringSolution {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i + 1);

            int currentMax = Math.max(len1, len2);

            if (currentMax > maxLength) {
                start = i - (currentMax - 1) / 2;
                maxLength = currentMax;
            }
        }

        return s.substring(start, start + maxLength);
    }

    private static int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcabcbb"));
        System.out.println(longestPalindrome("bbbbb"));
        System.out.println(longestPalindrome("pwwkew"));
    }
}
