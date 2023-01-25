package WorldOfMarcel;

import WorldOfMarcel.CellElement;

public class Cell {
    int Ox , Oy ;
    enum Type
    {
        EMPTY ,
        ENEMY ,
        SHOP ,
        FINISH
    }
    CellElement element ;
    boolean visited ;
    public Cell(int Ox , int Oy , CellElement element){
        this.visited = false ;
        this.Ox = Ox ;
        this.Oy = Oy ;
        this.element = element ;
    }
}
