package be.howest.game.mastermind.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.howest.game.mastermind.Template;

public class TemplateServiceTest {

	private TemplateService templateService;

	@Before
	public void before() {
		templateService = new TemplateService();
	}

	@Test
	public void read_reads_correct_template() {
		for (int id = 0; id < templateService.count(); id++) {
			Template template = templateService.read(id);
			assertNotNull(template);
		}
		assertNull(templateService.read(templateService.count()));
	}

	@Test
	public void findAll_returns_all_templates() {
		List<Template> templates = templateService.findAll();
		assertNotNull(templates);
		for (Template template : templates) {
			assertNotNull(template);
		}
	}

	@Test
	public void count_returns_correct_count_of_templates() {
		int count = templateService.count();
		List<Template> templates = templateService.findAll();
		assertEquals(count, templates.size());
	}
	
	@Test
	public void findAllTemplates_maps_all_templates_to_Templates() {
		int count = templateService.count();
		List<Template> templates = templateService.findAll();
		assertNotNull(templates);
		assertEquals(count, templates.size());
		for (Template template : templates) {
			assertNotNull(template);
		}
	}

}
