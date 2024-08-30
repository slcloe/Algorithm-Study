import java.util.*;
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;
        
        ArrayDeque<int[]> q=new ArrayDeque<>();
        int[] dx={-1,0,1,0};
        int[] dy={0,-1,0,1};
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(picture[i][j]==0) continue;
                answer[0]++;
                q.offer(new int[]{i,j});
                int color=picture[i][j];
                int cnt=1;
                picture[i][j]=0;
                while (!q.isEmpty()){
                    int[] xy=q.poll();
                    for(int d=0;d<4;d++){
                        int nx=xy[0]+dx[d];
                        int ny=xy[1]+dy[d];
                        if(-1<nx&&nx<m&&-1<ny&&ny<n){
                            if(picture[nx][ny]==color){
                                cnt++;
                                q.offer(new int[]{nx,ny});
                                picture[nx][ny]=0;
                            }
                        }
                    }
                }
                if(cnt>answer[1]) answer[1]=cnt;
            }
        }
        return answer;
    }
}
