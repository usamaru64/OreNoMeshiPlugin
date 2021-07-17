package com.github.usamaru64.orenomeshi

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun onEnable() {
        ConfigLoader.load(server.consoleSender)
        EventListener.register()
        Command.register()
    }
}
