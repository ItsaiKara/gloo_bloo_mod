package com.aika.items;





import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


import net.minecraft.text.Text;


import net.minecraft.world.World;



public class EstoutItem extends Item {
 
    public EstoutItem(Settings settings) {
        super(settings);
    }
    
    //use on entity do a funny
    
    

    @Override
    public void appendTooltip(ItemStack itemStack, World world, java.util.List<net.minecraft.text.Text> tooltip, net.minecraft.client.item.TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.bloo_gloo.estout.tooltip"));
    }


}
