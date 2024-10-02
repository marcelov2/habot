package org.alexdev.havana.messages.outgoing.messenger;

import org.alexdev.havana.game.messenger.MessengerUser;
import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

import java.util.List;

public class FRIEND_REQUESTS extends MessageComposer {
    private final List<MessengerUser> requests;

    public FRIEND_REQUESTS(List<MessengerUser> requests) {
        this.requests = requests;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.requests.size());
        response.writeInt(this.requests.size());

        for (MessengerUser messengerUser : this.requests) {
            response.writeInt(messengerUser.getUserId());
            response.writeString(messengerUser.getUsername());
            response.writeString(messengerUser.getUserId());
        }
    }

    @Override
    public short getHeader() {
        return 314; // "BD"
    }
}
