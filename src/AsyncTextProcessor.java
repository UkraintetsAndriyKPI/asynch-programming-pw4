import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class AsyncTextProcessor  {
    public static void main(String[] args) {
        String folderPath = "texts/";


        CompletableFuture.supplyAsync(() -> {
            long startTime = System.nanoTime();
            List<String> filePaths = new ArrayList<>();
            File folder = new File(folderPath);
            if (folder.isDirectory()) {
                for (File file :
                        Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".txt")))) {
                    filePaths.add(file.getAbsolutePath());
                }
            }
            System.out.println(filePaths);


            System.out.printf(
                    "Час котрий був затрачений для отримання усіх текстових файлів в папці %.2f мс\n",
                    (System.nanoTime() - startTime) / 1_000_000.0);
            return filePaths;

        }).thenApplyAsync(filePaths -> {
            long startTime = System.nanoTime();
            List<String> texts = new ArrayList<>();
            for (String filePath : filePaths) {
                try {
                    texts.add(new String(Files.readAllBytes(new File(filePath).toPath())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.print("\nТекст з файлів до маніпуляцій: "+ texts);

            for (int i = 0; i < texts.size(); i++) {
                texts.set(i, texts.get(i).replaceAll("[a-zA-Z ]", ""));
            }
            System.out.println("\nТекст з файлів після маніпуляцій: " + texts);
            System.out.printf(
                    "Час котрий був затрачений для зміни прочитаних речень %.2f мс\n",
                    (System.nanoTime() - startTime) / 1_000_000.0);

            return texts;
        }).thenRunAsync(()-> System.out.println("Кінець усіх обчислень"));


    }
}



