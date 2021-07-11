package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.item.displayName
import com.github.syari.spigot.api.string.toColor
import com.github.usamaru64.orenomeshi.CustomFood.CustomFood
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.event.entity.EntityPotionEffectEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object EventListener {
    fun register() {
        plugin.events {
            event<PlayerItemConsumeEvent> {
                val hasKey = CustomFood.container.byName.keys.contains(it.item.displayName)
                cancelEventIf<FoodLevelChangeEvent> { hasKey }
                cancelEventIf<EntityPotionEffectEvent> { hasKey }
                val player = it.player
                val name = it.item.displayName
                val food = CustomFood.getByName(name)
                if (food != null) {
                    player.saturation += food.saturation
                    player.foodLevel += food.feed
                    player.sendMessage("&f$name &fを食べた".toColor())
                    player.addPotionEffect(PotionEffect(PotionEffectType.LUCK, 120, 5))
                }
            }
        }
    }
}
