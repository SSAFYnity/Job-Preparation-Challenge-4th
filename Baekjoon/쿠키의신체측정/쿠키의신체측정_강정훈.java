import java.io.*;

class BodyShapeInspector {
    private int size;
    private String[] grid;

    public BodyShapeInspector(int size, String[] grid) {
        this.size = size;
        this.grid = grid;
    }

    public String inspectShape() {
        StringBuilder result = new StringBuilder();
        int[] heartPosition = locateHeart(result);
        calculateArmLengths(result, heartPosition);
        calculateBodyAndLegLengths(result, heartPosition);
        return result.toString();
    }

    private int[] locateHeart(StringBuilder result) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row].charAt(col) == '*') {
                    result.append(row + 2).append(" ").append(col + 1).append("\n");
                    return new int[]{row + 1, col};
                }
            }
        }
        return new int[2];
    }

    private void calculateArmLengths(StringBuilder result, int[] heartPosition) {
        result.append(getLength(heartPosition[0], heartPosition[1] - 1, 0, -1)).append(" ");
        result.append(getLength(heartPosition[0], heartPosition[1] + 1, 0, 1)).append(" ");
    }

    private void calculateBodyAndLegLengths(StringBuilder result, int[] heartPosition) {
        int bodyLength = getLength(heartPosition[0] + 1, heartPosition[1], 1, 0);
        result.append(bodyLength).append(" ");

        int legStartRow = heartPosition[0] + 1 + bodyLength;
        result.append(getLength(legStartRow, heartPosition[1] - 1, 1, 0)).append(" ");
        result.append(getLength(legStartRow, heartPosition[1] + 1, 1, 0));
    }

    private int getLength(int row, int col, int rowIncrement, int colIncrement) {
        int length = 0;
        while (isValid(row, col) && grid[row].charAt(col) == '*') {
            row += rowIncrement;
            col += colIncrement;
            length++;
        }
        return length;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String[] grid = new String[size];

        for (int i = 0; i < size; i++) {
            grid[i] = br.readLine();
        }
        BodyShapeInspector inspector = new BodyShapeInspector(size, grid);
        System.out.println(inspector.inspectShape());
    }
}