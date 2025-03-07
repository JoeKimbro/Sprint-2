package Sprint2;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Component;
import javax.swing.SwingUtilities;

public class Console {

    private Board board;
    private JPanel panel;
    private JTextField textField;
    private char currentPlayer = 'S'; 

    public Console(Board board, JPanel panel, JTextField textField) {
        this.board = board;
        this.panel = panel;
        this.textField = textField;
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addButtonListeners();
            }
        });
    }

    public MouseAdapter createNewGameAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int gridSize = Integer.parseInt(textField.getText());

                    if (gridSize > 16) {
                        JOptionPane.showMessageDialog(panel,
                            "Please enter a grid size between 3 and 16.\nThe grid size has been set to 16.",
                            "Invalid Grid Size", JOptionPane.WARNING_MESSAGE);
                        gridSize = 16;
                    }
                    else if (gridSize < 3) {
                        JOptionPane.showMessageDialog(panel,
                            "Please enter a grid size between 3 and 16.\nThe grid size has been set to 3.",
                            "Invalid Grid Size", JOptionPane.WARNING_MESSAGE);
                        gridSize = 3;
                    }
                    board = new Board(panel, gridSize);
                    board.createGrid();
                    addButtonListeners();
                    currentPlayer = 'S';
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Please enter a valid integer.");
                }
            }
        };
    }

    public MouseAdapter clearBoardAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (board != null) {
                    board.clearBoard();
                    currentPlayer = 'S';
                    addButtonListeners();
                }
            }
        };
    }
    private void addButtonListeners() {
        addListenersRecursively(panel);
    }
    private void addListenersRecursively(Container container) {
        Component[] components = container.getComponents();
        
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                for (ActionListener al : button.getActionListeners()) {
                    button.removeActionListener(al);
                }
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeMove((JButton) e.getSource());
                    }
                });
            } 
            else if (component instanceof Container) {
                addListenersRecursively((Container) component);
            }
        }
    }
    
    public void makeMove(JButton button) {
        if (button.getText().isEmpty()) {
            button.setText(String.valueOf(currentPlayer));
            currentPlayer = (currentPlayer == 'S') ? 'O' : 'S';

        }
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
    public void initializeExistingBoard() {
        addButtonListeners();
    }
}