package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.item.displayName
import com.github.syari.spigot.api.string.toColor
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
object EventListener {
    fun register() {
        plugin.events {
            cancelEventIf<FoodLevelChangeEvent> {
                it.item == MyFood.carrot
            }
            event<PlayerItemConsumeEvent> {
                val player = it.player
                val nowhunger = player.foodLevel
                val nowsaturation = player.saturation
                when (it.item) {
                    MyFood.carrot -> {
                        player.saturation = nowsaturation + ConfigLoader.saturation
                        player.foodLevel = nowhunger + ConfigLoader.feed
                        player.sendMessage("&f${it.item.displayName} &fを食べた".toColor())
                        player.addPotionEffect(PotionEffect(PotionEffectType.LUCK, 120, 5))
                    }
                }
            }
        }
    }
}
