package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.SoaringWisp;
import com.aetherteam.genesis.item.accessories.abilities.SpeedAccessory;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class SoaringStoneItem extends CompanionItem<SoaringWisp> implements SpeedAccessory {
    /**
     * The unique identifier for the item's movement speed bonus.
     */
    private static final ResourceLocation SPEED_MODIFIER_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "soaring_stone_speed_increases");

    public SoaringStoneItem(Properties properties) {
        super(GenesisEntityTypes.SOARING_WISP, properties);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();
        this.addSpeedModifier(modifiers, SPEED_MODIFIER_LOCATION);
        return modifiers;
    }

}
