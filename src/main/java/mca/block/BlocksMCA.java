package mca.block;

import mca.MCA;
import mca.TagsMCA;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerData;

public interface BlocksMCA {
    Block ROSE_GOLD_BLOCK = register("rose_gold_block", new Block(Block.Settings.of(Material.METAL, MapColor.GOLD)
            .requiresTool()
            .strength(3.0F, 6.0F)
            .sounds(BlockSoundGroup.METAL))
    );

    Block ROSE_GOLD_ORE = register("rose_gold_ore", new OreBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.STONE))
    );

    Block VILLAGER_SPAWNER = register("villager_spawner", new Block(Block.Settings.of(Material.METAL)
            .slipperiness(VillagerData.getLowerLevelExperience(7))
            .requiresTool()
            .sounds(BlockSoundGroup.METAL)));

    // TODO: <Block> JEWELER_WORKBENCH -> profession of a jeweler
    Block JEWELER_WORKBENCH = register("jeweler_workbench", new JewelerWorkbench(Block.Settings.of(Material.WOOD)
            .sounds(BlockSoundGroup.WOOD)));

    Block UPRIGHT_HEADSTONE = register("upright_headstone", new TombstoneBlock(Block.Settings.of(Material.STONE)
            .mapColor(MapColor.GRAY)
            .nonOpaque()
            .strength(12)
            .sounds(BlockSoundGroup.STONE), 90, 50, new Vec3d(0, -55, 23), TombstoneBlock.UPRIGHT_SHAPE));
    Block SLANTED_HEADSTONE = register("slanted_headstone", new TombstoneBlock(Block.Settings.of(Material.STONE)
            .mapColor(MapColor.GRAY)
            .nonOpaque()
            .strength(12)
            .sounds(BlockSoundGroup.STONE), 100, 15, new Vec3d(0, -30, 10), TombstoneBlock.SLANTED_SHAPE));
    Block CROSS_HEADSTONE = register("cross_headstone", new TombstoneBlock(Block.Settings.of(Material.STONE)
            .mapColor(MapColor.GRAY)
            .nonOpaque()
            .strength(12)
            .sounds(BlockSoundGroup.STONE), 80, 15, new Vec3d(0, -13, 15), TombstoneBlock.CROSS_SHAPE));

    static void bootstrap() {
        TagsMCA.Blocks.bootstrap();
        BlockEntityTypesMCA.bootstrap();
    }

    private static <T extends Block> T register(String name, T block) {
        return Registry.register(Registry.BLOCK, new Identifier(MCA.MOD_ID, name), block);
    }
}