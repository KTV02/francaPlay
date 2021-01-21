import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private JButton maleWon;
    private JButton femaleWon;
    private JButton switchMode;
    private JFrame frame;
    private Game game;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        maleWon= new JButton();


    }
    public GUI(){
        JFrame main = new JFrame("UserInterface");
        //main.setContentPane(new GUI().frame);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maleWon.setAlignmentX(100);
        maleWon.setAlignmentY(100);
        main.add(maleWon);
        //main.add(femaleWon);
        main.pack();
        main.setVisible(true);
        game=new Game();

    }
    public void actionPerformed(ActionEvent e){
        if(e.equals(maleWon)){
            cycle(game.getMale());
        }else if(e.equals(femaleWon)){
            cycle(game.getFemale());
        }else if(e.equals(switchMode)){
            //cycle trough modes by pressing switchMode Button repeatedly
            if(game.getMode()==PlayerConfig.ALCCLOTHED){
                game.setMode(PlayerConfig.ALCNAKED);
                System.out.println("ʕ•┏ل͜┓•ʔ");
            }if(game.getMode()==PlayerConfig.ALCNAKED){
                game.setMode(PlayerConfig.NOALCCLOTHED);
            }if(game.getMode()==PlayerConfig.NOALCCLOTHED){
                game.setMode(PlayerConfig.NOALCNAKED);
            }if(game.getMode()==PlayerConfig.NOALCCLOTHED){
                game.setMode(PlayerConfig.ALCCLOTHED);
            }
        }
    }
    public void cycle(String loser){
        game.buttonTriggered(loser);
    }

    public static void main(String[] args) {

       new GUI();

    }
}
