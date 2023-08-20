package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.fzzy_core.registry.ItemModelRegistry
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.model.CelestialTridentEntityModel
import me.fzzyhmstrs.imbued_gear.model.CelestialTridentItemEntityRenderer
import me.fzzyhmstrs.imbued_gear.model.ChampionsTridentEntityModel
import me.fzzyhmstrs.imbued_gear.model.ChampionsTridentItemEntityRenderer
import net.minecraft.client.util.ModelIdentifier

object RegisterItemModel {

    fun registerAll() {
        val celestialModelModes =
            ItemModelRegistry.ModelIdentifierPerModes(ModelIdentifier(IG.MOD_ID, "celestial_trident", "inventory"))
                .withHeld(ModelIdentifier(IG.MOD_ID, "celestial_trident_in_hand", "inventory"), true)
        ItemModelRegistry.registerItemModelId(RegisterTool.CELESTIAL_TRIDENT, celestialModelModes)
        ItemModelRegistry.registerItemEntityModel(
            RegisterTool.CELESTIAL_TRIDENT,
            CelestialTridentItemEntityRenderer,
            RegisterRenderer.CELESTIAL_TRIDENT,
            CelestialTridentEntityModel::class.java
        )

        val championsModelModes =
            ItemModelRegistry.ModelIdentifierPerModes(ModelIdentifier(IG.MOD_ID, "champions_trident", "inventory"))
                .withHeld(ModelIdentifier(IG.MOD_ID, "champions_trident_in_hand", "inventory"), true)
        ItemModelRegistry.registerItemModelId(RegisterTool.CHAMPIONS_TRIDENT, championsModelModes)
        ItemModelRegistry.registerItemEntityModel(
            RegisterTool.CHAMPIONS_TRIDENT,
            ChampionsTridentItemEntityRenderer,
            RegisterRenderer.CHAMPIONS_TRIDENT,
            ChampionsTridentEntityModel::class.java
        )
    }

}