package WorldOfMarcel;

import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {
    private final int width , length ;
    Character character ;
    Cell current ;
    static Grid map = null ;
    private Grid(int length , int width){
        this.length = length ;
        this.width = width ;
        //this.character = character ;
        this.current = new Cell(0, 0, new CellElement() {
            @Override
            public char toCharacter() {
                return 'P';
            }
        }) ;
    }
    public static Grid generateMap(int length , int width){
        if(map == null){
            map = new Grid(length , width) ;
            for(int i = 0 ; i < 5 ; i++){
                map.add(i , new ArrayList<Cell>()) ;
                for(int j = 0 ; j < 5 ; j++){
                    if( (i == 0 && j == 3) || (i == 1 && j == 3) || (i == 2 && j == 0) ){
                        map.get(i).add( new Cell(i, j, new Shop()) ) ;
                    }
                    else if(i == 0 && j == 0){
                        map.get(i).add( new Cell(i, j, new CellElement() {
                            @Override
                            public char toCharacter() {
                                return 'P';
                            }

                        }));
                    map.get(i).get(j).visited = true ;
                    }
                    else if( i == 3 && j == 4)
                        map.get(i).add( new Cell(i , j , new Enemy()) ) ;
                    else if(i == 4 && j == 4)
                        map.get(i).add( new Cell(i, j, new CellElement() {
                            @Override
                            public char toCharacter() {
                                return 'F';
                            }
                        }));
                    else
                        map.get(i).add( new Cell(i, j, new CellElement() {
                            @Override
                            public char toCharacter() {
                                return 'N';
                            }
                        }));

                }

            }
        }
        return map ;
    }
    public void goNorth(){
        if(current.Ox == 0)
            System.out.println("Nu se poate deplasa spre nord !");
        else{
            current = map.get(current.Ox - 1).get(current.Oy) ;
            //current.visited = true ;
        }

    }
    public void goSouth(){
        if(current.Ox == length - 1)
            System.out.println("Nu se poate deplasa spre sud !");
        else {
            current = map.get(current.Ox + 1).get(current.Oy) ;
            //current.visited = true ;
        }
    }
    public void goWest(){
        if(current.Oy == 0)
            System.out.println("Nu se poate deplasa spre vest !");
        else
        {
            current = map.get(current.Ox).get(current.Oy - 1) ;
            //current.visited = true ;
        }

    }
    public void goEast(){
        if(current.Oy == width - 1)
            System.out.println("Nu se poate deplasa spre est !");
        else{
            current = map.get(current.Ox).get(current.Oy + 1) ;
            //current.visited = true ;
        }

    }
    void printMap(){
        for(int i = 0 ; i < map.size() ; i++){
            for(int j = 0 ; j < map.get(i).size() ; j++)
                if(map.get(i).get(j).visited == false) System.out.print("? ");
                    else
                    System.out.print(map.get(i).get(j).element.toCharacter() + " ");

            System.out.println();
        }
    }
}
