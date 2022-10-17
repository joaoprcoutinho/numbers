package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListDeduplicatorTest {
    private List<Integer> list;
    private List<Integer> expected;
    @BeforeEach
    public void helper(){
        list = Arrays.asList(1,2,4,2,5);
        expected = Arrays.asList(1,2,4,5);
    }
    @Test
    public void deduplicate() {
        class Stub implements GenericListSorter{
            @Override public List<Integer> sort(List<Integer> list){
                return Arrays.asList(1, 2, 2, 4, 5);
            }
        }
        Stub sorter = new Stub();
        ListDeduplicator deduplicator = new ListDeduplicator(sorter);
        List<Integer> distinct = deduplicator.deduplicate(list);
        Assertions.assertEquals(expected, distinct);
    }
    @Test
    public void deduplicate_bug(){
        class Stub implements GenericListSorter{
            @Override public List<Integer> sort(List<Integer> list){
                return Arrays.asList(1, 2, 2, 4);
            }
        }
        Stub sorter = new Stub();
        ListDeduplicator deduplicator = new ListDeduplicator(sorter);
        List<Integer> distinct = deduplicator.deduplicate(Arrays.asList(1,2,4,2));
        Assertions.assertEquals(Arrays.asList(1,2,4), distinct);
    }
}
