package WorldOfMarcel;

public abstract class Character extends Entity{
    String character_name ;
    int Ox , Oy ;
    int exp ;
    int level ;
    int strength , charisma , dexterity ;
    Inventory inventory ;
    void buyPotion(Potion potion){
        if(inventory.coins >= potion.getPrice() &&
                (inventory.currentWeight() + potion.Weight()) <= inventory.maxInventoryWeight){
            inventory.addPotion(potion) ;
            inventory.coins -= potion.getPrice() ;
        }
    }

}
