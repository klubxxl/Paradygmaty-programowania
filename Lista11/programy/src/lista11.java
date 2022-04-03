// DANIEL KONSEWICZ

import java.util.concurrent.Semaphore;

// ZADANIE 1 CZYLI ZŁY PROGRAM
class IntCell {
    private int n = 0;
    public int getN() {return n;}
    public void setN(int n) {this.n = n;}
}

class Count extends Thread {
    private static IntCell n = new IntCell();
    @Override public void run() {
        int temp;
        for (int i = 0; i < 200000; i++) {
            temp = n.getN();
            n.setN(temp + 1);
        }
    }
    public static void main(String[] args) {
        Count p = new Count();
        Count q = new Count();
        p.start();
        q.start();
        try { p.join(); q.join(); }
        catch (InterruptedException e) { }
        System.out.println("1) The value of n is " + n.getN());
    }
}

// ZADANIE 2
// A
class IntCell2 {
    private int n = 0;
    private boolean setCheck = true;

    public synchronized int getN() {
        while (!setCheck)
            waitForNotifying();
        setCheck = false;
        notifyAll();
        return n;
    }

    public synchronized void setN(int n) {
        while(setCheck)
            waitForNotifying();
        this.n = n;
        setCheck = true;
        notifyAll();
    }

    private void waitForNotifying() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class CountTest extends Thread {
    private static IntCell2 n = new IntCell2();
    @Override public void run() {
        int temp;
        for (int i = 0; i < 200000; i++) {
            temp = n.getN();
            n.setN(temp + 1);
        }
    }
    public static void main(String[] args) {
        CountTest p = new CountTest();
        CountTest q = new CountTest();
        p.start();
        q.start();
        try { p.join(); q.join(); }
        catch (InterruptedException e) { }
        System.out.println("2a) The value of n is " + n.getN());
    }
}
// B
class Count2 extends Thread {
    static IntCell n = new IntCell();
    private static Semaphore semaphore = new Semaphore(1);

    @Override
    public void run() {
        int temp;
        for (int i = 0; i < 200000; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp = n.getN();
            n.setN(temp + 1);
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        Count2 p = new Count2();
        Count2 q = new Count2();
        p.start();
        q.start();
        try { p.join(); q.join(); }
        catch (InterruptedException e) { }
        System.out.println("2b) The value of n is " + n.getN());
    }
}
