package WorldOfMarcel;

public class HealthPotion implements Potion{
    @Override
    public void usePotion(Character character) {
        character.life_regen(regenValue());
        System.out.println("Ai folosit o potiune de viata !Acum nivelul tau de viata este " + character.current_health + " !");
        character.inventory.removePotion(this);
    }

    @Override
    public int getPrice() {
        return 5 ;
    }

    @Override
    public int regenValue() {
        return 300 ;
    }

    @Override
    public int Weight() {
        return 1 ;
    }

    @Override
    public String toString() {
        String s = "HealthPotion , price: " + getPrice() + " , regenValue: " + regenValue() + ", weight: " + Weight() + "\n" ;
        return  s ;
    }
}
