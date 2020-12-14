package net.glasslauncher.harvestcraft.mixin;

import jdk.nashorn.internal.codegen.CompilerConstants;
import net.glasslauncher.harvestcraft.HarvestCraft;
import net.minecraft.block.Farmland;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Farmland.class)
public class FarmlandMixin {

    @Inject(at=@At("HEAD"), method="method_1560")
    public void method_1560(Level arg, int i, int j, int k, EntityBase arg1, CallbackInfo callbackInfo) {
        if(arg1.method_1373() && HarvestCraft.sneakingProtectsCrops)
            return;
    }

}
