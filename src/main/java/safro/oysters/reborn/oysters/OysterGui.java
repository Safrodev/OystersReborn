package safro.oysters.reborn.oysters;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import safro.oysters.reborn.OystersReborn;

@Environment(EnvType.CLIENT)
public class OysterGui extends HandledScreen<OysterContainer> {

    public Identifier fishGui = new Identifier(OystersReborn.MODID, "textures/gui/oyster_gui.png");
    public OysterEntity tile;
    private String containerLabel;

    public OysterGui(OysterEntity oysterEntity, OysterContainer oysterContainer, String containerLabel, String textComponent) {
        super(oysterContainer, oysterContainer.playerInventory, new TranslatableText(textComponent));
        this.tile = oysterEntity;
        this.backgroundHeight = 146;
        this.containerLabel = new TranslatableText(textComponent).asString();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    public void drawForeground(MatrixStack matrices, int int_1, int int_2) {
        this.textRenderer.draw(matrices, this.containerLabel, 8.0F, 6.0F, 4210752);
    }

    @Override
    public void drawBackground(MatrixStack matrices, float v, int i, int i1) {
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, fishGui);
      //  this.client.getTextureManager().bindTexture(fishGui);
        int w = (this.width - this.backgroundWidth) / 2;
        int h = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices,w, h, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }
}
