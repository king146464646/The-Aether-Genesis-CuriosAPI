package com.aetherteam.genesis.data.generators.tags;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GenesisSoundTagData extends TagsProvider<SoundEvent> {
    public GenesisSoundTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, Registries.SOUND_EVENT, registries, Aether.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(AetherTags.SoundEvents.ACHIEVEMENT_SOUNDS).add(
                GenesisSoundEvents.UI_TOAST_AETHER_BRONZE_MINIBOSS.getKey()
        );
        this.tag(AetherTags.SoundEvents.BOSS_MUSIC).add(
                GenesisSoundEvents.MUSIC_MINIBOSS.getKey()
        );
    }
}