package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int size = 100;
        Integer[] list = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = random.nextInt(size);
        }
        Integer[] sort1List = Arrays.copyOf(list, size);
        Integer[] sort2List = Arrays.copyOf(list, size);
        Integer[] sort3List = Arrays.copyOf(list, size);

        long start1 = System.currentTimeMillis();
        IntegerList.bubbleSort(sort1List);
        System.out.println("Bubble sort time: " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        IntegerList.selectionSort(sort2List);
        System.out.println("Selection sort time: " + (System.currentTimeMillis() - start2));

        long start3 = System.currentTimeMillis();
        IntegerList.insertionSort(sort3List);
        System.out.println("Insertion sort time: " + (System.currentTimeMillis() - start3));

        for (Integer integer : sort1List) {
            System.out.print(integer + ", ");
        }
        System.out.println();
        for (Integer integer : sort2List) {
            System.out.print(integer + ", ");
        }
        System.out.println();
        for (Integer integer : sort2List) {
            System.out.print(integer + ", ");
        }
        System.out.println();


    }
}
