@file:Suppress("MemberVisibilityCanBePrivate")

package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.item.armor.*
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.util.Rarity

@Suppress("unused")
object RegisterArmor {

    internal val regArmor: MutableList<Item> = mutableListOf()

    /*
    garnet <- steel
    glowing <- garnet
    shimmering = base
    celestial <- many
    void mail <- steel
    lapidarists <- steel
    lich-king <- shimmering
    spellblades <- steel
    champions <- ametrine
    warriors <- glowing
    scholars <- shimmering
    elementalists <- garnet
    clerics <- shimmering
    eternity <- N/A
    */

    private fun <T: ArmorItem> register(item: T, name: String): T{
        regArmor.add(item)
        return FzzyPort.ITEM.register(IG.identity(name), item)
    }

    //Celestial Armor -tex done
    val ARCHONS_HELMET = register(ArchonsArmorItem(IgConfig.materials.armor.archon,ArmorItem.Type.HELMET,Item.Settings().rarity(Rarity.EPIC)),"archons_helmet")
    val ARCHONS_CHESTPLATE = register(ArchonsArmorItem(IgConfig.materials.armor.archon,ArmorItem.Type.CHESTPLATE,Item.Settings().rarity(Rarity.EPIC)),"archons_chestplate")
    val ARCHONS_LEGGINGS = register(ArchonsArmorItem(IgConfig.materials.armor.archon,ArmorItem.Type.LEGGINGS,Item.Settings().rarity(Rarity.EPIC)),"archons_leggings")
    val ARCHONS_BOOTS = register(ArchonsArmorItem(IgConfig.materials.armor.archon,ArmorItem.Type.BOOTS,Item.Settings().rarity(Rarity.EPIC)),"archons_boots")

    //Celestial Armor -tex done
    val CELESTIAL_HELM = register(ModifiableArmorItem(IgConfig.materials.armor.celestial,ArmorItem.Type.HELMET,Item.Settings().rarity(Rarity.EPIC)),"celestial_helmet")
    val CELESTIAL_PLATE = register(ModifiableArmorItem(IgConfig.materials.armor.celestial,ArmorItem.Type.CHESTPLATE,Item.Settings().rarity(Rarity.EPIC)),"celestial_chestplate")
    val CELESTIAL_GREAVES = register(ModifiableArmorItem(IgConfig.materials.armor.celestial,ArmorItem.Type.LEGGINGS,Item.Settings().rarity(Rarity.EPIC)),"celestial_leggings")
    val CELESTIAL_TREADS = register(ModifiableArmorItem(IgConfig.materials.armor.celestial,ArmorItem.Type.BOOTS,Item.Settings().rarity(Rarity.EPIC)),"celestial_boots")

    //Void Mail Set - Magic resistance - make with void gem, toss into void? -tex done
    val VOID_MAIL_HELMET = register(VoidMailArmorItem(IgConfig.materials.armor.void_mail,ArmorItem.Type.HELMET,Item.Settings()),"void_mail_helmet")
    val VOID_MAIL_CHESTPLATE = register(VoidMailArmorItem(IgConfig.materials.armor.void_mail,ArmorItem.Type.CHESTPLATE,Item.Settings()),"void_mail_chestplate")
    val VOID_MAIL_LEGGINGS = register(VoidMailArmorItem(IgConfig.materials.armor.void_mail,ArmorItem.Type.LEGGINGS,Item.Settings()),"void_mail_leggings")
    val VOID_MAIL_BOOTS = register(VoidMailArmorItem(IgConfig.materials.armor.void_mail,ArmorItem.Type.BOOTS,Item.Settings()),"void_mail_boots")

    //Lich-kings Robes - summoning magic, takes more damage during the day -tex done
    val LICH_KINGS_HELMET = register(LichKingsArmorItem(IgConfig.materials.armor.lich_king,ArmorItem.Type.HELMET,Item.Settings()),"lich_kings_helmet")
    val LICH_KINGS_CHESTPLATE = register(LichKingsArmorItem(IgConfig.materials.armor.lich_king,ArmorItem.Type.CHESTPLATE,Item.Settings()),"lich_kings_chestplate")
    val LICH_KINGS_LEGGINGS = register(LichKingsArmorItem(IgConfig.materials.armor.lich_king,ArmorItem.Type.LEGGINGS,Item.Settings()),"lich_kings_leggings")
    val LICH_KINGS_BOOTS = register(LichKingsArmorItem(IgConfig.materials.armor.lich_king,ArmorItem.Type.BOOTS,Item.Settings()),"lich_kings_boots")

    //Spellblades Armor - spell damage and cooldown - Tex done
    val SPELLBLADES_HELMET = register(SpellbladesArmorItem(IgConfig.materials.armor.spellblade,ArmorItem.Type.HELMET,Item.Settings()),"spellblades_helmet")
    val SPELLBLADES_CHESTPLATE = register(SpellbladesArmorItem(IgConfig.materials.armor.spellblade,ArmorItem.Type.CHESTPLATE,Item.Settings()),"spellblades_chestplate")
    val SPELLBLADES_LEGGINGS = register(SpellbladesArmorItem(IgConfig.materials.armor.spellblade,ArmorItem.Type.LEGGINGS,Item.Settings()),"spellblades_leggings")
    val SPELLBLADES_BOOTS = register(SpellbladesArmorItem(IgConfig.materials.armor.spellblade,ArmorItem.Type.BOOTS,Item.Settings()),"spellblades_boots")

    //Armor of the Champion - armor toughness and boost defensive spells -Tex done
    val CHAMPIONS_HELMET = register(ModifiableArmorItem(IgConfig.materials.armor.champion,ArmorItem.Type.HELMET,Item.Settings()),"champions_helmet")
    val CHAMPIONS_CHESTPLATE = register(ModifiableArmorItem(IgConfig.materials.armor.champion,ArmorItem.Type.CHESTPLATE,Item.Settings()),"champions_chestplate")
    val CHAMPIONS_LEGGINGS = register(ModifiableArmorItem(IgConfig.materials.armor.champion,ArmorItem.Type.LEGGINGS,Item.Settings()),"champions_leggings")
    val CHAMPIONS_BOOTS = register(ModifiableArmorItem(IgConfig.materials.armor.champion,ArmorItem.Type.BOOTS,Item.Settings()),"champions_boots")

    //Warriors Harness - damage against undead - tex done
    val WARRIORS_HELMET = register(ModifiableArmorItem(IgConfig.materials.armor.warrior,ArmorItem.Type.HELMET,Item.Settings()),"warriors_helmet")
    val WARRIORS_CHESTPLATE = register(ModifiableArmorItem(IgConfig.materials.armor.warrior,ArmorItem.Type.CHESTPLATE,Item.Settings()),"warriors_chestplate")
    val WARRIORS_LEGGINGS = register(ModifiableArmorItem(IgConfig.materials.armor.warrior,ArmorItem.Type.LEGGINGS,Item.Settings()),"warriors_leggings")
    val WARRIORS_BOOTS = register(ModifiableArmorItem(IgConfig.materials.armor.warrior,ArmorItem.Type.BOOTS,Item.Settings()),"warriors_boots")

    //Scholars Vestments - Player XP and spell XP
    val SCHOLARS_HELMET = register(ScholarsArmorItem(IgConfig.materials.armor.scholar,ArmorItem.Type.HELMET,Item.Settings()),"scholars_helmet")
    val SCHOLARS_CHESTPLATE = register(ScholarsArmorItem(IgConfig.materials.armor.scholar,ArmorItem.Type.CHESTPLATE,Item.Settings()),"scholars_chestplate")
    val SCHOLARS_LEGGINGS = register(ScholarsArmorItem(IgConfig.materials.armor.scholar,ArmorItem.Type.LEGGINGS,Item.Settings()),"scholars_leggings")
    val SCHOLARS_BOOTS = register(ScholarsArmorItem(IgConfig.materials.armor.scholar,ArmorItem.Type.BOOTS,Item.Settings()),"scholars_boots")

    //Elementalists Garb - elemental spells, mana cost
    val ELEMENTALISTS_HELMET = register(ElementalistsArmorItem(IgConfig.materials.armor.elementalist,ArmorItem.Type.HELMET,Item.Settings()),"elementalists_helmet")
    val ELEMENTALISTS_CHESTPLATE = register(ElementalistsArmorItem(IgConfig.materials.armor.elementalist,ArmorItem.Type.CHESTPLATE,Item.Settings()),"elementalists_chestplate")
    val ELEMENTALISTS_LEGGINGS = register(ElementalistsArmorItem(IgConfig.materials.armor.elementalist,ArmorItem.Type.LEGGINGS,Item.Settings()),"elementalists_leggings")
    val ELEMENTALISTS_BOOTS = register(ElementalistsArmorItem(IgConfig.materials.armor.elementalist,ArmorItem.Type.BOOTS,Item.Settings()),"elementalists_boots")

    //Eternity Shroud - health and spell duration/range, rare finds in dungeons - Tex Done
    val ETERNITY_HELMET = register(EternityArmorItem(IgConfig.materials.armor.eternity,ArmorItem.Type.HELMET,Item.Settings()),"eternity_helmet")
    val ETERNITY_CHESTPLATE = register(EternityArmorItem(IgConfig.materials.armor.eternity,ArmorItem.Type.CHESTPLATE,Item.Settings()),"eternity_chestplate")
    val ETERNITY_LEGGINGS = register(EternityArmorItem(IgConfig.materials.armor.eternity,ArmorItem.Type.LEGGINGS,Item.Settings()),"eternity_leggings")
    val ETERNITY_BOOTS = register(EternityArmorItem(IgConfig.materials.armor.eternity,ArmorItem.Type.BOOTS,Item.Settings()),"eternity_boots")

    //Hunters Garb - Ranged damage and "get-away quick"
    val HUNTERS_HELMET = register(ModifiableArmorItem(IgConfig.materials.armor.hunter,ArmorItem.Type.HELMET,Item.Settings()),"hunters_helmet")
    val HUNTERS_CHESTPLATE = register(ModifiableArmorItem(IgConfig.materials.armor.hunter,ArmorItem.Type.CHESTPLATE,Item.Settings()),"hunters_chestplate")
    val HUNTERS_LEGGINGS = register(ModifiableArmorItem(IgConfig.materials.armor.hunter,ArmorItem.Type.LEGGINGS,Item.Settings()),"hunters_leggings")
    val HUNTERS_BOOTS = register(ModifiableArmorItem(IgConfig.materials.armor.hunter,ArmorItem.Type.BOOTS,Item.Settings()),"hunters_boots")

    fun registerAll() {
    }
}
