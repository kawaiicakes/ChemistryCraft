package io.github.kawaiicakes.chemistrycraft.registry.item;

import com.smashingmods.chemlib.api.Chemical;
import com.smashingmods.chemlib.api.MatterState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public abstract class MineralItem<T extends Item & Chemical> extends Item {
    protected final String name;
    @Nullable
    protected final String phase;
    protected final T parent;
    protected final String description;

    public MineralItem(String allotropeName, @Nullable String phase, String description, T parent, Item.Properties properties) {
        super(properties);
        this.parent = parent;
        this.name = allotropeName;
        this.phase = phase;
        this.description = description;
    }

    @Override
    public abstract void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced);

    @Override
    public abstract void initializeClient(Consumer<IClientItemExtensions> consumer);

    // Minerals are by definition solid
    public MatterState getMatterState() {
        return MatterState.SOLID;
    }
}
