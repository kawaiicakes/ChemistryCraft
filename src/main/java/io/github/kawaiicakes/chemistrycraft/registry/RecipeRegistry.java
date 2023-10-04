package io.github.kawaiicakes.chemistrycraft.registry;

import io.github.kawaiicakes.chemistrycraft.recipe.BloomeryRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.registry.ChemistryCraftRegistry.RECIPE_REGISTRY;

public class RecipeRegistry {
    public static final RegistryObject<RecipeSerializer<BloomeryRecipe>> BLOOMERY_RECIPE_SERIALIZER =
            RECIPE_REGISTRY.register("blooming", () -> BloomeryRecipe.Serializer.INSTANCE);
}
