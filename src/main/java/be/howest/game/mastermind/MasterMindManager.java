package be.howest.game.mastermind;

import java.util.Random;

import be.howest.game.mastermind.util.Tools;

public class MasterMindManager {
	
	public MasterMind createMasterMind(Template template) {
		Secret secret = createSecret(template);
		return new MasterMind(secret, template);
	}
	
	Secret createSecret(Template template) {
		int[] colorCodes = createSecretArray(template);
		return new Secret(colorCodes, template);
	}
	
	int[] createSecretArray(Template template) {
		Random random = new Random();
		int[] colorCodes = Tools.getInitialColorsArray(template.getBoardWidth());
		for (int i = 0; i < colorCodes.length; i++) {
			int colorId = random.nextInt(template.getColorCount());
			if (Tools.arrayContainsValue(colorCodes, colorId)) {
				i--;
			} else {
				colorCodes[i] = colorId;
			}
		}
		return colorCodes;
	}

}
