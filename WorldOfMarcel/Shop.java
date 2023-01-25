package WorldOfMarcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop implements CellElement{
    ArrayList<Potion> shop ;
    Shop(){
        shop = new ArrayList<>() ;
        Random randomizer = new Random() ;
        for(int i = 1 ; i <= 4 ; i++){
            boolean rand = randomizer.nextBoolean() ;
            if(rand == true)
                shop.add(new HealthPotion()) ;
            else
                shop.add(new ManaPotion()) ;
        }
    }
    @Override
    public char toCharacter() {
        return 'S' ;
    }

    Potion getPotion(int i){
        System.out.println(shop.size());
        Potion potion = shop.get(i) ;
        shop.remove(i) ;
        return potion ;
    }

    @Override
    public String toString() {
        String string = "Sunteti la un shop ! Puteti cumpara ceva, sau puteti merge mai departe ! \n" ;
        for(int i = 0 ; i < shop.size() ; i++)
            string += (i + 1) + ". " +  shop.get(i) ;
        return string ;
    }
}
