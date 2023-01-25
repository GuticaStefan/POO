package WorldOfMarcel;

public abstract class Spell implements Visitor{
    int mana_cost , damage_value ;
    abstract void setDamage_value();
    abstract void setMana_cost() ;
}
