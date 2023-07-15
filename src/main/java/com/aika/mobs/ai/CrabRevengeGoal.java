package com.aika.mobs.ai;

import com.aika.mobs.CrabEntity;


import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class CrabRevengeGoal extends RevengeGoal {

    private final CrabEntity mob;

    public CrabRevengeGoal(PathAwareEntity crab, Class<?>[] noRevengeTypes) {
        super(crab, noRevengeTypes);
        this.mob = (CrabEntity) crab;
    }
    

    // @Override
    // public boolean canStart() {
    //     return ((CrabEntity) this.mob).isAngry() && this.mob.can;
    // }
    
}
