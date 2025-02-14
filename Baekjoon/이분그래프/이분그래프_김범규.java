package Baekjoon.이분그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class 이분그래프_김범규 {
    static int K, V, E;
    static ArrayList<ArrayList<Integer>> graph;
    //색 저장하는 용도
    static int[] colors;
    static boolean checkBipartite;
    public static void main(String[] args) throws IOException{
        //이분 그래프의 조건, BFS를 수행해서 같은레벨의 정점이 다른 색인경우 false
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            //정점과 간선 저장
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            colors = new int[V + 1];
            checkBipartite = true;

            //값 초기화
            for(int i = 0; i < V + 1; i++){
                graph.add(new ArrayList<>());
                colors[i] = 0;
            }

            //무방향이므로 추가...
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }

            for(int i = 0; i < V + 1; i++){

                if(!checkBipartite){
                    break;
                }

                //방문하지 않은(색이 안입혀진 정점)곳 탐방
                if(colors[i] == 0){
                    bfs(i, 1);
                }
            }

            System.out.println(checkBipartite ? "YES" : "NO");
        }
    }


    public static void bfs(int checkV, int color){
        Deque<Integer> dq = new ArrayDeque<>();

        dq.offer(checkV);
        colors[checkV] = color;

        //큐가 안비어있을 때 까지...
        while (!dq.isEmpty()) {
            int v = dq.poll();

            for(int adjV : graph.get(v)){
                if(colors[adjV] == 0){
                    dq.offer(adjV);
                    colors[adjV] = colors[v] * -1;
                }
                //같은 레벨 내에서 색이 같은 경우, 이는 이분그래프가 아님
                else if(colors[adjV] + colors[v] != 0){
                    checkBipartite = false;
                    return;
                }
            }
        }
    }
}
