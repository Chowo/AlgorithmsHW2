package org.example;

public class Main {
    public static void main(String[] args) {
        StringList list = new StringList();
        list.add("STRING1");
        list.add("STRING2");


        list.add("STRING3");
        list.add("STRING4");
        list.add("STRING2");
        System.out.println(list.remove(1));
        System.out.println(list.size());
        System.out.println();
        for (String entry : list.toArray()) {
            System.out.println(entry);
        }
        System.out.println();
        list.add(3, "STRING5");
        for (String entry : list.toArray()) {
            System.out.println(entry);
        }
//        System.out.println(list.size());
//        System.out.println(list.toArray().length);
//        for (String entry : list.toArray()) {
//            System.out.println(entry);
//        }
    }
}
