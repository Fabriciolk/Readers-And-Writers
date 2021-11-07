package ReadersAndWriters.Entity;

import ProcessCommunication.ProcessSimulator;
import ProcessCommunication.Semaphore;

public abstract class ExclusiveEntity extends Entity implements ProcessSimulator
{
    public ExclusiveEntity(Semaphore semaphore, int accessAmount)
    {
        super(semaphore, accessAmount);
    }

    @Override
    public void run() {
        for (int i = 0; i < accessAmount; i++)
        {
            semaphore.down(1);
            accessBase();
            if (i == accessAmount - 1) try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            semaphore.up(1);
        }
    }

    @Override
    public abstract void accessBase();
}
