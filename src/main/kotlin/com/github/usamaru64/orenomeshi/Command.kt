package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.command.tab.CommandTabArgument.Companion.argument
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.entity.Player

object Command {
    fun register() {
        plugin.command("orenomeshi") {
            aliases = listOf("onm", "usamarusama")
            tab {
                argument {
                    addAll("carrot", "reload")
                }
                execute {
                    val player = sender as? Player ?: return@execute sender.sendMessage("貴様、人ではないな")
                    when (args.lowerOrNull(0)) {
                        "carrot" -> {
                            player.inventory.addItem(MyFood.carrot)
                        }
                        "reload" -> {
                            ConfigLoader.load(sender)
                            player.sendMessage("りろーどするよ")
                        }
                        else -> {
                            player.sendMessage("carrotしかありません")
                        }
                    }
                }
            }
        }
    }
}
