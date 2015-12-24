/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package corruptiongame.main;

import corruptiongame.Item.Armor;
import corruptiongame.Item.Item;
import corruptiongame.Item.Weapon;

/**
 *
 * @author p1303674
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	Item i = new Armor("lel", 2, 2);
    	
    	if(i.getClass() == Armor.class){
    		System.out.println("I est une armor");
    	}
    	if(i.getClass() == Item.class){
    		System.out.println("I est un Item");
    	}
    	if(i.getClass() == Weapon.class){
    		System.out.println("I est un Weapon");
    	}
    	
    	
    	if(i instanceof Armor){
    		System.out.println("I est une instance de armor");
    	}
    	if(i instanceof Item){
    		System.out.println("I est une instance de Item");
    	}
    	if(i instanceof Weapon){
    		System.out.println("I est une instance de Weapon");
    	}
    }
    
}
