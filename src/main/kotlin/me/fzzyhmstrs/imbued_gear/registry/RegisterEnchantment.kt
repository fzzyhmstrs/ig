package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.amethyst_imbuement.AI
import me.fzzyhmstrs.amethyst_imbuement.LOGGER
import me.fzzyhmstrs.fzzy_core.coding_util.AbstractConfigDisableEnchantment
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.scepter.BankaiAugment
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object RegisterEnchantment {

    private var regEnchant: MutableMap<String, Enchantment> = mutableMapOf()

    val BANKAI = BankaiAugment().also{regEnchant["bankai"] = it}

    fun registerAll(){
        for (k in regEnchant.keys){
            val enchant = regEnchant[k]
            val id = IG.identity( k)
            Registry.register(Registries.ENCHANTMENT, id, enchant)
        }
        for (enchant in regEnchant) {
            val e = enchant.value
            val id = Identifier(AI.MOD_ID, enchant.key)
            if (e is AbstractConfigDisableEnchantment) {
                if (!e.isEnabled()) {
                    LOGGER.info("Augment $id is set as disabled in the configs!")
                }
            }
            if (e is ScepterAugment) {
                AugmentHelper.registerAugmentStat(e)
                if (!AugmentHelper.getAugmentEnabled(id.toString())) {
                    LOGGER.info("Augment $id is set as disabled in the configs!")
                }
            }
        }
    }
}