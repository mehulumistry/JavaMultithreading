package com.company;

/* Finding max from the number */

public class LCSepW1 {
    public static void main(String[] args) {
        int[] arr = new int[]{2,0,6,6};
        System.out.println(test(arr));
    }

    public static String calculateMaxTime(int[] B, int l1, int l2, int l3, int l4) {
        int d1 = -1;
        int d2 = -1;
        int d3 = -1;
        int d4 = -1;

        int[] A = B.clone();

        String specialFlag = "";

        for(int i=l1; i>=0;i--){
            int locInx = JavaHelper.getIndexFromArray(A, i);
            if(locInx != -1){
                if(i==2){
                    l2=3;
                    // for this case loop again if result is empty
                    specialFlag = "1";
                }
                d1 = i;
                A[locInx] = -1;
                break;
            }
        }

        for(int i=l2; i>=0;i--){
            int locInx = JavaHelper.getIndexFromArray(A, i);
            if(locInx != -1){
                d2 = i;
                A[locInx] = -1;
                break;
            }
        }
        for(int i=l3; i>=0;i--){
            int locInx = JavaHelper.getIndexFromArray(A, i);
            if(locInx != -1){
                d3 = i;
                A[locInx] = -1;
                break;
            }
        }
        for(int i=l4; i>=0;i--){
            int locInx = JavaHelper.getIndexFromArray(A, i);
            if(locInx != -1){
                d4 = i;
                A[locInx] = -1;
                break;
            }
        }

        if(d1 == -1 | d2 == -1 | d3 == -1 | d4 == -1) {
            return specialFlag;
        } else {
            return ""+d1+d2+":"+d3+d4;
        }
    }

    public static String test(int[] A){

        String result = calculateMaxTime(A,2,9,5,9);
        if(result.equals("1")) {
            return calculateMaxTime(A,1,9,5,9);
        }
        return result;

    }

}
