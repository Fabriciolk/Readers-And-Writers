package ReadersAndWriters.Entity;

import ProcessCommunication.Semaphore;
import Utilities.Array;

public class ExclusiveReader extends ExclusiveEntity
{
    /**
     *  No problema de Leitores e Escritores, podem existir vários leitores
     *  na base e somente um escritor. Essa classe é utilizada para leitores
     *  exclusivos, ou seja, se um leitor estiver acessando a base, ninguém
     *  mais a utiliza.
     */
    String accessedData;

    public ExclusiveReader(Semaphore semaphore, int accessAmount)
    {
        super(semaphore, accessAmount);
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
