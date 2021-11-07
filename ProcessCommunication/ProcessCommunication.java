package ProcessCommunication;

import Utilities.Array;

public class ProcessCommunication
{
    /**
     *  Esta classe é responsável por manipular as Threads. No momento,
     *  isso inclui executar e esperar para morrer todas elas, além de
     *  trabalhar em cima do vetor estático.
     */
    private Thread[] processes;
    private final int maxProcesses;
    private int processAmount = 0;

    protected ProcessCommunication(int maxProcesses)
    {
        processes = new Thread[maxProcesses];
        this.maxProcesses = maxProcesses;
    }

    public void runAllThreadsProcesses()
    {
        for (Thread process : processes) {
            process.start();
        }
    }

    public void joinAllThreadProcesses()
    {
        for (Thread process : processes) {
            try { process.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public boolean insertProcess(Thread process, int index)
    {
        if (index >= processes.length) return false;
        if (processes[index] == null) processAmount++;
        processes[index] = process;
        return true;
    }

    public void showAllProcesses()
    {
        Array.printInSequence(processes);
    }

    public int getProcessAmount() {
        return processAmount;
    }

    public int getMaxProcesses() {
        return maxProcesses;
    }

    public Thread[] getProcesses() {
        return processes;
    }

    protected void removeAllThreads()
    {
        processes = new Thread[maxProcesses];
    }
}
