package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.item.displayName
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object MyFood {
    val carrot = ItemStack(Material.CARROT).apply {
        displayName = "&2にんじん"
    }
}
