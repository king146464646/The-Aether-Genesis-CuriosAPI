package com.aetherteam.genesis.item.accessories.ring;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import static com.aetherteam.aether.item.AetherItems.AETHER_LOOT;

public class BoneRingItem extends RingItem {
    /**
     * The unique identifier for the item's attack damage bonus.
     */
    private static final ResourceLocation ATTACK_DAMAGE_MODIFIER_LOCATION = ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "bone_ring_damage_bonus");

    public BoneRingItem() {
        super(GenesisSoundEvents.ITEM_ACCESSORY_EQUIP_BONE_RING, new Item.Properties().stacksTo(1).rarity(AETHER_LOOT));
    }

    /**
     * Sets up an attack damage modifier when the Bone Ring is equipped.
     *
     * @param slotContext The {@link SlotContext} of the curio.
     * @param id          The slot-unique id of the curio.
     * @param stack       The {@link ItemStack} correlating to the item.
     * @return The attribute modifiers of the curio.
     */
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER_LOCATION, 1, AttributeModifier.Operation.ADD_VALUE));
        return modifiers;
    }
}
