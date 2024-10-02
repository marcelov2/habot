package org.alexdev.havana.game.games.snowstorm.events;

import org.alexdev.havana.game.games.GameObject;
import org.alexdev.havana.game.games.enums.GameObjectType;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class SnowStormMachineMoveSnowballsEvent extends GameObject {
    private final int playerId;
    private final int machineId;

    public SnowStormMachineMoveSnowballsEvent(int playerId, int machineId) {
        super(-1, GameObjectType.SNOWWAR_MACHINE_MOVE_SNOWBALLS_EVENT);
        this.playerId = playerId;
        this.machineId = machineId;
    }

    @Override
    public void serialiseObject(NettyResponse response) {
        response.writeInt(this.getGameObjectType().getObjectId());
        response.writeInt(this.playerId);
        response.writeInt(this.machineId);
    }
}
