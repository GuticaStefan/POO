package WorldOfMarcel;

public class Ice extends Spell{
    @Override
    void setDamage_value() {
        damage_value = 200 ;
    }

    @Override
    void setMana_cost() {
        mana_cost = 100 ;
    }


    @Override
    public void visit(Entity entity) {
        if(entity.ice)
            entity.receiveDamage(0);
        else
            entity.receiveDamage(damage_value);
    }
}
