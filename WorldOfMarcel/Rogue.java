package WorldOfMarcel;

import java.util.Random;

public class Rogue extends Character{
    Rogue(String character_name , int level , int exp){
        this.character_name = character_name ;
        this.level = level ;
        this.exp = exp ;
        strength = exp/2 ;
        dexterity = exp/2 ;
        charisma = exp ;
        fire = false ;
        ice = false ;
        earth = true ;
        current_health = 1500 ;
        max_health = 1500 ;
        current_mana =  900 ;
        max_mana =  900;
        inventory = new Inventory(8) ;
    }
    @Override
    public void receiveDamage(int damage) {

        Random randomizer = new Random();
        int chance = randomizer.nextInt(100) ;
        if(chance < (strength + chance))damage = damage / 2 ;
        current_health -= damage ;
    }

    @Override
    public int getDamage() {

        int damage = dexterity * 2 ;
        Random randomizer = new Random();
        int chance = randomizer.nextInt(100) ;
        if(chance < dexterity)damage = damage * 2 ;
        return damage;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
