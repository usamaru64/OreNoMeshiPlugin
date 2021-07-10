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
                    addAll(CustomFood.customfood.keys)
                }
                execute {
                    val player = sender as? Player ?: return@execute sender.sendMessage("貴様、人ではないな")
                    when (args.lowerOrNull(0)) {
                        "get" -> {
                            val id = args.getOrNull(1)
                            val name = CustomFood.getId(id)?.name
                            player.sendMessage("入力した値は${id}でした")
                            when (args.getOrNull(1)) {
                                null -> {
                                    player.sendMessage("idを入力してね")
                                }
                                "tomato" -> {
                                    player.sendMessage("&4&lマイクラにトマトは無い！！！".toColor())
                                }
                                CustomFood.getId(id)?.id -> {
                                    player.sendMessage("$name&fを返したよ".toColor())
                                    player.inventory
                                        .addItem(itemStack(Material.CARROT, "$name".toColor()))
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
