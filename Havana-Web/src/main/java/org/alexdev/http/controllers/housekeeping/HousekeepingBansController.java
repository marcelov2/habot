package org.alexdev.http.controllers.housekeeping;

import org.alexdev.duckhttpd.server.connection.WebConnection;
import org.alexdev.duckhttpd.template.Template;
import org.alexdev.havana.dao.mysql.BanDao;
import org.alexdev.havana.game.player.PlayerDetails;
import org.alexdev.http.Routes;
import org.alexdev.http.game.housekeeping.HousekeepingManager;
import org.alexdev.http.util.SessionUtil;

public class HousekeepingBansController {
    public static void bans(WebConnection client) {
        if (!client.session().getBoolean(SessionUtil.LOGGED_IN_HOUSKEEPING)) {
            client.redirect("/" + Routes.HOUSEKEEPING_PATH);
            return;
        }

        int currentPage = 0;

        if (client.get().contains("page")) {
            currentPage = Integer.parseInt(client.get().getString("page"));
        }

        String sortBy = "banned_at";

        if (client.get().contains("sort")) {
            if (client.get().getString("sort").equals("banned_at") ||
                    client.get().getString("sort").equals("banned_until")) {
                sortBy = client.get().getString("sort");
            }
        }

        Template tpl = client.template("housekeeping/users_bans");
        tpl.set("housekeepingManager", HousekeepingManager.getInstance());

        PlayerDetails playerDetails = (PlayerDetails) tpl.get("playerDetails");

        if (!HousekeepingManager.getInstance().hasPermission(playerDetails.getRank(), "bans")) {
            client.redirect("/" + Routes.HOUSEKEEPING_PATH);
            return;
        }

        tpl.set("pageName", "Bans");
        tpl.set("bans", BanDao.getActiveBans(currentPage, sortBy));
        tpl.set("nextBans", BanDao.getActiveBans(currentPage + 1, sortBy));
        tpl.set("previousBans", BanDao.getActiveBans(currentPage - 1, sortBy));
        tpl.set("page", currentPage);
        tpl.set("sortBy", sortBy);
        tpl.render();

        // Delete alert after it's been rendered
        client.session().delete("alertMessage");
    }
}
