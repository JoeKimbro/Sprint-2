package Sprint2;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class gui extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    private JTextField textField;
    private final ButtonGroup PlayerButtonBlue = new ButtonGroup();
    private final ButtonGroup PlayerButtonRed = new ButtonGroup();
    private final ButtonGroup SelectGameButton = new ButtonGroup();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    gui frame = new gui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public gui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

       
        JPanel panel = new JPanel();
        panel.setBounds(124, 85, 773, 516);
        contentPane.add(panel);
        
        
        int defaultGridSize = 3;
        

        Board board = new Board(panel, defaultGridSize);
        board.createGrid();  

        textField = new JTextField();
        textField.setText("3");  
        textField.setBounds(477, 623, 49, 26);
        contentPane.add(textField);
        textField.setColumns(10);

      
        Console console = new Console(board, panel, textField);

        
        JButton btnNewButton = new JButton("Create New Game Board ");
        btnNewButton.setBounds(654, 622, 193, 29);
        contentPane.add(btnNewButton);

       
        btnNewButton.addMouseListener(console.createNewGameAction());
       
        
        JButton btnNewButton_1 = new JButton("New Game");
        btnNewButton_1.setBounds(250, 622, 117, 29);
        contentPane.add(btnNewButton_1);

        btnNewButton_1.addMouseListener(console.clearBoardAction());

        JRadioButton rdbtnNewRadioButton = new JRadioButton("O");
        PlayerButtonBlue.add(rdbtnNewRadioButton);
        rdbtnNewRadioButton.setBounds(38, 500, 69, 23);
        contentPane.add(rdbtnNewRadioButton);

        JRadioButton rdbtnS = new JRadioButton("S");
        rdbtnS.setSelected(true);
        PlayerButtonBlue.add(rdbtnS);
        rdbtnS.setBounds(38, 474, 69, 23);
        contentPane.add(rdbtnS);

        JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("O");
        rdbtnNewRadioButton_1_1.setSelected(true);
        PlayerButtonRed.add(rdbtnNewRadioButton_1_1);
        rdbtnNewRadioButton_1_1.setBounds(903, 500, 141, 23);
        contentPane.add(rdbtnNewRadioButton_1_1);

        JRadioButton rdbtnNewRadioButton_1_1_1 = new JRadioButton("S");
        PlayerButtonRed.add(rdbtnNewRadioButton_1_1_1);
        rdbtnNewRadioButton_1_1_1.setBounds(903, 474, 141, 23);
        contentPane.add(rdbtnNewRadioButton_1_1_1);

        JRadioButton rdbtnNewRadioButton_1_1_1_1 = new JRadioButton("Simple Game");
        rdbtnNewRadioButton_1_1_1_1.setSelected(true);
        SelectGameButton.add(rdbtnNewRadioButton_1_1_1_1);
        rdbtnNewRadioButton_1_1_1_1.setBounds(62, 625, 141, 23);
        contentPane.add(rdbtnNewRadioButton_1_1_1_1);

        JRadioButton rdbtnNewRadioButton_1_1_1_1_1 = new JRadioButton("General Game");
        SelectGameButton.add(rdbtnNewRadioButton_1_1_1_1_1);
        rdbtnNewRadioButton_1_1_1_1_1.setBounds(903, 625, 141, 23);
        contentPane.add(rdbtnNewRadioButton_1_1_1_1_1);

        JLabel lblSos = new JLabel("SOS");
        lblSos.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
        lblSos.setBounds(464, 0, 90, 75);
        contentPane.add(lblSos);

        JLabel lblNewLabel_1_1 = new JLabel("Current Turn: ");
        lblNewLabel_1_1.setBounds(431, 58, 141, 16);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblBoardSize = new JLabel("Board Size:");
        lblBoardSize.setBounds(408, 628, 102, 16);
        contentPane.add(lblBoardSize);

        JLabel lblBlueorRed = new JLabel("Blue (Or Red)");
        lblBlueorRed.setBounds(512, 58, 118, 16);
        contentPane.add(lblBlueorRed);

        JLabel lblBluePlayer = new JLabel("Blue Player");
        lblBluePlayer.setBounds(38, 451, 91, 16);
        contentPane.add(lblBluePlayer);

        JLabel lblNewLabel_1_2 = new JLabel("Red Player");
        lblNewLabel_1_2.setBounds(926, 451, 92, 16);
        contentPane.add(lblNewLabel_1_2);
    }
}

