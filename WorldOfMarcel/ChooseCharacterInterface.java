package WorldOfMarcel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseCharacterInterface implements ListSelectionListener{
    JFrame frame = new JFrame() ;
    JPanel panel = new JPanel(new GridLayout(3 , 1)) ;
    JTextArea textArea = new JTextArea() ;
    JList<String> charactersList ;
    JScrollPane scrollPane ;
    Game game = Game.getInstance() ;
    JButton button ;
    int k ;
    Character player ;
    ArrayList<Account> accountList = game.accountList ;
    void run(int k){
        this.k = k ;
        frame.setSize(new Dimension(600 , 400));
        frame.setTitle("WorldOfMarcel");
        frame.setLocation(new Point(500 ,300));
        panel.setLayout(null) ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String characters[] = new String[accountList.get(k).list.size()] ;
        for(int i = 0 ; i < accountList.get(k).list.size() ; i++){
            characters[i] = accountList.get(k).list.get(i).character_name ;
        }

        charactersList = new JList<>(characters) ;
        charactersList.setBounds(10 , 30 , 100 , 200);
        charactersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //charactersList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        scrollPane = new JScrollPane(charactersList) ;
        scrollPane.setPreferredSize(new Dimension(250 , 80));
        panel.add(charactersList) ;
        panel.add(scrollPane) ;
        charactersList.addListSelectionListener(this) ;

        button = new JButton("SelectCharacter") ;
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setBounds(30 , 260 , 150 , 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(charactersList.isSelectionEmpty())return ;
                int nr ;
                for(nr = 0 ; nr < accountList.get(k).list.size() ; nr++)
                    if(accountList.get(k).list.get(nr).character_name.equals(charactersList.getSelectedValue()))
                        break ;

                    player = CharacterFactory.factory(accountList.get(k).list.get(nr).getClass().getSimpleName() , accountList.get(k).list.get(nr).character_name , accountList.get(k).list.get(nr).level , accountList.get(k).list.get(nr).exp) ;
                System.out.println(player.character_name) ;
            }
        });
        panel.add(button) ;
        textArea.setSize(new Dimension(100 , 100));
        textArea.setBounds(150 , 30 , 200 , 200);
        textArea.setLayout(null) ;
        textArea.setVisible(true);
        textArea.setBackground(Color.white);
        panel.add(textArea) ;
        frame.add(panel) ;
        frame.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

            for(int i = 0 ; i < accountList.get(k).list.size() ; i++){
                if(accountList.get(k).list.get(i).character_name.equals(charactersList.getSelectedValue())){
                    String s = "exp : " + accountList.get(k).list.get(i).exp + "\n" ;
                    s += "level : " + accountList.get(k).list.get(i).level + "\n" ;
                    s += "rasa : " + accountList.get(k).list.get(i).getClass().getSimpleName() ;
                    textArea.setText(s) ;
                    break ;
                }
            }
        }
    }

