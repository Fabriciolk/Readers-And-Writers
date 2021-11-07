package ReadersAndWriters;

import Exceptions.EntitiesNumberException;
import Exceptions.InvalidEntityParameterException;
import ProcessCommunication.ProcessCommunication;
import ProcessCommunication.ProcessSimulator;
import ProcessCommunication.Semaphore;
import Utilities.Array;
import java.util.Map;

public class ReadersAndWriters extends ProcessCommunication
{
    /**
     *  Esta classe representa o problema de Leitores e Escritores
     *  e tem como função definir quem serão as entidades e tornar
     *  preparada a execução de todas as Threads envolvidas.
     */
    private final FactoryEntities factoryEntities = new FactoryEntities();
    private final Semaphore semaphore;

    public ReadersAndWriters(Map<String, Map<Integer, Integer>> entities, String[] base, int maxEntities) throws EntitiesNumberException, InvalidEntityParameterException
    {
        super(maxEntities);
        this.semaphore = new Semaphore(base);
        addAllProcessesRandomly(entities);
    }

    private void addAllProcessesRandomly(Map<String, Map<Integer, Integer>> entitiesAmount) throws EntitiesNumberException, InvalidEntityParameterException
    {
        int maxEntitiesGiven = 0;

        for (var entity : entitiesAmount.entrySet())
        {
            Map<Integer, Integer> entityArguments = entity.getValue();
            if (entityArguments.size() != 1) throw new InvalidEntityParameterException(entityArguments.size());

            for (var argumentsMap : entityArguments.entrySet())
            {
                maxEntitiesGiven += argumentsMap.getValue();
                if (maxEntitiesGiven > super.getMaxProcesses()) throw new EntitiesNumberException(maxEntitiesGiven, super.getMaxProcesses());

                for (int i = 0; i < argumentsMap.getKey(); i++)
                {
                    ProcessSimulator process = factoryEntities.getInstanceOf(entity.getKey(), i, semaphore);
                    Thread thread = new Thread(process);
                    super.insertProcess(thread, Array.nullRandomIndex(super.getProcesses()));
                }
            }
        }
    }
}
