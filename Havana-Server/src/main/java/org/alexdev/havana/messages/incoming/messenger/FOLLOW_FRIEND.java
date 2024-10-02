package org.alexdev.havana.messages.incoming.messenger;

import org.alexdev.havana.game.messenger.MessengerUser;
import org.alexdev.havana.game.player.Player;
import org.alexdev.havana.game.player.PlayerManager;
import org.alexdev.havana.game.room.Room;
import org.alexdev.havana.game.room.RoomManager;
import org.alexdev.havana.messages.outgoing.messenger.FOLLOW_ERROR;
import org.alexdev.havana.messages.outgoing.messenger.ROOMFORWARD;
import org.alexdev.havana.messages.types.MessageEvent;
import org.alexdev.havana.server.netty.streams.NettyRequest;

public class FOLLOW_FRIEND implements MessageEvent {
    private enum FollowErrors {
        NOT_FRIEND(0),
        OFFLINE(1),
        ON_HOTELVIEW(2),
        NO_CREEPING_ALLOWED(3);

        private int id;

        FollowErrors(int id) {
            this.id = id;
        }

        public int getErrorId(){
            return id;
        }
    }

    @Override
    public void handle(Player player, NettyRequest reader) throws Exception {
        int friendId = reader.readInt();

        if (!player.getMessenger().hasFriend(friendId)) {
            player.send(new FOLLOW_ERROR(FollowErrors.NOT_FRIEND.getErrorId())); // Not their friend
            return;
        }

        Player friend = PlayerManager.getInstance().getPlayerById(friendId);

        if (friend == null) {
            player.send(new FOLLOW_ERROR(FollowErrors.OFFLINE.getErrorId())); // Friend is not online
            return;
        }

        if (friend.getRoomUser().getRoom() == null || !new MessengerUser(friend.getDetails()).canFollowFriend(player)) {
            player.send(new FOLLOW_ERROR(FollowErrors.ON_HOTELVIEW.getErrorId())); // Friend is on hotelview
            return;
        }

        if (!friend.getDetails().doesAllowStalking()) {
            player.send(new FOLLOW_ERROR(FollowErrors.NO_CREEPING_ALLOWED.getErrorId())); // Friend does not allow stalking
            return;
        }

        Room friendRoom = friend.getRoomUser().getRoom();
        player.getMessenger().hasFollowed(friendRoom);
        friendRoom.forward(player, false);
    }
}
