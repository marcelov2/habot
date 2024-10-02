package org.alexdev.havana.game.games.gamehalls.utils;

import org.alexdev.havana.game.player.Player;

public class GameShipMove {
    private Player player;
    private int x;
    private int y;
    private GameShipMoveResult moveResult;
    private GameShip ship;

    public GameShipMove(Player player, int x, int y, GameShipMoveResult moveResult, GameShip ship) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.moveResult = moveResult;
        this.ship = ship;
    }

    public Player getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameShipMoveResult getMoveResult() {
        return moveResult;
    }

    public GameShip getShip() {
        return ship;
    }
}
