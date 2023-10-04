package io.github.kawaiicakes.chemistrycraft.registry;

import io.github.kawaiicakes.chemistrycraft.recipe.BloomeryRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class RecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_REGISTRY =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);

    public static final RegistryObject<RecipeSerializer<BloomeryRecipe>> BLOOMERY_RECIPE_SERIALIZER =
            RECIPE_REGISTRY.register("blooming", () -> BloomeryRecipe.Serializer.INSTANCE);

    public static void register(IEventBus bus) {
        RECIPE_REGISTRY.register(bus);
    }
}
