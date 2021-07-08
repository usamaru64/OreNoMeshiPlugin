package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.item.displayName
import com.github.syari.spigot.api.item.itemStack
import org.bukkit.Material

object MyFood {
    var carrot = itemStack(Material.CARROT)
    fun load() {
        carrot = itemStack(Material.CARROT) {
            displayName = ConfigLoader.name
        }
    }
}
