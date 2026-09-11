package org.btwr.core.util;

import com.bwt.items.BwtItems;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import org.btwr.bwt_hct.data.ModDataAttachments;
import org.btwr.bwt_hct.data.RecentlyOnChoppingBlockCountdownData;
import org.btwr.bwt_hct.world.ModDamageTypes;
import org.btwr.shared_library.api.registry.HeadDropRegistry;

public class HeadDropHandler {

    public static void checkForHeadDrop(LivingEntity entity, DamageSource source) {
        if (entity instanceof PlayerEntity) {
            checkForPlayerHeadDrop(entity, source);
        } else {
            checkForMobHeadDrop(entity, source);
        }
    }

    private static void checkForPlayerHeadDrop(LivingEntity entity, DamageSource source) {
        var random = entity.getRandom();
        var attacker = source.getAttacker();

        boolean isBWTLoaded = FabricLoader.getInstance().isModLoaded("bwt");
        boolean isHCTLoaded = FabricLoader.getInstance().isModLoaded("bwt_hct");

        int lootingModifier = getLootingModifier(attacker);
        int headChance = random.nextInt(200);

        // Looting applied first for players (matches BTW EntityPlayerMP order)
        headChance -= lootingModifier;

        if (attacker instanceof PlayerEntity player) {
            ItemStack held = player.getMainHandStack();
            if (isBWTLoaded && held.isOf(BwtItems.netheriteBattleAxeItem)) {
                headChance = 0; // 100% guaranteed in PvP with battle axe
            }
        } else if (isHCTLoaded && isChoppingBlockKill(source, entity)) {
            headChance = 0; // 100% guaranteed from chopping block, no countdown check for players
        }

        if (headChance < 5) {
            dropHead(entity);
        }
    }

    // Mirrors EntityLivingBase#checkForHeadDrop from BTW
    private static void checkForMobHeadDrop(LivingEntity entity, DamageSource source) {
        var random = entity.getRandom();
        var attacker = source.getAttacker();

        boolean isBWTLoaded = FabricLoader.getInstance().isModLoaded("bwt");
        boolean isHCTLoaded = FabricLoader.getInstance().isModLoaded("bwt_hct");

        int lootingModifier = getLootingModifier(attacker);
        int headChance = random.nextInt(200);

        if (attacker instanceof PlayerEntity player) {
            ItemStack held = player.getMainHandStack();
            if (isBWTLoaded && held.isOf(BwtItems.netheriteBattleAxeItem)) {
                headChance >>= 2; // quadruple chance with battle axe
            }
        } else if (isHCTLoaded && isChoppingBlockKill(source, entity)) {
            headChance >>= 2; // quadruple chance from chopping block, countdown respected for mobs
        }

        // Looting applied after chance modifier for mobs
        headChance -= lootingModifier;

        if (headChance < 5) {
            dropHead(entity);
        }
    }

    private static int getLootingModifier(Entity attacker) {
        if (attacker instanceof LivingEntity living) {
            RegistryEntry<Enchantment> looting  = living.getWorld()
                    .getRegistryManager()
                    .get(RegistryKeys.ENCHANTMENT)
                    .getEntry(Enchantments.LOOTING)
                    .orElse(null);

            if (looting != null) {
                return EnchantmentHelper.getEquipmentLevel(looting, living);
            }
        }
        return 0;
    }

    private static boolean isChoppingBlockKill(DamageSource source, LivingEntity entity) {
        RecentlyOnChoppingBlockCountdownData recentlyOnData = entity.getAttached(
                ModDataAttachments.RECENTLY_ON_CHOPPING_BLOCK_COUNTDOWN
        );

        return source.isOf(ModDamageTypes.CHOPPING_BLOCK)
                || (recentlyOnData != null && recentlyOnData.getCountdown() > 0);
    }

    private static void dropHead(LivingEntity entity) {
        ItemStack head = HeadDropRegistry.getHeadForEntity(entity);
        if (head == null || head.isEmpty()) return;

        // If it's a player head, apply the skull owner profile
        if (entity instanceof PlayerEntity player && head.isOf(Items.PLAYER_HEAD)) {
            head.set(DataComponentTypes.PROFILE, new ProfileComponent(player.getGameProfile()));
        }

        entity.dropStack(head);
    }

}