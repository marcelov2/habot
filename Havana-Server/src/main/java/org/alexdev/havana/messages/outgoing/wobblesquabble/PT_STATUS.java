package org.alexdev.havana.messages.outgoing.wobblesquabble;

import org.alexdev.havana.game.games.wobblesquabble.WobbleSquabblePlayer;
import org.alexdev.havana.game.games.wobblesquabble.WobbleSquabbleStatus;
import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class PT_STATUS extends MessageComposer {
    private final WobbleSquabbleStatus[] statuses;

    public PT_STATUS(WobbleSquabblePlayer wsPlayer1, WobbleSquabblePlayer wsPlayer2) {
        this.statuses = new WobbleSquabbleStatus[2];
        this.statuses[0] = new WobbleSquabbleStatus(wsPlayer1.getPosition(), wsPlayer1.getBalance(), wsPlayer1.getMove(), wsPlayer1.isHit());
        this.statuses[1] = new WobbleSquabbleStatus(wsPlayer2.getPosition(), wsPlayer2.getBalance(), wsPlayer2.getMove(), wsPlayer2.isHit());
    }

    @Override
    public void compose(NettyResponse response) {
        for (int i = 0; i < 2; i++) {
            WobbleSquabbleStatus wsStatus = this.statuses[i];

            response.writeInt(wsStatus.getPosition());
            response.writeInt(wsStatus.getBalance());
            response.writeInt(wsStatus.getMove().getId());
            response.writeBool(wsStatus.isHit());
        }
    }

    @Override
    public short getHeader() {
        return 118;
    }
}
