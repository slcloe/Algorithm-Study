package a2402.study.week5;

/*
* 활용 알고리즘 : 이분 탐색
* 1. 수 A에 대해 배열 전체 탐색, 탐색으로 도는 수는 B라고 지칭
* 2. 이분 탐색으로 배열에 A-B가 있는지 검색
* 3. 있다면 answer 증가
*/

import java.io.*;
import java.util.*;

public class  bj_g4_1253_좋다 {
    static int N;
    static int[] arr;
    static boolean isFound;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N < 3){ // N이 3개 미만일 경우 좋은 수가 될 수 없음
            System.out.println(0);
            return;
        }
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){ arr[n] = Integer.parseInt(st.nextToken()); }
        Arrays.sort(arr);
        int answer = 0;
        for(int n=0; n<N; n++){
            isFound = false;
            for(int m=0; m<N; m++){
                if(n == m){ continue; }
                if(findOther(0, N-1, arr[n]-arr[m], n, m)){
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    static boolean findOther(int from, int to, int num, int idxN, int idxM) {
        if(isFound){ return true; }
        if(from > to){ return false; }
        int mid = (from + to) / 2;
        if(num == arr[mid] && mid!=idxN && mid!=idxM){
            isFound = true;
            return true;
        }
        if(num < arr[mid]){ return findOther(from, mid-1, num, idxN, idxM); }
        return findOther(mid+1, to, num, idxN, idxM);
    }
}
