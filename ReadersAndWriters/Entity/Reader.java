package ReadersAndWriters.Entity;

import ProcessCommunication.ProcessSimulator;
import ProcessCommunication.Semaphore;
import Utilities.Array;

public class Reader extends Entity implements ProcessSimulator
{
    private String accessedData;

    public Reader(Semaphore semaphore, int accessAmount)
    {
        super(semaphore, accessAmount);
    }

    @Override
    public void run()
    {
        for (int i = 0; i < accessAmount; i++)
        {
            semaphore.down(0);
            semaphore.accessControl[2]++;
            if (semaphore.accessControl[2] == 1) semaphore.down(1);
            semaphore.up(0);
            accessBase();
            if (i == accessAmount - 1) try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            semaphore.down(0);
            semaphore.accessControl[2]--;
            if (semaphore.accessControl[2] == 0) semaphore.up(1);
            semaphore.up(0);
        }
    }

    @Override
    public void accessBase() {
        accessedData = readBaseRandomly();
    }

    private String readBaseRandomly()
    {
        return Array.randomElement(semaphore.getBase());
    }


}
