package org.a4z0.lwjgl.game.buffer;

import java.nio.*;

import static org.lwjgl.system.MemoryUtil.*;

public class ByteBuf implements AutoCloseable {

    /* Address */
    protected long a;

    /* Position / Capacity */
    protected int p, c;

    /**
    * Construct a {@link ByteBuf}.
    */

    public ByteBuf() {
        this(4096);
    }

    /**
    * Construct a {@link ByteBuf}.
    *
    * @param i Initial capacity.
    */

    public ByteBuf(int i) {
        this.a = nmemAlloc(this.c = (i & 0x7FFFFFFF));
    }

    /**
    * @return the number of bytes in this {@link ByteBuf}.
    */

    public int size() {
        return this.p;
    }

    /**
    * Clears all bytes from this {@link ByteBuf}.
    */

    public void clear() {
        memSet(this.a, (this.p = 0), this.c);
    }

    /**
    * Closes this {@link ByteBuf}.
    */

    @Override
    public void close() {
        if(this.a == 0)
            return;

        nmemFree(this.a);

        this.a = 0;
    }

    /**
    * @return the number of remaining bytes.
    */

    protected int remaining() {
        return (this.c - this.p);
    }

    /**
    * Increases the capacity of this {@link ByteBuf}.
    */

    protected void grow() {
        this.a = nmemRealloc(this.a, this.c = (2 * this.c));
    }

    /**
    * Adds a {@link Byte} in this {@link ByteBuf}.
    *
    * @param b Value that will be added.
    *
    * @return this {@link ByteBuf}.
    */

    public ByteBuf put(byte b) {
        if(this.remaining() < Byte.BYTES)
            this.grow();

        memPutByte(this.a + this.p, b);

        this.p += Byte.BYTES;

        return this;
    }

    /**
    * Adds a {@link Short} in this {@link ByteBuf}.
    *
    * @param s Value that will be added.
    *
    * @return this {@link ByteBuf}.
    */

    public ByteBuf put(short s) {
        if(this.remaining() < Short.BYTES)
            this.grow();

        memPutShort(this.a + this.p, s);

        this.p += Short.BYTES;

        return this;
    }

    /**
    * Adds a {@link Integer} in this {@link ByteBuf}.
    *
    * @param i Value that will be added.
    *
    * @return this {@link ByteBuf}.
    */

    public ByteBuf put(int i) {
        if(this.remaining() < Integer.BYTES)
            this.grow();

        memPutInt(this.a + this.p, i);

        this.p += Integer.BYTES;

        return this;
    }

    /**
    * Adds a {@link Float} in this {@link ByteBuf}.
    *
    * @param f Value that will be added.
    *
    * @return this {@link ByteBuf}.
    */

    public ByteBuf put(float f) {
       if(this.remaining() < Float.BYTES)
           this.grow();

       memPutFloat(this.a + (this.p), f);

       this.p += Float.BYTES;

       return this;
    }

    /**
    * Adds a {@link Double} in this {@link ByteBuf}.
    *
    * @param d Value that will be added.
    *
    * @return this {@link ByteBuf}.
    */

    public ByteBuf put(double d) {
        if(this.remaining() < Double.BYTES)
            this.grow();

        memPutDouble(this.a + (this.p), d);

        this.p += Double.BYTES;

        return this;
    }

    /**
    * Adds a {@link Long} in this {@link ByteBuf}.
    *
    * @param l Value that will be added.
    *
    * @return this {@link ByteBuf}.
    */

    public ByteBuf put(long l) {
        if(this.remaining() < Long.BYTES)
            this.grow();

        memPutLong(this.a + this.p, l);

        this.p += Long.BYTES;

        return this;
    }

    /**
    * @return this {@link ByteBuf} as a new {@link ByteBuffer}.
    */

    public ByteBuffer asByteBuffer() {
        return memByteBuffer(this.a, this.p);
    }

    /**
    * @return this {@link ByteBuf} as a new {@link ByteBuffer}.
    */

    public ShortBuffer asShortBuffer() {
        return memShortBuffer(this.a, this.p);
    }

    /**
    * @return this {@link ByteBuf} as a new {@link IntBuffer}.
    */

    public IntBuffer asIntBuffer() {
        return memIntBuffer(this.a, this.p);
    }

    /**
    * @return this {@link ByteBuf} as a new {@link FloatBuffer}.
    */

    public FloatBuffer asFloatBuffer() {
        return memFloatBuffer(this.a, this.p);
    }

    /**
    * @return this {@link ByteBuf} as a new {@link DoubleBuffer}.
    */

    public DoubleBuffer asDoubleBuffer() {
        return memDoubleBuffer(this.a, this.p);
    }

    /**
    * @return this {@link ByteBuf} as a new {@link LongBuffer}.
    */

    public LongBuffer asLongBuffer() {
        return memLongBuffer(this.a, this.p);
    }
}