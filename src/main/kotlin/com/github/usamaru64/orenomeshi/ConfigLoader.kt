package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.config.configDirectory
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigIntDataType
import com.github.syari.spigot.api.string.toColor
import com.github.usamaru64.orenomeshi.CustomFood.CustomFood
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.Material
import org.bukkit.command.CommandSender

@OptIn(ExperimentalStdlibApi::class)
object ConfigLoader {
    fun load(sender: CommandSender) {
        CustomFood.container.foods = buildSet {
            plugin.configDirectory(sender, "food") {
                val id = file.nameWithoutExtension
                val feed = get("feed", ConfigIntDataType) ?: 0
                val saturation = get("saturation", ConfigIntDataType) ?: 0
                val name = get("name", ConfigDataType.String) ?: id
                val type = get("type", ConfigDataType.Material) ?: Material.CARROT
                val lore = get("lore", ConfigDataType.StringList, false)?.map { it.toColor() } ?: listOf()
                add(CustomFood(id, feed, saturation, name.toColor(), type, lore))
            }
        }
    }
}
