package be.howest.game.mastermind;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.howest.game.mastermind.services.TemplateService;

public class MasterMindManagerTest {

	private TemplateService templateService = null;
	MasterMindManager manager = null;

	@Before
	public void before() {
		templateService = new TemplateService();
		manager = new MasterMindManager();
	}

	@Test
	public void createSecret_creates_2000_valid_secrets_per_template() {
		List<Template> templates = templateService.findAll();
		for (Template template : templates) {
			for (int i = 0; i < 2000; i++) {
				Secret secret = manager.createSecret(template);
				assertTrue(Secret.isValid(secret));
			}
		}
	}
	
	@Test
	public void createMasterMind_creates_2000_valid_MasterMinds_per_template() {
		MasterMindManager manager = new MasterMindManager();
		List<Template> templates = templateService.findAll();
		for (Template template : templates) {
			for (int i = 0; i < 2000; i++) {
				MasterMind masterMind = manager.createMasterMind(template);
				assertTrue(MasterMind.isValid(masterMind));
			}
		}
	}

}
