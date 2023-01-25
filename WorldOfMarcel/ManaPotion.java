package WorldOfMarcel;

public class ManaPotion implements Potion{

    @Override
    public void usePotion(Character character) {
        character.mana_regen(regenValue());
        System.out.println("Ai folosit o potiune de mana !Acum nivelul tau de mana este " + character.current_mana + " !");
        character.inventory.removePotion(this);
    }

    @Override
    public int getPrice() {
        return 3;
    }

    @Override
    public int regenValue() {
        return 200;
    }

    @Override
    public int Weight() {
        return 1;
    }

    @Override
    public String toString() {
        String s = "ManaPotion , price: " + getPrice() + " , regenValue: " + regenValue() + ", weight: " + Weight() + "\n" ;
        return  s ;
    }
}
