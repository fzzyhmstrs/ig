package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.AC
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterTier
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

@Suppress("unused")
object RegisterTag {

    val TIER_4_SPELL_SCEPTERS: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier(AC.MOD_ID,"tier_four_spell_scepters"))
    val FOUR = ScepterTier(TIER_4_SPELL_SCEPTERS,4)
    fun registerAll(){}
}
