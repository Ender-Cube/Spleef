package me.zax71.spleef.listeners

import me.zax71.spleef.instances.Arena1
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.GameMode
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.item.Enchantment
import net.minestom.server.item.ItemHideFlag
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class PlayerLogin : EventListener<PlayerLoginEvent> {
    /*
    Use to pass variables in


    private final Instance lobby;
    public PlayerLogin(Instance lobby){
        this.playerManager = manager;
        this.lobby = lobby;
    }
     */
    override fun eventType(): Class<PlayerLoginEvent> {
        return PlayerLoginEvent::class.java
    }

    override fun run(event: PlayerLoginEvent): EventListener.Result {
        // When event is run
        val player = event.player

        // Spawning
        event.setSpawningInstance(Arena1.INSTANCE!!)
        player.respawnPoint = Pos(0.0, 1.0, 0.0)
        player.gameMode = GameMode.SURVIVAL

        // Adding compass to inventory
        val inventory = player.inventory

        val barrier = ItemStack.builder(Material.BARRIER)
            .displayName(Component.text("Quit to hub").decoration(TextDecoration.ITALIC, false)).build()
        inventory.setItemStack(8, barrier)

        val shovel = ItemStack.builder(Material.GOLDEN_SHOVEL)
            .meta { meta ->
                meta.enchantment(Enchantment.EFFICIENCY, 100)
                meta.hideFlag(ItemHideFlag.HIDE_ENCHANTS, ItemHideFlag.HIDE_ATTRIBUTES)
            }
            .build()
        inventory.setItemStack(0, shovel)


        inventory.addInventoryCondition { playerInventory, slot, _, inventoryConditionResult ->
            // Moving items is bad
            inventoryConditionResult.isCancel = true

            if (slot == 8) {
                println("Used Barrier")
            }

        }


        return EventListener.Result.SUCCESS
    }
}