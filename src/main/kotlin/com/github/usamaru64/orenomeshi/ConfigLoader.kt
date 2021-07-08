package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.config.configDirectory
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigIntDataType
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.command.CommandSender

object ConfigLoader {
    var id = "carrot"
    var feed = 1
    var saturation = 1
    var name = "&2にんじん"
    fun load(sender: CommandSender) {
        plugin.configDirectory(sender, "food") {
            id = file.nameWithoutExtension
            feed = get("feed", ConfigIntDataType, 1)
            saturation = get("saturation", ConfigIntDataType, 1)
            name = get("name", ConfigDataType.String, "&2にんじん")
        }
    }
}
