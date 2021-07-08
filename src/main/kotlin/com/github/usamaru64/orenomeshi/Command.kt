package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.command.tab.CommandTabArgument.Companion.argument
import com.github.syari.spigot.api.string.toColor
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.entity.Player

object Command {
    fun register() {
        plugin.command("orenomeshi") {
            aliases = listOf("onm", "usamarusama")
            tab {
                argument {
                    addAll("get", "reload")
                }
                argument("get **") {
                    addAll(ConfigLoader.id)
                }
                execute {
                    val player = sender as? Player ?: return@execute sender.sendMessage("貴様、人ではないな")
                    when (args.lowerOrNull(0)) {
                        "get" -> {

                            when (args.getOrNull(1)) {
                                null -> {
                                    player.sendMessage("idを入力してね")
                                }
                                "tomato" -> {
                                    player.sendMessage("&4&lマイクラにトマトは無い！！！".toColor())
                                }
                                ConfigLoader.id -> {
                                    player.inventory.addItem(MyFood.carrot)
                                }
                                else -> {
                                    player.sendMessage("そんなアイテムは無い")
                                }
                            }
                        }
                        "reload" -> {
                            ConfigLoader.load(sender)
                            MyFood.load()
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
