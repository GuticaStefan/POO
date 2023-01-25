package WorldOfMarcel;

import java.util.Random;

public class Warrior extends Character{
    Warrior(String character_name , int level , int exp){
        this.character_name = character_name ;
        this.level = level ;
        this.exp = exp ;
        strength = exp ;
        dexterity = exp/2 ;
        charisma = exp/2 ;
        fire = true ;
        ice = false ;
        earth = false ;
        current_health = 1500 ;
        max_health = 1500 ;
        current_mana =  900 ;
        max_mana =  900;
        inventory = new Inventory(10) ;
    }
    @Override
    public void receiveDamage(int damage) {

        Random randomizer = new Random();
        int chance = randomizer.nextInt(100) ;
        if(chance < (charisma + dexterity))damage = damage / 2 ;
        current_health -= damage ;
    }

    @Override
    public int getDamage() {

        int damage = strength * 2 ;
        Random randomizer = new Random();
        int chance = randomizer.nextInt(100) ;
        if(chance < strength)damage = damage * 2;
        return damage;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
