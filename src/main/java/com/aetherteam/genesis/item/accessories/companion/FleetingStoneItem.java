package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.FleetingWisp;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

/**
 * [CODE COPY] - {@link com.aetherteam.aether.item.accessories.cape.AgilityCapeItem}
 */
public class FleetingStoneItem extends CompanionItem<FleetingWisp> {
    /**
     * The unique identifier for the item's step height modifier.
     */
    private static final ResourceLocation STEP_HEIGHT_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "fleeting_wisp_step_height_increase");

    public FleetingStoneItem(Properties properties) {
        super(GenesisEntityTypes.FLEETING_WISP, properties);
    }

    /**
     * Applies a step height modifier to the wearer as long as they aren't holding shift. If they are, the modifier is removed until they stop holding shift.
     *
     * @param slotContext The {@link SlotContext} of the curio.
     * @param stack       The Curio {@link ItemStack}.
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        AttributeInstance stepHeight = livingEntity.getAttribute(Attributes.STEP_HEIGHT);
        if (stepHeight != null) {
            if (!stepHeight.hasModifier(STEP_HEIGHT_LOCATION) && !livingEntity.isShiftKeyDown()) {
                stepHeight.addTransientModifier(this.getStepHeightModifier());
            }
            if (livingEntity.isShiftKeyDown()) {
                stepHeight.removeModifier(STEP_HEIGHT_LOCATION);
            }
        }
    }



    /**
     * Removes the step height modifier when the Agility Cape is unequipped.
     *
     * @param slotContext The {@link SlotContext} of the curio.
     * @param stack       The {@link ItemStack} of the curio.
     */
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        AttributeInstance stepHeight = livingEntity.getAttribute(Attributes.STEP_HEIGHT);
        if (stepHeight != null) {
            if (stepHeight.hasModifier(STEP_HEIGHT_LOCATION)) {
                stepHeight.removeModifier(STEP_HEIGHT_LOCATION);
            }
        }
        super.onUnequip(slotContext, newStack, stack);
    }

    /**
     * @return The step height {@link AttributeModifier}. The default step height is 0.5, so this is an additional 0.5 to give the wearer a full block of step height.
     */
    public AttributeModifier getStepHeightModifier() {
        return new AttributeModifier(STEP_HEIGHT_LOCATION, 0.5, AttributeModifier.Operation.ADD_VALUE);
    }
}
