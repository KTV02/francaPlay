import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class GUI implements ActionListener {
    private JButton maleLost;
    private JButton femaleLost;
    private JButton switchMode;
    private JFrame frame;
    private JTextField input;
    private Game game;
    private JLabel display;
    private JPanel design;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        maleLost = new JButton("male"); //game.getMale()
        maleLost.addActionListener(this);

        femaleLost = new JButton("female"); //game.getFemale()
        femaleLost.addActionListener(this);

        switchMode = new JButton("Switch Modes");
        switchMode.addActionListener(this);

        input = new JTextField();
        input.addActionListener(this);
        display = new JLabel();


    }


    public void display(String content) {
        display.setText(content);
    }

    public GUI() {

        JFrame main = new JFrame("Ausreden gibts nicht! Was da steht wird gemacht!");
        //main.setContentPane(new GUI().frame);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        design = new JPanel();
        design.setLayout(new GridLayout());
        game = new Game(this);

        createUIComponents();
        main.add(design);
        design.add(input);
        design.add(display);
        design.add(maleLost);
        design.add(femaleLost);


        design.add(switchMode);
        main.setSize(screenSize.width/2,screenSize.height/6);
        //main.pack();
        main.setVisible(true);
        getNames();


    }

    public void getNames() {
        while (game.getMale() == null)
            display.setText("Namen des Mannes eingeben");
        input.setText("");
        while (game.getFemale() == null)
            display.setText("Namen der Frau eingeben");
        display.setText("Wer hat verloren?");
        dynamicShift();

    }
    private void dynamicShift(){
        design.remove(input);
        design.repaint();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == maleLost) {
            cycle(game.getMale());
        } else if (e.getSource() == femaleLost) {
            cycle(game.getFemale());
        } else if (e.getSource() == switchMode) {
            //cycle trough modes by pressing switchMode Button repeatedly
            System.out.println(game.getMode());
            if (game.getMode() == PlayerConfig.ALCCLOTHED) {
                game.setMode(PlayerConfig.ALCNAKED);
                display("Sexy");
            }
            else if (game.getMode() == PlayerConfig.ALCNAKED) {
                game.setMode(PlayerConfig.NOALCCLOTHED);
                display.setText("Under Construction 1");
            }
            else if (game.getMode() == PlayerConfig.NOALCCLOTHED) {
                game.setMode(PlayerConfig.NOALCNAKED);
                display.setText("Under Construction 2");
            }
            else if (game.getMode() == PlayerConfig.NOALCNAKED) {
                game.setMode(PlayerConfig.ALCCLOTHED);
                display("Normal");
            }
    }else if(e.getSource()==input){
            if(game.getMale()==null) {
                String name = input.getText();
                game.setMale(name);
                maleLost.setText(name);
            }
            else if(game.getFemale()==null) {
                String name= input.getText();
                game.setFemale(name);
                femaleLost.setText(name);
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
