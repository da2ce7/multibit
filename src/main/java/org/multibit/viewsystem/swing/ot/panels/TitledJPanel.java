/*
 * The MIT License
 *
 * Copyright 2013 Cameron Garnham <da2ce7@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.multibit.viewsystem.swing.ot.panels;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box.Filler;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.multibit.viewsystem.swing.core.ColorAndFontConstants;
import org.multibit.viewsystem.swing.core.components.FontSizer;
import org.multibit.viewsystem.swing.core.components.MultiBitLabel;

/**
 * Titled JPanel: This is my OT version of MultiBitTitledPanel
 * 
 * 
 * @author Cameron Garnham <da2ce7@gmail.com>
 */
public class TitledJPanel extends JPanel {
    
    private static final Dimension MIN_SIZE = new Dimension(0, 0);
    private static final Dimension MAX_SIZE = new Dimension(32767, 32767);
    
    private final Font font = FontSizer.INSTANCE.getAdjustedDefaultFont();
    private final Font titleFont = FontSizer.INSTANCE.getAdjustedDefaultFontWithDelta(ColorAndFontConstants.MULTIBIT_LARGE_FONT_INCREASE);
    
    private final JLabel titleLabel = new JLabel();
    
    public TitledJPanel(final ComponentOrientation componentOrientation) {
        
        // set component orientation (this will auto-update)
        super.setComponentOrientation(componentOrientation);
        
        // setup common features
        super.setLayout(new GridBagLayout());
        super.setOpaque(false);
        super.setFont(font);
        
        this.titleLabel.setComponentOrientation(componentOrientation);
        
        final TransGradientPanel transGradientPanel = new TransGradientPanel(componentOrientation);
        transGradientPanel.setLayout(new GridBagLayout());
        
        // set defaults
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.BOTH;
        
        // add a filler will take up the rest of the line
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1;
        constraints.weighty = 1;
        transGradientPanel.add(new Filler(MIN_SIZE, MIN_SIZE, MAX_SIZE), constraints);
        transGradientPanel.add(this.titleLabel, constraints);
        transGradientPanel.add(new Filler(MIN_SIZE, MIN_SIZE, MAX_SIZE), constraints);
        super.add(transGradientPanel,constraints);
    }
    
    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }
    
    
    
}
