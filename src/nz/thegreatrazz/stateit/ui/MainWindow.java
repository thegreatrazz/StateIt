package nz.thegreatrazz.stateit.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    private JMenuBar jMenuBar;
    private JMenu jMenuBar_File;
    private JMenuItem jMenuItem_File_Exit;

    public MainWindow() {
        initializeComponents();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            System.err.println("Failed to apply system look and feel.");
        }

        new MainWindow().setVisible(true);
    }

    private void initializeComponents() {
        // MainWindow : JFrame
        this.setSize(640, 480);
        this.setTitle("StateIt GUI");

        // jMenuBar
        jMenuBar = new JMenuBar();
        {
            jMenuBar_File = new JMenu("File");
            jMenuBar.add(jMenuBar_File);

            jMenuItem_File_Exit = new JMenuItem("Exit");
            jMenuItem_File_Exit.addActionListener(this::close);
            jMenuBar_File.add(jMenuItem_File_Exit);

        }
        this.setJMenuBar(jMenuBar);
    }

    public void close() {
        // TODO: make sure everything is saved first

        this.setVisible(false);
        this.dispose();
    }

    private void close(ActionEvent actionEvent) {
        close();
    }

    /// WTF!!!!?!?!??!?!?!?!?!?!
}
