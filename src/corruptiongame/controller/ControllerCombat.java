package corruptiongame.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import corruptiongame.character.Attack;
import corruptiongame.character.Defense;
import corruptiongame.character.Enemy;
import corruptiongame.character.RPGCharacter;
import corruptiongame.character.Skill;
import corruptiongame.character.Stats;

import java.util.Map;

import me.grea.antoine.utils.Dice;
import me.grea.antoine.utils.Log;

public class ControllerCombat {
	private RPGCharacter player;
	private List<Enemy> enemy;
    private Map<Stats, Integer> statsPlayerDefault;
    private boolean playerAttacked;
    private long timer;
    private boolean updateBlock;
    private int skillChoosed;
    private int turn;
    private int target;
    
	
	public ControllerCombat( RPGCharacter player,List<Enemy> enemy){
		this.player = player;
		this.enemy = enemy;
        this.statsPlayerDefault = player.getAllStats();
        playerAttacked = false;
        this.turn = 0;
        this.skillChoosed = -1;
	}
	
	public boolean update(long elapsedTime){
		if(updateBlock){
			timer += elapsedTime;
			if(timer > 2000){
				updateBlock = false;
				timer = 0;
				for(int i = 0; i < enemy.size(); i++){
					enemy.get(i).applyDefaultName();
				}
			}
		}else{
			if(turn == 0){
				if(skillChoosed > -1){
					useSkill(player, enemy.get(target), skillChoosed);
					turn++;
					skillChoosed = -1;
				}
			}else{
				if(turn <= enemy.size()){
					useRandomSkill(enemy.get(turn - 1));
					turn++;
				}else{
					turn = 0;
				}
			}
		}
		return updateBlock;
	}
	public void useSkill(RPGCharacter src, RPGCharacter target, int idSkill){
		Skill choosed = src.getSkills().get(idSkill);
		int damage = target.getStats(Stats.HEALTH);
        choosed.perform(src, target);
        Log.d("enemy HP = " + target.getStats(Stats.HEALTH));
        Log.d("Player HP = " + src.getStats(Stats.HEALTH));
        damage -=  target.getStats(Stats.HEALTH);
        damage *= -1;
        if(choosed instanceof Attack){
        if(src.getName() == player.getName())
        	playerAttacked = true;
        target.setName(target.getName() + " " + String.valueOf(damage) +" HP");
        }
	}
	
	public void chooseSkill(int idSkill, int target){
		skillChoosed = idSkill;
		this.target = target;
	}
	
	public void useRandomSkill(RPGCharacter src){
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
		if(src instanceof Enemy){
			((Enemy) src).applyAttackName();
		}
		updateBlock = true;
	}
	public boolean checkEnd(){
		if(player.getStats(Stats.HEALTH) <= 0){
			return true;
		}
		else{
			boolean end = true;
			for(int i = 0; i < enemy.size(); i++){
				if(enemy.get(i).getStats(Stats.HEALTH) > 0)
					end = false;
			}
		return end;
				
		}
	}
	public void exit(){
		if(playerAttacked){
			player.modifyStats(Stats.EVIL, enemy.size());
			player.gainXp(enemy.size() * 10);
		}
		for(int i = 0; i < enemy.size(); i++){
			int n = Dice.roll(enemy.get(i).getInventory().size());
			if(n != 0){
				player.addItem(enemy.get(i).getInventory().get(n-1));
			}
		}
		Set<Stats> k = statsPlayerDefault.keySet();
		Iterator<Stats> i = k.iterator();
		Stats s;
		while(i.hasNext()){
			s = i.next();
			if(s != Stats.HEALTH)
				player.setStats(s, statsPlayerDefault.get(s));
		}
		
	}
}
