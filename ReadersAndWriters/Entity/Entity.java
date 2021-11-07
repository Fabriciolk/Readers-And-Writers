package ReadersAndWriters.Entity;

import ProcessCommunication.Semaphore;

public abstract class Entity
{
    public Semaphore semaphore;
    public int accessAmount;

    Entity(Semaphore semaphore, int accessAmount)
    {
        this.semaphore = semaphore;
        this.accessAmount = accessAmount;
    }
}
