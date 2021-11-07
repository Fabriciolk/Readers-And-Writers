package Utilities;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Array
{
    /**
     *  Esta classe é uma auxiliar que contém algumas
     *  operações genéricas de vetores e Listas, utilizadas
     *  por outras quando necessário.
     */

    public static <T> T randomElement(T[] array)
    {
        Random randomGen = new Random();
        return array[randomGen.nextInt(array.length)];
    }

    public static <T> void setRandomIndex(T[] array, T newValue)
    {
        Random randomGen = new Random();
        array[randomGen.nextInt(array.length)] = newValue;
    }

    public static <T> void setNullUntil(int index, List<T> list)
    {
        for (int i = 0; i < index; i++)
        {
            list.add(null);
        }
    }

    public static <T> int nullRandomIndex(List<T> list)
    {
        Random randomGen = new Random();
        int index = randomGen.nextInt(list.size());
        while (list.get(index) != null) index = randomGen.nextInt(list.size());
        return index;
    }

    public static int nullRandomIndex(Object[] array)
    {
        Random randomGen = new Random();
        int index = randomGen.nextInt(array.length);
        while (array[index] != null) index = randomGen.nextInt(array.length);
        return index;
    }

    public static <T> void printInSequence(List<T> list)
    {
        Iterator<T> iterator = list.iterator();
        System.out.print("[");

        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + ", ");
        }

        System.out.print("]\n");
    }

    public static void printInSequence(Object[] array)
    {
        System.out.print("[");

        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + ", ");
        }

        System.out.print("]\n");
    }
}
