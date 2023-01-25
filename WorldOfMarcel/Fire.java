package WorldOfMarcel;

public class Fire extends Spell{

    @Override
    void setDamage_value() {
        damage_value = 300 ;
    }

    @Override
    void setMana_cost() {
        mana_cost = 150 ;
    }

    @Override
    public void visit(Entity entity) {
        if(entity.fire)
            entity.receiveDamage(0);
        else
            entity.receiveDamage(damage_value);
    }
}
