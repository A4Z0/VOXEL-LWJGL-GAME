package org.a4z0.lwjgl.demo.util;

public final class Pair<F, S> {

    private final F first;
    private final S second;

    /**
    * Construct a {@link Pair}.
    *
    * @param first First Value.
    * @param second Second Value.
    */

    Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
    * @return the First Value.
    */

    public F getFirst() {
        return this.first;
    }

    /**
    * @return the Second Value.
    */

    public S getSecond() {
        return this.second;
    }

    /**
    * Swap the values of this {@link Pair}.
    *
    * @return a new {@link Pair} with the swapped values.
    */

    public Pair<S, F> swap() {
        return Pair.of(this.getSecond(), this.getFirst());
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Pair<?,?>) && ((Pair<?, ?>) o).getFirst().equals(this.getFirst()) && ((Pair<?, ?>) o).getSecond().equals(this.getSecond());
    }

    /**
    * Construct a {@link Pair}.
    *
    * @param first First Value.
    * @param second Second Value.
    *
    * @return a new {@link Pair}.
    */

    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }
}