package lijek.harvestcraft.mixin;

import lijek.harvestcraft.HarvestCraft;
import net.minecraft.block.Crops;
import net.minecraft.block.Plant;
import net.minecraft.entity.Item;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(Crops.class)
public abstract class CropsMixin extends Plant {

    protected CropsMixin(int i, int j) {
        super(i, j);
    }

    private void spawnItemEntity(Level level, int x, int y, int z, ItemBase item, int count){
        float var8 = 0.7F;
        float var9 = level.rand.nextFloat() * var8 + (1.0F - var8) * 0.5F;
        float var10 = level.rand.nextFloat() * var8 + (1.0F - var8) * 0.5F;
        float var11 = level.rand.nextFloat() * var8 + (1.0F - var8) * 0.5F;
        Item var12 = new Item(level, (float)x + var9, (float)y + var10, (float)z + var11, new ItemInstance(item, count));
        var12.pickupDelay = 10;
        level.spawnEntity(var12);
    }

    @Override
    public void beforeDestroyedByExplosion(Level level, int x, int y, int z, int meta, float dropChance) {
        super.beforeDestroyedByExplosion(level, x, y, z, meta, dropChance);
        if (!level.isClient) {
            if(meta > 0) {
                for (int var7 = 0; var7 < 3; ++var7) {
                    if (level.rand.nextInt(15) <= meta) {
                        spawnItemEntity(level, x, y, z, ItemBase.seeds, HarvestCraft.seedHarvestMultiplier);
                    }
                }
            }
            if(HarvestCraft.alwaysDropOneSeed)
                spawnItemEntity(level, x, y, z, ItemBase.seeds, 1);
        }
    }

    @Override
    public int getDropCount(Random rand){
        return HarvestCraft.wheatHarvestMultiplier;
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player){
        if(HarvestCraft.rightClickHarvest) {
            int meta = level.getTileMeta(x, y, z);
            if (meta == 7) {
                for (int var7 = 0; var7 < HarvestCraft.seedHarvestMultiplier * 2; ++var7) {
                    if (level.rand.nextInt(15) <= meta) {
                        spawnItemEntity(level, x, y, z, ItemBase.seeds, 1);
                    }
                }
                spawnItemEntity(level, x, y, z, ItemBase.wheat, HarvestCraft.wheatHarvestMultiplier);
                level.setTileMeta(x, y, z, 0);
            }
        }
        return false;
    }
}