package org.alexdev.havana.messages.outgoing.inventory;

import org.alexdev.havana.game.inventory.Inventory;
import org.alexdev.havana.game.item.Item;
import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

import java.util.Map;

public class INVENTORY extends MessageComposer {
    private final Inventory inventory;
    private final Map<Integer, Item> casts;

    public INVENTORY(Inventory inventory, Map<Integer, Item> casts) {
        this.inventory = inventory;
        this.casts = casts;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.casts.size());

        for (var kvp : this.casts.entrySet()) {
            this.inventory.serialise(response, kvp.getValue(), kvp.getKey());
        }

        response.writeInt(this.inventory.getDisplayedItems().size());
    }

    @Override
    public short getHeader() {
        return 140; // "BL"
    }
}
