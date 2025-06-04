package threadassignment.oddeven;

public class Eventhread extends Thread{

    private final int max;
    private final Object lock;


    public   Eventhread(Object lock,int max) {
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
                while(Oddthread.no<=max&&Oddthread.no%2==1)
                {
                   try {
                       lock.wait();//telling that is not my turn
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                }
                if(Oddthread.no>max)
                {
                    lock.notifyAll();
                    break;

                }

                System.out.println("even thread"+Oddthread.no);
                Oddthread.no++;
                lock.notifyAll();//telling odd thread thatt is this here
            }


        }
    }

}
