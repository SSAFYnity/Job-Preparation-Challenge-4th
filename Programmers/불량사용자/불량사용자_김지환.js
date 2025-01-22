function solution(user_id, banned_id) {
  let set = new Set();
  const length = banned_id.length;
  const userLength = user_id.length;
  let selected = Array(userLength).fill().map(() => false);

  function dfs(index, arr) {
    if (index === length) {
      set.add(arr.sort().join(','))
      return;
    }

    for (let j = 0; j < userLength; j++) {
      if (selected[j]) continue;

      if (user_id[j].length == banned_id[index].length && check(user_id[j], banned_id[index])) {
        selected[j] = true;
        dfs(index + 1, [...arr, user_id[j]])
        selected[j] = false;
      }
    }

  }

  dfs(0, []);

  return set.size
}

function check(user, banned) {
  let count = [...user].filter((char, index) => char === banned[index]).length
  let star = [...banned].filter(char => char === '*').length;
  return count + star === banned.length;
}