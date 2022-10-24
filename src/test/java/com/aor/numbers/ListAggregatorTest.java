package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListAggregatorTest {
    private List <Integer> list;

    @BeforeEach
    public void setup(){
        list = Arrays.asList(1,2,4,2,5);
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
        int distinct = aggregator.distinct(list, new ListDeduplicator());

        Assertions.assertEquals(4, distinct);
    }

    @Test
    public void max_bug_7263(){
        List<Integer> lista1 = Arrays.asList(-1,-4,-5);

        ListAggregator aggregator = new ListAggregator();
        int max = aggregator.max(lista1);

        Assertions.assertEquals(-1, max);
    }
    @Test
    public void distinct_bug_8726(){
        List<Integer> lista2 = Arrays.asList(1,2,4,2);

        ListAggregator aggregator = new ListAggregator();
        //int distinct = aggregator.distinct(lista2);
        class StubListDeduplicator implements GenericListDeduplicator {

            @Override
            public List<Integer> deduplicate(List<Integer> list) {
                return Arrays.asList(1,2,4);
            }
        }
//        ListDeduplicator deduplicator = new ListDeduplicator();
        int distinct = aggregator.distinct(Arrays.asList(1, 2, 4, 2), new StubListDeduplicator()); //injeção de "deduplicator"

        Assertions.assertEquals(3, distinct);
    }




}
