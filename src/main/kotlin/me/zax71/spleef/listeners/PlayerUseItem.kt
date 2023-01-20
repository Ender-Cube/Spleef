package me.zax71.spleef.listeners

import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.item.Material

class PlayerUseItem : EventListener<PlayerUseItemEvent> {
    override fun eventType(): Class<PlayerUseItemEvent> {
        return PlayerUseItemEvent::class.java
    }

    override fun run(event: PlayerUseItemEvent): EventListener.Result {
        val player = event.player
        val itemStack = event.itemStack

        if (itemStack.material() == Material.BARRIER) {
            player.sendMessage("Quitting to hub")
        }


        return EventListener.Result.SUCCESS
    }
}