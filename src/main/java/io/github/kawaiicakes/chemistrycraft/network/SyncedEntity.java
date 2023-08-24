package io.github.kawaiicakes.chemistrycraft.network;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Classes implementing this interface agree to provide methods allowing for syncing packets with one or more clients
 * when server & client should be synced. Originally written to fix energy not automatically syncing with clients on join.
 */
public interface SyncedEntity {
    /**
     * Implementing classes must pass an instance of themselves to the <code>MinecraftForge.EVENT_BUS</code> and
     * annotate this method using <code>@SubscribeEvent</code>.
     * @param event The PlayerLoggedInEvent fired whenever a player joins the server.
     */
    void syncPacketsOnConnect(PlayerEvent.PlayerLoggedInEvent event);
}
