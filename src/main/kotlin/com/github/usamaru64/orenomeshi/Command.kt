package com.github.usamaru64.orenomeshi

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.command.tab.CommandTabArgument.Companion.argument
import com.github.syari.spigot.api.item.itemStack
import com.github.syari.spigot.api.message.sendChatMessage
import com.github.usamaru64.orenomeshi.CustomFood.CustomFood
import com.github.usamaru64.orenomeshi.Main.Companion.plugin
import org.bukkit.entity.Player

@kotlin.ExperimentalStdlibApi
object Command {
    fun register() {
        plugin.command("orenomeshi") {
            aliases = listOf("onm", "usamarusama")
            tab {
                argument {
                    addAll("get", "stats", "reload", "help", "?")
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
                                player.sendChatMessage("&7[&aOreNoMeshi&7] &fアイテムのファイル名を入力してください")
                                return@execute
                            }
                            val food = CustomFood.getById(id)
                            if (food != null) {
                                val name = food.name
                                val type = food.type
                                val lore = food.lore
                                val quantity = args.getOrNull(2)?.toIntOrNull() ?: 1
                                player.sendChatMessage("&7[&aOreNoMeshi&7]  &f$name&f を ${quantity}個 取得しました")
                                player.inventory
                                    .addItem(itemStack(type, name, lore = lore) { amount = quantity })
                            } else {
                                player.sendChatMessage("&7[&aOreNoMeshi&7] &fアイテムが存在しません")
                            }
                        }
                        "stats" -> {
                            player.sendChatMessage("&7[&aOreNoMeshi&7] &6満腹度&7: &f${player.foodLevel} &e隠し満腹度&7: &f${player.saturation}")
                        }
                        "reload" -> {
                            ConfigLoader.load(sender)
                            player.sendChatMessage("&7[&aOreNoMeshi&7] &fリロードが完了しました")
                        }
                        else -> {
                            player.sendChatMessage("&7[&aOreNoMeshi&7]")
                            player.sendChatMessage("&3/onm get [file] [amount] &7: &fこのプラグインで追加されたアイテムを取得します")
                            player.sendChatMessage("&3/onm stats &7: &f現在の満腹度と隠し満腹度を表示します")
                            player.sendChatMessage("&3/onm reload &7: &fコンフィグファイルを再読み込みします")
                            player.sendChatMessage("&3/onm help &7: &fこのヘルプを表示します")
                        }
                    }
                }
            }
        }
    }
}
