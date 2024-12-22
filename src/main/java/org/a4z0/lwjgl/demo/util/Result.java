package org.a4z0.lwjgl.demo.util;

import java.util.Objects;
import java.util.Optional;

public final class Result<T> {

    private final T value;
    private final String message;

    /**
    * Construct a {@link Result}.
    *
    * @param value Value.
    * @param message Error Message.
    */

    Result(final T value, final String message) {
        this.value = value;
        this.message = message;
    }

    /**
    * @return an {@link Optional} of the Value.
    */

    public Optional<T> get() {
        return Optional.ofNullable(this.value);
    }

    /**
    * @return the Value or throws an error.
    */

    public T getOrThrow() {
        return this.get().orElseThrow(() -> new RuntimeException(this.message));
    }

    /**
    * @return ...
    */

    public Optional<String> getError() {
        return Optional.ofNullable(this.message);
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
        return (o instanceof Result) && ((Result<?>) o).value.equals(this.value);
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public String toString() {
        return "Result{\"Value:\" " + this.value + ", \"Message\": \"" + this.message + "\"}";
    }

    /**
    * @return the Hashcode.
    */

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    /**
    * Construct a {@link Result}.
    *
    * @param value Value.
    *
    * @return a new {@link Result}.
    */

    public static <T> Result<T> success(final T value) {
        return new Result<>(value, null);
    }

    /**
    * Construct a {@link Result}.
    *
    * @param message Error Message.
    *
    * @return a new {@link Result}.
    */

    public static <T> Result<T> error(final String message) {
        return new Result<>(null, message);
    }
}