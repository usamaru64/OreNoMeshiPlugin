package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.item.displayName
import com.github.syari.spigot.api.message.sendChatMessage
import com.github.usamaru64.orenomeshi.CustomFood.CustomFood
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object EventListener {
    fun register() {
        plugin.events {
//            event<FoodLevelChangeEvent> { it.isCancelled = true }
//            event<EntityPotionEffectEvent> { it.isCancelled = true }
            event<PlayerItemConsumeEvent> {
//                val hasKey = CustomFood.container.byName.keys.contains(it.item.displayName)
                val player = it.player
                val name = it.item.displayName
                val food = CustomFood.getByName(name)
                if (food != null) {
                    it.isCancelled = true
                    player.saturation += food.saturation
                    player.foodLevel += food.feed
                    player.inventory.itemInMainHand.amount += -1
                    player.sendChatMessage("&f$name &fを食べた")
                    player.addPotionEffect(PotionEffect(PotionEffectType.LUCK, 120, 5))
                }
            }
        }
    }
}
