@file:Suppress("unused")

package me.fzzyhmstrs.imbued_gear.item

import net.minecraft.item.DyeItem
import net.minecraft.item.ItemStack

interface TwoLayerDyeable {
    companion object{

        fun registerClient(){
            //ColorProviderRegistry.ITEM.register({stack, layer -> if (layer == 0){(stack.item as TwoLayerDyeable).getColor(stack, 0)} else if (layer == 1){(stack.item as TwoLayerDyeable).getColor(stack, 1)} else -1 }, RegisterTool.HUNTERS_ARBALEST, )
        }

        private val DYE = "dye_colors"
        private val DYE_LAYER_0 = "dye_layer_0"
        private val DYE_LAYER_1 = "dye_layer_1"
    }

    fun hasColor(stack: ItemStack, layer: Int): Boolean {
        val nbtCompound = stack.getSubNbt(DYE) ?: return false
        return when (layer) {
            0 -> {
                nbtCompound.contains(DYE_LAYER_0)
            }
            1 -> {
                nbtCompound.contains(DYE_LAYER_1)
            }
            else -> {
                false
            }
        }
    }

    fun removeColor(stack: ItemStack, layer: Int){
        val nbtCompound = stack.getSubNbt(DYE) ?: return
        when (layer) {
            0 -> {
                nbtCompound.remove(DYE_LAYER_0)
            }
            1 -> {
                nbtCompound.remove(DYE_LAYER_1)
            }
        }
    }

    fun setColor(stack: ItemStack, layer: Int, color: Int){
        val nbtCompound = stack.getSubNbt(DYE) ?: return
        when (layer) {
            0 -> {
                nbtCompound.putInt(DYE_LAYER_0,color)
            }
            1 -> {
                nbtCompound.putInt(DYE_LAYER_1,color)
            }
        }
    }

    fun getColor(stack: ItemStack, layer: Int): Int{
        return when (layer) {
            0 -> {
                val nbtCompound = stack.getSubNbt(DYE)
                nbtCompound?.getInt(DYE_LAYER_0) ?: 0x79553A
            }
            1 -> {
                val nbtCompound = stack.getSubNbt(DYE)
                nbtCompound?.getInt(DYE_LAYER_1) ?: 0x70B046
            }
            else -> {
                10511680
            }
        }
    }

    fun blendAndSetColor(stack: ItemStack, colors: List<DyeItem>, layer: Int): ItemStack {
        val item = stack.item
        if (item !is TwoLayerDyeable) return stack.copy()
        var n: Int
        var h: Float
        val ints = IntArray(3)
        var i = 0
        var j = 0
        val itemStack = stack.copyWithCount(1)
        if (item.hasColor(stack,layer)) {
            val k = item.getColor(itemStack,layer)
            val f = (k shr 16 and 0xFF).toFloat() / 255.0f
            val g = (k shr 8 and 0xFF).toFloat() / 255.0f
            h = (k and 0xFF).toFloat() / 255.0f
            i += (f.coerceAtLeast(g.coerceAtLeast(h)) * 255.0f).toInt()
            ints[0] = ints[0] + (f * 255.0f).toInt()
            ints[1] = ints[1] + (g * 255.0f).toInt()
            ints[2] = ints[2] + (h * 255.0f).toInt()
            ++j
        }
        for (dyeItem in colors) {
            val fs = dyeItem.color.colorComponents
            val l = (fs[0] * 255.0f).toInt()
            val m = (fs[1] * 255.0f).toInt()
            n = (fs[2] * 255.0f).toInt()
            i += l.coerceAtLeast(m.coerceAtLeast(n))
            ints[0] = ints[0] + l
            ints[1] = ints[1] + m
            ints[2] = ints[2] + n
            ++j
        }
        var k = ints[0] / j
        var o = ints[1] / j
        var p = ints[2] / j
        h = i.toFloat() / j.toFloat()
        val q = k.coerceAtLeast(o.coerceAtLeast(p)).toFloat()
        k = (k.toFloat() * h / q).toInt()
        o = (o.toFloat() * h / q).toInt()
        p = (p.toFloat() * h / q).toInt()
        n = k
        n = (n shl 8) + o
        n = (n shl 8) + p
        item.setColor(itemStack, layer, n)
        return itemStack
    }
}