//Christine Breckendridge, Sherin Stevens, Katelyn Jaing
//Period 4
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Board 
{
    private Tile[][] board;
    private int currentPlayer;
    private int count1;
    private int count2;
  
    public Board(){ //Sherin
        board = new Tile[8][8];
        board[3][3] = new Tile(1,3,3); 
        board[3][4] = new Tile(2,3,4);
        board[4][3] = new Tile(2,4,3);
        board[4][4] = new Tile(1,4,4);
        currentPlayer = 1;
        count1 = 2;
        count2 = 2;
    }
    
    public boolean isValid(Tile n){ 
        if( board[n.getRow()][n.getCol()] != null )
            return false;
        boolean ans = true;
        for( Tile x: checkCol(n) )
        {
            if( x == null )
            {
                for( Tile y: checkRow(n) )
                {
                    if( y == null )
                    {
                        for( Tile z : diagOne( n ) )
                        {
                            if( z == null )
                            {
                                for( Tile w : diagTwo( n ) )
                                {
                                    if( w == null )
                                        ans = false;
                                    else
                                        break;
                                }
                            }
                            else
                                break;
                        }
                    }
                    else
                        break;
                }
            }
            else
                break;
        }
        return ans;
    }
    
    public void swapTile(Tile[] n) //Sherin
    {
        for ( int i=0;i<n.length;i++) 
        {
            if(n[i] != null){
                n[i].setColor( currentPlayer );
            }
        } 
        for(Tile x: n){
            if(x != null)
                board[x.getRow()][x.getCol()] = x;
        }
       
    }
    
    public Tile[] getTilesToSwap(Tile n) //Sherin
    {
        Tile[] total = concatArrays(checkCol(n), checkRow(n), diagOne(n), 
                diagTwo(n)); 
        return total; 
    }
    
    public Tile[] concatArrays( Tile[] a, Tile[] b, Tile[] c, Tile[] d ) //Sherin
    {
        Tile[] finalArray = new Tile[ a.length + b.length + c.length + d.length ];
        int count = 0;
        
        for( int i = 0; i < a.length; i++ )
        {
            finalArray[ count ] = a[ i ];
            count++;
        }
        for( int i = 0; i < b.length; i++ )
        {
            finalArray[ count ] = b[ i ];
            count++;
        }
        for( int i = 0; i < c.length; i++ )
        {
            finalArray[ count ] = c[ i ];
            count++;
        }
        for( int i = 0; i < d.length; i++ )
        {
            finalArray[ count ] = d[ i ];
            count++;
        }
        return finalArray;
    }
  
    public Tile[] checkCol( Tile n )
    {
        Tile [] answer = new Tile[ 8 ];
        int nColor = n.getColor();
        int nColumn = n.getCol();
        int count = 0;  //how many tiles to turn
        int c1 = 0; //tile to bottom
        int c2 = 0; //tile to top
        //check to make sure there's a tile with same color in column
        //tile to bottom
        for( int r = n.getRow() + 1; r < board.length; r++ )
        {
            if( board[r][nColumn] == null )
                break;
            else if( board[ r ][nColumn ] != null && 
                    board[ r ][ nColumn ].getColor() == nColor )
                c1++;
        }
        //tile to top
        for( int r = n.getRow() - 1; r >= 0; r-- )
        {
            if( board[r][nColumn] == null )
                break;
            else if( board[ r ][ nColumn ] != null && 
                    board[ r ][ nColumn ].getColor() == nColor )
                c2++;
        }
        if( c1 > 0 )//check from tile to bottom
        {
            for( int r = n.getRow() + 1; r < board.length-1; r++ )
            {
                if( board[ r ][ nColumn ] == null || 
                        board[ r ][ nColumn ].getColor() == nColor )
                    break;
                else 
                {
                    answer[count] = board[ r ][ nColumn ];
                    count++;
                }
            }
        }
        if( c2 > 0 )//check from tile to top
        {
            for( int r = n.getRow() - 1; r > 0; r-- )
            {
                if( board[ r ][ nColumn ] == null || 
                        board[ r ][ nColumn ].getColor() == nColor )
                    break;
                else
                {
                    answer[ count ] = board[ r ][ nColumn ];
                    count++;
                }
            }
        }
        return answer;
    }
   
    public Tile[] checkRow( Tile n )
    {
        Tile[] answer = new Tile[ 8 ];
        int nColor = n.getColor();
        int nRow = n.getRow();
        int count = 0;  //how many tiles to switch
        int r1 = 0;  //tile to right
        int r2 = 0; //tile to left
        //checks make sure tile with same color in row
        //tile to right
        for( int c = n.getCol() + 1; c < board.length; c++ )
        {
            if( board[nRow][c] == null )
                break;
            else if( board[ nRow][c] != null && board[nRow][c].getColor() == nColor )
                r1++;
        }
        //tile to left
        for( int c = n.getCol() - 1; c >= 0; c-- )
        {
            if( board[nRow][c] == null )
                break;
            else if( board[nRow][c] != null && board[nRow][c].getColor() == nColor )
                r2++;
        }
        if( r1 > 0 )//tile to right
        {
            for( int c = n.getCol() + 1; c < board.length-1; c++ )
            {
                if( board[ nRow][ c ] == null || 
                        board[ nRow ][ c ].getColor() == nColor )
                    break;
                else
                {
                    answer[ count ] = board[ nRow ][ c ];
                    count++;
                }
            }
        }
        if( r2 > 0 )//tile to left
        {
            for ( int c = n.getCol() - 1; c > 0; c-- )
            {
                if( board[ nRow][ c ] == null || 
                        board[ nRow ][ c ].getColor() == nColor )
                    break;
                else
                {
                    answer[ count ] = board[nRow][c];
                    count++;
                }
            }
        }
        
        return answer;
    }
    
    public Tile[] diagOne( Tile n )
    {
        Tile[] answer = new Tile[ 8 ];
        int nColor = n.getColor();
        int nColumn = n.getCol();
        int nRow = n.getRow();
        int count = 0;  //tiles to switch
        int d1 = 0;  //tile(left) to down right
        int d2 = 0; //tile(right) to up left
        
        int r = nRow + 1;
        int c = nColumn + 1;
        //check diag if there's a tile with same color
        //tile(left) to down left
        while( r  < board.length && c  < board.length )
        {
            if( board[r][c] == null )
                break;
            else if( board[ r ][ c] != null && board[ r][c].getColor() == nColor )
                d1++;
            r++;
            c++;
        }
        //tile(right) to up left
        r = nRow-1;
        c = nColumn-1;
        while( r >= 0 && c >= 0 )
        {
            if( board[r][c] == null )
                break;
            else if( board[ r][c] != null && board[r][c].getColor() == nColor )
                d2++;
            r--;
            c--;
        }
        
        if( d1 > 0 )//tile( left) to down right
        {
            r = nRow+1;
            c = nColumn +1;
            while( r < board.length-1 && c <board.length-1 )
            {
                if( board[r][c] == null || board[r][c].getColor() == nColor )
                    break;
                else
                {
                    answer[count] = board[r][c];
                    count++;
                }
                r++;
                c++;
            }
        }
        if( d2 > 0 )//tile(right) to up left
        {
            r = nRow-1;
            c = nColumn-1;
            while( r > 0 && c > 0 )
            {
                if( board[r][c] == null || board[r][c].getColor() == nColor )
                    break;
                else
                {
                    answer[count] = board[r][c];
                    count++;
                }
                r--;
                c--;
            }
        }
        
        return answer;
    }
    
    public Tile[] diagTwo( Tile n )
    {
        Tile[] answer = new Tile[ 8 ];
        int nColor = n.getColor();
        int nColumn = n.getCol();
        int nRow = n.getRow();
        int count = 0;  //tiles to switch
        int d1 = 0;  //tile( left) to up right
        int d2 = 0; //tile( right) to down left
        
        //check diag if there's a tile with same color
        //tile( left) to up right
        int r = nRow - 1;
        int c = nColumn + 1;
        while( r >= 0 && c < board.length )
        {
            if( board[r][c] == null )
                break;
            else if( board[r][c] != null && board[r][c].getColor() == nColor )
                d1++;
            r--;
            c++;
        }
        //tile( right) to down left
        r = nRow +1;
        c = nColumn -1;
        while( r < board.length && c >= 0 )
        {
            if( board[r][c] == null )
                break;
            else if( board[r][c] != null && board[r][c].getColor() == nColor )
                d2++;
            r++;
            c--;
        }
        
        if( d1 > 0 )//tile( left) to up right 
        {
            r = nRow -1;
            c = nColumn +1;
            while( r > 0 && c < board.length-1 )
            {
                if( board[r][c] == null || board[r][c].getColor() == nColor )
                    break;
                else
                {
                    answer[count] = board[r][c];
                    count++;
                }
                r--;
                c++;
            }
        }
        if( d2 > 0 )//tile(right) to down left
        {
            r = nRow+1;
            c = nColumn-1;
            while( r < board.length-1 && c > 0 )
            {
                if( board[r][c] == null || board[r][c].getColor() == nColor )
                    break;
                else
                {
                    answer[count] = board[r][c];
                    count++;
                }
                r++;
                c--;
            }
        }
        
        return answer;
    }
    
    public Tile[][] getBoard() 
    {
        return board; 
    }
	
    public int getBlackTiles(){  //Christine   
		int count = 0;
        for(Tile[] x: board){
            for(Tile y: x){
                if(y != null && y.getColor() == 1)
                    count++;
            }
        }
        return count;
    }
	
    public int getWhiteTiles(){   //Christine  
		int count = 0;
        for(Tile[] x: board){
            for(Tile y: x){
            if(y != null && y.getColor() == 2)
                count++;
            }
        }
        return count;
    }
    
    public void addTile(Tile n){   //Sherin 
        if(isValid(n)){
            board[n.getRow()][n.getCol()] = n;
            
            swapTile(getTilesToSwap(n));
            if( ( isFull() == 64 ) || ( !playerMoves( currentPlayer ) && !playerMoves( otherPlayer() ) ) )//Katelyn
            {
                JFrame gameOver = new JFrame("" );
                gameOver.setSize( 400, 200 );
                gameOver.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE);
                class GameOver extends JComponent
                {
                    public void paintComponent( Graphics g )
                    {
                        String win;
                        if( getWhiteTiles() > getBlackTiles() )
                            win = "WHITE";
                        else
                            win = "BLACK";
                        Graphics2D g2 = (Graphics2D)g;
                        g2.drawString( "Game over " + win + " wins", 100, 50 );
                    }
                }
                GameOver game = new GameOver();
                gameOver.add( game );
                gameOver.setVisible( true );
            }
            else if( playerMoves( otherPlayer() ) )//Katelyn
            {
                swapPlayers();
            }
            else//Katelyn
            {
                JFrame noMove = new JFrame("" );
                noMove.setSize( 400, 200 );
                noMove.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
                class NoMove extends JComponent
                {
                    public void paintComponent(Graphics g )
                    {
                        Graphics2D g2 = (Graphics2D)g;
                        g2.drawString("No valid moves for Player " + otherPlayer() + " Player " + currentPlayer + " goes again", 50, 50 );
                    }
                }
                NoMove m = new NoMove();
                noMove.add( m );
                noMove.setVisible( true );
            }
        }
    }
    
    public int getCurrentPlayer(){ 
	return currentPlayer;
    }
    
    public int otherPlayer()//Katelyn
    {
        if( currentPlayer == 1 )
            return 2;
        else
            return 1;
    }
    
    public void swapPlayers()//Katelyn
    {
        if( currentPlayer == 1 )
            currentPlayer =2;
        else
            currentPlayer =1;
    }
    
    public boolean playerMoves( int n )//Katelyn
    {
        boolean ans = false;
        for( int row = 0; row < board.length; row++ )
        {
            for( int col = 0; col < board.length; col++ )
            {
                if( board[row][col] == null )
                {
                    Tile x = new Tile( n, row, col );
                    if( isValid( x ) )
                    {
                        ans = true;
                        break;
                    }
                }
                if( ans == true )
                    break;
            }
            if( ans == true )
                break;
        }
        return ans;
    }
    
    public int isFull()//Katelyn
    {
        int count = 0;
        for( int row = 0; row < board.length; row++ )
        {
            for( int col = 0; col < board.length; col++ )
            {
                if( board[row][col] != null )
                    count++;
            }
        }
        return count;
    }
}
