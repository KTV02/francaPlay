import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class GUI implements ActionListener, EventListener {
    private JButton maleLost;
    private JButton femaleLost;
    private JButton switchMode;
    private JFrame frame;
    private JTextField input;
    private Game game;
    private JLabel display;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        maleLost = new JButton("male"); //game.getMale()
        maleLost.addActionListener(this);

        femaleLost = new JButton("female"); //game.getFemale()
        femaleLost.addActionListener(this);

        switchMode = new JButton("Switch Modes");
        switchMode.addActionListener(this);

        input = new JTextField("input");
        //input.addKeyListener(this);
        display = new JLabel();


    }

    public void display(String content) {
        display.setText(content);
    }

    public GUI() {

        JFrame main = new JFrame("UserInterface");
        //main.setContentPane(new GUI().frame);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel design = new JPanel();
        design.setLayout(new GridLayout());
        game = new Game(this);
        getNames();
        createUIComponents();
        main.add(design);
        design.add(maleLost);
        design.add(femaleLost);
        design.add(input);
        design.add(display);
        design.add(switchMode);


        main.pack();
        main.setVisible(true);


    }

    public void getNames() {
        while (game.getMale() == null)
            display.setText("Namen des Mannes bei input eingeben");
        while (game.getFemale() == null)
            display.setText("Namen des Mannes bei input eingeben");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == maleLost) {
            cycle(game.getMale());
        } else if (e.getSource() == femaleLost) {
            cycle(game.getFemale());
        } else if (e.getSource() == switchMode) {
            //cycle trough modes by pressing switchMode Button repeatedly
            if (game.getMode() == PlayerConfig.ALCCLOTHED) {
                game.setMode(PlayerConfig.ALCNAKED);
                System.out.println("ʕ•┏ل͜┓•ʔ");
            }
            if (game.getMode() == PlayerConfig.ALCNAKED) {
                game.setMode(PlayerConfig.NOALCCLOTHED);
            }
            if (game.getMode() == PlayerConfig.NOALCCLOTHED) {
                game.setMode(PlayerConfig.NOALCNAKED);
            }
            if (game.getMode() == PlayerConfig.NOALCCLOTHED) {
                game.setMode(PlayerConfig.ALCCLOTHED);
            }
        }
    }

    public void cycle(String loser) {
        game.buttonTriggered(loser);
    }

    public static void main(String[] args) {

        new GUI();

    }
}
