
package jp.ac.ritsumei.cs.draw;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * A menu bar that contains menu items.
 */
public class DrawMenuBar extends JMenuBar {
    
    private static final long serialVersionUID = 1937943829729036145L;
    
    /**
     * The constant string indicating the name of an untitled file.
     */
    public static final String UNTITLED = "Untitled";
    
    /**
     * The application for a drawing tool.
     */
    private DrawTool tool;
    
    /**
     * A canvas on which a figure is drawn.
     */
    private DrawCanvas canvas;
    
    /**
     * The name of a file that stores information about figures on the canvas.
     */
    private String filename;
    
    /**
     * The name of a directory opened by a file chooser.
     */
    private File directory;
    
    /**
     * Creates a menu bar.
     * @param tool the application for a drawing tool
     * @param canvas the canvas on which a figure is drawn
     */
    DrawMenuBar(DrawTool tool) {
        this.tool = tool;
        
        getCurrnetCanvas();
        
        filename = UNTITLED;
        directory = new File(".");
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        add(fileMenu);
        initFileMenu(fileMenu);
    }
    
    private void getCurrnetCanvas() {
    	this.canvas = tool.tabManager.getCanvas();
    }
    
    private DrawCanvas getCanvas(int index) {
    	return tool.tabManager.getCanvas(index);
    }
    
    /**
     * Creates menu items packed in a given file menu.
     * @param menu the file menu.
     */
    private void initFileMenu(JMenu menu) {
        JMenuItem newItem = new JMenuItem("New...");
        newItem.setMnemonic('N');
        menu.add(newItem);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK, false));
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newFile();
            }
        });
        
        JMenuItem openItem = new JMenuItem("Open...");
        openItem.setMnemonic('O');
        menu.add(openItem);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK, false));
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                openFile();
            }
        });
        
        menu.addSeparator();
        
        JMenuItem saveItem = new JMenuItem("Save...");
        saveItem.setMnemonic('S');
        menu.add(saveItem);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK, false));
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveFile();
            }
        });
        
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        saveAsItem.setMnemonic('A');
        menu.add(saveAsItem);
        saveAsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveAsFile();
            }
        });
        
        menu.addSeparator();
        
        JMenuItem closeItem = new JMenuItem("Close File");
        closeItem.setMnemonic('W');
        menu.add(closeItem);
        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeFile();
            }
        });
        
        menu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('X');
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitTool();
            }
        });
    }
    
    /**
     * Creates a new file that will store information about figures.
     * @return <code>true</code> if the new was successfully created, otherwise <code>false</code>
     */
    boolean newFile() {
    	File file = getFile(false);
        String name = file.getPath();
        if (name == null) {
            return false;
        }
        
        if (fileExists(name)) {
            JOptionPane.showMessageDialog(tool, "File exits: " + name + ".");
                return false;
        }
        
        filename = file.getName();
        
        DrawCanvas newCanvas = new DrawCanvas(tool, tool.tabManager.selector);
        tool.tabManager.createTab(filename, newCanvas);
        tool.tabManager.changeLastTab();
        
        return true;
    }
    
    /**
     * Opens an existing file that stores information about figures.
     * @return <code>true</code> if the file was successfully opened, otherwise <code>false</code>
     */
    boolean openFile() {
    	File file = getFile(true);
        String name = file.getPath();
        if (name == null) {
            return false;
        }
        
        if (filename.equals(name)) {
            int confirm = JOptionPane.showConfirmDialog(tool,
              "The changes were made to this file. Do you want to re-open the file?",
              "Select an Option", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.NO_OPTION) {
                return false;
            }
        }
        
        DrawCanvas newCanvas = new DrawCanvas(tool, tool.tabManager.selector);
        boolean result = newCanvas.getFigureManager().load(name);
        if (!result) {
            JOptionPane.showMessageDialog(tool, "Cannnot open: " + name + ".");
                return false;
        }
        
        filename = name;
        
        tool.tabManager.createTab(file.getName(), newCanvas);
        tool.tabManager.changeLastTab();
        
        return true;
    }
    
    /**
     * Saves information about figures into the temporary file.
     * @return <code>true</code> if the file was successfully saved, otherwise <code>false</code>
     */
    boolean saveTempFile() {
    	getCurrnetCanvas();
    	filename = canvas.getFilename();
        String tempName = filename + "~";
        System.out.println("Save: " + tempName);
        return canvas.getFigureManager().store(tempName);
    }
    
    /**
     * Saves information about figures into the current file.
     * @return <code>true</code> if the file was successfully saved, otherwise <code>false</code>
     */
    boolean saveFile() {
    	getCurrnetCanvas();
    	filename = canvas.getFilename();
        return saveFile(filename);
    }
    
    /**
     * Saves information about figures into the new file.
     * @param fmanager the manager that manages all the drawn figures
     * @return <code>true</code> if the file was successfully saved, otherwise <code>false</code>
     */
   boolean saveAsFile() {
	    getCurrnetCanvas();
	    File file = getFile(false);
        String name = file.getPath();
        if (name == null) {
            return false;
        }
        
        filename = file.getName();
        if (saveFile(filename)) {
            canvas.setFileName(filename);
            return true;
        }
        return false;
    }
    
    /**
     * Saves information about figures into a file with a given name.
     * @param name the name of the file storing the information
     * @return <code>true</code> if the file was successfully saved, otherwise <code>false</code>
     */
    private boolean saveFile(String name) {
    	getCurrnetCanvas();
        boolean result = canvas.getFigureManager().store(name);
        if (!result) {
            JOptionPane.showMessageDialog(tool, "Cannot save: " + filename + ".");
            return false;
        }
        
        canvas.setChanged(false);
        return true;
    }
    
    /**
     * Closes the current file storing information about figures.
     */
    void closeFile() {
    	if (tool.tabManager.getTabCount() >= 1) {
	    	getCurrnetCanvas();
	        if (canvas.hasChanged()) {
	            int result = JOptionPane.showConfirmDialog(tool,
	              "Do you want to save the changes you have made to this file?");
	            
	            if (result == JOptionPane.CANCEL_OPTION) {
	                return;
	                
	            } else if (result == JOptionPane.YES_OPTION) {
	                if (!saveFile()) {
	                    int result2 = JOptionPane.showConfirmDialog(tool,
	                      "Do you want to close without saving the chages?");
	                    if (result2 == JOptionPane.CANCEL_OPTION) {
	                        return;
	                    }
	                }
	            }
	        }
	        
	        canvas.stopAutoSave();
	        tool.tabManager.closeCurrentTab();
    	}
    	
    	if (tool.tabManager.getTabCount() == 0) {
    		DrawCanvas newCanvas = new DrawCanvas(tool, tool.tabManager.selector);
    		tool.tabManager.createTab("Untitled", newCanvas);
            tool.tabManager.changeLastTab();
    	}
    }
    
    void exitTool() {
    	getCurrnetCanvas();
        if (canvas.hasChanged()) {
            int result = JOptionPane.showConfirmDialog(tool,
              "Do you want to save the changes you have made to this file?");
            
            if (result == JOptionPane.CANCEL_OPTION) {
                return;
                
            } else if (result == JOptionPane.YES_OPTION) {
                if (!saveFile()) {
                    int result2 = JOptionPane.showConfirmDialog(tool,
                      "Do you want to close without saving the chages?");
                    if (result2 == JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                }
            }
        }
        
        canvas.stopAutoSave();
        
    }
    
    /**
     * Tests if there exists a file with a given name.
     * @param name the name to be checked
     * @return <code>true</code> if there exists a file with the given name, otherwise <code>false</code>
     */
    private boolean fileExists(String name) {
        File file = new File(name);
        return file.exists();
    }
    
    /**
     * Obtains the name of a file specified by a user.
     * @param open <code>true</code> if a file open chooser will be activated,
     *        or <code>false</code>if a file save chooser will be activated
     * @return the name of the specified file.
     */
    private File getFile(boolean open) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("File Chooser");
        chooser.setCurrentDirectory(directory);
        
        int result;
        if (open) {
            result = chooser.showOpenDialog(tool);
        } else {
            result = chooser.showSaveDialog(tool);
        }

        File file = chooser.getSelectedFile();

        if (file != null && result == JFileChooser.APPROVE_OPTION) {
            directory = chooser.getCurrentDirectory();
            return file;
        }
        return null;
    }
}
