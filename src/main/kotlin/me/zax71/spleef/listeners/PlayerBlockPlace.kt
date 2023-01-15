package me.zax71.spleef.listeners

import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerBlockPlaceEvent

class PlayerBlockPlace : EventListener<PlayerBlockPlaceEvent> {
    override fun eventType(): Class<PlayerBlockPlaceEvent> {
        return PlayerBlockPlaceEvent::class.java
    }

    override fun run(event: PlayerBlockPlaceEvent): EventListener.Result {
        event.isCancelled = true

        return EventListener.Result.SUCCESS
    }
}