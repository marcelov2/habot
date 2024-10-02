package org.alexdev.havana.messages.outgoing.messenger;

import org.alexdev.havana.game.messenger.MessengerError;
import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class MESSENGER_ERROR extends MessageComposer {
    private int clientMessageId; // The client likes to log the message id that caused this error
    private final MessengerError error;

    public MESSENGER_ERROR(MessengerError error) {
        this.error = error;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.clientMessageId);
        response.writeInt(this.error.getErrorType().getErrorCode());

        if (this.error.getErrorReason() != null) {
            response.writeInt(this.error.getErrorReason().getReasonCode());
        }
    }

    @Override
    public short getHeader() {
        return 260; // "DD"
    }
}