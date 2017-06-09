package game;

import java.util.HashMap;
import java.util.Map;

import utility.Util;

public enum SpecialSkill {
	LUNA, MOONBOW;
	
	private static final Map<SpecialSkill, Integer> skillCharges;
	static {
		skillCharges = new HashMap<>();
		skillCharges.put(LUNA, 3);
		skillCharges.put(MOONBOW, 2);
	}
	
	@Override
	public String toString() {
		return Util.capitalize(this.name());
	}
}
