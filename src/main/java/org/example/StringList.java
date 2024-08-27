package org.example;

import org.example.exceptions.IndexIsOutOfBoundsException;
import org.example.exceptions.NoSuchStringException;
import org.example.exceptions.NullListException;

public class StringList implements StringListInterface {
    private String[] list;

    public StringList() {
        list = new String[5];
    }

    @Override
    public String add(String item) {
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

    private String[] makeBiggerList() {
        String[] biggerList = new String[list.length * 2];
        System.arraycopy(list, 0, biggerList, 0, list.length);
        return biggerList;
    }

    @Override
    public String add(int index, String item) {
        int listSize = trimNullsInList(list).length;
        if (index > listSize || index < 0) {
            throw new IndexIsOutOfBoundsException(index);
        } else {
            String[] tmp;
            if (list[list.length - 1] != null) {
                tmp = makeBiggerList();
            } else {
                tmp = list;
            }

            String tmpString1 = list[index];
            String tmpString2 = tmpString1;
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
    public String set(int index, String item) {
        if (index >= list.length || index < 0 || list[index] == null) {
            throw new IndexIsOutOfBoundsException(index);
        } else {
            list[index] = item;
        }
        return item;
    }

    @Override
    public String remove(String item) {
        int listSize = trimNullsInList(list).length;
        String tmp = null;
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
            throw new NoSuchStringException(item);
        }
        return tmp;
    }

    @Override
    public String remove(int index) {
        int listSize = trimNullsInList(list).length;
        String removableItem;
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
    public boolean contains(String item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index >= list.length || index < 0) {
            throw new IndexIsOutOfBoundsException(index);
        }
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullListException();
        }
        boolean equals = false;
        String[] tmp = trimNullsInList(list);
        if (tmp == otherList.toArray()) {
            return true;
        } else if (tmp.length == otherList.toArray().length) {
            for (int i = 0; i < tmp.length; i++) {
                if (!tmp[i].equals(otherList.toArray()[i])) {
                    equals = false;
                    break;
                } else {
                    equals = true;
                }
            }
        }
        return equals;
    }

    private String[] trimNullsInList(String[] listToTrim) {
        int newLength = 0;
        for (String s : listToTrim) {
            if (s != null) {
                newLength++;
            }
        }
        String[] trimmedList = new String[newLength];
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
        list = new String[5];
    }

    @Override
    public String[] toArray() {
        return trimNullsInList(list);
    }
}
