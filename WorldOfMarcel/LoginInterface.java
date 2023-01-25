package WorldOfMarcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginInterface implements ActionListener {
    Game game = Game.getInstance() ;
    Character player ;
    ArrayList<Account> accountList = game.accountList ;
    int k ;
    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText() ;
        String password = new String(passwordField.getPassword()) ;

        for(k = 0 ; k < accountList.size() ; k++) {
            if (accountList.get(k).playerInformation.credentials.getEmail().equals(email)
                    && accountList.get(k).playerInformation.credentials.getPassword().equals(password))
                break;

        }
        if(k == accountList.size()){
            JOptionPane.showMessageDialog(null , "Email sau parola incorecte !");
        }
        else{
            JOptionPane.showMessageDialog(null , "Logarea a avut succes !");
            ChooseCharacterInterface characterInterface = new ChooseCharacterInterface() ;
            frame.setVisible(false);
            characterInterface.run(k);
        }

    }
    JLabel passwordLabel , emailLabel ;
    JTextField emailField ;
    JButton button ;
    JPasswordField passwordField ;
    JPanel panel = new JPanel(new GridLayout(3 , 1)) ;

    JFrame frame = new JFrame() ;
    void run(){
        panel.setLayout(null);
        frame.setTitle("Login");
        frame.setLocation(new Point(500 ,300));
        frame.add(panel) ;
        frame.setSize(new Dimension(400 , 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        emailLabel = new JLabel("Email") ;
        emailLabel.setBounds(10 , 27 , 70 , 20) ;
        panel.add(emailLabel) ;
        emailField = new JTextField() ;
        emailField.setBounds(100 , 27 , 193 , 28);
        panel.add(emailField) ;

        passwordLabel = new JLabel("Password") ;
        passwordLabel.setBounds(10 , 55 , 70 , 20) ;
        panel.add(passwordLabel) ;
        passwordField = new JPasswordField() ;
        passwordField.setBounds(100 , 55 , 193 , 28);
        panel.add(passwordField) ;

        button = new JButton("Login") ;
        button.setBounds(100 , 110 , 90 , 25);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.addActionListener(this);
        panel.add(button);

        frame.setVisible(true);
    }

}
