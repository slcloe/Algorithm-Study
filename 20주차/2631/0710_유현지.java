/*
 * | 활용 알고리즘 | Binary Search, LIS
 *
 * | 접근 방법 |
 *  1. 최장부분수열의 길이를 찾아서 전체 인원 수에서 뺀다
 */

package a2407.study.week20;

import java.io.*;

public class bj_g4_2631_줄세우기 {
    static int size = 0;
    static int[] arr, lis;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new int[N];
        for(int n=0; n<N; n++){ arr[n] = Integer.parseInt(br.readLine()); }
        lis[0] = arr[0];
        for(int n=1; n<N; n++){
            if(lis[size] < arr[n]){
                lis[++size] = arr[n];
            }
            else{
                int idx = binarySearch(0, size, arr[n]);
                lis[idx] = arr[n];
            }
        }
        System.out.println(N - size - 1);
    }

    static int binarySearch(int from, int to, int num){
        while(from < to){
            int mid = (from + to) / 2;
            if(lis[mid] < num){ from = mid + 1; }
            else{ to = mid; }
        }
        return to;
    }
}