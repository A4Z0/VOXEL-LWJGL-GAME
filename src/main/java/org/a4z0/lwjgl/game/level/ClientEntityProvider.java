package org.a4z0.lwjgl.game.level;

import org.a4z0.lwjgl.api.entity.Entity;
import org.a4z0.lwjgl.api.entity.Player;
import org.a4z0.lwjgl.api.level.EntityProvider;
import org.a4z0.lwjgl.api.level.Level;
import org.a4z0.lwjgl.game.entity.EntityPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientEntityProvider implements EntityProvider {

    protected final Map<Class<? extends Entity>, List<? extends Entity>> entities = new HashMap<>();
    protected final ClientLevel level;

    /**
    * Construct a {@link ClientEntityProvider}.
    *
    * @param level Level.
    */

    public ClientEntityProvider(ClientLevel level) {
        this.level = level;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public <T extends Entity> T spawn(Class<? super T> e, int x, int y, int z) {
        if(e.equals(Player.class))
            return (T) new EntityPlayer();

        return null;
    }
}