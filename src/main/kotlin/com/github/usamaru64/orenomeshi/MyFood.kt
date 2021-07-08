package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.item.itemStack
import com.github.usamaru64.orenomeshi.CustomFood.CustomFood
import org.bukkit.Material

object MyFood {
    var carrot = itemStack(Material.CARROT)
    fun load() {
        CustomFood.customfood.keys.forEach {
            val name = it
            carrot = itemStack(Material.CARROT, displayName = "${CustomFood.getId(name)}")
        }
    }
}
