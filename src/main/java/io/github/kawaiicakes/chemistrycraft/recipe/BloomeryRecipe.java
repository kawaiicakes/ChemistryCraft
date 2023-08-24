package io.github.kawaiicakes.chemistrycraft.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class BloomeryRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output; //  These are used with JEI for identification
    private final NonNullList<Ingredient> recipeItems;

    public BloomeryRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override // If returns true, then crafting may proceed. A recipe's functionality is generally dependent on this
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide) { // This line is necessary for some reason...
            return false;
        }

        //  Checks for equality from recipe to item in respective slot
        return recipeItems.get(0).test(pContainer.getItem(1));
    }

    @Override // this is necessary to override for JEI compatibility
    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return this.output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    //  defines the RecipeType for this class (implements RecipeType<>)
    public static class Type implements RecipeType<BloomeryRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "blooming";
    }

    public static class Serializer implements RecipeSerializer<BloomeryRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MOD_ID, "blooming");

        @Override // Parses a recipe from JSON. Check vanilla implementation of RecipeSerializer to get better idea of what to do
        public BloomeryRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new BloomeryRecipe(pRecipeId, output, inputs);
        }

        @Override //    Sends recipes to logical client
        public @Nullable BloomeryRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            return new BloomeryRecipe(pRecipeId, output, inputs);
        }

        @Override //    Buffers must always be written and read in the same order
        public void toNetwork(FriendlyByteBuf buf, BloomeryRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
