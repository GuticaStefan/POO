package WorldOfMarcel;

public class CharacterFactory {
    public static Character factory(String character_name , String name , int level , int exp){
        if(character_name.equals("Warrior"))
            return  new Warrior(name , level , exp) ;
        if(character_name.equals("Rogue"))
            return  new Rogue(name , level , exp) ;
        if(character_name.equals("Mage"))
            return new Mage(name , level , exp) ;
        return null ;
    }
}
