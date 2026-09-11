package org.btwr.core.datagen;

import org.btwr.core.BTWRMod;
import org.btwr.core.block.ModBlocks;
import org.btwr.core.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BTWRLangProvider extends FabricLanguageProvider {

    public BTWRLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder tb) {
        this.addItemGroup("group_btwr", "Better Than Wolves: Remastered!", tb);
        this.generateBlockTranslations(tb);
        this.generateItemTranslations(tb);
        this.generateConfigTranslations(tb);

        tb.add("subtitles.btwr.creeper_sheared", "Creeper sheared");

        tb.add("difficulty.btwr", "Difficulty");
        tb.add("difficulty.btwr.standard", "Standard");
        tb.add("difficulty.btwr.relaxed", "Relaxed");
        tb.add("difficulty.btwr.hostile", "Hostile");
        tb.add("difficulty.btwr.classic", "Classic");
        tb.add("difficulty.btwr.hostile_locked", "Hostile Locked");

        tb.add("difficulty.btwr.standard.tooltip", "The standard experience.\n Recommended for those looking\n for a moderate challenge.");
        tb.add("difficulty.btwr.relaxed.tooltip", "A more relaxed experience.\n Recommended for those looking\n for lower pressure survival.");
        tb.add("difficulty.btwr.hostile.tooltip", "A much more difficult experience.\n Recommended for those looking\n for the greatest challenge.");
        tb.add("difficulty.btwr.classic.tooltip", "The old-school BTW experience.\n Returns BTW to its tech roots.\n Provides little survival challenge.");
        tb.add("difficulty.btwr.hostile_locked.tooltip", "A much more difficult experience.\n Recommended for those looking\n for the greatest challenge.");
    }

    private void generateBlockTranslations(TranslationBuilder tb) {
        tb.add(ModBlocks.BLIGHT, "Blight");
        tb.add("block.btwr.blight.mature", "Mature Blight");

        tb.add(ModBlocks.FLINT_BLOCK, "Block of Flint");
        tb.add(ModBlocks.DIAMOND_INGOT_BLOCK, "Block of Diamond Ingot");

        tb.add(ModBlocks.SPIDER_EYE_BLOCK, "Block of Spider Eye");
        tb.add(ModBlocks.SPIDER_EYE_SLAB, "Spider Eye Slab");

        tb.add(ModBlocks.PLACED_STICK, "Placed Stick");
    }

    private void generateItemTranslations(TranslationBuilder tb) {
        tb.add(ModItems.BEAST_LIVER_COOKED, "Cooked Liver");
        tb.add(ModItems.BEAST_LIVER_RAW, "Liver Of The Beast");

        tb.add(ModItems.DIAMOND_SHEARS, "Diamond Shears");
        tb.add(ModItems.CLUB_WOOD, "Wooden Club");
        tb.add(ModItems.CLUB_BONE, "Bone Club");

        tb.add(ModItems.LEATHER_TANNED_BOOTS, "Tanned Leather Boots");
        tb.add(ModItems.LEATHER_TANNED_CHESTPLATE, "Tanned Leather Chestplate");
        tb.add(ModItems.LEATHER_TANNED_HELMET, "Tanned Leather Helmet");
        tb.add(ModItems.LEATHER_TANNED_LEGGINGS, "Tanned Leather Leggings");

        tb.add(ModItems.DIAMOND_INGOT, "Diamond Ingot");
        tb.add(ModItems.DIAMOND_PLATE,"Diamond Armor Plate");
        tb.add(ModItems.LEATHER_CUT, "Cut Leather");
        tb.add(ModItems.LEATHER_SCOURED, "Scoured Leather");
        tb.add(ModItems.LEATHER_SCOURED_CUT, "Cut Scoured Leather");
        tb.add(ModItems.LEATHER_TANNED, "Tanned Leather");
        tb.add(ModItems.LEATHER_TANNED_CUT, "Cut Tanned Leather");

        tb.add(ModItems.OCULAR_OF_ENDER, "Ocular of Ender");
        tb.add(ModItems.ENDER_SPECTACLES, "Ender Spectacles");
    }

    private void generateConfigTranslations(TranslationBuilder tb) {
        this.addConfigMenuDefaults(tb);
        this.addConfigMenuTitle("BTWR: Core Client Configuration Menu", tb);
        this.addConfigCategory("general", "General", tb);
        //this.addConfig("knockbackRestrictions", "Knockback Restrictions", tb);
        //this.addConfig("spawnBabyZombies", "Spawn Baby Zombies", tb);
        //this.addConfig("spawnMobsOnWood", "Spawn Mobs on Wood", tb);
        //this.addConfig("increasedMonsterSpawnsPerChunk", "Increase Mobs Spawns per Chunk", tb);
        //this.addConfig("changedCreeperExplosionPos", "Changed Creeper Explosion", tb);
        //this.addConfigTooltip("knockbackRestrictions", "Disables knockback if not using a suitable weapon", tb);
        //this.addConfigTooltip("spawnBabyZombies", "Can baby zombies spawn naturally?", tb);
        //this.addConfigTooltip("spawnMobsOnWood", "Can mobs spawn on wooden blocks?", tb);
        //this.addConfigTooltip("increasedMonsterSpawnsPerChunk", "Slightly increase the amount of mobs that can spawn per chunk", tb);
        //this.addConfigTooltip("changedCreeperExplosionPos", "Changes the location of creeper's explosion origin to\n be calculated from their eyes instead of their feet", tb);
    }

    private void addConfigMenuDefaults(TranslationBuilder tb) {
        this.addSimpleText("clientSettingsText", "Client Settings:", tb);
        this.addSimpleText("emptyClientConfigText", "§eNote:§r There are currently no client config settings.", tb);
        this.addSimpleText("serverSettingsText", "Server Settings:", tb);
        this.addSimpleText("serverSettingsNoAccessText", "§eNote:§r Server settings are not accessible in menus." +
                "\nThey can only be changed by editing the config file manually and require a world reload to take effect.", tb
        );
    }

    private void addItemGroup(String entryPath, String translation, TranslationBuilder tb) {
        tb.add("itemgroup." + entryPath, translation);
    }

    private void addConfigMenuTitle(String translation, TranslationBuilder tb) {
        tb.add("title." + BTWRMod.MOD_ID + ".config", translation);
    }

    private void addConfigCategory(String categoryPath, String translation, TranslationBuilder tb) {
        tb.add("config." + BTWRMod.MOD_ID + ".category." + categoryPath, translation);
    }

    private void addSimpleText(String configPath, String translation, TranslationBuilder tb) {
        tb.add("config." + BTWRMod.MOD_ID + ".text." + configPath, translation);
    }

    private void addConfig(String configPath, String translation, TranslationBuilder tb) {
        tb.add("config." + BTWRMod.MOD_ID + "." + configPath, translation);
    }

    private void addConfigTooltip(String configPath, String translation, TranslationBuilder tb) {
        tb.add("config." + BTWRMod.MOD_ID + ".tooltip." + configPath, translation);
    }

}