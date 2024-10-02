package org.alexdev.havana.game.navigator;

import org.alexdev.havana.dao.mysql.NavigatorDao;
import org.alexdev.havana.game.player.PlayerRank;
import org.alexdev.havana.util.config.GameConfiguration;

import java.util.*;

public class NavigatorManager {
    private static NavigatorManager instance;
    private final HashMap<Integer, NavigatorCategory> categoryMap;
    private final Map<Integer, NavigatorStyle> navigatorStyles;

    private NavigatorManager() {
        this.categoryMap = NavigatorDao.getCategories();
        this.navigatorStyles = NavigatorDao.getNavigatorStyles();
        //NavigatorDao.resetBadPrivateRoomCategories();
    }

    /**
     * Get all categories by the parent id.
     *
     * @param parentId the parent id of the categories
     * @param rank
     * @return the list of categories
     */
    public List<NavigatorCategory> getCategoriesByParentId(int parentId, PlayerRank rank) {
        List<NavigatorCategory> categories = new ArrayList<>();

        boolean hideEmptyPublicRoomCategories = GameConfiguration.getInstance().getBoolean("navigator.hide.empty.public.categories");

        for (NavigatorCategory category : this.categoryMap.values()) {
            if (hideEmptyPublicRoomCategories) {
                if (category.isPublicSpaces() && category.getRoomCount() <= 0) {
                    continue;
                }
            }

            if (category.getMinimumRoleAccess().getRankId() > rank.getRankId()) {
                continue;
            }

            if (category.getParentId() != parentId) {
                continue;
            }

            categories.add(category);
        }

        categories.sort(Comparator.comparingInt(NavigatorCategory::getOrderId));
        return categories;
    }

    /**
     * Get the {@link NavigatorCategory} by id
     *
     * @param categoryId the id of the category
     * @return the category instance
     */
    public NavigatorCategory getCategoryById(int categoryId) {
        if (this.categoryMap.containsKey(categoryId)) {
            return this.categoryMap.get(categoryId);
        }

        return null;
    }

    /**
     * Get the map of navigator categories
     *
     * @return the list of categories
     */
    public HashMap<Integer, NavigatorCategory> getCategories() {
        return this.categoryMap;
    }

    /**
     * Get the navigator style by room id, will give default style if the room id doesn't exist.
     *
     * @param roomId the room id
     * @return the style
     */
    public NavigatorStyle getNavigatorStyle(int roomId) {
        if (this.navigatorStyles.containsKey(roomId)) {
            return this.navigatorStyles.get(roomId);
        }

        return null;//new NavigatorStyle(-1, "officialrooms_fr/percy_jackson_nav.png", 1);
    }

    /**
     * Get instance of {@link NavigatorManager}
     *
     * @return the manager instance
     */
    public static NavigatorManager getInstance() {
        if (instance == null) {
            instance = new NavigatorManager();
        }

        return instance;
    }

    /**
     * Reloads the singleton for the {@link NavigatorManager}.
     */
    public static void reset() {
        instance = null;
        getInstance();
    }
}
