package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.item.displayName
import com.github.syari.spigot.api.string.toColor
import com.github.usamaru64.orenomeshi.CustomFood.CustomFood
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
object EventListener {
    fun register() {
        plugin.events {
            cancelEventIf<FoodLevelChangeEvent> {
                CustomFood.customfoodname.keys.contains(it.item?.displayName)
            }
            event<PlayerItemConsumeEvent> {
                val player = it.player
                val nowhunger = player.foodLevel
                val nowsaturation = player.saturation
                val name = it.item.displayName
                player.sendMessage("味見したのは$name&fです".toColor())
                player.sendMessage("${CustomFood.customfoodname}")
                player.sendMessage("${CustomFood.getName(name)}&fを出しました".toColor())
                when (name) {
                    CustomFood.getName(name)?.name?.toColor() -> {
                        player.saturation = nowsaturation + CustomFood.getName(name)?.saturation!!
                        player.foodLevel = nowhunger + CustomFood.getName(name)?.feed!!
                        player.sendMessage("&f$name &fを食べた".toColor())
                        player.addPotionEffect(PotionEffect(PotionEffectType.LUCK, 120, 5))
                    }
                }
            }
        }
    }
}
