package org.btwr.core.mixin.loot;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import org.btwr.core.item.ModItems;
import org.btwr.core.mixin.accessor.RegistryEntryListDirectAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Makes BTWRItems.DIAMOND_SHEARS valid for any vanilla leaves loot table
 * that normally requires #minecraft:shears.
 */
@Mixin(MatchToolLootCondition.class)
public abstract class MatchToolLootConditionMixin implements LootCondition {

    @Unique
    private static final List<ItemPredicate> ITEM_PREDICATES = new ArrayList<>();

    // Capture all MatchTool predicates as they are constructed
    @Inject(at = @At("RETURN"), method = "<init>")
    private void capturePredicates(CallbackInfo ci) {
        MatchToolLootCondition self = (MatchToolLootCondition)(Object)this;
        self.predicate().ifPresent(ITEM_PREDICATES::add);
    }

    static {
        // When all item tags are loaded
        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
            // Wrap vanilla shears and diamond shears in registryEntries
            RegistryEntry<Item> vanillaShears = Registries.ITEM.getEntry(Items.SHEARS);
            RegistryEntry<Item> diamondShears = Registries.ITEM.getEntry(ModItems.DIAMOND_SHEARS);

            // Iterate over all captured predicates
            for (ItemPredicate predicate : ITEM_PREDICATES) {
                predicate.items().ifPresent(registryEntries -> {
                    // Only replace RegistryEntryList.Direct (normal vanilla lists)
                    if (registryEntries instanceof RegistryEntryList.Direct && registryEntries.contains(vanillaShears) && !registryEntries.contains(diamondShears)) {
                        RegistryEntryListDirectAccessor<Item> accessor = (RegistryEntryListDirectAccessor<Item>) registryEntries;
                        ArrayList<RegistryEntry<Item>> newContents = new ArrayList<>(accessor.getEntries());
                        newContents.add(diamondShears);
                        accessor.setEntries(ImmutableList.copyOf(newContents));
                        accessor.setEntrySet(null);
                    }
                });
            }
        });
    }

}