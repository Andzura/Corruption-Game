package corruptiongame.controller;

import java.util.ArrayList;
import java.util.List;

import corruptiongame.character.Attack;
import corruptiongame.character.Defense;
import corruptiongame.character.Character;
import corruptiongame.character.Skill;
import me.grea.antoine.utils.Dice;

public class ControllerCombat {
	private Character player;
	private List<Character> enemy;
	
	public ControllerCombat( Character player,List<Character> enemy,boolean playerTurn){
		this.player = player;
		this.enemy = enemy;
	}
	
	public int chooseSkill(Character src, Character target, int idSkill){
		Skill choosed = src.getSkills().get(idSkill);
		return choosed.perform(src, target);		
	}
	
	public int randomSkill(Character src){
		int skillCount = src.getSkills().size();
		int rnd = Dice.roll(skillCount - 1);
		Skill skill = src.getSkills().get(rnd);
		if(skill.getClass() == Attack.class){
			return skill.perform(src, player);
		}else{
			if(((Defense) skill).isSelfOnly())
				return skill.perform(src, src);
			rnd = Dice.roll(enemy.size() - 1);
			return skill.perform(src, enemy.get(rnd));
		}
	}
}
