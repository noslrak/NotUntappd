package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static network.ReadWebPage.welcomeMessage;

public class Main extends JFrame {
    private static String password = "idontremember";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private List<Tool> tools;
    private Tool activeTool;

    public Main() {
        super("NotUntappd");
        initializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
        MouseListener dml = new MouseListener();
        addMouseListener(dml);
        addMouseMotionListener(dml);
    }

    // EFFECTS: if activeTool != null, then mouseClickedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseClicked(MouseEvent e) {
        if (activeTool != null) {
            activeTool.mouseClickedInDrawingArea(e);
        }
        repaint();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        activeTool = null;
        tools = new ArrayList<>();
    }

    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0, 1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int operation;
        String code;

        new Main();
        System.out.println("Please select an option: [1] Premium notUntappd or any other Integer for Free notUntappd");
        operation = scanner.nextInt();
        scanner.nextLine();
        if (operation != 1) {
            new FreeNotUntappd();
        } else {
            System.out.println("Please enter verification code");
            code = scanner.nextLine();
            if (code.equals(password)) {
                new PremiumNotUntappd();
            } else {
                System.out.println("Incorrect verification code");
            }
        }
    }

    private class MouseListener extends MouseAdapter {
        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(e);
        }

//        // EFFECTS: translates the mouse event to current drawing's coordinate system
//        private MouseEvent translateEvent(MouseEvent e) {
//            return SwingUtilities.convertMouseEvent(e.getComponent(), e, currentDrawing);
//        }
    }
}
