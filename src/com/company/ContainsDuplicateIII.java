package com.company;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeSet;

/**
 * Date: 9/4/20
 * Time: 11:55 PM
 */
public class ContainsDuplicateIII {
    public static void main(String[] args) {
        SolContainsDuplicateIII sol = new SolContainsDuplicateIII();
        //System.out.println(sol.containsNearbyAlmostDuplicateA1(new int[]{1,3,4,5,3,3,4,9,8,5,3},2, 0));

        System.out.println(sol.containsNearbyAlmostDuplicateA2(new int[]{2147483646,2147483647},3, 3));
    }
}

class SolContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicateA1(int[] nums, int k, int t) {

        if(nums.length <= 20000 && k > 0 && k <= 10000 && t >=0){

            if(t == 0) {

                Hashtable<Integer, Integer> hs = new Hashtable<>(20000);

                for (int i=0; i<nums.length; i++) {
                    hs.put(nums[i], i);
                }

                int minLoop = Math.min(k, nums.length - 1);

                for(int i = minLoop; i>=0; i--){
                    if(hs.contains(nums[i])) {
                        int temp = hs.get(nums[i]);
                        if(temp != i && Math.abs(temp - i) <= k) return true;
                    }
                }

                return false;

            }

            for(int j = 1; j<=k; j++){
                for(int i = 0; i < nums.length - j; i++){

                    if((long)Math.abs(nums[i] - nums[i+j]) <= t || (long)Math.abs(nums[i+j] - nums[i]) <= t) {
                        return true;
                    }

                }
            }

        }

        return false;
    }

    /*
     * nums[i] - t <= nums[j] <= nums[i] + t
     * i - t <= j <= i + t
     * */

    public boolean containsNearbyAlmostDuplicateA2(int[] nums, int k, int t) {

        if(nums==null||nums.length<2||k<0||t<0) return false;

        for(int i = 0; i<nums.length; i++){

            long leftBoundary = (long)nums[i] - t;
            long rightBoundary = (long)nums[i] + t;

            int rightIndx = Math.min(i + k, nums.length - 1);

            // Optimize the searching Right now it is O(k) -> O(logK)

            for(int j=i+1; j<=rightIndx; j++){
                if(leftBoundary <= nums[j] && rightBoundary >= nums[j]){
                    return true;
                }
            }

        }
        return false;

    }

    public boolean containsNearbyAlmostDuplicateA3(int[] nums, int k, int t) {

        if(nums==null||nums.length<2||k<0||t<0) return false;

        TreeSet<Long> ts = new TreeSet<>();

        for(int i = 0; i<nums.length; i++){

            long l = (long) nums[i];

            Long leftBoundary = ts.ceiling(l - t);
            Long rightBoundary = ts.floor(l + t);


            if(leftBoundary != null && t >= leftBoundary - l || rightBoundary !=null && t >= l - rightBoundary){
                return true;
            }

            ts.add(l);

            if(i >= k){
                ts.remove((long)nums[i-k]);
            }

        }
        return false;

    }

}
