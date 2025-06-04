package threadassignment.oddeven;

public class EvenOdd {
    public static void main(String[] args)
    {
        int max=10;

        Object lock =new Object();//shared object to communicate

        Thread odd =new Oddthread(lock,max);
        Thread even=new Eventhread(lock,max);

        odd.start();
        even.start();
    }
}
