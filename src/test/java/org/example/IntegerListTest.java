package org.example;

import org.example.exceptions.IndexIsOutOfBoundsException;
import org.example.exceptions.NoSuchIntegerException;
import org.example.exceptions.NullListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.IntegerListTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerListTest {

    private final IntegerList list = new IntegerList();

    @BeforeEach
    void make_a_list_of_integers() {
        list.add(INTEGER1);
        list.add(INTEGER2);
        list.add(INTEGER3);
        list.add(INTEGER4);
        list.add(INTEGER2);
    }

    @Test
    public void test_that_element_can_be_added_above_initial_size() {
        list.add(INTEGER5);
        assertEquals(list.get(5), INTEGER5);
    }

    @Test
    public void test_that_adding_by_index_throws_exception_when_index_is_out_of_bounds1() {
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.add(11, INTEGER6));
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.add(-1, INTEGER6));
    }

    @Test
    public void test_that_adding_by_index_is_working_and_no_integers_was_lost() {
        list.add(3, INTEGER6);
        assertEquals(INTEGER6, list.get(3));
        assertEquals(INTEGER4, list.get(4));
        assertEquals(INTEGER2, list.get(5));
    }

    @Test
    public void test_that_set_sets_new_Integer_and_size_does_not_change() {
        list.set(3, INTEGER6);
        assertEquals(INTEGER6, list.get(3));
        assertEquals(5, list.size());
    }

    @Test
    public void test_that_set_throws_exception_when_index_is_out_of_bounds() {
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.set(11, INTEGER6));
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.set(-1, INTEGER6));
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.set(5, INTEGER6));
    }

    @Test
    public void test_Remove_Integer_removes_only_one_first_found_item() {
        assertEquals(INTEGER2, list.remove(INTEGER2));
        assertEquals(4, list.size());
        assertTrue(list.contains(54));
    }

    @Test
    public void test_that_Remove_throws_exception_when_item_not_found() {
        assertThrows(NoSuchIntegerException.class, () -> list.remove(INTEGER6));
    }

    @Test
    public void test_Remove_by_index_removes() {
        assertEquals(INTEGER2, list.remove(1));
        assertEquals(4, list.size());
    }

    @Test
    public void test_Remove_by_index_throws_exception_when_index_ix_out_of_bounds() {
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.remove(11));
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.remove(5));
    }

    @Test
    public void testContains() {
        assertTrue(list.contains(54));
        assertTrue(list.contains(INTEGER3));
        assertFalse(list.contains(INTEGER5));

    }

    @Test
    public void testIndexOf() {
        assertEquals(1, list.indexOf(54));
        assertEquals(-1, list.indexOf(INTEGER5));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(4, list.lastIndexOf(54));
        assertEquals(-1, list.indexOf(INTEGER5));
    }

    @Test
    public void testGet() {
        assertEquals(5, list.get(3));
    }

    @Test
    public void test_that_Equals_works() {
        IntegerList comparableList = new IntegerList();
        comparableList.add(100);
        comparableList.add(54);
        comparableList.add(17);
        comparableList.add(5);
        comparableList.add(54);
        assertTrue(comparableList.equals(list));
    }

    @Test
    public void test_that_Equals_throws_exception_when_comparable_list_is_null() {
        IntegerList nullList = null;
        assertThrows(NullListException.class, () -> list.equals(nullList));
    }

    @Test
    public void testSize() {
        assertEquals(5, list.size());
        list.add(INTEGER6);
        list.add(INTEGER5);
        assertEquals(7, list.size());
    }

    @Test
    public void testIsEmpty() {
        IntegerList emptyList = new IntegerList();
        assertTrue(emptyList.isEmpty());
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToArray() {
        Integer[] array = list.toArray();
        assertEquals(array.length, list.size());
        assertEquals(array[2], list.get(2));
    }
}