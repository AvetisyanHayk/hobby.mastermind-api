package be.howest.game.mastermind.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import be.howest.game.mastermind.Template;

/**
 * Template Service class
 * @author Hayk
 *
 */
public class TemplateService {
	
	private static final Map<Integer, Template> TEMPLATES = new LinkedHashMap<>();
	
	static {
		TEMPLATES.put(0, new Template(0, 4, 4));
		TEMPLATES.put(1, new Template(1, 4, 6));
		TEMPLATES.put(2, new Template(2, 4, 8));
		TEMPLATES.put(3, new Template(3, 5, 8));
		TEMPLATES.put(4, new Template(4, 5, 10));
		TEMPLATES.put(5, new Template(5, 6, 8));
		TEMPLATES.put(6, new Template(6, 6, 10));
		TEMPLATES.put(7, new Template(7, 7, 10));
		TEMPLATES.put(8, new Template(8, 8, 10));
	}
	
	/**
	 * Reads a template by id
	 * @param id Template-id
	 * @return Null if template not found or the corresponding Template
	 */
	public Template read(int id) {
		if (id >= 0 && id < count())
			return TEMPLATES.get(id);
		return null;
	}
	
	/**
	 * Gets List of all available templates
	 * @return ArrayList of all available templates
	 */
	public List<Template> findAll() {
		return new ArrayList<>(TEMPLATES.values());
	}
	
	/**
	 * Gets total count of available templates
	 * @return Total count of available templates
	 */
	public int count() {
		return TEMPLATES.size();
	}
}
