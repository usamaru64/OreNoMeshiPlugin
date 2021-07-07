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
            event<PlayerItemConsumeEvent> {
                val player = it.player
                val nowhunger = player.foodLevel
                val food = it.item
//                event<FoodLevelChangeEvent> {
//                    food == MyFood.carrot
//                }
                when (food) {
                    MyFood.carrot -> {
                        event<FoodLevelChangeEvent> {
                            it.isCancelled
                        }
                        player.foodLevel = nowhunger + 1
                        player.sendMessage("&f${food.displayName} &fを食べた".toColor())
                        player.addPotionEffect(PotionEffect(PotionEffectType.LUCK, 120, 5))
                    }
                }
            }
        }
    }
}
