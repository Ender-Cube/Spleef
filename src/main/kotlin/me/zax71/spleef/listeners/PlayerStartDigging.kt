package me.zax71.spleef.listeners

import me.zax71.spleef.instances.Arena1
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerStartDiggingEvent
import net.minestom.server.instance.block.Block
import net.minestom.server.sound.SoundEvent

class PlayerStartDigging : EventListener<PlayerStartDiggingEvent> {
    override fun eventType(): Class<PlayerStartDiggingEvent> {
        return PlayerStartDiggingEvent::class.java
    }

    override fun run(event: PlayerStartDiggingEvent): EventListener.Result {

        val player = event.player
        val blockPos = event.blockPosition
        Arena1.INSTANCE?.setBlock(blockPos, Block.AIR)
        val snowBreak = Sound.sound(Key.key("block.snow.break"), Sound.Source.BLOCK, 1f, 1f)
        player.playSound(snowBreak)

        return EventListener.Result.SUCCESS
    }
}