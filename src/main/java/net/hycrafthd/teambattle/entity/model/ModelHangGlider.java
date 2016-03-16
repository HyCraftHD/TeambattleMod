package net.hycrafthd.teambattle.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHangGlider extends ModelBase {
	// fields
	ModelRenderer middle;
	ModelRenderer right;
	ModelRenderer left;
	ModelRenderer leftback;
	ModelRenderer leftfront;
	ModelRenderer rightfront;
	ModelRenderer rightback;

	public ModelHangGlider() {
		textureWidth = 128;
		textureHeight = 64;

		middle = new ModelRenderer(this, 0, 0);
		middle.addBox(-16F, 0F, -16F, 32, 1, 32);
		middle.setRotationPoint(0F, -5F, 0F);
		middle.setTextureSize(128, 64);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		right = new ModelRenderer(this, 0, 0);
		right.addBox(0F, 0F, -16F, 20, 1, 32);
		right.setRotationPoint(16F, -5F, 0F);
		right.setTextureSize(128, 64);
		right.mirror = true;
		setRotation(right, 0F, 0F, 0.6108652F);
		left = new ModelRenderer(this, 0, 0);
		left.addBox(-20F, 0F, -16F, 20, 1, 32);
		left.setRotationPoint(-16F, -5F, 0F);
		left.setTextureSize(128, 64);
		left.mirror = true;
		setRotation(left, 0F, 0F, -0.6108652F);
		leftback = new ModelRenderer(this, 0, 0);
		leftback.addBox(0F, 0F, 0F, 1, 37, 1);
		leftback.setRotationPoint(30F, 6F, -15F);
		leftback.setTextureSize(128, 64);
		leftback.mirror = true;
		setRotation(leftback, 0.3490659F, 0F, 0.9599311F);
		leftfront = new ModelRenderer(this, 0, 0);
		leftfront.addBox(0F, 0F, 0F, 1, 37, 1);
		leftfront.setRotationPoint(30F, 6F, 14F);
		leftfront.setTextureSize(128, 64);
		leftfront.mirror = true;
		setRotation(leftfront, -0.3490659F, 0F, 0.9599311F);
		rightfront = new ModelRenderer(this, 0, 0);
		rightfront.addBox(-1F, 0F, 0F, 1, 37, 1);
		rightfront.setRotationPoint(-30F, 6F, 14F);
		rightfront.setTextureSize(128, 64);
		rightfront.mirror = true;
		setRotation(rightfront, -0.3490659F, 0F, -0.9599311F);
		rightback = new ModelRenderer(this, 0, 0);
		rightback.addBox(-1F, 0F, 0F, 1, 37, 1);
		rightback.setRotationPoint(-30F, 6F, -15F);
		rightback.setTextureSize(128, 64);
		rightback.mirror = true;
		setRotation(rightback, 0.3490659F, 0F, -0.9599311F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		middle.render(f5);
		right.render(f5);
		left.render(f5);
		leftback.render(f5);
		leftfront.render(f5);
		rightfront.render(f5);
		rightback.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
