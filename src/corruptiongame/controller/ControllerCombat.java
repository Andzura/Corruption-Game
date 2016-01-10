package corruptiongame.controller;

import java.util.ArrayList;
import java.util.List;

import corruptiongame.character.Attack;
import corruptiongame.character.Defense;
import corruptiongame.character.RPGCharacter;
import corruptiongame.character.Skill;
import corruptiongame.character.Stats;

import java.util.Map;

import me.grea.antoine.utils.Dice;
import me.grea.antoine.utils.Log;

public class ControllerCombat {
	private RPGCharacter player;
	private List<RPGCharacter> enemy;
        private Map<Stats, Integer> statsPlayerDefault;
	
	public ControllerCombat( RPGCharacter player,List<RPGCharacter> enemy){
		this.player = player;
		this.enemy = enemy;
                this.statsPlayerDefault = player.getAllStats();
	}
	
	public void chooseSkill(RPGCharacter src, RPGCharacter target, int idSkill){
		Skill choosed = src.getSkills().get(idSkill);
        choosed.perform(src, target);
        Log.d("enemy HP = " + target.getStats(Stats.HEALTH));
        Log.d("Player HP = " + src.getStats(Stats.HEALTH));
	}
	
	public void randomSkill(RPGCharacter src){
		int skillCount = src.getSkills().size();
		int rnd = Dice.roll(skillCount - 1);
		Skill skill = src.getSkills().get(rnd);
		if(skill.getClass() == Attack.class){
			skill.perform(src, player);
		}else{
			if(((Defense) skill).isSelfOnly())
				skill.perform(src, src);
			rnd = Dice.roll(enemy.size() - 1);
			skill.perform(src, enemy.get(rnd));
		}
	}
}
