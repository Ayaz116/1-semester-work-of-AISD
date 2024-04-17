import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final int INF = 99999;

    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++) {
            String fileName = "input_" + i + ".txt";
            solve(fileName);
        }
    }

    private static void solve(String fileName) {
        try {
            File file = new File("Files", fileName);
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }
            scanner.close();

            long startTime = System.nanoTime();
            int[][] dist = floydWarshall(graph, n);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            System.out.println("Файл: " + fileName);
            System.out.println("Решение:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] == INF)
                        System.out.print("INF ");
                    else
                        System.out.print(dist[i][j] + "   ");
                }
                System.out.println();
            }
            System.out.println("Время: " + duration + " миллисекунд");
            System.out.println("Количество итераций: " + (n * n * n));
            System.out.println("---------------------------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int[][] floydWarshall(int[][] graph, int n) {
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dist[i][j] = graph[i][j];

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }
}

