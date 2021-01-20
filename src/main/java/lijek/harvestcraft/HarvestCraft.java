package lijek.harvestcraft;

import net.modificationstation.stationloader.api.common.config.Category;
import net.modificationstation.stationloader.api.common.config.Configuration;
import net.modificationstation.stationloader.api.common.mod.StationMod;

public class HarvestCraft implements StationMod {
    Configuration config;

    public static boolean rightClickHarvest = true;
    public static boolean sneakingProtectsCrops = true;
    public static boolean walkingAndJumpingDestroysCrops = true;
    public static boolean alwaysDropOneSeed = true;
    public static int wheatHarvestMultiplier = 1;
    public static int seedHarvestMultiplier = 1;
    @Override
    public void preInit() {
        config = getDefaultConfig();
        config.load();

        Category general = config.getCategory("general");
        rightClickHarvest = general.getProperty("rightClickHarvest", rightClickHarvest).getBooleanValue();
        sneakingProtectsCrops = general.getProperty("sneakingProtectsCrops", sneakingProtectsCrops).getBooleanValue();
        walkingAndJumpingDestroysCrops = general.getProperty("walkingAndJumpingDestroysCrops", walkingAndJumpingDestroysCrops).getBooleanValue();
        alwaysDropOneSeed = general.getProperty("alwaysDropOneSeed", alwaysDropOneSeed).getBooleanValue();
        wheatHarvestMultiplier = general.getProperty("wheatHarvestMultiplier", wheatHarvestMultiplier).getIntValue();
        seedHarvestMultiplier = general.getProperty("seedHarvestMultiplier", seedHarvestMultiplier).getIntValue();
        config.save();
    }
}
