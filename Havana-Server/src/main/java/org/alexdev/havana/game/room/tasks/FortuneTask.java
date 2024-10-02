package org.alexdev.havana.game.room.tasks;

import org.alexdev.havana.dao.mysql.ItemDao;
import org.alexdev.havana.game.GameScheduler;
import org.alexdev.havana.game.item.Item;

import java.util.concurrent.ThreadLocalRandom;

public class FortuneTask implements Runnable {
    private final Item fortune;

    public FortuneTask(Item item) {
        this.fortune = item;
    }

    public void run() {
        if (!fortune.getRequiresUpdate()) {
            return;
        }

        // Set random number that gets picked up by the FortuneTask
        int randomNumber = ThreadLocalRandom.current().nextInt(1, 11); // between 1 and 10

        this.fortune.setCustomData(Integer.toString(randomNumber));
        this.fortune.updateStatus();
        this.fortune.setRequiresUpdate(false);
        this.fortune.save();
    }
}
