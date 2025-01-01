const core = require('@actions/core');
const github = require('@actions/github');

// 특정 이름이 주어졌을 때, 해당 이름이 속한 팀의 다른 인원을 반환하는 함수입니다.
function findTeamMembers(teams, name) {
    for (let team of teams) {
        if (team.includes(name)) {
            return team.filter(member => member !== name);
        }
    }
    return []; // 이름이 팀에 속하지 않을 경우 빈 배열 반환
}

async function run() {
  try {
    const token = process.env.GH_TOKEN;
    const reviwerTeam = process.env.REVIWER_TEAM;
    const author = process.env.ACTOR;

    console.log(author);
    
    if (!token) {
      throw new Error('GitHub token is not provided');
    }

    const octokit = github.getOctokit(token);
    const context = github.context;
    const { owner, repo } = context.repo;
    const pull_number = context.issue.number;

    reviewers = findTeamMembers(reviwerTeam.split('|').map(team => team.split(',')), author);
    console.log(reviewers);

    if (reviewers.length > 0) {
      await octokit.rest.pulls.requestReviewers({
        owner,
        repo,
        pull_number,
        reviewers
      });
    }
    
  } catch (error) {
    core.setFailed(error.message);
  }
}

run();