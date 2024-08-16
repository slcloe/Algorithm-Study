/*
 * | 활용 알고리즘 | BFS
 *
 * | 활용 자료구조 | Queue, Node, Set
 *
 * | 접근 방법 |
 *  1. 기존 배열, 정렬 배열을 문자열로 만든다
 *  2. 기존 배열과 depth를 Node에 담아 Queue의 초기값으로 넣는다
 *  3. 방문 여부는 문자열 Set으로 체크한다
 *  4. 정렬 배열의 문자열과 같으면 depth를 출력하고 중단, 다르다면 뒤집은 문자열을 Queue에 넣고 BFS를 수행한다
 *  5. 마지막까지 정렬 문자열로 만들 수 없다면 -1을 출력한다
 */

package a2408.study.week25;

import java.io.*;
import java.util.*;

public class bj_g4_1327_소트_게임 {
    static class Node{
        String str;
        int depth;

        public Node(String str, int depth){
            this.str = str;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] arr_origin = br.readLine().replace(" ", "").toCharArray();
        char[] arr_ordered = Arrays.copyOf(arr_origin, N);
        Arrays.sort(arr_ordered);
        String str_origin = new String(arr_origin);
        String str_ordered = new String(arr_ordered);

        ArrayDeque<Node> queue = new ArrayDeque<>();
        HashSet<String> set = new HashSet<>();
        queue.offerLast(new Node(str_origin, 0));
        set.add(str_origin);

        while(!queue.isEmpty()){
            Node now = queue.pollFirst();
            String str = now.str;
            int depth = now.depth;

            if(str_ordered.equals(str)){
                System.out.println(depth);
                return;
            }

            for(int i=0; i<=N-K; i++){
                String nStr = reverseStr(str, i, i+K, N);
                if(set.contains(nStr)){ continue; }
                set.add(nStr);
                queue.offerLast(new Node(nStr, depth + 1));
            }
        }
        System.out.println(-1);
    }

    static String reverseStr(String str, int from, int to, int N){
        StringBuilder result = new StringBuilder();
        for(int i=0; i<from; i++){
            result.append(str.charAt(i));
        }
        for(int i=to-1; i>=from; i--){
            result.append(str.charAt(i));
        }
        for(int i=to; i<N; i++){
            result.append(str.charAt(i));
        }
        return result.toString();
    }
}
