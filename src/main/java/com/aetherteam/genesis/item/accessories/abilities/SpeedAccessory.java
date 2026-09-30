package com.aetherteam.genesis.item.accessories.abilities;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public interface SpeedAccessory {
    /**
     * Sets up a speed modifier for an accessory when equipped.<br><br>
     *
     * @param modifiers The attribute {@link Multimap} of the curio.
     * @param location  A unique {@link ResourceLocation} for the attribute.
     */
    default void addSpeedModifier(Multimap<Holder<Attribute>, AttributeModifier> modifiers, ResourceLocation location) {
        modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(location, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }
}
