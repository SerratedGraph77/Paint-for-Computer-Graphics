import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class DrawFrame extends JFrame {

     
    private static final long serialVersionUID = 1L;

    DrawCanvas drawCanvas;
    
    DrawFrame(int sz) {
        super("Draw");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize((sz*25)+100, (sz*25)+100);
        
        int cellWidth=25;
        
        drawCanvas=new DrawCanvas(this,sz*cellWidth,sz*cellWidth,cellWidth);

        add("Center", drawCanvas);
        
        JPanel topPanel= new JPanel();
        add("North", topPanel);
        
        
        Color [] colors = {
            new Color(255,0,255),
            new Color(0,255,255),
            new Color(255,255,0),
            new Color(255,0,0),
            new Color(0,255,0),
            new Color(0,0,255),
            new Color(0,0,0)
        };
        
        for(int i=0;i<colors.length;i++)
        {
            JButton button = new JButton();
            button.setBackground(colors[i]);
            button.addActionListener(e ->
            {
                drawCanvas.setColor(button.getBackground());
            });
            topPanel.add(button);
        }
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e ->
        {
            drawCanvas.clear();
        });
        
        add("South", clearButton);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
	JFrame frame = new JFrame();
    	Object result = JOptionPane.showInputDialog(frame, "Enter Size of Grid:");
	int size = Integer.parseInt(result.toString());
        new DrawFrame(size);
    }
}
