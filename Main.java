import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        List<Integer> arr = new ArrayList<Integer>();
        List<Integer> commonEven = new ArrayList<>();
        List<Integer> commonOdd = new ArrayList<>();

        for (int i = 1; i <= 10000; i++) {
            arr.add(i);
        }

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 4; i++) {
            int startNum = i * 2500;
            int finalNum = (i + 1) * 2500;
            List<Integer> sub = arr.subList(startNum, finalNum);

            Thread t = new Thread(new ParityWorker(sub, commonEven, commonOdd));
            threads.add(t);
            t.start();

        }
        for (Thread t : threads) {
            t.join();

        }

        System.out.println(commonEven.size());
        System.out.println(commonOdd.size());

        Collections.sort(commonEven);
        Collections.sort(commonOdd);

        try (PrintWriter writer = new PrintWriter("output.txt", StandardCharsets.UTF_8)) {
            for (int i = 0; i < commonEven.size(); i++) {
                
                writer.println("Sıra: " + (i + 1) + " | Çift: " + commonEven.get(i) + " | Tek: " + commonOdd.get(i));
            }
            System.out.println("Dosya oluşturuldu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
