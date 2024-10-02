package org.alexdev.havana.game.item.roller;

import org.alexdev.havana.game.entity.Entity;
import org.alexdev.havana.game.item.Item;

import java.util.ArrayList;
import java.util.List;

public class RollerEntry {
    private Item roller;
    private List<RollingData> rollingItems;
    private Entity rollingEntity;

    public RollerEntry(Item roller) {
        this.roller = roller;
        this.rollingItems = new ArrayList<>();
        this.rollingEntity = null;
    }

    public Item getRoller() {
        return roller;
    }

    public List<RollingData> getRollingItems() {
        return rollingItems;
    }

    public Entity getRollingEntity() {
        return rollingEntity;
    }

    public void setRollingEntity(Entity rollingEntity) {
        this.rollingEntity = rollingEntity;
    }
}
