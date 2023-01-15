package me.zax71.spleef.listeners

import net.minestom.server.coordinate.Pos
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerMoveEvent

class PlayerMove : EventListener<PlayerMoveEvent> {
    override fun eventType(): Class<PlayerMoveEvent> {
        return PlayerMoveEvent::class.java
    }

    override fun run(event: PlayerMoveEvent): EventListener.Result {

        val pos = event.newPosition
        val player = event.player
        if (pos.y < -1) {
            player.teleport(Pos(20.5, 22.0, 0.5, 90.0F, 64.0F))
        }
        return EventListener.Result.SUCCESS
    }
}