package WorldOfMarcel;

import java.util.List;

public abstract class Entity implements Element{
    List<Spell> abilities ;
    int current_health , max_health  ;
    int current_mana , max_mana  ;
    boolean fire , ice , earth ;
    public void life_regen(int health){
        current_health += health ;
        if(current_health > max_health)
            current_health = max_health ;
    }
    public void mana_regen(int mana){
        current_mana += mana ;
        if(current_mana > max_mana)
            current_mana = max_mana ;
    }
    public void use_ability(Spell spell , Enemy enemy){
        if(spell.mana_cost <= current_mana){
            enemy.receiveDamage(spell.damage_value) ;
            current_mana -= spell.mana_cost ;
        }
    }
    public abstract void receiveDamage(int damage);
    public abstract int getDamage() ;

}
