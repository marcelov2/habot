package org.alexdev.havana.messages.incoming.messenger;

import org.alexdev.havana.dao.mysql.MessengerDao;
import org.alexdev.havana.game.player.Player;
import org.alexdev.havana.game.player.PlayerDetails;
import org.alexdev.havana.game.player.PlayerManager;
import org.alexdev.havana.messages.outgoing.messenger.MESSENGER_SEARCH;
import org.alexdev.havana.messages.types.MessageEvent;
import org.alexdev.havana.server.netty.streams.NettyRequest;

import java.util.ArrayList;
import java.util.List;

public class FINDUSER implements MessageEvent {
    @Override
    public void handle(Player player, NettyRequest reader) throws Exception {
        String searchQuery = reader.readString();

        List<Integer> userList = MessengerDao.search(searchQuery.toLowerCase());

        List<PlayerDetails> friends = new ArrayList<>();
        List<PlayerDetails> others = new ArrayList<>();

        for (int userId : userList) {
            if (player.getMessenger().hasFriend(userId)) {
                friends.add(PlayerManager.getInstance().getPlayerData(userId));
            } else {
                others.add(PlayerManager.getInstance().getPlayerData(userId));
            }
        }

        friends.removeIf(playerDetails -> playerDetails.getId() == player.getDetails().getId());

        others.removeIf(playerDetails -> playerDetails.getId() == player.getDetails().getId());
        others.removeIf(playerDetails -> playerDetails.getName().equals("Abigail.Ryan"));

        player.send(new MESSENGER_SEARCH(friends, others));
    }
}
