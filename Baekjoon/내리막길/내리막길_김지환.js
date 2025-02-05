const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

const [N, M] = input[0].split(' ').map(Number)

const map = input.slice(1).map((e) => e.split(' ').map(Number))
let dp = Array(N).fill().map((e) => Array(M).fill(-1))

const dx = [-1, 1, 0, 0]
const dy = [0, 0, -1, 1]

console.log(dfs(0, 0))

function dfs(x, y) {
  if (x === N - 1 && y === M - 1) {
    return 1;
  }

  if (dp[x][y] !== -1) return dp[x][y];

  dp[x][y] = 0;

  for (let d = 0; d < 4; d++) {
    const nx = x + dx[d];
    const ny = y + dy[d];
    if (isIn(nx, ny) && map[nx][ny] < map[x][y]) {
      dp[x][y] += dfs(nx, ny)
    }
  }

  return dp[x][y];
}

function isIn(x, y) {
  return 0 <= x && x < N && 0 <= y && y < M;
}