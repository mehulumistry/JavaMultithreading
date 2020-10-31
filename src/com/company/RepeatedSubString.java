package com.company;

/**
 * Date: 9/4/20
 * Time: 1:03 AM
 */

public class RepeatedSubString {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.repeatedSubstringPattern("abab"));
    }
}

class Solution {


    boolean checkForRepeatForGivenNumber(int i, String s, int strlen) {
        String temp = s.substring(0, i);

        for (int j=i; j<=strlen; j=j+i){
            int lastIndex = j + i;
            if(lastIndex - 1 > strlen){
                return false;
            } else {
                boolean isEql = temp.equals(s.substring(j,lastIndex));
                if(!isEql) {
                    return false;
                }
            }

        }
        return true;
    }

    boolean repeatedSubstringPattern(String s) {
        int strlen = s.length();
        int absStrLen = strlen - 1;

        for(int i=1; i<=absStrLen; i++){
            int res = strlen / i;
            if(res >= 2) {
                boolean out = checkForRepeatForGivenNumber(i, s, absStrLen);
                if(out) return true;
            } else break;
        }
        return false;


    }
}
