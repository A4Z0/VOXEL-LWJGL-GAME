package org.a4z0.lwjgl.demo.util;

import java.util.Objects;
import java.util.Optional;

public class DataResult<T> {

    protected final T result;
    protected final String errorMessage;

    /**
    * Construct a {@link DataResult}.
    *
    * @param result Result.
    * @param errorMessage Error Message.
    */

    protected DataResult(final T result, final String errorMessage) {
        this.result = result;
        this.errorMessage = errorMessage;
    }

    /**
    * @return an Optional of the Result.
    */

    public Optional<T> result() {
        return Optional.ofNullable(this.result);
    }

    /**
    * @return the Result or throws an error.
    */

    public T resultOrThrow() {
        return this.result().orElseThrow(() -> new RuntimeException(this.errorMessage));
    }

    /**
    * @return an Optional of the Error.
    */

    public Optional<String> error() {
        return Optional.ofNullable(this.errorMessage);
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
        return (o instanceof DataResult) && ((DataResult<?>) o).result.equals(this.result);
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public String toString() {
        return "DataResult[" + this.result + "]";
    }

    /**
    * @return the Hashcode.
    */

    @Override
    public int hashCode() {
        return Objects.hash(this.result);
    }

    /**
    * Construct a {@link DataResult}.
    *
    * @param result Result.
    *
    * @return a new {@link DataResult}.
    */

    public static <T> DataResult<T> success(final T result) {
        return new DataResult<>(result, null);
    }

    /**
    * Construct a {@link DataResult}.
    *
    * @param errorMessage Error Message.
    *
    * @return a new {@link DataResult}.
    */

    public static <T> DataResult<T> error(final String errorMessage) {
        return new DataResult<>(null, errorMessage);
    }
}