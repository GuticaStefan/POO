package WorldOfMarcel;

public class Earth extends Spell{

    @Override
    void setDamage_value() {
        damage_value = 250 ;
    }

    @Override
    void setMana_cost() {
        mana_cost = 125 ;
    }

    @Override
    public void visit(Entity entity) {
        if(entity.earth)
            entity.receiveDamage(0);
        else
            entity.receiveDamage(damage_value);
    }
}
