/*
 * | 활용 자료구조 | Node Array
 *
 * | 접근 방법 |
 *  1. 정점의 진입 간선, 진출 간선 수를 저장하는 Vertex 클래스를 만든다
 *  2. edges 결과에 따라 각 정점의 진입 간선, 진출 간선 수를 갱신한다
 *  3. Vertex를 순회하며 아래 기준에 맞춰 answer를 갱신한다
 *     3-1. 진입 0, 진출 2 이상 -> 추가한 정점
 *     3-2. 진입 1 이상, 진출 0 -> 막대 그래프
 *     3-3. 진입 2 이상, 진출 2 이상 -> 8자 그래프
 *  4. 추가한 정점의 진출 간선에서 3-2와 3-3의 합을 빼면 도넛 그래프의 개수다
 */

package a2406.study.week16;

public class pr_3_258711_도넛과_막대_그래프 {
    static class Vertex{
        int out;
        int in;

        public Vertex(){
            this.out = 0;
            this.in = 0;
        }
    }

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int N = 0;

        for(int[] edge: edges){
            N = Math.max(N, Math.max(edge[0], edge[1]));
        }

        Vertex[] graph = new Vertex[N+1];
        for(int n=1; n<=N; n++){
            graph[n] = new Vertex();
        }

        for(int[] edge: edges){
            graph[edge[0]].out++;
            graph[edge[1]].in++;
        }

        for(int n=1; n<=N; n++){
            if(answer[0]==0 && graph[n].in==0 && 2<=graph[n].out){
                answer[0] = n;
            }
            else if(1<=graph[n].in && graph[n].out==0){
                answer[2]++;
            }
            else if(2<=graph[n].in && 2<=graph[n].out){
                answer[3]++;
            }
        }
        answer[1] = graph[answer[0]].out - (answer[2] + answer[3]);

        return answer;
    }
}
