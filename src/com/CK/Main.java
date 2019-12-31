package com.CK;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//        int[][] a = {{1, 3}, {2, 5}, {3, 7}, {4, 10}};
//        int[][] b = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
//        int target = 10;
        int[][] a = {{1, 8}, {2, 15}, {3, 9}};
        int[][] b = {{1, 8}, {2, 11}, {3, 12}};
        int target = 20;
        new Solution().OptimalUtilization(a, b, target);
    }
}

class Solution {
    public List<int[]> OptimalUtilization(int[][] a, int[][] b, int target) {
        int na = a.length, nb = b.length;
        int ptA = 0, ptB = nb - 1;
        Arrays.sort(a, (n1, n2)-> n1[1] - n2[1]);
        Arrays.sort(b, (n1, n2)-> n1[1] - n2[1]);
        List<int[]> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        while (ptA < na && ptB >= 0) {
            int val = a[ptA][1] + b[ptB][1];
            if (val > target) {
                ptB--;
            } else if (val < target) {
                pq.offer(new int[]{a[ptA][0], b[ptB][0], Math.abs(val - target)});
                ptA++;
            } else {
                pq.offer(new int[]{a[ptA][0], b[ptB][0], Math.abs(val - target)});
                if (ptA == na - 1) {
                    ptB--;
                    continue;
                }
                if (ptB == 0) {
                    ptA++;
                    continue;
                }
                if (Math.abs(a[ptA + 1][1] + b[ptB][1] - target) <= Math.abs(a[ptA][1] + b[ptB - 1][1] - target)) {
                    ptA++;
                } else
                    ptB--;
            }
        }
        if (pq.isEmpty())
            return res;
        int min = pq.peek()[2];
        while(!pq.isEmpty() && pq.peek()[2] == min) {
            int[] curr = pq.poll();
            res.add(new int[]{curr[0], curr[1]});
        }
        return res;
    }
}
