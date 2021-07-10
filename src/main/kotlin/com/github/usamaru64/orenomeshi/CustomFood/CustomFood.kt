package com.github.usamaru64.orenomeshi.CustomFood

data class CustomFood(
    val id: String,
    val feed: Int,
    val saturation: Int,
    val name: String
) {
    companion object {

        var customfood = mapOf<String, CustomFood>()
            set(value) {
                field = value
                customfoodname = value.entries.associate { it.value.name to it.value }
            }

        var customfoodname = mapOf<String, CustomFood>()
            private set

        fun getId(id: String?) = customfood[id]

        fun getName(name: String?) = customfoodname[name]
    }
}
