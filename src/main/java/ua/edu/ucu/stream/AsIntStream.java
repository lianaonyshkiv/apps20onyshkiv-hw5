package ua.edu.ucu.stream;


import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;

import java.util.ArrayList;

public class AsIntStream implements IntStream {
    private static AsIntStream values;
    private final ArrayList<Integer> array;

    private AsIntStream(ArrayList<Integer> elements) {
        // To Do
        array = new ArrayList<>();
        array.addAll(elements);
    }

    public static IntStream of(int... values) {
        ArrayList<Integer> el = new ArrayList<>();
        for (int value : values) {
            el.add(value);
        }
        return new AsIntStream(el);
    }

    @Override
    public Double average() {
        if (array.size() == 0) {
            throw new IllegalArgumentException();
        }
        return (sum() / (double) count());
    }

    public int maxMinComparisons(String param) {
        if (array.size() == 0) {
            throw new IllegalArgumentException();
        }
        int min = array.get(0);
        int max = array.get(0);
        for (int el : array) {
            min = Math.min(min, el);
            max = Math.max(max, el);
        }
        if (param.equals("min")) {
            return min;
        } else {
            return max;
        }
    }

    @Override
    public Integer max() {
        return maxMinComparisons("max");
    }

    @Override
    public Integer min() {
        return maxMinComparisons("min");
    }

    @Override
    public long count() {
        return array.size();
    }

    @Override
    public Integer sum() {
        if (array.size() == 0) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        for (int el : array) {
            result += el;
        }
        return result;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int el : array) {
            if (predicate.test(el)) {
                result.add(el);
            }
        }
        return new AsIntStream(result);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int el : array) {
            action.accept(el);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int el : array) {
            int newEl = mapper.apply(el);
            result.add(newEl);
        }
        return new AsIntStream(result);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int el : array) {
            IntStream stream = func.applyAsIntStream(el);
            int[] streamArray = stream.toArray();
            for (int e : streamArray) {
                result.add(e);
            }
        }
        return new AsIntStream(result);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int el : array) {
            result = op.apply(result, el);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[array.size()];
        int counter = 0;
        for (Object el : array) {
            result[counter] = (int) el;
            counter += 1;
        }
        return result;
    }

}
