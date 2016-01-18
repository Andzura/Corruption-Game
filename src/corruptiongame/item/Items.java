package corruptiongame.item;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import me.grea.antoine.utils.Log;

public class Items {
        private static List<Item> itemList;

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

                itemList = new ArrayList<>();

                                                    // Loading armors
                        sc = null;
                        is = Items.class.getResourceAsStream("/data/armor.txt");

                        if(is != null){
                            sc =new Scanner(is);
                            sc.useDelimiter("\\r?\\n");

                            if(sc.hasNextInt()){
                                nbItems = sc.nextInt();
                                Armor armor = null;

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

                                        armor = new Armor(name, defense, evil, health, strength, weight);
                                        itemList.add(armor);
                                }
                            }

                            sc.close();
                        }
                        else
                                Log.f("Armor file couldn't be found !");


                                                     // Loading consumables
                        sc = null;
                        is = Items.class.getResourceAsStream("/data/consumable.txt");

                        if(is != null){
                            sc =new Scanner(is);
                            sc.useDelimiter("\\r?\\n");

                            if(sc.hasNextInt()){
                                nbItems = sc.nextInt();
                                Consumable consumable = null;

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

                                        consumable = new Consumable(name, defense, evil, health, strength, weight);
                                        itemList.add(consumable);
                                }
                            }

                            sc.close();
                        }
                        else
                                Log.f("Consumable file couldn't be found !");


                                                    // Loading weapons
                        sc = null;
                        is = Items.class.getResourceAsStream("/data/weapon.txt");

                        if(is != null){
                            sc =new Scanner(is);
                            sc.useDelimiter("\\r?\\n");

                            if(sc.hasNextInt()){
                                nbItems = sc.nextInt();
                                Weapon weapon = null;

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

                                        weapon = new Weapon(name, defense, evil, health, strength, weight);
                                        itemList.add(weapon);
                                }
                            }

                            sc.close();
                        }
                        else
                                Log.f("Armor file couldn't be found !");
        }

        public static Item getItem(int id){
                return itemList.get(id).copy();
        }

        public static List<Item> getItem(List<Integer> listId){
                List<Item> listItem = new ArrayList<>();
                for(int i = 0; i < listId.size(); i++){
                        listItem.add(Items.getItem(listId.get(i)));
                }
                return listItem;
        }
}
