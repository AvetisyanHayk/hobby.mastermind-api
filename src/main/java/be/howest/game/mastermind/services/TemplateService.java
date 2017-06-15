package be.howest.game.mastermind.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import be.howest.game.mastermind.Template;

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
	
	public Template read(int id) {
		return TEMPLATES.get(id);
	}
	
	public List<Template> findAll() {
		return new ArrayList<>(TEMPLATES.values());
	}
	
	public int count() {
		return TEMPLATES.size();
	}
}
