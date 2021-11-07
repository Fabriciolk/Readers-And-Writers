package Exceptions;

public class InvalidEntityParameterException extends Exception
{
    /**
     *  Esta classe é uma Exception, usada quando o Map<> que representa
     *  as classes a serem utilizadas para acesso à base conter um valor inaadequado.
     */
    public InvalidEntityParameterException (int keyValueAmount)
    {
        super("Invalid entity parameter. " + keyValueAmount + " number of key-value is not valid.");
    }
}
