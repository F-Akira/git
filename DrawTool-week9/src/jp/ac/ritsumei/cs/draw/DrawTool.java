
package jp.ac.ritsumei.cs.draw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Locale;

/**
 * Implements a drawing tool.
 */
public class DrawTool extends JFrame {
    
    private static final long serialVersionUID = -88007108717580447L;
    
    /**
     * The menu bar that contains menu items.
     */
    private DrawMenuBar menuBar;
    private JPanel tabber;
    TabManager tabManager;
    
    /**
     * Creates an application for a drawing tool.
     * @param title the title of this application that are shown in the top of the application window
     */
    public DrawTool(String title) {
        super(title);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout());
        contentPane.add(panel, BorderLayout.NORTH);
        
        tabber = new JPanel(new BorderLayout());
        contentPane.add(tabber, BorderLayout.CENTER);
 
        FigureSelector selector = new FigureSelector();
        panel.add(selector, BorderLayout.EAST);
        
        tabManager = new TabManager(this, selector);
        
        menuBar = new DrawMenuBar(this);
        setJMenuBar(menuBar);
    }
    
    public JPanel getTabber() {
    	return tabber;
    }
    
    /**
     * Returns the menu bar that contains menu items.
     * @return the menu bar
     */
    DrawMenuBar getDrawMenuBar() {
        return menuBar;
    }
    
    /**
     * Intends to close the application window.
     */
    private void closeWindow() {
        if (menuBar != null) {
            menuBar.closeFile();
        }
    }
    
    /**
     * Terminates the application.
     */
    public void terminate() {
        System.exit(0);
    }
    
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        final DrawTool main = new DrawTool("DrawTool");
        
        main.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                main.closeWindow();
            }
        });
        
        main.pack();
        main.validate();
        main.setVisible(true);
    }
}
