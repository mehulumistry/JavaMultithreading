package com.company.multithreading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Date: 11/29/20
 * Time: 1:24 PM
 */
public class ReferenceAllocationInMultiThreading {

    private Map<Integer, String> idToNameMap; // ref is allocated to heap, because it is class member
    private static long numberOfInstances = 0; // class member, static fields and methods are not thread safe

    ReferenceAllocationInMultiThreading() {
       this.idToNameMap = new HashMap<>();
       numberOfInstances++;
   }

    public List<String> getAllNames() {
        int count = idToNameMap.size(); // multiple threads will have count variable on their stack.
        List<String> allNames = new ArrayList<>(); // ref in stack and ArrayList in heap

        allNames.addAll(idToNameMap.values());

        return allNames;
   }

   Supplier<Integer> incrementer(final int start) {
        return () -> start + 2; // cannot do start++
   }


}
