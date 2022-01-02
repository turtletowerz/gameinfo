package com.turtletowerz.gameinfo.lines;

import com.turtletowerz.gameinfo.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Line {
	public int pos;
	String text;
	int width;

	float x;
	float y;

	public abstract void update(MinecraftClient client);

	public void updateWidth(MinecraftClient client) {
		this.width = client.textRenderer.getWidth(this.text.replaceAll("§[0-9a-z]", ""));

		if (Config.INSTANCE.RightHUD) {
			this.x = (client.getWindow().getScaledWidth() - this.width - 3);
		} else {
			this.x = 2;
		}
	}

	public int getWidth() {
		return width;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getHeight() {
		return 9 * Config.INSTANCE.getScaleY(); // * 0.9f;
	}

	public void draw(MatrixStack matrices, TextRenderer textRenderer) {
		textRenderer.drawWithShadow(matrices, this.text, this.x, this.y, 0xFFFFFFFF);//, this.color);
		// if (!Config.INSTANCE.UseScale) {
		// 	textRenderer.drawWithShadow(matrices, text, x, y, this.color);
		// 	//textRenderer.draw(matrices, this.shadowText, this.XOffset, this.YOffset, this.shadowColor);
		// 	//textRenderer.draw(matrices, this.text, x, y, this.color);
		// } else {
		// 	matrices.push();

		// 	matrices.scale(ScaleX, ScaleY, 1);
		// 	float tx = x / ScaleX;
		// 	float ty = y / ScaleY;
		// 	//float tXOffset = XOffset/ScaleX;
		// 	//float tYOffset = YOffset/ScaleY;
		// 	textRenderer.drawWithShadow(matrices, text, tx, ty, this.color);
		// 	//textRenderer.draw(matrices, this.shadowText, tXOffset, tYOffset, this.shadowColor);
		// 	//textRenderer.draw(matrices, this.text, tx, ty, this.color);

		// 	matrices.pop();
		// }		
	}
}
