package org.alexdev.havana.messages.outgoing.rooms.items;

import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class TELEPORTER_INIT extends MessageComposer {
    private final int teleporterId;
    private final int roomId;

    public TELEPORTER_INIT(int teleporterId, int roomId) {
        this.teleporterId = teleporterId;
        this.roomId = roomId;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.teleporterId);
        response.writeInt(this.roomId);
    }

    @Override
    public short getHeader() {
        return 62; // "@~"
    }
}
