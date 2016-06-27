
package jp.ac.ritsumei.cs.draw;

import javax.swing.*;
import java.awt.*;

/**
 * Selects one of the predefined figures.
 */
public class FigureSelector extends JPanel {
    
    private static final long serialVersionUID = -8502935409963644243L;
    
    /**
     * The combo box for selecting the shape of a figure to be drawn.
     */
    private JComboBox<String> figureCombo;
    
    /**
     * Creates a new figure selector.
     */
    FigureSelector() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        createComboBox();
    }
    
    /**
     * Creates the combo box for choosing the shape of a figure to be drawn.
     */
    private void createComboBox() {
        JLabel shapeLabel = new JLabel("Shape:");
        add(shapeLabel);
        figureCombo = new JComboBox<String>();
        figureCombo.addItem(Line.name);
        figureCombo.addItem(Rect.name);
        figureCombo.addItem(FilledRect.name);
        figureCombo.addItem(Oval.name);
        figureCombo.addItem(FilledOval.name);
        add(figureCombo);
    }
    
    /**
     * Obtains the name indicating the kind of the selected figure.
     * @return the name string of the selected figure
     */
    String getFigureKind() {
        return (String)figureCombo.getSelectedItem();
    }
}
