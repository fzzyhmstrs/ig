package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.AC
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterTier
import me.fzzyhmstrs.imbued_gear.IG
import net.minecraft.entity.damage.DamageType
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

@Suppress("unused")
object RegisterTag {

    val TIER_4_SPELL_SCEPTERS: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier(AC.MOD_ID,"tier_four_spell_scepters"))
    val FOUR = ScepterTier(TIER_4_SPELL_SCEPTERS,4)
    val COSMOS_SCEPTERS: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("cosmos_scepters"))

    val CELESTIAL_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("celestial_gear"))
    val CHAMPIONS_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("champions_gear"))
    val ELEMENTALISTS_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("elementalists_gear"))
    val ETERNITY_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("eternity_gear"))
    val LICH_KINGS_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("lich_kings_gear"))
    val SCHOLARS_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("scholars_gear"))
    val SPELLBLADES_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("spellblades_gear"))
    val VOID_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("void_gear"))
    val WARRIORS_GEAR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, IG.identity("warriors_gear"))

    val ELEMENTAL: TagKey<DamageType> = TagKey.of(RegistryKeys.DAMAGE_TYPE, IG.identity("elemental"))
    fun registerAll(){}
}
