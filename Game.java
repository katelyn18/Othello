//Christine Breckendridge, Sherin Stevens, Katelyn Jaing
//Period 4
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {
    //Christine, Katelyn
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Othello");
        frame.setSize(425,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Board board = new Board();
        
        class BoardGraphics extends JComponent{
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                Rectangle r = new Rectangle(5,25,400,400);
                g2.setColor(Color.lightGray);
                g2.fill(r);
                g2.setColor(Color.BLACK);
                Line2D.Double line1 = new Line2D.Double(55,25,55,425);
                g2.draw(line1);
                Line2D.Double line2 = new Line2D.Double(105,25,105,425);
                g2.draw(line2);
                Line2D.Double line3 = new Line2D.Double(155,25,155,425);
                g2.draw(line3);
                Line2D.Double line4 = new Line2D.Double(205,25,205,425);
                g2.draw(line4);
                Line2D.Double line5 = new Line2D.Double(255,25,255,425);
                g2.draw(line5);
                Line2D.Double line6 = new Line2D.Double(305,25,305,425);
                g2.draw(line6);
                Line2D.Double line7 = new Line2D.Double(355,25,355,425);
                g2.draw(line7);

                Line2D.Double line8 = new Line2D.Double(5,75,405,75);
                g2.draw(line8);
                Line2D.Double line9 = new Line2D.Double(5,125,405,125);
                g2.draw(line9);
                Line2D.Double line10 = new Line2D.Double(5,175,405,175);
                g2.draw(line10);
                Line2D.Double line11 = new Line2D.Double(5,225,405,225);
                g2.draw(line11);
                Line2D.Double line12 = new Line2D.Double(5,275,405,275);
                g2.draw(line12);
                Line2D.Double line13 = new Line2D.Double(5,325,405,325);
                g2.draw(line13);
                Line2D.Double line14 = new Line2D.Double(5,375,405,375);
                g2.draw(line14);
                
                g2.drawString("Player 1: " + board.getBlackTiles() + "   Player 2: " + board.getWhiteTiles(),145,15);
                g2.drawString( "Current Player: " + board.getCurrentPlayer(), 159, 440 );
		//Christine
                //get pieces on board and add them
                for(Tile[] x :board.getBoard()){
                    for(Tile a: x){
                        if(a != null && a.getColor()==1 ){
                            g2.setColor(Color.BLACK);
                            Ellipse2D.Double circle = new Ellipse2D.Double(a.getCol()*50 + 5,a.getRow()*50 + 25,50,50);
                            g2.fill(circle);
                        }
                        else if(a!= null ){
                            g2.setColor(Color.WHITE);
                            Ellipse2D.Double circle = new Ellipse2D.Double(a.getCol()*50 + 5,a.getRow()*50 + 25,50,50);
                            g2.fill(circle);
                        }
                    }
                }
            }
        }
        final BoardGraphics b = new BoardGraphics();
        //Katelyn
        class Mouse implements MouseListener{
            public void mouseClicked(MouseEvent e) {
                if(e.getX()>=12 && e.getX()<=412 && e.getY()>=55 && e.getY()<=455){
                    int rowSelected = (int)((e.getY()-55) / 50 + 0.5);
                    int colSelected = (e.getX()-12) / 50 ;
                    if( rowSelected >= 0 && colSelected >= 0 )
                    {
                        //System.out.println( rowSelected + " " + colSelected );
                        Tile n = new Tile(board.getCurrentPlayer(), rowSelected, colSelected);
                        //update board by adding piece if play is valid
                            boolean ans = board.isValid(n);
                            if( ans == false )
                            {
                                //why does it stay at one? 
                                JFrame newFrame = new JFrame( "" );
                                newFrame.setSize( 200, 200 );
                                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                                class FailGraphics extends JComponent
                                {
                                    public void paintComponent( Graphics g )
                                    {
                                        Graphics2D g2 = (Graphics2D) g;
                                        g2.drawString( "Invalid Move", 50, 50 );
                                    }
                                }
                                FailGraphics f = new FailGraphics();
                                newFrame.add( f );
                                newFrame.setVisible( true );
                            }
                            else if ( board.isFull() != 64 )
                            {
                                board.addTile(n );
                                b.repaint();
                            }
                        
                    }
                }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }    
        }
        MouseListener mouse = new Mouse();
        frame.addMouseListener(mouse);
        frame.add(b);
        frame.setVisible(true);
    }
    
    

}
