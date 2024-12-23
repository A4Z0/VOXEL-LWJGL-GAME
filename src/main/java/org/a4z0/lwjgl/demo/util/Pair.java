package org.a4z0.lwjgl.demo.util;

import java.util.Objects;

public class Pair<F, S> {

    protected final F first;
    protected final S second;

    /**
    * Construct a {@link Pair}.
    *
    * @param first First Value.
    * @param second Second Value.
    */

    protected Pair(final F first, final S second) {
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

    /**
    * Checks if this is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return (o instanceof Pair<?,?>) && ((Pair<?, ?>) o).getFirst().equals(this.getFirst()) && ((Pair<?, ?>) o).getSecond().equals(this.getSecond());
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public String toString() {
        return "Pair[" + this.getFirst() + ", " + this.getSecond() + "]";
    }

    /**
    * @return the Hashcode.
    */

    @Override
    public int hashCode() {
        return Objects.hash(this.getFirst(), this.getSecond());
    }

    /**
    * Construct a {@link Pair}.
    *
    * @param first First Value.
    * @param second Second Value.
    *
    * @return a new {@link Pair}.
    */

    public static <F, S> Pair<F, S> of(final F first, final S second) {
        return new Pair<>(first, second);
    }
}