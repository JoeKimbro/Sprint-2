package Sprint2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

public class tests {
    private Board board;
    private JPanel panel;
    private JTextField textField;
    private ButtonGroup selectGameButton;
    private JRadioButton simpleGameRadioButton;
    private JRadioButton generalGameRadioButton;
    private ButtonGroup buttonGroup;
    private Console console;
    private JFrame frame;

    @Before
    public void setUp() {
        panel = new JPanel();
        textField = new JTextField();
        textField.setText("3");
        board = new Board(panel, 3);
        board.createGrid();
        console = new Console(board, panel, textField);
        
        simpleGameRadioButton = new JRadioButton("Simple Game");
        generalGameRadioButton = new JRadioButton("General Game");
        
        // Create button group and add buttons
        buttonGroup = new ButtonGroup();
        buttonGroup.add(simpleGameRadioButton);
        buttonGroup.add(generalGameRadioButton);
        
        // Set initial state
        simpleGameRadioButton.setSelected(true);
    }

    @Test
    public void testCreateNewGameActionValidInput() {
        // Set a valid grid size
    	
    	frame = new JFrame("Test Frame");
        JPanel contentPane = new JPanel();
        frame.setContentPane(contentPane);
        
        // Create the button group
        selectGameButton = new ButtonGroup();
        
        // Create radio buttons
        simpleGameRadioButton = new JRadioButton("Simple Game");
        generalGameRadioButton = new JRadioButton("General Game");
        
        // Add radio buttons to button group
        selectGameButton.add(simpleGameRadioButton);
        selectGameButton.add(generalGameRadioButton);
        
        // Add radio buttons to content pane
        contentPane.add(simpleGameRadioButton);
        contentPane.add(generalGameRadioButton);
        
        // Set Simple Game as default (more explicitly)
        simpleGameRadioButton.setSelected(true);
        generalGameRadioButton.setSelected(false);
        
        // Pack and make visible (helps ensure proper initialization)
        frame.pack();
        frame.setVisible(true);
    
        textField.setText("5");
        
        // Simulate mouse click
        JButton button = new JButton("Create New Game Board");
        MouseEvent event = new MouseEvent(button, MouseEvent.MOUSE_CLICKED, 
                System.currentTimeMillis(), 0, 0, 0, 1, false);
        
        console.createNewGameAction().mouseClicked(event);
        
        // Verify the panel has a 5x5 grid (25 components)
        assertEquals(25, panel.getComponentCount());
    }
    
    @Test
    public void testSimpleGameButtonClick() {
        // Click the Simple Game button
        simpleGameRadioButton.doClick();
        
        // Verify it's selected
        assertTrue("Simple Game button should be selected after clicking it", 
                simpleGameRadioButton.isSelected());
    }
    
    @Test
    public void testGeneralGameButtonClick() {
        // Click the General Game button
        generalGameRadioButton.doClick();
        
        // Verify it's selected
        assertTrue("General Game button should be selected after clicking it", 
                generalGameRadioButton.isSelected());
        
        assertFalse("Simple Game button should be deselected when General Game is clicked", 
                simpleGameRadioButton.isSelected());
    }
    
    @Test
    public void testToggleBetweenButtons() {
        // Initially, Simple Game should be selected
        assertTrue(simpleGameRadioButton.isSelected());
        assertFalse(generalGameRadioButton.isSelected());
        
        // Click General Game
        generalGameRadioButton.doClick();
        
        // Now General Game should be selected
        assertFalse(simpleGameRadioButton.isSelected());
        assertTrue(generalGameRadioButton.isSelected());
        
        // Click Simple Game
        simpleGameRadioButton.doClick();
        
        // Now Simple Game should be selected again
        assertTrue(simpleGameRadioButton.isSelected());
        assertFalse(generalGameRadioButton.isSelected());
    }
    @Test
    public void testCreateNewGameActionTooLargeInput() {
        // Set a grid size that's too large
        textField.setText("25");
        
        // Simulate mouse click
        JButton button = new JButton("Create New Game Board");
        MouseEvent event = new MouseEvent(button, MouseEvent.MOUSE_CLICKED, 
                System.currentTimeMillis(), 0, 0, 0, 1, false);
        
        console.createNewGameAction().mouseClicked(event);
        
        // It should create a 15x15 grid (225 components) as per the code logic
        assertEquals(256, panel.getComponentCount());
    }

    @Test
    public void testCreateNewGameActionTooSmallInput() {
        // Set a grid size that's too small
        textField.setText("1");
        
        // Simulate mouse click
        JButton button = new JButton("Create New Game Board");
        MouseEvent event = new MouseEvent(button, MouseEvent.MOUSE_CLICKED, 
                System.currentTimeMillis(), 0, 0, 0, 1, false);
        
        console.createNewGameAction().mouseClicked(event);
        
        // It should create a 3x3 grid (9 components) as per the code logic
        assertEquals(9, panel.getComponentCount());
    }

    @Test
    public void testCreateNewGameActionInvalidInput() {
        // Set an invalid input (non-numeric)
        textField.setText("abc");
        
        // Simulate mouse click
        JButton button = new JButton("Create New Game Board");
        MouseEvent event = new MouseEvent(button, MouseEvent.MOUSE_CLICKED, 
                System.currentTimeMillis(), 0, 0, 0, 1, false);
        
        // This should not throw an exception due to try-catch in the code
        console.createNewGameAction().mouseClicked(event);
        
        // The grid should remain unchanged (3x3 = 9 components)
        assertEquals(9, panel.getComponentCount());
    }

    @Test
    public void testClearBoardAction() {
        // First make some moves
        Component[] components = panel.getComponents();
        for (int i = 0; i < 3; i++) {
            if (components[i] instanceof JButton) {
                JButton button = (JButton) components[i];
                console.makeMove(button);
            }
        }
        
        // Verify some buttons have text
        boolean hasText = false;
        for (Component comp : components) {
            if (comp instanceof JButton && !((JButton) comp).getText().isEmpty()) {
                hasText = true;
                break;
            }
        }
        assertTrue("At least one button should have text after making moves", hasText);
        
        // Now clear the board
        JButton clearButton = new JButton("New Game");
        MouseEvent event = new MouseEvent(clearButton, MouseEvent.MOUSE_CLICKED, 
                System.currentTimeMillis(), 0, 0, 0, 1, false);
        
        console.clearBoardAction().mouseClicked(event);
        
        // Verify all buttons are cleared
        for (Component comp : components) {
            if (comp instanceof JButton) {
                assertEquals("", ((JButton) comp).getText());
            }
        }
    }

    @Test
    public void testMakeMove() {
        // Get a button from the panel
        Component[] components = panel.getComponents();
        JButton button = null;
        for (Component comp : components) {
            if (comp instanceof JButton) {
                button = (JButton) comp;
                break;
            }
        }
        
        assertNotNull("Should have found a button", button);
        
        // Initial player is 'S'
        assertEquals('S', console.getCurrentPlayer());
        
        // Make a move
        console.makeMove(button);
        
        // Button should now have 'S'
        assertEquals("S", button.getText());
        
        // Player should now be 'O'
        assertEquals('O', console.getCurrentPlayer());
        
        // Make another move
        Component[] updatedComponents = panel.getComponents();
        JButton anotherButton = null;
        for (Component comp : updatedComponents) {
            if (comp instanceof JButton && !((JButton) comp).equals(button)) {
                anotherButton = (JButton) comp;
                break;
            }
        }
        
        assertNotNull("Should have found another button", anotherButton);
        console.makeMove(anotherButton);
        
        // This button should now have 'O'
        assertEquals("O", anotherButton.getText());
        
        // Player should now be back to 'S'
        assertEquals('S', console.getCurrentPlayer());
    }

    @Test
    public void testMakeMoveOnAlreadyUsedButton() {
        // Get a button from the panel
        Component[] components = panel.getComponents();
        JButton button = null;
        for (Component comp : components) {
            if (comp instanceof JButton) {
                button = (JButton) comp;
                break;
            }
        }
        
        assertNotNull("Should have found a button", button);
        
        // Initial player is 'S'
        assertEquals('S', console.getCurrentPlayer());
        
        // Make a move
        console.makeMove(button);
        
        // Button should now have 'S'
        assertEquals("S", button.getText());
        
        // Player should now be 'O'
        char playerBeforeSecondMove = console.getCurrentPlayer();
        assertEquals('O', playerBeforeSecondMove);
        
        // Try to make a move on the same button
        console.makeMove(button);
        
        // Button should still have 'S'
        assertEquals("S", button.getText());
        
        // Player should still be 'O' (unchanged)
        assertEquals(playerBeforeSecondMove, console.getCurrentPlayer());
    }

    @Test
    public void testInitializeExistingBoard() {
        // Create a new board and console
        JPanel newPanel = new JPanel();
        Board newBoard = new Board(newPanel, 4);
        newBoard.createGrid();
        Console newConsole = new Console(newBoard, newPanel, textField);
        
        // Call initializeExistingBoard
        newConsole.initializeExistingBoard();
        
        // Test that the buttons have listeners by making a move
        Component[] components = newPanel.getComponents();
        JButton button = null;
        for (Component comp : components) {
            if (comp instanceof JButton) {
                button = (JButton) comp;
                break;
            }
        }
        
        assertNotNull("Should have found a button", button);
        
        // Make a move
        newConsole.makeMove(button);
        
        // Button should now have 'S'
        assertEquals("S", button.getText());
    }

    @Test
    public void testCreateGrid() {
        board.createGrid();
        
        // Check if the panel has the right layout
        assertTrue(panel.getLayout() instanceof GridLayout);
        GridLayout layout = (GridLayout) panel.getLayout();
        assertEquals(3, layout.getRows());
        assertEquals(3, layout.getColumns());
        
        // Check if the panel has the right number of components
        assertEquals(9, panel.getComponentCount());
        
        // Check if all components are JButtons with empty text
        for (Component comp : panel.getComponents()) {
            assertTrue(comp instanceof JButton);
            assertEquals("", ((JButton) comp).getText());
        }
    }

    @Test
    public void testCreateGridWithDifferentSize() {
        board = new Board(panel, 5);
        board.createGrid();
        
        // Check if the panel has the right layout
        assertTrue(panel.getLayout() instanceof GridLayout);
        GridLayout layout = (GridLayout) panel.getLayout();
        assertEquals(5, layout.getRows());
        assertEquals(5, layout.getColumns());
        
        // Check if the panel has the right number of components
        assertEquals(25, panel.getComponentCount());
    }

    @Test
    public void testGetGridPanel() {
        assertEquals(panel, board.getGridPanel());
    }

    @Test
    public void testClearBoard() {
        board.createGrid();
        
        // Set text on some buttons
        Component[] components = panel.getComponents();
        for (int i = 0; i < 3; i++) {
            if (components[i] instanceof JButton) {
                ((JButton) components[i]).setText("S");
            }
        }
        
        // Verify buttons have text
        boolean hasText = false;
        for (Component comp : components) {
            if (comp instanceof JButton && !((JButton) comp).getText().isEmpty()) {
                hasText = true;
                break;
            }
        }
        assertTrue("At least one button should have text", hasText);
        
        // Clear the board
        board.clearBoard();
        
        // Verify all buttons are cleared
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                assertEquals("", ((JButton) comp).getText());
            }
        }
    }
}

