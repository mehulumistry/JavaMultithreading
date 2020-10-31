package com.company;

/**
 * Date: 9/19/20
 * Time: 3:38 PM
 */
public class HackerRank1 {
    public static void main(String[] args) {
        System.out.println(countStrings2("aaa"));
    }


    //public static Hashtable<Integer, Character> ht;

    public static char[] circularShift(char[] chrs) {
        int len = chrs.length;
        char[] newChar = new char[len];

        for(int i =0; i < len - 1; i++) {
            int j = i + 1;
            newChar[j] = chrs[i];
        }
        newChar[0] = chrs[len - 1];
        return newChar;
    }

    public static int countStrings2(String s) {
        // Write your code here
        char tempChar = 0;
        int count = 0;
        char firstChar = s.charAt(0);
        char lastChar = s.charAt(s.length() - 1);

        if(firstChar == lastChar) {
            count += 1;
        }

        for(int i = 0; i< s.length(); i++) {
            char t = s.charAt(i);
            if(tempChar == t) {
                count += 1;
            } else tempChar = 0;

            tempChar = t;
        }

        return count;
    }

    public static int countStrings(String s) {
        // Write your code here
        char[] chrs = new char[s.length()];

        for(int i = 0; i< s.length(); i++) {
            char t = s.charAt(i);
            chrs[i] = t;
        }

        int count = 0;
        char[] tempChars = chrs;

        for(int i =0; i< s.length(); i++) {
            tempChars = circularShift(tempChars);
            if(tempChars[0] == tempChars[s.length() - 1]) count ++;
        }
        return count;

    }


}

