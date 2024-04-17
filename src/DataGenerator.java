import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {
    private static final Random random = new Random();

    public static void main(String[] args) {
        generateInputData(50, 100, 10000);
    }

    private static void generateInputData(int numSets, int minSize, int maxSize) {
        for (int i = 1; i <= numSets; i++) {
            int size = random.nextInt(maxSize - minSize + 1) + minSize;
            int[][] graph = generateRandomGraph(size);
            writeToFile("input_" + (i+50) + ".txt", size, graph);
        }
    }

    private static int[][] generateRandomGraph(int n) {
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = random.nextInt(100);
                }
            }
        }
        return graph;
    }

    private static void writeToFile(String fileName, int n, int[][] graph) {
        try {
            File file = new File("Files", fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(n + "\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(graph[i][j] + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
