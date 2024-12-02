package org.a4z0.lwjgl.demo.chunk;

import org.a4z0.lwjgl.demo.DevelopmentConstants;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.level.Location;
import org.a4z0.lwjgl.demo.server.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChunkProvider {

    protected final Server server;
    protected final Level level;

    protected final Map<Long, Chunk> Cached = new HashMap<>();

    /**
    * Construct a {@link ChunkProvider}.
    *
    * @param server ...
    * @param level ....
    */

    public ChunkProvider(Server server, Level level) {
        this.server = server;
        this.level = level;
    }

    /**
    * @return the {@link Server}.
    */

    public Server getServer() {
        return this.server;
    }

    /**
    * @return the {@link Level}.
    */

    public Level getLevel() {
        return this.level;
    }

    /**
    * Checks if a Chunk in a position exists.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return true if it exists, false otherwise.
    */

    public boolean exists(int x, int y, int z) {
        return this.exists(new ChunkPosition(x, y, z));
    }

    /**
    * ...
    *
    * @param position ...
    *
    * @return true if it exists, false otherwise.
    */

    public boolean exists(ChunkPosition position) {
        return this.Cached.containsKey(position.getIndex());
    }

    /**
    * Provides a {@link Chunk}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Chunk}.
    */

    public Chunk provide(int x, int y, int z) {
        return this.provide(new ChunkPosition(x, y, z));
    }

    /**
    * Provides a {@link Chunk}.
    *
    * @param position ...
    *
    * @return a {@link Chunk}.
    */

    public Chunk provide(ChunkPosition position) {
        return this.Cached.computeIfAbsent(position.getIndex(), (_ignored) -> new Chunk(this.level, position));
    }

    /**
    * Removes a {@link Chunk}.
    *
    * @param chunk ...
    *
    * @return an {@link Optional} of a {@link Chunk}.
    */

    public Optional<Chunk> remove(Chunk chunk) {
        return this.remove(chunk.getPosition());
    }

    /**
    * Removes a {@link Chunk}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return an {@link Optional} of a {@link Chunk}.
    */

    public Optional<Chunk> remove(int x, int y, int z) {
        return this.remove(new ChunkPosition(x, y, z));
    }

    /**
    * Removes a {@link Chunk}.
    *
    * @param position ...
    *
    * @return an {@link Optional} of a {@link Chunk}.
    */

    public Optional<Chunk> remove(ChunkPosition position) {
        return Optional.of(this.Cached.remove(position.getIndex()));
    }

    /**
    * Ticks this {@link ChunkProvider}.
    */

    public void tick() {
        if(this.Cached.isEmpty()) {
            Location playerLoc = DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().clone();

            for (int x = (int) playerLoc.getX() - 16; x < (int) playerLoc.getX() + 16; x++) {
                for (int z = (int) playerLoc.getZ() - 16; z < (int) playerLoc.getZ() + 16; z++) {
                    Chunk playerChunk = this.provide(x << 4, 0, z << 4);

                    playerChunk.load(true);
                }
            }
        }

        for(Chunk value : this.Cached.values()) {
            value.getLayers().render();
        }
    }
}