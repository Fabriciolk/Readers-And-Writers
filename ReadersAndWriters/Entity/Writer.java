package ReadersAndWriters.Entity;

import ProcessCommunication.Semaphore;
import Utilities.Array;

public class Writer extends ExclusiveEntity
{
    public Writer(Semaphore semaphore, int accessAmount)
    {
        super(semaphore, accessAmount);
    }

    private void writeBaseRandomly(String input)
    {
        Array.setRandomIndex(semaphore.getBase(), input);
    }

    @Override
    public void accessBase() {
        writeBaseRandomly("MODIFICIADO.");
    }
}
