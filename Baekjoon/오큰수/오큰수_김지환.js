const path = require('path');

let input = process.platform === 'linux'
  ? require('fs').readFileSync('/dev/stdin').toString().trim().split('\n')
  : require('fs').readFileSync(path.join(__dirname, 'test.txt')).toString().trim().split('\n');

const [N] = input[0].split(' ').map(Number);
const numbers = input[1].split(' ').map(Number);
const stack = []
const result = Array(N).fill(-1);

for (let i = 0; i < N; i++) {
  while (stack.length > 0 && numbers[stack[stack.length - 1]] < numbers[i]) {
    result[stack.pop()] = numbers[i];
  }
  stack.push(i);
}

console.log(result.join(' '))