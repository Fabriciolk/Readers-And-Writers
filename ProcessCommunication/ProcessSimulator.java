package ProcessCommunication;

public interface ProcessSimulator extends Runnable
{
    @Override
    void run();

    void accessBase();
}
