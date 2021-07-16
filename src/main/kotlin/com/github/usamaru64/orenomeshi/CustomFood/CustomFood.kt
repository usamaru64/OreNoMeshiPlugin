package com.github.usamaru64.orenomeshi.CustomFood

import org.bukkit.Material
import org.bukkit.potion.PotionEffectType

data class CustomFood(
    val id: String,
    val feed: Int,
    val saturation: Int,
    val name: String,
    val type: Material,
    val lore: List<String>,
    val effect: List<PotionEffectType>
) {
    companion object {
        val container = CustomFoodContainer()

        fun getById(id: String?) = container.byId[id]

        fun getByName(name: String?) = container.byName[name]
    }
}
class CustomFoodContainer {
    var byId: Map<String, CustomFood> = mapOf()
        private set

    var byName: Map<String, CustomFood> = mapOf()
        private set

    var foods: Set<CustomFood>
        get() = byId.values.toSet()
        set(value) {
            this.byId = value.associateBy { it.id }
            this.byName = value.associateBy { it.name }
        }
}
