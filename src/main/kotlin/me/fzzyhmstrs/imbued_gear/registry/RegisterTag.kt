package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.AC
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterTier
import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.imbued_gear.IG
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

@Suppress("unused")
object RegisterTag {

    val TIER_4_SPELL_SCEPTERS =  FzzyPort.ITEM.tagOf(Identifier(AC.MOD_ID,"tier_four_spell_scepters"))
    val FOUR = ScepterTier(TIER_4_SPELL_SCEPTERS,4)
    val COSMOS_SCEPTERS =  FzzyPort.ITEM.tagOf(IG.identity("cosmos_scepters"))

    val CELESTIAL_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("celestial_gear"))
    val CHAMPIONS_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("champions_gear"))
    val ELEMENTALISTS_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("elementalists_gear"))
    val ETERNITY_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("eternity_gear"))
    val HUNTERS_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("hunters_gear"))
    val LICH_KINGS_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("lich_kings_gear"))
    val SCHOLARS_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("scholars_gear"))
    val SPELLBLADES_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("spellblades_gear"))
    val VOID_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("void_gear"))
    val WARRIORS_GEAR =  FzzyPort.ITEM.tagOf(IG.identity("warriors_gear"))

    val ELEMENTAL = TagKey.of(RegistryKeys.DAMAGE_TYPE, IG.identity("elemental"))

    val SPELL_ATTRIBUTE_ENCHANTS = FzzyPort.ENCHANTMENT.tagOf(IG.identity("spell_attribute_enchantments"))
    fun registerAll(){}
}
