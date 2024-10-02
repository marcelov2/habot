package org.alexdev.havana.game.games.snowstorm.events;

import org.alexdev.havana.game.games.GameObject;
import org.alexdev.havana.game.games.enums.GameObjectType;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class SnowStormDeleteObjectEvent extends GameObject {
    private final int objectId;

    public SnowStormDeleteObjectEvent(int objectId) {
        super(objectId, GameObjectType.SNOWWAR_REMOVE_OBJECT_EVENT);
        this.objectId = objectId;
    }

    @Override
    public void serialiseObject(NettyResponse response) {
        response.writeInt(this.getGameObjectType().getObjectId());
        response.writeInt(this.objectId);
    }
}
