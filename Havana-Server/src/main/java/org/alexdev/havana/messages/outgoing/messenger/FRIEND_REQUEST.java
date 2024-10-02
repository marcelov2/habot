package org.alexdev.havana.messages.outgoing.messenger;

import org.alexdev.havana.game.messenger.MessengerUser;
import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class FRIEND_REQUEST extends MessageComposer {
    private final MessengerUser requester;

    public FRIEND_REQUEST(MessengerUser requester) {
        this.requester = requester;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.requester.getUserId());
        response.writeString(this.requester.getUsername());
        response.writeString(String.valueOf(this.requester.getUserId()));
    }

    @Override
    public short getHeader() {
        return 132;
    }
}
