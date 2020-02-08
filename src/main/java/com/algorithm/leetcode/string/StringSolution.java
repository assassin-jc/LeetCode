package com.algorithm.leetcode.string;

import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * *****************************************************************************
 *
 * @date 2020/1/15 15:10
 * @Author: jiangchen;
 * @Package com.algorithm.leetcode.string
 * Description:
 * Version: 1.0
 * ****************************************************************************
 */
public class StringSolution {


    /*

        请你来实现一个 atoi 函数，使其能将字符串转换成整数。
        首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
        当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
        该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
        注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
        在任何情况下，若函数不能进行有效的转换时，请返回 0。
        说明：
        假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     */

    private static int myAtoi(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        String input = str.trim();
        boolean isMinus = false;
        int index = 0;
        if (input.charAt(0) == '-') {
            isMinus = true;
            index++;
        } else if (input.charAt(0) == '+') {
            index++;
        } else if (!Character.isDigit(input.charAt(0))) {
            return 0;
        }
        long result = 0;
        for (int i = index; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                break;
            } else {
                result = result * 10 + input.charAt(i) - '0';
            }
        }
        return isMinus ? (int) (-1 * result) : (int) (result);
    }

    /*
    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
        示例 1：
        输入: "babad"
        输出: "bab"
        注意: "aba" 也是一个有效答案。
        示例 2：
        输入: "cbbd"
        输出: "bb"
     */


    public static String longestPalindrome(String s) {

        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);

    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
     * <p>
     * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
     * <p>
     * 如果 S[i] == "I"，那么 A[i] < A[i+1]
     * 如果 S[i] == "D"，那么 A[i] > A[i+1]
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输出："IDID"
     * 输出：[0,4,1,3,2]
     * 示例 2：
     * <p>
     * 输出："III"
     * 输出：[0,1,2,3]
     * 示例 3：
     * <p>
     * 输出："DDI"
     * 输出：[3,2,0,1]
     *
     * @param S
     * @return
     */
    public int[] diStringMatch(String S) {
        int n = S.length();
        int lo = 0, hi = n;
        int[] result = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            if (S.charAt(i) == 'I') {
                result[i] = lo++;
            } else {
                result[i] = hi--;
            }
        }
        result[n] = lo;
        return result;
    }

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc" 
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(new StringBuilder(word).reverse().append(" "));
        }
        return sb.toString().trim();
    }

    /**
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        StringBuilder stringBuilder = new StringBuilder(str);
        if (str.equals(stringBuilder.reverse().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean IsPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        myAtoi("42");
        longestPalindrome("babad");
    }
}
