const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

const [N, M] = input[0].split(' ').map(Number)

let grid = input.slice(1, N + 1).map((e) => e.split(' ').map(Number))
let max = -1;

const shapes = [
  // ---- 
  [[0, 0], [1, 0], [2, 0], [3, 0]],
  [[0, 0], [0, 1], [0, 2], [0, 3]],

  // 네모
  [[0, 0], [0, 1], [1, 1], [1, 0]],

  // L
  [[0, 0], [0, 1], [0, 2], [1, 2]],
  [[0, 0], [1, 0], [2, 0], [0, 1]],
  [[0, 0], [1, 0], [1, 1], [1, 2]],
  [[2, 0], [0, 1], [1, 1], [2, 1]],

  // L 대칭
  [[1, 0], [1, 1], [1, 2], [0, 2]],
  [[0, 0], [0, 1], [1, 1], [2, 1]],
  [[0, 0], [1, 0], [0, 1], [0, 2]],
  [[0, 0], [1, 0], [2, 0], [2, 1]],

  // Z
  [[0, 0], [0, 1], [1, 1], [1, 2]],
  [[1, 0], [2, 0], [0, 1], [1, 1]],

  // Z 대칭
  [[1, 0], [0, 1], [1, 1], [0, 2]],
  [[0, 0], [1, 0], [1, 1], [2, 1]],

  // ㅗ
  [[0, 0], [1, 0], [2, 0], [1, 1]],
  [[1, 0], [0, 1], [1, 1], [1, 2]],
  [[1, 0], [0, 1], [1, 1], [2, 1]],
  [[0, 0], [0, 1], [1, 1], [0, 2]],
]

grid.forEach((row, y) => {
  row.forEach((num, x) => {
    // x, y에서 가능한 모든 경우를 실행 후, 최대 값을 구한다.
    shapes.forEach((shape) => {
      let localSum = 0;

      for (let i = 0; i < 4; i++) {
        const [sx, sy] = shape[i];
        if (isIn(x + sx, y + sy) === false) {
          localSum = -1;
          break;
        }
        localSum += grid[y + sy][x + sx];
      }
      max = Math.max(max, localSum)
    })

  })
})

console.log(max)

function isIn(x, y) {
  return x >= 0 && x < M && y >= 0 && y < N
}