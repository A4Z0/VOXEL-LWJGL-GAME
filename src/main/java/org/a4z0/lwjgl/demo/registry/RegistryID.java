package org.a4z0.lwjgl.demo.registry;

import java.util.Arrays;

public class RegistryID<T> {

    public static final int GROWTH_FACTOR = 1;

    protected int[] queryWithValue;
    protected T[] queryWithID;

    protected int i;

    /**
    * Construct a {@link RegistryID}.
    *
    * @param i ...
    */

    public RegistryID(int i) {
        this.queryWithValue = new int[i];

        //noinspection unchecked
        this.queryWithID = (T[]) new Object[i];
    }



    public int size() {
        return this.i;
    }

    /**
    * Retrieves an ID attached to the Value.
    *
    * @param value Value.
    *
    * @return an ID or -1.
    */

    public int getID(T value) {
        for(int i = 0; i < this.i; i++)
            if(this.queryWithID[i] == value)
                return this.queryWithValue[i];

        return -1;
    }

    /**
    * Retrieves a Value attached to the ID.
    *
    * @param id ID.
    *
    * @return a Value or null.
    */

    public T get(int id) {
        for(int i = 0; i < this.i; i++)
            if(this.queryWithValue[i] == id)
                return this.queryWithID[i];

        return null;
    }

    /**
    * Adds a Value.
    *
    * @param value Value to be added.
    */

    public void add(T value) {
        this.set(this.i + 1, value);
    }

    /**
    * Sets a Value.
    *
    * @param id ID to be set.
    * @param value Value to attached to the ID.
    */

    public void set(int id, T value) {
        System.out.println("Id: " + id);
        for(int i = 0; i < this.i; i++) {
            if(this.queryWithValue[i] == id) {
                this.queryWithID[i] = value;

                return;
            }
        }

        this.growth();

        this.queryWithValue[this.i] = id;
        this.queryWithID[this.i] = value;

        this.i++;
    }

    /**
    * Removes a Value.
    *
    * @param value Value to be removed.
    */

    public void remove(T value) {
        this.remove(this.getID(value));
    }

    /**
    * Removes a Value.
    *
    * @param id ID to be removed.
    */

    public void remove(int id) {
        for(int i = 0; i < this.i; i++) {
            if(this.queryWithValue[i] == id) {
                System.arraycopy(this.queryWithValue, i + 1, this.queryWithValue, i, this.i - i - 1);
                System.arraycopy(this.queryWithID, i + 1, this.queryWithID, i, this.i - i - 1);

                this.i--;

                return;
            }
        }
    }

    /**
    * Increases the capacity of this {@link RegistryID}.
    */

    private void growth() {
        if(this.i + GROWTH_FACTOR > this.queryWithValue.length) {
            this.queryWithValue = Arrays.copyOf(this.queryWithValue, this.queryWithValue.length * 2 + GROWTH_FACTOR);
            this.queryWithID = Arrays.copyOf(this.queryWithID, this.queryWithID.length * 2 + GROWTH_FACTOR);
        }
    }
}