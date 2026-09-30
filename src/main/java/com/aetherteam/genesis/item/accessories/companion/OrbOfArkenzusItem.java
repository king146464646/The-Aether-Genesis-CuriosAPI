package com.aetherteam.genesis.item.accessories.companion;

import com.aetherteam.aether.item.accessories.abilities.SlowFallAccessory;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.companion.ShadeOfArkenzus;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class OrbOfArkenzusItem extends CompanionItem<ShadeOfArkenzus> implements SlowFallAccessory {
    public OrbOfArkenzusItem(Properties properties) {
        super(GenesisEntityTypes.SHADE_OF_ARKENZUS, properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        this.handleSlowFall(slotContext.entity());
    }

    @Override
    public boolean canWalkOnPowderedSnow(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}
