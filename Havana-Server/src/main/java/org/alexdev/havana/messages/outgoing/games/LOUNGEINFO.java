package org.alexdev.havana.messages.outgoing.games;

import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class LOUNGEINFO extends MessageComposer {
    @Override
    public void compose(NettyResponse response) {
        response.writeInt(0);
        //response.writeString("Rank name"); // Rank name here
        //response.writeInt(1); // Minimum points
        //response.writeInt(1); // Maximum points
    }

    @Override
    public short getHeader() {
        return 231; // "Cg"
    }
}
