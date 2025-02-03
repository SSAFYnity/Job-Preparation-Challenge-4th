import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int targetChannel = Integer.parseInt(br.readLine());
        int brokenButtonCnt = Integer.parseInt(br.readLine());
        boolean[] brokenButtons = new boolean[10];
        if (brokenButtonCnt != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int index = 0; index < brokenButtonCnt; index++) {
                brokenButtons[Integer.parseInt(st.nextToken())] = true;
            }
        }
        RemoteController remoteController = new RemoteController(targetChannel, brokenButtons);
        remoteController.execute();
        System.out.println(remoteController.pushedCnt);
    }
}
class RemoteController {
    int pushedCnt;
    int targetChannel;
    boolean[] brokenButtons;

    public RemoteController(int targetChannel, boolean[] brokenButtons) {
        this.targetChannel = targetChannel;
        this.brokenButtons = brokenButtons;
        pushedCnt = Math.abs(targetChannel - 100); // 기본 pushedCnt
    }
    public void execute() {
        for (int i = 0; i <= 1000000; i++) {
            String channel = String.valueOf(i);
            if (isValid(channel)) {
                int count = channel.length() + Math.abs(i - targetChannel);
                pushedCnt = Math.min(pushedCnt, count);
            }
        }
    }

    private boolean isValid(String channel) {
        for (char c : channel.toCharArray()) {
            // 고장난 버튼이 포함됐을 경우 false 반환
            if (brokenButtons[c - '0']) {
                return false;
            }
        }
        return true;
    }
}