package File;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DatasetReader
{
    /**
     *  Esta classe é usada para ler todas as linhas do arquivo txt
     *  (no caso, cada linha representa uma palavra, algumas vezes
     *  acompanha por um ponto final, vírgula ou algo similar).
     */

    private String nameFile;
    private BufferedReader bufferedReader;
    private String[] allLines;

    public DatasetReader(String nameFile) throws FileNotFoundException
    {
        this.nameFile = nameFile;
        bufferedReader = new BufferedReader(new FileReader(nameFile));
        allLines = bufferedReader.lines().toArray(String[]::new);
    }

    public String getNameFile()
    {
        return nameFile;
    }

    public String[] getAllLines()
    {
        return allLines;
    }
}
