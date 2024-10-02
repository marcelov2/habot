package org.alexdev.havana.game.games.snowstorm.events;

import org.alexdev.havana.game.games.GameObject;
import org.alexdev.havana.game.games.enums.GameObjectType;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class SnowStormMachineAddSnowballEvent extends GameObject {
    private final int machineId;

    public SnowStormMachineAddSnowballEvent(int machineId) {
        super(machineId, GameObjectType.SNOWWAR_MACHINE_ADD_SNOWBALL_EVENT);
        this.machineId = machineId;
    }

    @Override
    public void serialiseObject(NettyResponse response) {
        response.writeInt(this.getGameObjectType().getObjectId());
        response.writeInt(this.machineId);
    }
}
