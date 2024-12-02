package org.a4z0.lwjgl.demo.registry;

import java.util.Arrays;
import java.util.Set;

public class Registry<T>  {

    public static final int GROWTH_FACTOR = 1;

    protected Key[] kA;
    protected T[] eA;

    protected int i;

    /**
    * Construct a {@link Registry}.
    */

    public Registry() {
        this.kA = new Key[1];

        //noinspection unchecked
        this.eA = (T[]) new Object[1];
    }

    /**
    * @return ...
    */

    public int size() {
        return this.i;
    }

    /**
    * @return ...
    */

    public Set<Key> getKeys() {
        return Set.of(this.kA);
    }

    /**
    * Retrieves a Value.
    *
    * @param k {@link Key} attached to a Value.
    *
    * @return a Value if it exists, null otherwise.
    */

    public T get(Key k) {
        for(int i = 0; i < this.i; i++)
            if(this.kA[i].equals(k))
                return this.eA[i];

        return null;
    }

    /**
    * Register a Value.
    *
    * @param k {@link Key} that will hold the Value.
    * @param e Value to be registered.
    *
    * @return this {@link Registry}.
    */

    public Registry<T> register(Key k, T e) {
        for(int i = 0; i < this.i; i++) {
            if(this.kA[i].equals(k)) {
                this.eA[i] = e;

                return this;
            }
        }

        if(this.i + GROWTH_FACTOR > this.kA.length) {
            this.kA = Arrays.copyOf(this.kA, this.kA.length * 2 + GROWTH_FACTOR);
            this.eA = Arrays.copyOf(this.eA, this.eA.length * 2 + GROWTH_FACTOR);
        }

        this.kA[this.i] = k;
        this.eA[this.i] = e;

        this.i++;

        return this;
    }

    /**
    * Unregister a Value.
    *
    * @param k {@link Key} to be removed.
    *
    * @return this {@link Registry}.
    */

    public Registry<T> unregister(Key k) {
        for(int i = 0; i < this.i; i++) {
            if(this.kA[i].equals(k)) {
                System.arraycopy(this.kA, i + 1, this.kA, i, this.i - i - 1);
                System.arraycopy(this.eA, i + 1, this.eA, i, this.i - i - 1);

                this.i--;

                return this;
            }
        }

        return this;
    }
}