package org.alexdev.havana.messages.outgoing.rooms;

import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class UPDATE_VOTES extends MessageComposer {
    private final int rating;

    public UPDATE_VOTES(int rating) {
        this.rating = rating;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.rating);
    }

    @Override
    public short getHeader() {
        return 345;
    }
}
