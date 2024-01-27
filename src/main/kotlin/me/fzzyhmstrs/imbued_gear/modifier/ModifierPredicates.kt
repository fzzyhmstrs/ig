@file:Suppress("unused")

package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.amethyst_core.AC
import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import net.minecraft.util.Identifier
import java.util.function.Predicate

object ModifierPredicates {

    private val CHAMPIONS_AUGMENTS = FzzyPort.ENCHANTMENT.tagOf(Identifier(AC.MOD_ID,"champions_augments"))

    val CHAMPIONS_PREDICATE = Predicate {id: Identifier -> FzzyPort.ENCHANTMENT.registry().getEntry(FzzyPort.ENCHANTMENT.get(id)).isIn(CHAMPIONS_AUGMENTS)}
}