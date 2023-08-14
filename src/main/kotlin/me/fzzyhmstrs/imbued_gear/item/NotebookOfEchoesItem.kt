package me.fzzyhmstrs.imbued_gear.item

import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class NotebookOfEchoesItem(settings: Settings): Item(settings) {
  
  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
      val stack = user.getStackInHand(hand)
      if (world.isClient){
          world.playSound(user,user.blockPos,SoundEvents.ITEM_BOOK_PAGE_TURN,SoundCategory.PLAYERS,0.7f,1.0f)
          MinecraftClient.getInstance().setScreen(null)
      }
      return super.use(world,user,hand)
  }
  
}
