package WorldOfMarcel;

import java.util.Random;

public class Mage extends Character{
    Mage(String character_name , int level , int exp){
        this.character_name = character_name ;
        this.level = level ;
        this.exp = exp ;
        strength = exp/2 ;
        dexterity = exp ;
        charisma = exp/2 ;
        fire = false ;
        ice = true ;
        earth = false ;
        current_health = 1500 ;
        max_health = 1500 ;
        current_mana =  900 ;
        max_mana =  900;
        inventory = new Inventory(6) ;
    }
    @Override
    public void receiveDamage(int damage)
    {
        Random randomizer = new Random();
        int chance = randomizer.nextInt(100) ;
        if(chance < (strength + dexterity))damage = damage / 2 ;
        current_health -= damage ;
    }

    @Override
    public int getDamage() {
        int damage = charisma * 2 ;
        Random randomizer = new Random();
        int chance = randomizer.nextInt(100) ;
        if(chance < charisma)damage = damage * 2 ;
        return damage;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
