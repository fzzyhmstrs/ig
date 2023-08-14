package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.amethyst_core.AC
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import java.util.function.Predicate

object ModifierPredicates {

    private val CHAMPIONS_AUGMENTS: TagKey<Enchantment> = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier(AC.MOD_ID,"champions_augments"))

    val CHAMPIONS_PREDICATE = Predicate {id: Identifier -> isInTag(id, CHAMPIONS_AUGMENTS)}

    private fun isInTag(id: Identifier, tag: TagKey<Enchantment>): Boolean{
        val augment = Registries.ENCHANTMENT.get(id)?:return false
        val opt = Registries.ENCHANTMENT.getEntry(Registries.ENCHANTMENT.getRawId(augment))
        var bl = false
        opt.ifPresent { entry -> bl = entry.isIn(tag) }
        return bl
    }
}