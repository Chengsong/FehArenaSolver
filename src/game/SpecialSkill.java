package game;

import java.util.HashMap;
import java.util.Map;

import utility.Util;

public enum SpecialSkill {
	AEGIS, AETHER, ASTRA, BLAZING_FLAME, BLAZING_LIGHT, BLAZING_THUNDER, BLAZING_WIND,
	BONFIRE, BUCKLER, CHILLING_WIND, DAYLIGHT, DRACONIC_AURA, DRAGON_FANG, DRAGON_GAZE,
	ESCUTCHEON, GALEFORCE, GLACIES, GLIMMER, GLOWING_EMBER, GROWING_FLAME, GROWING_LIGHT,
	GROWING_THUNDER, GROWING_WIND, HEAVENLY_LIGHT, HOLY_VESTMENTS, ICEBERG, IGNIS, IMBUE,
	KINDLED_FIRE_BALM, LUNA, MIRACLE, MOONBOW, NEW_MOON, NIGHT_SKY, NOONTIME, PAVISE, REPRISAL,
	RETRIBUTION, RISING_FLAME, RISING_LIGHT, RISING_THUNDER, RISING_WIND, SACRED_COWL, SOL,
	SOLID_EARTH_BALM, STILL_WATER_BALM, SWIFT_WINDS_BALM, VENGEANCE;
	
	private static final Map<SpecialSkill, Integer> skillCharges;
	static {
		skillCharges = new HashMap<>();
		skillCharges.put(AEGIS, 3);
		skillCharges.put(AETHER, 5);
		skillCharges.put(ASTRA, 5);
		skillCharges.put(BLAZING_FLAME, 5);
		skillCharges.put(BLAZING_LIGHT, 5);
		skillCharges.put(BLAZING_THUNDER, 5);
		skillCharges.put(BLAZING_WIND, 5);
		skillCharges.put(BONFIRE, 3);
		skillCharges.put(BUCKLER, 3);
		skillCharges.put(CHILLING_WIND, 3);
		skillCharges.put(DAYLIGHT, 3);
		skillCharges.put(DRACONIC_AURA, 3);
		skillCharges.put(DRAGON_FANG, 4);
		skillCharges.put(DRAGON_GAZE, 4);
		skillCharges.put(ESCUTCHEON, 2);
		skillCharges.put(GALEFORCE, 5);
		skillCharges.put(GLACIES, 4);
		skillCharges.put(GLIMMER, 3);
		skillCharges.put(GLOWING_EMBER, 4);
		skillCharges.put(GROWING_FLAME, 5);
		skillCharges.put(GROWING_LIGHT, 5);
		skillCharges.put(GROWING_THUNDER, 5);
		skillCharges.put(GROWING_WIND, 5);
		skillCharges.put(HEAVENLY_LIGHT, 2);
		skillCharges.put(HOLY_VESTMENTS, 3);
		skillCharges.put(ICEBERG, 3);
		skillCharges.put(IGNIS, 4);
		skillCharges.put(IMBUE, 1);
		skillCharges.put(KINDLED_FIRE_BALM, 1);
		skillCharges.put(LUNA, 3);
		skillCharges.put(MIRACLE, 5);
		skillCharges.put(MOONBOW, 2);
		skillCharges.put(NEW_MOON, 3);
		skillCharges.put(NIGHT_SKY, 4);
		skillCharges.put(NOONTIME, 3);
		skillCharges.put(PAVISE, 3);
		skillCharges.put(REPRISAL, 2);
		skillCharges.put(RETRIBUTION, 3);
		skillCharges.put(RISING_FLAME, 5);
		skillCharges.put(RISING_LIGHT, 5);
		skillCharges.put(RISING_THUNDER, 5);
		skillCharges.put(RISING_WIND, 5);
		skillCharges.put(SACRED_COWL, 2);
		skillCharges.put(SOL, 4);
		skillCharges.put(SOLID_EARTH_BALM, 1);
		skillCharges.put(STILL_WATER_BALM, 1);
		skillCharges.put(SWIFT_WINDS_BALM, 1);
		skillCharges.put(VENGEANCE, 3);
	}
	
	@Override
	public String toString() {
		return Util.capitalize(this.name());
	}
	
	/**
	 * Returns the charge turn of the given skill
	 * @param skill
	 * @return the skill charge
	 */
	public static int getCharge(SpecialSkill skill) {
		return skillCharges.get(skill);
	}
	
	public static boolean hasMeleeMitigation(SpecialSkill skill) {
		return skill.equals(BUCKLER) || skill.equals(ESCUTCHEON) || skill.equals(PAVISE);
	}
	
	public static boolean hasRangedMitigation(SpecialSkill skill) {
		return skill.equals(AEGIS) || skill.equals(HOLY_VESTMENTS) || skill.equals(SACRED_COWL);
	}
}
