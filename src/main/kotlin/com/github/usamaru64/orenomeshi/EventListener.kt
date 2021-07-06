package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.event.events
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
object EventListener {
    fun register() {
        plugin.events {
            event<PlayerItemConsumeEvent> {
                when (val player = it.player) {
                    MyFood.carrot -> {
                        player.addPotionEffect(PotionEffect(PotionEffectType.LUCK, 120, 5))
                    }
                }
            }
        }
    }
}
