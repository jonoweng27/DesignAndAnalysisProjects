package edu.yu.da;

import java.util.Arrays;

public class MultiMerge extends MultiMergeBase {
    int[][] arr;
    public MultiMerge() {
        super();
    }

    @Override
    public int[] merge(int[][] arrays) {
        //break down recursively until down to one array, and then merge the arrays into another array
        arr=Arrays.copyOfRange(arrays, 0, arrays.length);
        recurse(0, arrays.length-1);
        return arr[0];
    }
    private void recurse(int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo+(hi - lo)/2;
        recurse(lo, mid);
        recurse(mid+1, hi);
        arr[lo]=merge(arr[lo],arr[(int)Math.ceil(((double)lo+hi+1)/2)]);
    }
    //merges two sorted arrays and returns the one sorted array
    private int[] merge(int[] a, int[] b) {
        int[] newArr = new int[a.length+b.length];
        int aNum=0;
        int bNum=0;
        int newArrNum=0;
        while (aNum<a.length&&bNum<b.length) {
            if (a[aNum]<b[bNum]) {
                newArr[newArrNum++]=a[aNum++];
            } else {
                newArr[newArrNum++]=b[bNum++];
            }
        }
        while(aNum<a.length) {
            newArr[newArrNum++]=a[aNum++];
        }
        while(bNum<b.length) {
            newArr[newArrNum++]=b[bNum++];
        }
        combinedAMerge();
        return newArr;
    }
}