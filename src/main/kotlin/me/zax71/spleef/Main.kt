package me.zax71.spleef

import de.leonhard.storage.Yaml
import me.zax71.spleef.blocks.Sign
import me.zax71.spleef.listeners.*
import net.minestom.server.MinecraftServer
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.extras.MojangAuth
import net.minestom.server.network.packet.client.play.ClientPlayerDiggingPacket
import net.minestom.server.utils.NamespaceID

var config: Yaml = Yaml(
    "config.yml",
    System.getProperty("user.dir"),
)

fun main() {
    // Initialisation
    val minecraftServer = MinecraftServer.init()

    // Register events
    val globalEventHandler = MinecraftServer.getGlobalEventHandler()
    val entityNode = EventNode.type("listeners", EventFilter.ALL)
    entityNode
        .addListener(PlayerBlockPlace())
        .addListener(PlayerLogin())
        .addListener(PlayerMove())
        .addListener(PlayerStartDigging())
        .addListener(PlayerUseItem())
    globalEventHandler.addChild(entityNode)

    // Register block handler for signs
    MinecraftServer.getBlockManager().registerHandler(NamespaceID.from("minecraft:sign")) { Sign() }


    // Piracy = bad
    MojangAuth.init()

    // Start the server
    // Uses port 25565 by default as this is the port Docker uses, only useful to configure when outside a docker container
    minecraftServer.start("0.0.0.0", config.getOrSetDefault("port", 25565))
}