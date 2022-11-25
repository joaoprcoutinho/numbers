package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ListDeduplicatorTest {
    private List<Integer> list;
    private List<Integer> expected;
    private GenericListSorter stubGenericListSorter;
    @BeforeEach
    public void helper(){
        list = Arrays.asList(1,2,4,2,5);
        expected = Arrays.asList(1,2,4,5);
        stubGenericListSorter = Mockito.mock(GenericListSorter.class);
        Mockito.when(stubGenericListSorter.sort(list)).thenReturn(Arrays.asList(1, 2, 2, 4, 5));
        Mockito.when(stubGenericListSorter.sort(Arrays.asList(1,2,4,2))).thenReturn(Arrays.asList(1, 2, 2, 4));

    }
    @Test
    public void deduplicate() {
        ListDeduplicator deduplicator = new ListDeduplicator(stubGenericListSorter);
        List<Integer> distinct = deduplicator.deduplicate(list);
        Assertions.assertEquals(expected, distinct);
    }
    @Test
    public void deduplicate_bug(){
        ListDeduplicator deduplicator = new ListDeduplicator(stubGenericListSorter);
        List<Integer> distinct = deduplicator.deduplicate(Arrays.asList(1,2,4,2));
        Assertions.assertEquals(Arrays.asList(1,2,4), distinct);
    }
}
