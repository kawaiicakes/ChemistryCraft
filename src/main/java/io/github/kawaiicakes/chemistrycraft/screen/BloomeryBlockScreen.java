package io.github.kawaiicakes.chemistrycraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.kawaiicakes.chemistrycraft.screen.renderer.EnergyInfoArea;
import io.github.kawaiicakes.chemistrycraft.utils.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

import static io.github.kawaiicakes.chemistrycraft.ChemistryCraft.MOD_ID;

public class BloomeryBlockScreen extends AbstractContainerScreen<BloomeryBlockMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MOD_ID,"textures/gui/bloomery_gui.png");
    private EnergyInfoArea energyInfoArea;

    public BloomeryBlockScreen(BloomeryBlockMenu menu, Inventory inv, Component component) {
        super(menu, inv, component);
    }

    @Override
    protected void init() {
        super.init();
        assignEnergyInfoArea();
    }

    private void assignEnergyInfoArea() {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // x increases per pixel to right, y increases per pixel downwards
        this.energyInfoArea = new EnergyInfoArea(x + 156, y + 13, this.menu.entity.getEnergyStorage());
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        renderEnergyInfoAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);
    }

    private void renderEnergyInfoAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 156, 13, 8, 64)) {
            renderTooltip(pPoseStack, energyInfoArea.getTooltips(),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    @Override //renders background of screen?
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        // this renders the actual texture of the screen
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(poseStack, x, y);
        this.energyInfoArea.draw(poseStack);
    }

    private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) { //FIXME: MAGIC NUMBERS! Maybe a standardized coordinate template for my GUIs?
            blit(pPoseStack, x + 105, y + 33, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override //Renders screen
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
