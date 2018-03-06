
package com.github.epd.sprout.actors.mobs;

import com.github.epd.sprout.Challenges;
import com.github.epd.sprout.Dungeon;
import com.github.epd.sprout.actors.Char;
import com.github.epd.sprout.actors.buffs.Blindness;
import com.github.epd.sprout.actors.buffs.Buff;
import com.github.epd.sprout.items.Generator;
import com.github.epd.sprout.items.food.Meat;
import com.github.epd.sprout.messages.Messages;
import com.github.epd.sprout.sprites.BrownBatSprite;
import com.github.epd.sprout.utils.GLog;
import com.watabou.utils.Random;

public class BrownBat extends Mob {

	{
		name = Messages.get(this, "name");
		spriteClass = BrownBatSprite.class;

		HP = HT = 6;
		defenseSkill = 2;

		EXP = 1;
		maxLvl = 15;

		flying = true;

		if (Dungeon.moreLoots) {
			loot = Generator.Category.BERRY;
			lootChance = 0.75f;

			lootOther = new Meat();
			lootChanceOther = 1f;
		} else {

			loot = new Meat();
			lootChance = 0.5f; // by default, see die()

			lootOther = Generator.Category.BERRY;
			lootChanceOther = 0.05f; // by default, see die()
		}

	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(1, 4);
	}

	@Override
	public int attackSkill(Char target) {
		return 5 + Dungeon.depth;
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		if (Random.Int(10) == 0) {
			Buff.prolong(enemy, Blindness.class, Random.Int(3, 10));
			GLog.n(Messages.get(BrownBat.class, "blind"));
			Dungeon.observe();
			state = FLEEING;
		}

		return damage;
	}

	@Override
	public int dr() {
		return 1;
	}

	@Override
	public String defenseVerb() {
		return Messages.get(this, "def");
	}

	@Override
	public void die(Object cause) {

		if (Random.Int(5) == 0) {
			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				if (Random.Int(2) == 0 && enemy != null) {
					mob.beckon(enemy.pos);
				}
			}
			GLog.n(Messages.get(BrownBat.class, "die"));
		}

		super.die(cause);

	}


	@Override
	public String description() {
		return Messages.get(this, "desc");
	}


}
