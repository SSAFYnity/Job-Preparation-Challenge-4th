const core = require('@actions/core');
const github = require('@actions/github');

async function run() {
  try {
    const token = process.env.GH_TOKEN;
    if (!token) {
      throw new Error('GitHub token is not provided');
    }

    const octokit = github.getOctokit(token);
    const context = github.context;
    const { owner, repo } = context.repo;
    const pull_number = context.issue.number;

    const { data: files } = await octokit.rest.pulls.listFiles({
      owner,
      repo,
      pull_number
    });

    // 파일 확장자와 라벨 매핑
    const labelMap = {
      '.java': 'Java',
      '.py': 'Python',
      '.js': 'JavaScript',
      '.cpp': 'C++'
    };

    // 변경된 파일 목록 출력 및 라벨 추가
    const labels = new Set();
    files.forEach(file => {
      // 확장자에 따른 라벨 추가
      Object.keys(labelMap).forEach(ext => {
        if (file.filename.endsWith(ext)) {
          labels.add(labelMap[ext]);
        }
      });
    });

    if (labels.size > 0) {
      await octokit.rest.issues.addLabels({
        owner,
        repo,
        issue_number: pull_number,
        labels: Array.from(labels),
      });
    }
  } catch (error) {
    core.setFailed(error.message);
  }
}

run();