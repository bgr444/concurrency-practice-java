import java.util.List;

public class ParityWorker implements Runnable {
    private List<Integer> sub;
    private List<Integer> evenList;
    private List<Integer> oddList;

    public ParityWorker(List<Integer> sub, List<Integer> evenList, List<Integer> oddList) {
        this.sub = sub;
        this.evenList = evenList;
        this.oddList = oddList;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " is started!");

        for (Integer i : sub) {
            if (i % 2 == 0) {
               synchronized (evenList) {
                    evenList.add(i);
                }
            } else {
                synchronized (oddList) {
                    oddList.add(i);
                }
            }
        }
        System.out.println(threadName + " is finished!");

    }

    public List<Integer> getSub() {
        return sub;
    }

    public List<Integer> getEvenList() {
        return evenList;
    }

    public List<Integer> getOddList() {
        return oddList;
    }

    public void setSub(List<Integer> sub) {
        this.sub = sub;
    }

}
