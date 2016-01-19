package corruptiongame.character;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import me.grea.antoine.utils.Log;

public class Skills {
    private static List<Skill> skillList;

        public static void loadItems(){
                int nbItems;
                String name;
                int defense;
                int evil;
                int health;
                int strength;
                int weight;
                Scanner sc;
                InputStream is;

                skillList = new ArrayList<>();

                                                    // Loading attacks
                        sc = null;
                        is = Skill.class.getResourceAsStream("/skill/attack.txt");

                        if(is != null){
                            sc =new Scanner(is);
                            sc.useDelimiter("\\r?\\n");

                            if(sc.hasNextInt()){
                                nbItems = sc.nextInt();
                                Attack attack = null;

                                Scanner lsc = null;
                                for(int i = 0; i < nbItems; i++){
                                        name = sc.next();
                                        lsc = new Scanner(sc.next());
                                        defense = lsc.nextInt();
                                        evil = lsc.nextInt();
                                        health = lsc.nextInt();
                                        strength = lsc.nextInt();
                                        weight = lsc.nextInt();
                                        lsc.close();

                                        attack = new Attack(name, evil);
                                        skillList.add(attack);
                                }
                            }

                            sc.close();
                        }
                        else
                                Log.f("Armor file couldn't be found !");


                                                     // Loading consumables
                        sc = null;
                        is = Skills.class.getResourceAsStream("/skill/defense.txt");

                        if(is != null){
                            sc =new Scanner(is);
                            sc.useDelimiter("\\r?\\n");

                            if(sc.hasNextInt()){
                                nbItems = sc.nextInt();
                                Defense def = null;

                                Scanner lsc = null;
                                for(int i = 0; i < nbItems; i++){
                                        name = sc.next();
                                        lsc = new Scanner(sc.next());
                                        defense = lsc.nextInt();
                                        evil = lsc.nextInt();
                                        health = lsc.nextInt();
                                        strength = lsc.nextInt();
                                        weight = lsc.nextInt();
                                        lsc.close();

                                        if(defense != 0)
                                                def = new Defense(name, Stats.DEFENSE, evil);
                                        else if(health != 0)
                                                def = new Defense(name, Stats.HEALTH, evil);
                                        else if(strength != 0)
                                                def = new Defense(name, Stats.STRENGTH, evil);
                                                
                                        skillList.add(def);
                                }
                            }

                            sc.close();
                        }
                        else
                                Log.f("Consumable file couldn't be found !");
        }

        public static Skill getSkill(int id){
                return skillList.get(id).copy();
        }

        public static List<Skill> getSkill(List<Integer> listId){
                List<Skill> listSkill = new ArrayList<>();
                for(int i = 0; i < listId.size(); i++){
                        listSkill.add(Skills.getSkill(listId.get(i)));
                }
                return listSkill;
        }
}
