package ReadersAndWriters;

import ProcessCommunication.ProcessSimulator;
import ProcessCommunication.Semaphore;
import ReadersAndWriters.Entity.Reader;
import ReadersAndWriters.Entity.Writer;
import ReadersAndWriters.Entity.ExclusiveReader;

public class FactoryEntities
{
    /**
     * Este método retorna uma instância da classe passada como parâmetro em 'className'.
     * @param className : Nome da classe a partir da qual será retornada uma instância.
     * @param accessAmount : Quantidade de acessos à base que a entidade irá fazer.
     * @param semaphore : Instância do semáforo (importante ser a mesma para todos os processos para acessem a mesma base)
     * @return : Instância da classe 'className'
     */
    protected ProcessSimulator getInstanceOf(String className, int accessAmount, Semaphore semaphore)
    {
        switch (className)
        {
            case "Reader":
                new Reader(semaphore, accessAmount);
                break;
            case "Writer":
                new Writer(semaphore, accessAmount);
                break;
            case "ExclusiveReader":
                new ExclusiveReader(semaphore, accessAmount);
                break;
            default:
                System.out.println("Not found '" + className + "' class name");
                return null;
        }

        return null;
    }
}
