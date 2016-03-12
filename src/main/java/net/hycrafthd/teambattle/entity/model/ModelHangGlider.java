package net.hycrafthd.teambattle.entity.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHangGlider extends ModelBase {

	public ModelRenderer shape2;
	public ModelRenderer shape4;
	public ModelRenderer shape7;
	public ModelRenderer shape9;
	public ModelRenderer shape10;
	public ModelRenderer shape11;
	public ModelRenderer shape13;

	public ModelHangGlider() {
		this.textureHeight = 128;
		this.textureWidth = 128;

		this.shape10 = new ModelRenderer(this, 122, 32);
		this.shape10.setRotationPoint(39.5F, -24.0F, -13.0F);
		this.shape10.addBox(0.0F, 0.0F, 0.0F, 1, 60, 1, 0.0F);
		setRotateAngle(this.shape10, 0.1745329F, 0.0F, 0.5934119F);

		this.shape13 = new ModelRenderer(this, 0, 0);
		this.shape13.setRotationPoint(-13.5F, -32.599998F, -14.0F);
		this.shape13.addBox(0.0F, 0.0F, 0.0F, 28, 1, 28, 0.0F);
		setRotateAngle(this.shape13, 0.0F, 0.0F, 2.792527F);

		this.shape7 = new ModelRenderer(this, 122, 32);
		this.shape7.setRotationPoint(-39.5F, -24.0F, -13.0F);
		this.shape7.addBox(0.0F, 0.0F, 0.0F, 1, 60, 1, 0.0F);
		setRotateAngle(this.shape7, 0.1745329F, 0.0F, -0.5934119F);

		this.shape9 = new ModelRenderer(this, 122, 32);
		this.shape9.setRotationPoint(-39.5F, -24.0F, 13.0F);
		this.shape9.addBox(0.0F, 0.0F, 0.0F, 1, 60, 1, 0.0F);
		setRotateAngle(this.shape9, -0.1745329F, 0.0F, -0.5934119F);

		this.shape11 = new ModelRenderer(this, 122, 32);
		this.shape11.setRotationPoint(39.5F, -24.0F, 13.0F);
		this.shape11.addBox(0.0F, 0.0F, 0.0F, 1, 60, 1, 0.0F);
		setRotateAngle(this.shape11, -0.1745329F, 0.0F, 0.5934119F);

		this.shape2 = new ModelRenderer(this, 0, 0);
		this.shape2.setRotationPoint(-14.0F, -33.5F, -14.0F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 28, 1, 28, 0.0F);

		this.shape4 = new ModelRenderer(this, 0, 0);
		this.shape4.setRotationPoint(14.0F, -33.5F, -14.0F);
		this.shape4.addBox(0.0F, 0.0F, 0.0F, 28, 1, 28, 0.0F);
		setRotateAngle(this.shape4, 0.0F, 0.0F, 0.3490658F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape10.render(f5);
		this.shape13.render(f5);
		this.shape7.render(f5);
		this.shape9.render(f5);
		this.shape11.render(f5);
		this.shape2.render(f5);
		this.shape4.render(f5);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
