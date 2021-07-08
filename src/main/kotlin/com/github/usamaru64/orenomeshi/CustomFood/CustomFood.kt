package com.github.usamaru64.orenomeshi.CustomFood

class CustomFood(
    val id: String,
    val feed: Int,
    val saturation: Int,
    val name: String
) {
    companion object {
        var customfood = mapOf<String, CustomFood>()

        fun getId(name: String?) = customfood[name]

        val ID get() = customfood.keys
    }
}
