package com.kawaiicakes.chemistrycraft.datagen;

import com.kawaiicakes.chemistrycraft.ChemistryCraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ItemTags extends ForgeRegistryTagsProvider<Item> {
    public ItemTags(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ForgeRegistries.ITEMS, ChemistryCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        TagKey<Item> tagKey = Objects.requireNonNull();
        tag(tagKey).add()
    }

    @Override
    @Nonnull
    public String getName() {
        return ChemistryCraft.MOD_ID + ":tags";
    }
}
