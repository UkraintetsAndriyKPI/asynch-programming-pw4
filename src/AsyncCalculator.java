import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncCalculator  {
    static ExecutorService executor = Executors.newFixedThreadPool(4);
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        CompletableFuture<Double> future = CompletableFuture
                .runAsync(()-> System.out.println("Асинхронна задача розпочала роботу."))
                .supplyAsync(()-> {
                    double[] numbers = generateNumbers(20);
                    System.out.print("Початкова послідовність чисел: ");
                    printNumbers(numbers);
                    return numbers;
                }).thenApplyAsync(numbers -> {
                    System.out.println("Вичисляємо результат...");
                    double sum = 0;
                    for (int i = 0; i < numbers.length - 1; i++) {
                        sum += numbers[i] * numbers[i + 1];
                    }
                    return sum;
                }, executor).thenApplyAsync(result -> {
                    System.out.println("Обробка результату...");
                    return result;
                }, executor);


        future.thenAcceptAsync(result -> {
            System.out.println("\nОбчислений результат: " + result);
            System.out.println("Час роботи асинхронних операцій: " + (System.nanoTime() - startTime) / 1_000_000 + " мс");
            executor.shutdown();
        }, executor);
    }


    private static void printNumbers(double[] numbers) {
        System.out.println(Arrays.toString(numbers));
    }

    private static double[] generateNumbers(int count) {
        Random random = new Random();
        double[] numbers = new double[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = (double) (int) (random.nextDouble() * 100) / 100;
        }
        return numbers;
    }

}