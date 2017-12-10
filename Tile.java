//Christine Breckendridge, Sherin Stevens, Katelyn Jaing
//Period 4
public class Tile{
    private int color;
    private int row;
    private int col;
    
    public Tile(int x, int r, int c){
        color = x;
        row = r;
        col = c;
    }
    
   public int getRow(){
       return row;
   }
   
   public int getCol(){
       return col;
   }
   
   public int getColor(){
       return color;
   }
   
   public void setColor(int c){
       color = c;
   }
}
