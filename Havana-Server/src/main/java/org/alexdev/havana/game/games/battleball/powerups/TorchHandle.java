package org.alexdev.havana.game.games.battleball.powerups;

import org.alexdev.havana.game.games.battleball.BattleBallGame;
import org.alexdev.havana.game.games.battleball.BattleBallTile;
import org.alexdev.havana.game.games.battleball.enums.BattleBallColourState;
import org.alexdev.havana.game.games.battleball.enums.BattleBallTileState;
import org.alexdev.havana.game.games.player.GamePlayer;
import org.alexdev.havana.game.games.player.GameTeam;
import org.alexdev.havana.game.games.utils.TileUtil;
import org.alexdev.havana.game.pathfinder.Position;
import org.alexdev.havana.game.room.Room;

import java.util.ArrayList;
import java.util.List;

public class TorchHandle {
    public static void handle(BattleBallGame game, GamePlayer gamePlayer, Room room) {
        GameTeam gameTeam = gamePlayer.getTeam();
        List<BattleBallTile> tilesToUpdate = new ArrayList<>();

        Position nextPosition = gamePlayer.getPlayer().getRoomUser().getPosition();

        while (TileUtil.isValidGameTile(gamePlayer, (BattleBallTile) game.getTile(nextPosition.getX(), nextPosition.getY()), false)) {
            nextPosition = nextPosition.getSquareInFront();

            if (!TileUtil.isValidGameTile(gamePlayer, (BattleBallTile) game.getTile(nextPosition.getX(), nextPosition.getY()), false)) {
                break;
            }

            tilesToUpdate.add((BattleBallTile) game.getTile(nextPosition.getX(), nextPosition.getY()));

        }

        for (BattleBallTile tile : tilesToUpdate) {
            if (tile.getState() == BattleBallTileState.SEALED) {
                continue;
            }

            if (tile.getColour() == BattleBallColourState.DISABLED) {
                continue;
            }

            BattleBallTileState state = tile.getState();
            BattleBallColourState colour = tile.getColour();

            if (state == BattleBallTileState.DEFAULT) {
                state = BattleBallTileState.TOUCHED; // Don't make it 4 hits, make it 3
            }


            BattleBallTileState newState = null;

            if (colour.getColourId() != gameTeam.getId()) {
                newState = BattleBallTileState.CLICKED;
            } else {
                newState = BattleBallTileState.getStateById(state.getTileStateId() + 1);
            }

            BattleBallColourState newColour = BattleBallColourState.getColourById(gameTeam.getId());

            tile.getNewPoints(gamePlayer, newState, newColour);

            tile.setColour(newColour);
            tile.setState(newState);

            if (newState == BattleBallTileState.SEALED) {
                tile.checkFill(gamePlayer, game.getFillTilesQueue());
            }

            game.getUpdateTilesQueue().add(tile);
        }
    }
}
