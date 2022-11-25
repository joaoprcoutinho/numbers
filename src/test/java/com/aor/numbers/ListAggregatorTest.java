package com.aor.numbers;

import com.sun.tools.javac.jvm.Gen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;


public class ListAggregatorTest {
    List<Integer> list;
    private  GenericListDeduplicator stubGenericListDeduplicator;
    @BeforeEach
    public void helper() {
        list = Arrays.asList(1, 2, 4, 2, 5);

        stubGenericListDeduplicator = Mockito.mock(GenericListDeduplicator.class);
        Mockito.when(stubGenericListDeduplicator.deduplicate(list)).thenReturn(Arrays.asList(1, 2, 4, 5));
        Mockito.when(stubGenericListDeduplicator.deduplicate(Arrays.asList(1, 2, 2, 4))).thenReturn(Arrays.asList(1, 2, 4));

    }

    @Test
    public void sum() {
        ListAggregator aggregator = new ListAggregator();
        int sum = aggregator.sum(list);

        Assertions.assertEquals(14, sum);
    }

    @Test
    public void max() {
        ListAggregator aggregator = new ListAggregator();
        int max = aggregator.max(list);

        Assertions.assertEquals(5, max);
    }

    @Test
    public void min() {
        ListAggregator aggregator = new ListAggregator();
        int min = aggregator.min(list);

        Assertions.assertEquals(1, min);
    }

    @Test
    public void distinct() {
        ListAggregator aggregator = new ListAggregator();
        int distinct = aggregator.distinct(list, stubGenericListDeduplicator);
        Assertions.assertEquals(4, distinct);
    }
    @Test
    public void max_bug_7263(){
        List<Integer> test = Arrays.asList(-1,-4,-5);

        ListAggregator aggregator = new ListAggregator();
        int max = aggregator.max(test);

        Assertions.assertEquals(-1, max);
    }
    @Test
    public void distinct_bug(){
        ListAggregator aggregator = new ListAggregator();
        int distinct = aggregator.distinct(Arrays.asList(1, 2, 2, 4), stubGenericListDeduplicator);
        Assertions.assertEquals(3, distinct);
    }

}
