package org.example;

import org.example.exceptions.IndexIsOutOfBoundsException;
import org.example.exceptions.NoSuchIntegerException;
import org.example.exceptions.NullListException;

import java.util.Arrays;

public class IntegerList implements IntegerListInterface {
    private Integer[] list;

    public IntegerList() {
        list = new Integer[5];
    }

    @Override
    public Integer add(Integer item) {
        int listSize = trimNullsInList(list).length;
        if (listSize == 0) {
            list[0] = item;
        } else {
            if (list[list.length - 1] != null) {
                list = makeBiggerList();
            }
        }
        for (int i = 0; i < listSize + 1; i++) {
            if (list[i] == null) {
                list[i] = item;
            }
        }
        return item;
    }

    private Integer[] makeBiggerList() {
        Integer[] biggerList = new Integer[list.length * 2];
        System.arraycopy(list, 0, biggerList, 0, list.length);
        return biggerList;
    }

    @Override
    public Integer add(int index, Integer item) {
        int listSize = trimNullsInList(list).length;
        if (index > listSize || index < 0) {
            throw new IndexIsOutOfBoundsException(index);
        } else {
            Integer[] tmp;
            if (list[list.length - 1] != null) {
                tmp = makeBiggerList();
            } else {
                tmp = list;
            }

            Integer tmpString1 = list[index];
            Integer tmpString2 = tmpString1;
            for (int j = index; j < listSize + 1; j++) {
                if (j > index) {
                    tmpString2 = tmp[j];
                    tmp[j] = tmpString1;
                    tmpString1 = tmpString2;
                } else {
                    tmp[j] = item;
                }
            }
            list = tmp;
            list[index] = item;
        }

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index >= list.length || index < 0 || list[index] == null) {
            throw new IndexIsOutOfBoundsException(index);
        } else {
            list[index] = item;
        }
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int listSize = trimNullsInList(list).length;
        Integer tmp = null;
        for (int i = 0; i < listSize; i++) {
            if (list[i].equals(item)) {
                tmp = list[i];
                for (int j = i; j < listSize - 1; j++) {
                    list[j] = list[j + 1];
                }
            }
        }
        list[listSize - 1] = null;
        if (tmp == null) {
            throw new NoSuchIntegerException(item);
        }
        return tmp;
    }

    @Override
    public Integer remove(int index) {
        int listSize = trimNullsInList(list).length;
        Integer removableItem;
        if ((index >= list.length) || (index < 0) || (list[index] == null)) {
            throw new IndexIsOutOfBoundsException(index);
        } else {
            removableItem = list[index];
            for (int i = index; i < listSize - 1; i++) {
                list[i] = list[i + 1];
            }
            list[listSize - 1] = null;
        }
        return removableItem;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] sortedList = Arrays.copyOf(list, trimNullsInList(list).length);
        selectionSort(sortedList);
        if (binarySearch(list, item) == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= list.length || index < 0) {
            throw new IndexIsOutOfBoundsException(index);
        }
        return list[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullListException();
        }
        boolean equals = false;
        Integer[] tmp = trimNullsInList(list);
        if (otherList.toArray() == tmp) {
            return true;
        } else if (otherList.toArray().length == tmp.length) {
            for (int i = 0; i < tmp.length; i++) {
                if (otherList.toArray()[i] != tmp[i]) {
                    equals = false;
                    break;
                } else {
                    equals = true;
                }
            }
        }
        return equals;
    }

    private Integer[] trimNullsInList(Integer[] listToTrim) {
        int newLength = 0;
        for (Integer s : listToTrim) {
            if (s != null) {
                newLength++;
            }
        }
        Integer[] trimmedList = new Integer[newLength];
        for (int i = 0; i < listToTrim.length; i++) {
            if (list[i] == null) {
                break;
            } else {
                trimmedList[i] = list[i];
            }
        }
        return trimmedList;
    }

    @Override
    public int size() {
        return trimNullsInList(list).length;
    }

    @Override
    public boolean isEmpty() {
        return list[0] == null;
    }

    @Override
    public void clear() {
        list = new Integer[5];
    }

    @Override
    public Integer[] toArray() {
        return trimNullsInList(list);
    }

    private static void swapElements(Integer[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public static void selectionSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swapElements(array, i, minIndex);
        }
    }

    public static void bubbleSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] < array[j + 1]) {
                    swapElements(array, j, j + 1);
                }
            }
        }
    }

    public static void insertionSort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            for (; j >0 && array[j-1] >= temp; j--) {
                array[j] = array[j-1];
            }
            array[j] = temp;
        }
    }

    private static int binarySearch(Integer[] array, Integer item) {
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == array[mid]) {
                return mid;
            }
            if (item < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;

    }

}
