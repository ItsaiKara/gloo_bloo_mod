package com.aika.mobs.ai;



import com.aika.mobs.CrabEntity;


import net.minecraft.entity.ai.goal.Goal;

public class CrabFindNestGoal extends Goal {
    private final CrabEntity mob;
    private double searchDistance = 10.0D;
    
    
    public CrabFindNestGoal(CrabEntity crab) {
        this.mob = crab;
    }


    @Override
    public boolean canStart() {
        // If time of day is 
        if (this.mob.getWorld().isDay()) {
            return false;
        } else {
            //if mob.nestBlock is null
            if (this.mob.getNest() == null) {
                return false;
            } 
            return true;
        }
    }

    public boolean isSandNearby() {
        CrabEntity mob = this.mob;
        return mob.getNest() != null;
        
    }
    
}
