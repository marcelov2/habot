package org.alexdev.havana.messages.outgoing.pets;

import org.alexdev.havana.game.pets.Pet;
import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class PETSTAT extends MessageComposer {
    private final Pet pet;

    public PETSTAT(Pet pet) {
        this.pet = pet;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.pet.getRoomUser().getInstanceId());
        response.writeInt(this.pet.getAge());
        response.writeInt(this.pet.getHunger());
        response.writeInt(this.pet.getThirst());
        response.writeInt(this.pet.getHappiness());
        response.writeInt(this.pet.getDetails().getNatureNegative());
        response.writeInt(this.pet.getDetails().getNaturePositive());
    }

    @Override
    public short getHeader() {
        return 210; // "CR"
    }
}
