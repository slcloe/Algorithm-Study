import java.util.*;
import java.io.*;

class Solution {
    
    ArrayList<int[]>[] edges;
    int maxSummit = 0,  minIntensity = Integer.MAX_VALUE;
    boolean isGate[];
    boolean isSummit[];
    boolean[] v ;
    
    int[] searchPath(int start, int n){
        int max = Integer.MIN_VALUE;

        boolean[] v = new boolean[n + 1];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->
                                                      {
                                                          if (o1[1]==o2[1]) return Integer.compare(o1[0], o2[0]);
                                                          return Integer.compare(o1[1], o2[1]);
                                                       });

        pq.offer(new int[]{start, 0}); // 정점, 가중치
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int min = cur[1];
            
            if (min > minIntensity) continue;
            if (isSummit[minVertex]){
                max = Math.max(min, max);
                return new int[]{minVertex, max};
            }
            
            if (v[minVertex]) continue;
            if (minVertex != start && isGate[minVertex]) continue;
            
            v[minVertex] = true;
            max = Math.max(min, max);
            for(int[] edge : edges[minVertex]){
                if (!v[edge[0]])
                    pq.offer(new int[]{edge[0], edge[1]});
            }
        }       
        return new int[]{-1, -1};
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        edges = new ArrayList[n + 1];
        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];
        v = new boolean[n + 1];
        for (int i = 0;i < n + 1;i++){
            edges[i] = new ArrayList<int[]>();
        }
        for(int[] path : paths){
            edges[path[0]].add(new int[] {path[1], path[2]});
            edges[path[1]].add(new int[] {path[0], path[2]});
        }
        // gate -> summits (최소 weight 를 가지는 경로를 구하기)
        // 한 경로 중 MAX weight 를 찾아냄.
        // 경로당 weight 중 min 값과 summits 를 찾아야함.
        for(int gate: gates)
            isGate[gate] = true;
        for(int summit: summits)
            isSummit[summit] = true;
        
        for(int gate: gates){
            v = new boolean[n + 1];
            int[] tmp = searchPath(gate, n);
            if (tmp[1] == -1) continue;
            if (minIntensity > tmp[1]){
                minIntensity = tmp[1];
                maxSummit = tmp[0];
            }
            else if (minIntensity == tmp[1])
                maxSummit = Math.min(maxSummit, tmp[0]);
            
        }
        
        int[] answer = {maxSummit, minIntensity};
        return answer;
    }
}

