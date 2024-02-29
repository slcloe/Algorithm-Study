package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class week1_1253_0227_김한슬 {

    static int[] arr;
    static boolean[] v;
    static int N;

    static void binarySearch(int n, int start){
        int l = start;
        int r = N;
        int mid;

        while (l < r){
            mid = (l + r) / 2;
            if (arr[mid] < n){
                l = mid + 1;
            }else if (arr[mid] > n){
                r = mid;
            }else{
                v[mid] = true;
                int i = mid + 1;
                while (0 <= i && i < N && arr[mid] == arr[i]){
                    v[i] = true;
                    i++;
                }
                i = mid - 1;
                while (0 <= i && i < N && arr[mid] == arr[i]){
                    v[i] = true;
                    i--;
                }
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        v = new boolean[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);


        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                binarySearch(arr[i] + arr[j], j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (v[i]) cnt++;
        }
        System.out.println(Arrays.toString(v));
        System.out.println(cnt);
    }
}
