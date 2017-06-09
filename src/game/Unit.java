package game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents a Unit in FEH, which is able to fight against another unit.
 */
public class Unit {
	public enum Triangle {
		RED, BLUE, GREEN, COLORLESS;
		
		@Override
		public String toString() {
			return capitalize(this.name().toLowerCase());
		}
	}
	public enum Movement {
		INFANTRY, ARMOR, HORSE, FLIER;
		
		@Override
		public String toString() {
			return capitalize(this.name().toLowerCase());
		}
	}
	private enum Debuff {
		GRAVITY, PANIC
	}
	
	private static final int HP = 0;
	private static final int ATK = 1;
	private static final int DEF = 2;
	private static final int RES = 3;
	private static final int SPD = 4;
	private static final String NONE = "NONE";
	
	private final Map<String, Integer> skills; // <skill name, skill level>, includes weapon skill
	private final String assist;
	private final String special;
	private final int[] stats; // hp, atk, def, res, spd
	private final int[][] statChanges; 	// statChanges[0]: changes that only lasts for one battle, 
										// statChanges[1]: changes that lasts throughout the turn
	private final Set<Debuff> debuffs;
	private final int range; // range 0 means no weapon
	private final Triangle type;
	private final Movement moveType;
	private final boolean isHealer;
	
	private int currentHP;
	private int skillCharge;
	
	/**
	 * Creates new Unit specified by the builder
	 * @param b the builder
	 */
	private Unit(Builder b) {
		this.skills = b.skills;
		this.assist = b.assist;
		this.special = b.special;
		this.stats = b.stats;
		this.statChanges = new int[2][5];
		this.debuffs = new HashSet<>();
		this.range = b.range;
		this.type = b.type;
		this.moveType = b.moveType;
		this.isHealer = b.isHealer;
		this.currentHP = stats[HP];
		this.skillCharge = 0;
	}
	
	/**
	 * Fights against the enemy unit, returns the damage done
	 * @param enemy the enemy unit
	 * @return the damage done by this unit on enemy
	 */
	public int fight(Unit enemy) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Attacks the enemy unit, return whether the enemy unit is alive
	 * @param enemy the enemy unit
	 * @return true iff enemy is alive
	 */
	private boolean attack(Unit enemy) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Apply the given debuff to given enemy
	 * @param enemy the enemy unit
	 * @param debuff the debuff effect
	 */
	private void applyDebuff(Unit enemy, Debuff debuff) {
		enemy.debuffs.add(debuff);
	}
	
	/**
	 * Returns how many tiles this unit can move
	 * @return the number of tiles this unit is able to move
	 * @throws IllegalStateException if no movement type is specified
	 */
	public int getMovement() {
		if (debuffs.contains(Debuff.GRAVITY)) {
			return 1;
		}
		
		switch (moveType) {
		case INFANTRY: return 2;
		case ARMOR: return 1;
		case HORSE: return 3;
		case FLIER: return 2;
		default: throw new IllegalStateException();
		}
	}
	
	/**
	 * Returns whether this unit can fly
	 * @return true iff this unit can fly over mountains/rivers
	 */
	public boolean canFly() {
		return moveType == Movement.FLIER;
	}
	
	/**
	 * Returns whether this unit can pass other units
	 * @return true iff this unit can pass other units
	 */
	public boolean canPass() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns whether the unit is alive
	 * @return true iff the unit's HP > 0
	 */
	public boolean isAlive() {
		return currentHP > 0;
	}
	
	// ----- STATIC CODE BELOW -----
	
	/**
	 * Helper method that returns the string with its first letter capitalized
	 * @requires s.length() > 0
	 * @param s the input string
	 * @return the string with its first letter capitalized
	 */
	private static String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
	/**
	 * Builder for Unit
	 */
	public static class Builder {
		private Map<String, Integer> skills;
		private int[] stats;
		private int range;
		private Triangle type;
		private Movement moveType;
		private boolean isHealer;
		private String assist;
		private String special;
		
		/**
		 * Creates a base unit with specified weapon and movement types
		 * @param type weapon type
		 * @param moveType movement type
		 */
		public Builder(Triangle type, Movement moveType) {
			this.type = type;
			this.moveType = moveType;
			this.skills = new HashMap<>();
			this.stats = new int[5];
			this.assist = NONE;
			this.special = NONE;
		}
		
		/**
		 * Set the stats of the unit
		 * @param hp
		 * @param atk
		 * @param def
		 * @param res
		 * @param spd
		 * @return this
		 */
		public Builder stats(int hp, int atk, int def, int res, int spd) {
			stats[HP] = hp;
			stats[ATK] = atk;
			stats[DEF] = def;
			stats[RES] = res;
			stats[SPD] = spd;
			return this;
		}
		
		/**
		 * Adds a skill that does not have a skill level
		 * @param skillName
		 * @return this
		 */
		public Builder skill(String skillName) {
			return skill(skillName, 0);
		}
		
		
		/**
		 * Adds a skill with skill level
		 * @param skillName
		 * @param skillLevel
		 * @return this
		 */
		public Builder skill(String skillName, int skillLevel) {
			skills.put(skillName, skillLevel);
			return this;
		}
		
		/**
		 * Set the assist skill
		 * @param skillName
		 * @return this
		 */
		public Builder assist(String skillName) {
			assist = skillName;
			return this;
		}
		
		/**
		 * Set the special skill
		 * @param skillName
		 * @return this
		 */
		public Builder special(String skillName) {
			special = skillName;
			return this;
		}
		
		/**
		 * Set the range of the unit, default value 0 (no weapon)
		 * @param range Range of the unit, usually between 1-2. 0 range indicates no weapon.
		 * @return this
		 */
		public Builder range(int range) {
			this.range = range;
			return this;
		}
		
		/**
		 * Mark the unit as a healer
		 * @return this
		 */
		public Builder isHealer() {
			isHealer = true;
			return this;
		}
		
		/**
		 * Build the Unit object given the builder configuration.
		 * @return a new Unit object
		 */
		public Unit build() {
			return new Unit(this);
		}
	}
}
