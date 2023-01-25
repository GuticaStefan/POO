package WorldOfMarcel;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    ArrayList<Potion> potionList ;
    int maxInventoryWeight ;
    int coins ;
    Inventory(int maxInventoryWeight){
        coins = 0 ;
        this.maxInventoryWeight = maxInventoryWeight ;
        potionList = new ArrayList<>() ;
    }
    void addPotion(Potion potion){
        if(potion.getPrice() <= coins && (potion.Weight() + currentWeight() <= maxInventoryWeight))
            potionList.add(potion) ;
    }
    void removePotion(Potion potion){
        potionList.remove(potion) ;
    }
    int currentWeight(){
        int w = 0 ;
        for(int i = 0 ; i < potionList.size() ; i++)
            w += potionList.get(i).Weight() ;
        return w ;
    }
}
