package io.github.kawaiicakes.chemistrycraft.compat.jei;

import io.github.kawaiicakes.chemistrycraft.recipe.BloomeryRecipe;
import io.github.kawaiicakes.chemistrycraft.registry.ItemRegistry;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

//TODO: add energy/pressure requirements to JEI
public class BloomeryRecipeCategory implements IRecipeCategory<BloomeryRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(MOD_ID, "blooming");
    public final static ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/bloomery_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public BloomeryRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ItemRegistry.BLOOMERY_ITEM.get()));
    }

    @Override
    public RecipeType<BloomeryRecipe> getRecipeType() {
        return ChemistryCraftJEIPlugin.BLOOMERY;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("bloomery_recipe");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override // recipe is built here
    public void setRecipe(IRecipeLayoutBuilder builder, BloomeryRecipe recipe, IFocusGroup focuses) {
        // Same X,Y values as slot 1 of bloomery
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}
