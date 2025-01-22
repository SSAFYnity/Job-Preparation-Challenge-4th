from collections import deque

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]


def bfs(board, d):
    n = len(board)
    visited = [[1e9] * n for _ in range(n)]
    visited[0][0] = 0

    q = deque()
    q.append((0, 0, 0, d))  # r, c, cost, 방향

    while q:
        r, c, cost, direction = q.popleft()

        if r == n - 1 and c == n - 1:
            continue

        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]
            if 0 <= nr < n and 0 <= nc < n:
                if board[nr][nc] == 0:
                    if k == direction:
                        next_cost = cost + 100
                    else:
                        next_cost = cost + 600

                    if next_cost <= visited[nr][nc]:
                        visited[nr][nc] = next_cost
                        q.append((nr, nc, next_cost, k))
    return visited[n - 1][n - 1]


def solution(board):
    answer = min(bfs(board, 1), bfs(board, 3))

    return answer