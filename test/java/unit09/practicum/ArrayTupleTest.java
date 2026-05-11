package unit09.practicum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ArrayTupleTest {
    @Test
    public void testArrayTuple() {
        // set up
        Object[] elements = {1, 2, 3};
        ArrayTuple<Integer> tuple = new ArrayTuple<>(elements);
        String expected = "[1, 2, 3]";

        // invoke
        String tupleAsString = tuple.toString();

        // analyze
        assertEquals(expected, tupleAsString);
    }
}
