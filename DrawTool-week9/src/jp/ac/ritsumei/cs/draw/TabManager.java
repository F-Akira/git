package jp.ac.ritsumei.cs.draw;

import java.util.*;
import javax.swing.*;

public class TabManager {
	private JTabbedPane tab;
	private JPanel tabber;
	FigureSelector selector;
	
	public TabManager(DrawTool tool, FigureSelector selector) {
		this.selector = selector;
		tabber = tool.getTabber();
		
		DrawCanvas canvas = new DrawCanvas(tool, selector);
		tab = new JTabbedPane();
		tabber.add(tab);
		
        createTab("Untitled", canvas);
	}
	
    public void createTab(String title, DrawCanvas canvas) {
    	tab.addTab(title, canvas);
    	canvas.setFileName(title);
    }
    
    public DrawCanvas getCanvas() {
    	return (DrawCanvas) tab.getSelectedComponent();
    }
    
    public DrawCanvas getCanvas(int index) {
    	return (DrawCanvas) tab.getComponentAt(index);
    }
    
    public void changeLastTab() {
    	tab.setSelectedIndex(tab.getTabCount()-1);
    }
    
    public void closeCurrentTab() {
    	tab.remove(tab.getSelectedIndex());
    }
    
    public int getTabCount() {
    	return tab.getTabCount();
    }
}
