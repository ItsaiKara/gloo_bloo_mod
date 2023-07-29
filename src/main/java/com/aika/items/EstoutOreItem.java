package com.aika.items;


import com.aika.mobs.CrabEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;



public class EstoutOreItem extends Item {
 
    public EstoutOreItem(Settings settings) {
        super(settings);
    }
    
 
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        //check if entity is a crab
        
        if(entity instanceof CrabEntity){
            //play sound
            user.playSound(SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, 1.0F, 0.5F);
            //kill crab
            ((CrabEntity) entity).ignite();
        }
        
        
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, java.util.List<net.minecraft.text.Text> tooltip, net.minecraft.client.item.TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.gloo_bloo.estout_ore_tooltip").formatted(net.minecraft.util.Formatting.GRAY).formatted(net.minecraft.util.Formatting.ITALIC));
    }


}
