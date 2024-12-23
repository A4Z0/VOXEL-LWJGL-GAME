package org.a4z0.lwjgl.demo.resource;

public class Key {

    private static final char NAMESPACE_SEPARATOR = ':';
    private static final String DEFAULT_NAMESPACE = "default";

    protected final String namespace;
    protected final String path;

    /**
    * Construct a {@link Key}.
    *
    * @param namespace Namespace.
    * @param path Path.
    */

    protected Key(final String namespace, final String path) {
        this.namespace = namespace;
        this.path = path;
    }

    /**
    * @return the Namespace.
    */

    public String getNamespace() {
        return this.namespace;
    }

    /**
    * @return the Path.
    */

    public String getPath() {
        return this.path;
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
        return (o instanceof Key)
            && ((Key) o).namespace.equals(this.namespace)
            && ((Key) o).path.equals(this.path);
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public String toString() {
        return this.getNamespace() + NAMESPACE_SEPARATOR + this.getPath();
    }

    /**
    * @return the Hashcode.
    */

    @Override
    public int hashCode() {
        return 31 * this.getNamespace().hashCode() * this.getPath().hashCode();
    }

    /**
    * Construct a {@link Key}.
    *
    * @param path Path.
    *
    * @return a new {@link Key}.
    */

    public static Key of(final String path) {
        return new Key(DEFAULT_NAMESPACE, path);
    }

    /**
    * Construct a {@link Key}.
    *
    * @param namespace Namespace.
    * @param path Path.
    *
    * @return a new {@link Key}.
    */

    public static Key of(final String namespace, final String path) {
        return new Key(namespace, path);
    }
}