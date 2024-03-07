import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class UI extends JFrame implements ActionListener, MouseMotionListener {

    private JButton colorButton = new JButton("Choose Color");
    private JButton clearButton = new JButton("Clear");
    private JSlider brushSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 50, 10);

    private int oldX;
    private int oldY;
    private int brushSize = 10;

    private Color color = Color.BLACK;

    UI() {
        setSize(500, 700);
        setLayout(null);

        colorButton.setBounds(50, 50, 150, 50);
        add(colorButton);
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor();
            }
        });

        clearButton.setBounds(210, 50, 100, 50);
        add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCanvas();
            }
        });

        brushSizeSlider.setBounds(320, 50, 150, 50);
        add(brushSizeSlider);
        brushSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                brushSize = brushSizeSlider.getValue();
            }
        });

        addMouseMotionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This method is not used for color selection anymore
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = getGraphics();
        g.setColor(color);
        if (oldX != 0 && oldY != 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(brushSize));
            g2.drawLine(oldX, oldY, e.getX(), e.getY());
        }
        oldX = e.getX();
        oldY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        oldX = 0;
        oldY = 0;
    }

    private void clearCanvas() {
        Graphics g = getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void chooseColor() {
        color = JColorChooser.showDialog(this, "Choose Color", color);
    }

    public static void main(String[] args) {
        new UI();
    }
}
