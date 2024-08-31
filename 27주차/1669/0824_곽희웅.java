package com.example.algo;

import java.util.*;
public class Main_bj_1669 {
    /*

    접근 방식 : 처음엔 PriorityQueue를 사용하는 정렬 문제인 줄 알고 접근했는데, 메모리 초과 발생.
               후에 수학을 활용하여 풀이
    주안점
    1. 가장 빠르게 멍멍이의 키에 도달할 수 있는 방법은 +1, +2, +3, ..., +2, +1이라는 것을 유의
    2. 그런데 2의 경우 +1, +2, +1도 가능하지만 +1, +1도 가능하기 때문에 단순히 현재 키만 제곱 후 차이값과 비교

     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int Y = sc.nextInt();

        if(X == Y) System.out.print(0);
        else {
            int diff = Y - X;

            int start = 1;

            while(true) {
                long next = (long) Math.pow(start, 2) + start;
                if(next >= diff) break;
                start++;
            }
            System.out.print(start*2-((long) Math.pow(start, 2) >= diff ? 1 : 0));
        }
    }
}
