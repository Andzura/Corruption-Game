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
import corruptiongame.item.Armor;
import corruptiongame.item.Consumable;
import corruptiongame.item.Item;
import corruptiongame.item.Weapon;

import java.util.Map;

import me.grea.antoine.utils.Dice;
import me.grea.antoine.utils.Log;

public class ControllerCombat extends Controller{
	private List<Enemy> enemy;
    private Map<Stats, Integer> statsPlayerDefault;
    private boolean playerAttacked;
    private long timer;
    private boolean updateBlock;
    private int skillChoosed;
    private int turn;
    private RPGCharacter target;
	private int itemChoosed;
    
	
	public ControllerCombat( RPGCharacter player,List<Enemy> enemy){
		super(player);
		this.enemy = enemy;
        this.statsPlayerDefault = player.getAllStats();
        playerAttacked = false;
        this.turn = 0;
        this.skillChoosed = -1;
        this.updateBlock = true;
        this.target = null;
        this.itemChoosed = -1;
        for(int i = 0; i < enemy.size(); i++){
        	enemy.get(i).applyStartName();
        	System.out.println(enemy.get(i).getName());
        }
       
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
				if(skillChoosed > -1 && target != null){
					useSkill(player, target, skillChoosed);
					turn++;
					skillChoosed = -1;
					target = null;
				}
				if(itemChoosed > -1){
					useItem();
					turn++;
					itemChoosed = -1;
					target = null;
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
        updateBlock = true;
	}
	
	public void useItem(){
		Item item = player.getInventory().get(itemChoosed);
		if(item instanceof Consumable){
			((Consumable) item).use(player);
		}else if(item instanceof Armor){
			player.equipArmor((Armor)item);
		}else if(item instanceof Weapon){
			player.equipWeapon((Weapon)item);
		}
	}
	
	public void chooseItem(int id){
		this.itemChoosed = id;
	}
	//if defense, apply it on the player and return true
	public boolean chooseSkill(int idSkill){
		this.skillChoosed = idSkill;
		if(player.getSkills().get(idSkill) instanceof Defense){
			target = player;
			return true;
		}
		return false;
	}
	
	public void chooseTarget(RPGCharacter target){
		this.target = target;
	}
	
	public void useRandomSkill(RPGCharacter src){
		int skillCount = src.getSkills().size();
		int rnd = Dice.roll(skillCount - 1);
		Skill skill = src.getSkills().get(rnd);
		if(skill instanceof Attack){
			skill.perform(src, player);
		}else{
			skill.perform(src, src);
		}
		if(src instanceof Enemy && skill instanceof Attack){
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
