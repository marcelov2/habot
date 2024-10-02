package org.alexdev.havana.messages.outgoing.user;

import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class IGNORE_USER_RESULT extends MessageComposer {
    private final int result;

    public IGNORE_USER_RESULT(int result) {
        this.result = result;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.result);
    }

    @Override
    public short getHeader() {
        return 419;
    }
}
