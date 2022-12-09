import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class DrawCanvas extends JComponent implements MouseListener, MouseMotionListener {

    int cellSize;
    Point size;
    Color currentColor;
    int clickButton;
    Color [][] cells;
    
    void setColor(Color color)
    {
        currentColor=color;
        
    }
    
    Point pixelsToCell(Point pix)
    {
        pix.x/=cellSize;
        pix.y/=cellSize;
        return pix;
    }
    
    public DrawCanvas(DrawFrame parent, int w, int h, int cellSize) {
        
        setBackground(new Color(255,255,255));
        setSize(w, h);
        
        currentColor=new Color(255,0, 0);
        
        size = new Point(w/cellSize,h/cellSize);
        this.cellSize=cellSize;
        cells = new Color[w/cellSize][h/cellSize];
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        for(int i=0;i<cells.length;i++)
        for(int j=0;j<cells[i].length;j++)
        cells[i][j]=this.getBackground();
    }


    
    public void doDraw(Point posPix)
    {
        Point cellPos = pixelsToCell(posPix);
        if (//if cell pos is valid
                cellPos.x >= 0 && cellPos.x < size.x && 
                cellPos.y >= 0 && cellPos.y < size.y
            ) {

            //if left button is pressed
            if((clickButton&1)!=0)
                cells[cellPos.x][cellPos.y]=currentColor;

            ///if right button is pressed
            if((clickButton&2)!=0)
                cells[cellPos.x][cellPos.y]=this.getBackground();
        }

        update(getGraphics());
    }
    
    public void clear()
    {
        for(int i=0;i<size.x;i++)
        for(int j=0;j<size.y;j++)
        cells[i][j]=this.getBackground();
        update(getGraphics());
    }
    

    
    public void paint(Graphics g) {
        
        for(int i=0;i<size.x;i++)
        for(int j=0;j<size.y;j++)
        {
            Color color = cells[i][j];
            g.setColor(color);
            g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
            g.setColor(new Color(0,0,0));
            g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clickButton=0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    
        doDraw(e.getPoint());
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //save clicked button
        clickButton=e.getButton();
        
        doDraw(e.getPoint());
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
