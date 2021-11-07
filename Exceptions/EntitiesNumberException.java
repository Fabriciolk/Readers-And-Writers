package Exceptions;

public class EntitiesNumberException extends Exception
{
    /**
     *  Esta classe é uma Exception, usada quando a quantidade
     *  máxima de entidades definida de antemão não é respeitada.
     */
    public EntitiesNumberException(int triedTotalEntities, int rightTotalEntities)
    {
        super(triedTotalEntities + " is not a valid number for total of entities. It should be " + rightTotalEntities);
    }
}
