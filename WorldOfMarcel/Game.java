package WorldOfMarcel;

import WorldOfMarcel.Account;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Game {
    ArrayList<Account> accountList = new ArrayList<>();
    HashMap<Cell.Type , ArrayList<String>> storiesMap = new HashMap<>();
    private static Game obj = null ;
    private Game(){

    }
    public static Game getInstance(){
        if(obj == null)
            obj = new Game() ;
        return obj ;
    }
    void parsingAccounts(){
        File input = new File("C:\\Users\\Stefan\\IdeaProjects\\TemaPoo\\src\\WorldOfMarcel\\accounts.json") ;
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input)) ;
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray accounts = fileObject.get("accounts").getAsJsonArray() ;
            for(JsonElement accountsElement : accounts){
                JsonObject jsonObject = accountsElement.getAsJsonObject() ;
                JsonObject credentials = jsonObject.get("credentials").getAsJsonObject() ;
                String email = credentials.get("email").getAsString() ;
                String password = credentials.get("password").getAsString() ;
                String name = jsonObject.get("name").getAsString();
                String country = jsonObject.get("country").getAsString();
                JsonArray favorite_games = jsonObject.get("favorite_games").getAsJsonArray() ;
                TreeSet<String> games = new TreeSet<>() ;
                for(JsonElement favorite_gamesElement : favorite_games){
                    games.add(favorite_gamesElement.getAsString()) ;
                }

                int maps_completed = jsonObject.get("maps_completed").getAsInt() ;
                JsonArray characters = jsonObject.get("characters").getAsJsonArray() ;
                ArrayList<Character> characterList = new ArrayList<>() ;
                for(JsonElement charactersElement : characters){
                    JsonObject object = charactersElement.getAsJsonObject() ;
                    String character_name = object.get("name").getAsString() ;
                    String profession = object.get("profession").getAsString() ;
                    int level = object.get("level").getAsInt() ;
                    int experience = object.get("experience").getAsInt() ;
                    characterList.add(CharacterFactory.factory(profession , character_name , level , experience)) ;
                }
                Account.Information.InformationBuilder builder = new Account.Information.InformationBuilder(name , country ,new Credentials(email , password))  ;
                accountList.add(new Account(new Account.Information(builder), characterList , maps_completed)) ;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    void parsingStories(){
        storiesMap.put(Cell.Type.EMPTY , new ArrayList<>()) ;
        storiesMap.put(Cell.Type.ENEMY, new ArrayList<>()) ;
        storiesMap.put(Cell.Type.SHOP , new ArrayList<>()) ;
        storiesMap.put(Cell.Type.FINISH , new ArrayList<>()) ;
        File input = new File("C:\\Users\\Stefan\\IdeaProjects\\TemaPoo\\src\\WorldOfMarcel\\stories.json");
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input)) ;
            JsonObject jsonObject = fileElement.getAsJsonObject() ;
            JsonArray jsonArray = jsonObject.get("stories").getAsJsonArray();
            for(JsonElement jsonElement : jsonArray){
                JsonObject object = jsonElement.getAsJsonObject() ;
                String type = object.get("type").getAsString() ;
                String value = object.get("value").getAsString() ;
                if(type.equals("EMPTY"))
                    storiesMap.get(Cell.Type.EMPTY).add(value) ;
                if(type.equals("ENEMY"))
                    storiesMap.get(Cell.Type.ENEMY).add(value) ;
                if(type.equals("SHOP"))
                    storiesMap.get(Cell.Type.SHOP).add(value) ;
                if(type.equals("FINISH"))
                    storiesMap.get(Cell.Type.FINISH).add(value) ;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void printStories(Cell current){
        if(current.visited)
            return ;
        if(current.element.toCharacter() == 'S'){
            Random randomizer = new Random();
            int rand = randomizer.nextInt(storiesMap.get(Cell.Type.SHOP).size()) ;
            System.out.println(storiesMap.get(Cell.Type.SHOP).get(rand));

        }
        if(current.element.toCharacter() == 'E'){
            Random randomizer = new Random();
            int rand = randomizer.nextInt(storiesMap.get(Cell.Type.ENEMY).size()) ;
            System.out.println(storiesMap.get(Cell.Type.ENEMY).get(rand));
        }
        if(current.element.toCharacter() == 'N'){
            Random randomizer = new Random();
            int rand = randomizer.nextInt(storiesMap.get(Cell.Type.EMPTY).size()) ;
            System.out.println(storiesMap.get(Cell.Type.EMPTY).get(rand));
        }
        if(current.element.toCharacter() == 'F'){
            Random randomizer = new Random();
            int rand = randomizer.nextInt(storiesMap.get(Cell.Type.FINISH).size()) ;
            System.out.println(storiesMap.get(Cell.Type.FINISH).get(rand));
        }
        current.visited = true ;
    }

    void run() throws Exception{
        parsingAccounts(); ;
        parsingStories();
        System.out.println("Alege modul de joc : terminal sau fereastra grafica");
        Scanner scanner = new Scanner(System.in) ;
        String command = scanner.nextLine() ;
        String email , password;
        if(command.equals("terminal")){
            System.out.print("Introduceti email-ul : ");
            email = scanner.nextLine() ;
            System.out.print("Introduceti parola : ");
            password = scanner.nextLine() ;
            int k ;
            for(k = 0 ; k < accountList.size() ; k++) {
                if (accountList.get(k).playerInformation.credentials.getEmail().equals(email)
                        && accountList.get(k).playerInformation.credentials.getPassword().equals(password))
                    break;

            }
                if(k == accountList.size())
                    System.out.println("Nume sau parola incorecte !");
                else{
                    System.out.println("Logarea a avut succes !");
                    System.out.println("Alegeti un personaj al contului introducand numarul acestuia : ");
                    for(int i = 0 ; i <  accountList.get(k).list.size() ; i++)
                        System.out.println((i+1) + ". " + accountList.get(k).list.get(i).character_name);
                    int nr = scanner.nextInt() - 1 ;
                    Character player =  CharacterFactory.factory(accountList.get(k).list.get(nr).getClass().getSimpleName() , accountList.get(k).list.get(nr).character_name , accountList.get(k).list.get(nr).level , accountList.get(k).list.get(nr).exp) ;
                    player.inventory.coins = 100 ;
                    System.out.println("Caracterul din rasa " + accountList.get(k).list.get(nr).getClass().getSimpleName() + " a fost ales cu succes !");

                    Grid map = Grid.generateMap(5 , 5) ;
                        System.out.println("Introduceti comanda -p- pentru a avansa in joc !");
                        scanner.nextLine() ;
                        command = scanner.nextLine() ;
                        if(command.equals("p") == false)throw new InvalidCommandException() ;
                        while(map.current.element.toCharacter() != 'S'){
                                map.goEast(); ;
                                printStories(map.current);
                                map.printMap();
                                command = scanner.nextLine() ;
                        }
                        Shop shop = (Shop) map.current.element ;
                        System.out.println(shop);
                        for(int i = 0 ; i < shop.shop.size() ; i++)
                            if(shop.shop.get(i).getClass().getSimpleName().equals("HealthPotion")){
                                player.buyPotion(shop.shop.get(i));
                                System.out.println("Potiunea de tipul HealthPotion a fost achizitionata cu succes !");
                                break ;
                            }
                        scanner.nextLine() ;
                        for(int i = 0 ; i < shop.shop.size() ; i++){
                            if(shop.shop.get(i).getClass().getSimpleName().equals("ManaPotion")){
                                player.buyPotion(shop.shop.get(i));
                                System.out.println("Potiunea de tipul ManaPotion a fost achizitionata cu succes !");
                                break ;
                            }
                        }

                    command = scanner.nextLine() ;
                    if(command.equals("p") == false)throw new InvalidCommandException() ;
                        map.goEast();
                        printStories(map.current);
                        map.printMap();
                        for(int i = 0 ; i < 3 ; i++){
                            command = scanner.nextLine() ;
                            if(command.equals("p") == false)throw new InvalidCommandException() ;
                            map.goSouth();
                            printStories(map.current);
                            map.printMap();
                        }
                        if(map.current.element.toCharacter() == 'E'){
                            System.out.println("Lupta a inceput !Tu ataci primul !");
                        }
                        Enemy enemy = (Enemy) map.current.element ;
                        enemy.abilities = new ArrayList<>() ;
                        player.abilities = new ArrayList<>() ;
                        Random randomizer = new Random();
                        for(int i = 0 ; i < 4 ; i++){
                            int ability = randomizer.nextInt(3) ;
                            if( ability == 0 )
                                player.abilities.add(new Fire()) ;
                            if(ability == 1)
                                player.abilities.add(new Ice()) ;
                            if(ability == 2)
                                player.abilities.add(new Earth()) ;
                            player.abilities.get(i).setDamage_value();
                            player.abilities.get(i).setMana_cost();
                        }
                    for(int i = 0 ; i < 4 ; i++){
                        int ability = randomizer.nextInt(3) ;
                        if( ability == 0 )
                            enemy.abilities.add(new Fire()) ;
                        if(ability == 1)
                            enemy.abilities.add(new Ice()) ;
                        if(ability == 2)
                            enemy.abilities.add(new Earth()) ;
                        enemy.abilities.get(i).setDamage_value();
                        enemy.abilities.get(i).setMana_cost();
                    }
                    int turn = 0 ; // player ataca pe turn = 0
                        while (player.current_health > 0 && enemy.current_health > 0){
                            command = scanner.nextLine() ;
                            if(command.equals("p") == false)throw new InvalidCommandException() ;
                            if(turn == 0){
                                int dmg = enemy.current_health ;
                                if(player.abilities.size() != 0 && player.current_mana >= player.abilities.get(0).mana_cost){
                                    enemy.accept(player.abilities.get(0)) ;
                                    player.current_mana -= player.abilities.get(0).mana_cost ;
                                    dmg = dmg - enemy.current_health ;
                                    System.out.println("Inamicul a primit " + dmg  + " damage folosind abilitatea " + player.abilities.get(0).getClass().getSimpleName() +" ramanand cu " + enemy.current_health + " viata si " + enemy.current_mana + " mana !");
                                    player.abilities.remove(0) ;
                                }
                                else {
                                    if(player.inventory.potionList.size() > 0){
                                        player.inventory.potionList.get(0).usePotion(player);
                                        player.inventory.potionList.get(0).usePotion(player);
                                        System.out.println("da");
                                    }
                                    enemy.receiveDamage(player.getDamage());
                                    dmg = dmg - enemy.current_health ;
                                    System.out.println("Inamicul a primit " + dmg + " damage folosind un basic hit, ramanand cu " + enemy.current_health + " viata si " + enemy.current_mana + " mana !");
                                }
                                turn = 1 ;
                            }
                            else{
                                int dmg = player.current_health ;
                                if(enemy.abilities.size() != 0 && enemy.current_mana >= enemy.abilities.get(0).mana_cost){
                                    player.accept(enemy.abilities.get(0));
                                    enemy.current_mana -= enemy.abilities.get(0).mana_cost ;
                                    dmg = dmg - player.current_health ;
                                    System.out.println("Inamicul ti-a dat " + dmg + " damage folosind abilitatea " + enemy.abilities.get(0).getClass().getSimpleName() +" ramanand cu " + player.current_health + " viata si " + player.current_mana + " mana !");
                                    enemy.abilities.remove(0) ;
                                }
                                else {
                                    player.receiveDamage(enemy.getDamage());
                                    dmg = dmg - player.current_health ;
                                    System.out.println("Inamicul ti-a dat " + dmg + " damage cu un basic hit si ai ramas cu " + player.current_health + " viata si" + player.current_mana + " mana !");
                                }
                                turn = 0 ;
                            }
                        }
                    if(enemy.current_health > 0)
                        System.out.println("Ai pierdut batalia !");
                    else {
                        System.out.println("Ai castigat batalia !");
                        int chance = randomizer.nextInt(5) ;
                        if(chance != 4){
                            player.inventory.coins += 10 ;
                            System.out.println("Ai capturat 10 monede de la inamic !");
                        }
                        System.out.println("Foloseste comanda -p- pentru a merge mai departe !");
                        command = scanner.nextLine() ;
                        if(command.equals("p") == false)throw new InvalidCommandException() ;
                        map.goSouth();
                        printStories(map.current);
                        map.printMap();
                        if(map.current.element.toCharacter() == 'F'){
                            System.out.println("Felicitari ! Ai ajuns la finalul jocului !");
                        }
                    }
                }
        }
        else if(command.equals("fereastra grafica")){
            LoginInterface loginInterface = new LoginInterface() ;
            loginInterface.run();

        }
        else throw new InvalidCommandException() ;
    }
}
