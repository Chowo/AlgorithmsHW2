package org.example;

import org.example.exceptions.IndexIsOutOfBoundsException;
import org.example.exceptions.NoSuchStringException;
import org.example.exceptions.NullListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.StringListTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringListTest {

    private final StringList list = new StringList();

    @BeforeEach
    void make_a_list_of_strings() {
        list.add(STRING1);
        list.add(STRING2);
        list.add(STRING3);
        list.add(STRING4);
        list.add(STRING2);
    }

    @Test
    public void test_that_element_can_be_added_above_initial_size() {
        list.add(STRING5);
        assertEquals(list.get(5), STRING5);
    }

    @Test
    public void test_that_adding_by_index_throws_exception_when_index_is_out_of_bounds1() {
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.add(11, STRING6));
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.add(-1, STRING6));
    }

    @Test
    public void test_that_adding_by_index_is_working_and_no_strings_was_lost() {
        list.add(3, STRING6);
        assertEquals(STRING6, list.get(3));
        assertEquals(STRING4, list.get(4));
        assertEquals(STRING2, list.get(5));
    }

    @Test
    public void test_that_set_sets_new_String_and_size_does_not_change() {
        list.set(3, STRING6);
        assertEquals(STRING6, list.get(3));
        assertEquals(5, list.size());
    }

    @Test
    public void test_that_set_throws_exception_when_index_is_out_of_bounds() {
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.set(11, STRING6));
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.set(-1, STRING6));
        assertThrows(IndexIsOutOfBoundsException.class, () -> list.set(5, STRING6));
    }

    @Test
    public void test_Remove_String_removes_only_one_first_found_item() {
        assertEquals(STRING2, list.remove(STRING2));
        assertEquals(4, list.size());
        assertTrue(list.contains("Line2"));
    }

    @Test
    public void test_that_Remove_throws_exception_when_item_not_found() {
        assertThrows(NoSuchStringException.class, () -> list.remove(STRING6));
    }

    @Test
    public void test_Remove_by_index_removes() {
        assertEquals(STRING2, list.remove(1));
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
        assertTrue(list.contains("Line2"));
        assertTrue(list.contains(STRING3));
        assertFalse(list.contains(STRING5));

    }

    @Test
    public void testIndexOf() {
        assertEquals(1, list.indexOf("Line2"));
        assertEquals(-1, list.indexOf(STRING5));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(4, list.lastIndexOf("Line2"));
        assertEquals(-1, list.indexOf(STRING5));
    }

    @Test
    public void testGet() {
        assertEquals("Line4", list.get(3));
    }

    @Test
    public void test_that_Equals_works() {
        StringList comparableList = new StringList();
        comparableList.add("Line1");
        comparableList.add("Line2");
        comparableList.add("Line3");
        comparableList.add("Line4");
        comparableList.add("Line2");
        assertTrue(comparableList.equals(list));
    }

    @Test
    public void test_that_Equals_throws_exception_when_comparable_list_is_null() {
        StringList nullList = null;
        assertThrows(NullListException.class, () -> list.equals(nullList));
    }

    @Test
    public void testSize() {
        assertEquals(5, list.size());
        list.add(STRING6);
        list.add(STRING5);
        assertEquals(7, list.size());
    }

    @Test
    public void testIsEmpty() {
        StringList emptyList = new StringList();
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
        String[] array = list.toArray();
        assertEquals(array.length, list.size());
        assertEquals(array[2], list.get(2));
    }
}