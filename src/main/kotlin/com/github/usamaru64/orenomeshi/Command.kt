package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.command.tab.CommandTabArgument.Companion.argument
import com.github.syari.spigot.api.item.itemStack
import com.github.syari.spigot.api.string.toColor
import com.github.usamaru64.orenomeshi.CustomFood.CustomFood
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.Material
import org.bukkit.entity.Player

@kotlin.ExperimentalStdlibApi
object Command {
    fun register() {
        plugin.command("orenomeshi") {
            aliases = listOf("onm", "usamarusama")
            tab {
                argument {
                    addAll("get", "reload")
                }
                argument("get **") {
                    addAll(CustomFood.container.byId.keys)
                }
                execute {
                    val player = sender as? Player ?: return@execute sender.sendMessage("貴様、人ではないな")
                    when (args.lowerOrNull(0)) {
                        "get" -> {
                            val id = args.getOrNull(1)
                            if (id == null) {
                                player.sendMessage("idを入力してね")
                                return@execute
                            } else if (id == "tomato") {
                                player.sendMessage("&4&lマイクラにトマトは無い！！！".toColor())
                                return@execute
                            }
                            val food = CustomFood.getById(id)
                            if (food != null) {
                                val name = food.name
                                val quantity = args.getOrNull(2)?.toIntOrNull() ?: 1
                                player.sendMessage("$name&fを返したよ".toColor())
                                player.inventory
                                    .addItem(itemStack(Material.CARROT, name) { amount = quantity })
                            } else {
                                player.sendMessage("そんなアイテムは無い")
                            }
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
