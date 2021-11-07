import Exceptions.EntitiesNumberException;
import Exceptions.InvalidEntityParameterException;
import File.DatasetReader;
import ProcessCommunication.ProcessCommunication;
import ReadersAndWriters.ReadersAndWriters;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SO_EP_II
{
    /**
     * Esta classe é responsável por gerar os resultados do EP II.
     * Isso inclui definir quais são as entidades, quantos acessos
     * à base elas poderão fazer, qual a proporção entre elas, etc,
     * e gerar os resultados dos tempos de execução das Threads.
     * Além disso, a classe também escreve os resultados em um arquivo
     * csv para ser trabalhado por um script R, gerando os gráficos e
     * figuras necessárias.
     */
    public static void main(String[] args)
    {
        String fileDir = System.getProperty("user.dir") + "\\File\\bd.txt";
        Map<String, Map<Integer, Integer>> entitiesAmount;
        Map<String, Map<Integer, Integer>> exclusiveEntitiesAmount;
        int numberLines = 100;
        int proportionTimes = 50;
        int proportion = 100;

        try {
            FileWriter differenceExecutionCSVFile = new FileWriter("differenceExecution.csv");
            FileWriter avrgExecutionReadersWriters = new FileWriter("avrgExecReadersWriters.csv");
            FileWriter avrgExecutionExclusive = new FileWriter("avrgExecutionExclusive.csv");

            // Escreve o nome das colunas do dataset

            differenceExecutionCSVFile.append("0x" + proportion);
            avrgExecutionReadersWriters.append("0x" + proportion);
            avrgExecutionExclusive.append("0x" + proportion);
            for (int i = 1; i <= proportion; i++) {
                differenceExecutionCSVFile.append("," + i + "x" + (proportion - i));
                avrgExecutionReadersWriters.append("," + i + "x" + (proportion - i));
                avrgExecutionExclusive.append("," + i + "x" + (proportion - i));
            }
            differenceExecutionCSVFile.append("\n");
            avrgExecutionReadersWriters.append("\n");
            avrgExecutionExclusive.append("\n");

            // Quantidade de vezes que o programa vai rodar.

            for (int line = 0; line < numberLines; line++)
            {
                StringBuilder csvLineDiffExec = new StringBuilder();
                StringBuilder csvLineAvrExecRW = new StringBuilder();
                StringBuilder csvLineAvrExecExclusive = new StringBuilder();

                // Linha de controle para saber quantas linhas foi escrita no dataset.

                System.out.println("Writing line " + (line + 1) + " ...");

                // Proporção de leitores e escritores: 0x100, 1x99, 2x98, ..., 99x1, 100x0

                for (int i = 0; i <= proportion; i++)
                {
                    // HashMap para tipos de entidades e seus parâmetros e
                    // variáveis para controle dos tempos de execução.

                    entitiesAmount = new HashMap<>();
                    exclusiveEntitiesAmount = new HashMap<>();
                    double totalTimeRW = 0;
                    double totalTimeExclusive = 0;
                    double start = 0;

                    // Define quais classes vão ser e seus parâmetros
                    // (quantidade de classes e de acessos na base)

                    entitiesAmount.put("Reader", Map.of(i, proportionTimes));
                    entitiesAmount.put("Writer", Map.of(proportion - i, proportionTimes));
                    exclusiveEntitiesAmount.put("ExclusiveReader", Map.of(i, proportionTimes));
                    exclusiveEntitiesAmount.put("Writer", Map.of(proportion - i, proportionTimes));

                    // Quantidade de vezes que é executado cada proporção de leitores e escritores.

                    for (int j = 0; j < proportionTimes; j++)
                    {
                        // Lê o arquivo txt em duas instâncias diferentes para as duas abordagens de execução:
                        // com e sem Leitores e Escritores.

                        DatasetReader dbReader = new DatasetReader(fileDir);
                        DatasetReader dbReader2 = new DatasetReader(fileDir);

                        ProcessCommunication readerWriter = new ReadersAndWriters(entitiesAmount, dbReader.getAllLines(), proportion);
                        ProcessCommunication exclusiveAcess = new ReadersAndWriters(exclusiveEntitiesAmount, dbReader2.getAllLines(), proportion);

                        // Executa todas as Threads utilizando Leitores e Escritores (Leitor com prioridade)
                        // e calcula o tempo decorrido.

                        start = System.currentTimeMillis();
                        readerWriter.runAllThreadsProcesses();
                        readerWriter.joinAllThreadProcesses();
                        totalTimeRW += System.currentTimeMillis() - start;

                        // Executa todas as Threads sem utilizar Leitores e Escritores
                        // e calcula o tempo decorrido.

                        start = System.currentTimeMillis();
                        exclusiveAcess.runAllThreadsProcesses();
                        exclusiveAcess.joinAllThreadProcesses();
                        totalTimeExclusive += System.currentTimeMillis() - start;
                    }

                    // Escreve no dataset uma linha com todos os resultados
                    // das proporções de leitores e escritores

                    csvLineDiffExec.append((int)((totalTimeRW / proportionTimes - totalTimeExclusive / proportionTimes) * Math.pow(10, 3))/Math.pow(10, 3));
                    csvLineAvrExecRW.append((int)((totalTimeRW / proportionTimes) * Math.pow(10, 3))/Math.pow(10, 3));
                    csvLineAvrExecExclusive.append((int)((totalTimeExclusive / proportionTimes) * Math.pow(10, 3))/Math.pow(10, 3));

                    if (i != proportion)
                    {
                        csvLineDiffExec.append(",");
                        csvLineAvrExecRW.append(",");
                        csvLineAvrExecExclusive.append(",");
                    }

                    //System.out.println("Difference averague time to execute: " + (totalTimeRW/proportionTimes - totalTimeExclusive/proportionTimes));
                    //System.out.println(" (ReaderWriters) Average time to run " + proportionTimes + " times the proportion " + i + " Readers and " + (proportion - i) + " Writers: " + totalTimeRW/proportionTimes + " milliseconds.");
                    //System.out.println("(ExclusiveAccess) Average time to run " + proportionTimes + " times the proportion " + i + " Readers and " + (proportion - i) + " Writers: " + totalTimeExclusive/proportionTimes + " milliseconds.");
                }

                differenceExecutionCSVFile.append(csvLineDiffExec + "\n");
                avrgExecutionReadersWriters.append(csvLineAvrExecRW + "\n");
                avrgExecutionExclusive.append(csvLineAvrExecExclusive + "\n");
            }

            differenceExecutionCSVFile.flush();
            differenceExecutionCSVFile.close();
            avrgExecutionReadersWriters.flush();
            avrgExecutionReadersWriters.close();
            avrgExecutionExclusive.flush();
            avrgExecutionExclusive.close();
        }
        catch (EntitiesNumberException | IOException | InvalidEntityParameterException e) {
            e.printStackTrace();
        }
    }
}
