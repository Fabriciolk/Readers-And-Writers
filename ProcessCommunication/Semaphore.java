package ProcessCommunication;

public class Semaphore
{
    /**
     *  Esta classe é utilizada para controle do acesso à base, tendo
     *  o controle da sincronização por meio dos métodos synchronized.
     */
    private String[] base;
    public int[] accessControl = {1, 1, 0}; // mutex; baseAccess; readerCounter

    public Semaphore(String[] base)
    {
        this.base = base;
    }

    public String[] getBase() {
        return base;
    }

    public synchronized void down(int index)
    {
        if (accessControl[index] > 0) {
            accessControl[index]--;
        }
        else {
            sleep();
        }
    }

    public synchronized void up(int index)
    {
        accessControl[index]++;
        notify();
    }

    private void sleep()
    {
        try {
            wait();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
