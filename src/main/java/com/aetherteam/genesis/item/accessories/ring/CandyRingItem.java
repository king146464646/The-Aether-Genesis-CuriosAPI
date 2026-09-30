package com.aetherteam.genesis.item.accessories.ring;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.item.components.GenesisDataComponents;
import com.aetherteam.genesis.item.components.HungerTracker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class CandyRingItem extends RingItem {
    public CandyRingItem() {
        super(GenesisSoundEvents.ITEM_ACCESSORY_EQUIP_CANDY_RING, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    /**
     * Saves the player's exhaustion and saturation levels to the Candy Ring's NBT data.
     *
     * @param slotContext The {@link SlotContext} of the curio.
     * @param stack       The {@link ItemStack} being equipped.
     */
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            stack.set(GenesisDataComponents.STORED_HUNGER_VALUES, new HungerTracker(player.getFoodData().getExhaustionLevel(), player.getFoodData().getSaturationLevel()));
        }
    }

    /**
     * Keeps the player's exhaustion and saturation levels within the limits stored by the Candy Ring.
     *
     * @param slotContext The {@link SlotContext} of the curio.
     * @param stack       The {@link ItemStack} correlating to the item.
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            if (slotContext.entity() instanceof Player player && stack.has(GenesisDataComponents.STORED_HUNGER_VALUES)) {
                HungerTracker foodStuff = stack.get(GenesisDataComponents.STORED_HUNGER_VALUES);
                float exhaustionLevel = foodStuff.exhaustionLevel();
                float saturationLevel = foodStuff.saturationLevel();
                if (player.getFoodData().getExhaustionLevel() > exhaustionLevel) { // Reduce exhaustion to stored level if it goes up.
                    player.getFoodData().setExhaustion(exhaustionLevel);
                }
                if (player.getFoodData().getSaturationLevel() < saturationLevel) { // Increase saturation to stored level if it goes down.
                    player.getFoodData().setSaturation(saturationLevel);
                }
            }
        }
    }

}
