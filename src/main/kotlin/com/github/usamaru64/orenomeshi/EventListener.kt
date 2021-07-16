package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.item.displayName
import com.github.syari.spigot.api.message.sendChatMessage
import com.github.syari.spigot.api.sound.playSound
import com.github.usamaru64.orenomeshi.CustomFood.CustomFood
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect

object EventListener {
    fun register() {
        plugin.events {
            event<PlayerItemConsumeEvent> { it ->
                val player = it.player
                val name = it.item.displayName
                val food = CustomFood.getByName(name)
                if (food != null) {
                    it.isCancelled = true
                    it.item.amount += -1
                    player.saturation += food.saturation
                    player.foodLevel += food.feed
                    food.effect.forEach {
                        player.addPotionEffect(PotionEffect(it, 120, 5))
                    }
                    player.playSound("entity.player.burp")
                    player.sendChatMessage("&f$name &fを食べた")
//                    player.addPotionEffect(PotionEffect(PotionEffectType.LUCK, 120, 5))
                }
            }
        }
    }
}
