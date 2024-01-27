package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.entity.*
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.world.World

object RegisterEntity {

    val CELESTIAL_TRIDENT_ENTITY: EntityType<CelestialTridentEntity> = FzzyPort.ENTITY_TYPE.register(
        IG.identity( "celestial_trident"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<CelestialTridentEntity>, world: World ->
            CelestialTridentEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )

    val CHAMPIONS_TRIDENT_ENTITY: EntityType<ChampionsTridentEntity> = FzzyPort.ENTITY_TYPE.register(
        IG.identity( "champions_trident"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<ChampionsTridentEntity>, world: World ->
            ChampionsTridentEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )

    val CELESTIAL_TRIDENT_AVATAR_ENTITY: EntityType<CelestialTridentAvatarEntity> = FzzyPort.ENTITY_TYPE.register(
        IG.identity( "celestial_trident_avatar"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<CelestialTridentAvatarEntity>, world: World ->
            CelestialTridentAvatarEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )

    val IMBUED_ARROW_ENTITY: EntityType<ImbuedArrowEntity> = FzzyPort.ENTITY_TYPE.register(
        IG.identity( "imbued_arrow"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<ImbuedArrowEntity>, world: World ->
            ImbuedArrowEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )

    val CRYSTALLINE_ARROW_ENTITY: EntityType<CrystallineArrowEntity> = FzzyPort.ENTITY_TYPE.register(
        IG.identity( "crystalline_arrow"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<CrystallineArrowEntity>, world: World ->
            CrystallineArrowEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )

    val BAITING_ARROW_ENTITY: EntityType<BaitingArrowEntity> = FzzyPort.ENTITY_TYPE.register(
        IG.identity( "baiting_arrow"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<BaitingArrowEntity>, world: World ->
            BaitingArrowEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )


    fun registerAll(){
    }


}