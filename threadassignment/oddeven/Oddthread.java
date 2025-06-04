package threadassignment.oddeven;

public class Oddthread extends Thread {

    private final int max;
    private final Object lock;
    public static int no=1;

    public Oddthread(Object lock,int max) {
        this.max =max;
        this.lock = lock;
    }

    @Override

    public void run()
    {
        while(true)
        {
            synchronized (lock)
            {
                while(no<=max&&no%2==0)
                {
                    try{
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(no>max)
                {
                    lock.notifyAll();
                    break;
                }

                System.out.println("Odd thread "+no);
                no++;
                lock.notifyAll();
            }
        }
    }
}
