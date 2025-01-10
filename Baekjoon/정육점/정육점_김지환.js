const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

const [N, M] = input[0].split(' ').map(Number);
const meats = input.slice(1).map((line) => line.split(' ').map(Number))

let total = 0;
meats.forEach(e => total += e[0])

if (total < M) {
  console.log(-1)
  return;
}

meats.sort((a, b) => {
  if (a[1] === b[1]) return b[0] - a[0];
  return a[1] - b[1];
});

let min = Number.MAX_SAFE_INTEGER;

let totalWeight = 0;
let priceSum = 0;
let bfPrice = 0;

for (let i = 0; i < N; i++) {
  totalWeight += meats[i][0];

  if (bfPrice != meats[i][1]) {
    priceSum = bfPrice = meats[i][1];
  } else {
    priceSum += meats[i][1];
  }

  if (totalWeight >= M) {
    min = Math.min(min, priceSum);
  }
}

console.log(min === Number.MAX_SAFE_INTEGER ? -1 : min)