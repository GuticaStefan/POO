package WorldOfMarcel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Account {
    Information playerInformation ;
    ArrayList<Character> list ;
    int gamesPlayed ;
    public Account(Information information , ArrayList<Character> list , int gamesPlayed){
        playerInformation = information ;
        this.list = list ;
        this.gamesPlayed = gamesPlayed;
    }
     static class Information{
        Credentials credentials ;
        TreeSet<String> favGames ;
        String name , country ;
        public Information(InformationBuilder builder){
            this.name = builder.name ;
            this.country = builder.country ;
            this.credentials = builder.credentials ;
            this.favGames = builder.favGames ;
        }
        public static class InformationBuilder{
            Credentials credentials ;
            TreeSet<String> favGames ;
            String name , country ;
            public InformationBuilder(String name , String country , Credentials credentials){
                this.name = name ;
                this.country = country ;
                setCredentials(credentials) ;
            }
            public void setCredentials(Credentials credentials) {
                this.credentials = credentials ;
            }

            public InformationBuilder setFavGames(TreeSet<String> favGames) {
                this.favGames = favGames ;
                return this ;
            }

            public InformationBuilder setCountry(String country) {
                this.country = country ;
                return this ;
            }

            public InformationBuilder setName(String name) {
                this.name = name ;
                return this ;
            }
        }
    }
}
