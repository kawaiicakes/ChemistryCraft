package io.github.kawaiicakes.chemistrycraft.compat.jei;

import io.github.kawaiicakes.chemistrycraft.recipe.BloomeryRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

@JeiPlugin
public class ChemistryCraftJEIPlugin implements IModPlugin {
    public static RecipeType<BloomeryRecipe> BLOOMERY = new RecipeType<>(BloomeryRecipeCategory.UID, BloomeryRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                BloomeryRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<BloomeryRecipe> bloomeryRecipes = rm.getAllRecipesFor(BloomeryRecipe.Type.INSTANCE);
        registration.addRecipes(BLOOMERY, bloomeryRecipes);
    }
}
