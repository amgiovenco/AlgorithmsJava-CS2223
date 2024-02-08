import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {

    private static JFrame frame;
    private static JPanel panel;
    private static JButton greenButton;
    private static JButton yellowButton;
    private static JButton orangeButton;
    private static JComboBox<Integer> markerSelector = new JComboBox<>();

    public static void main(String[] args) {
        frame = new JFrame("Double Trouble");
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                for (int i = 0; i < Main.greenMarker; i++) {
                    g.fillRect(10 + i * 20, 10, 10, 50);
                }

                g.setColor(Color.YELLOW);
                for (int i = 0; i < Main.yellowMarker; i++) {
                    g.fillRect(10 + i * 20, 70, 10, 50);
                }

                g.setColor(Color.ORANGE);
                for (int i = 0; i < Main.orangeMarker; i++) {
                    g.fillRect(10 + i * 20, 130, 10, 50);
                }
            }
        };

        greenButton = new JButton("Remove Green Marker");
        yellowButton = new JButton("Remove Yellow Marker");
        orangeButton = new JButton("Remove Orange Marker");

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markerSelector.removeAllItems();
                for (int i = 1; i <= Main.greenMarker; i++) {
                    markerSelector.addItem(i);
                }
                int markerToRemove = (int) markerSelector.getSelectedItem();
                Main.removeMarker(1, markerToRemove);
                Main.computerMove();
                panel.repaint();
            }
        });

        yellowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markerSelector.removeAllItems();
                for (int i = 1; i <= Main.yellowMarker; i++) {
                    markerSelector.addItem(i);
                }
                int markerToRemove = (int) markerSelector.getSelectedItem();
                Main.removeMarker(1, markerToRemove);
                Main.computerMove();
                panel.repaint();
            }
        });

        orangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markerSelector.removeAllItems();
                for (int i = 1; i <= Main.orangeMarker; i++) {
                    markerSelector.addItem(i);
                }
                int markerToRemove = (int) markerSelector.getSelectedItem();
                Main.removeMarker(1, markerToRemove);
                Main.computerMove();
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(300, 200));
        frame.add(panel, BorderLayout.CENTER);
        markerSelector.setPreferredSize(new Dimension(80, 25));
        frame.add(markerSelector, BorderLayout.SOUTH);
        frame.add(greenButton, BorderLayout.NORTH);
        frame.add(yellowButton, BorderLayout.WEST);
        frame.add(orangeButton, BorderLayout.EAST);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
