package WorldOfMarcel;
import java.util.Random;
public class Enemy extends Entity implements CellElement{
    public Enemy(){
        max_health = 1000 ;
        max_mana = 700 ;
        Random randomizer = new Random() ;
        current_health = randomizer.nextInt(max_health - 500) + 500;
        current_mana = randomizer.nextInt(max_mana - 150) + 150 ;
        fire = randomizer.nextBoolean() ;
        ice = randomizer.nextBoolean() ;
        earth = randomizer.nextBoolean() ;
    }
    @Override
    public void receiveDamage(int damage) {
        Random randomizer = new Random() ;
        boolean miss_chance = randomizer.nextBoolean() ;
        if(miss_chance == false)
            current_health -= damage ;
    }

    @Override
    public int getDamage() {
        Random randomizer = new Random() ;
        boolean critical_chance = randomizer.nextBoolean() ;
        int damage = randomizer.nextInt(40) + 20 ;
        if(critical_chance == true)
            damage *= 2 ;
        return damage ;
    }

    @Override
    public char toCharacter() {
        return 'E' ;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
