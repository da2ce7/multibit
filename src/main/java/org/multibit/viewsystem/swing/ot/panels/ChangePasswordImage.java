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

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Icon;
import javax.swing.JPanel;
import org.multibit.controller.ot.OTController;
import org.multibit.utils.ImageLoader;
import org.multibit.viewsystem.DisplayHint;
import org.multibit.viewsystem.View;
import org.multibit.viewsystem.Viewable;
import org.multibit.viewsystem.swing.MultiBitFrame;
import org.multibit.viewsystem.swing.core.ColorAndFontConstants;

/**
 *
 * @author Cameron Garnham <da2ce7@gmail.com>
 */
public class ChangePasswordImage extends JPanel implements Viewable {
    
    private final MultiBitFrame mainFrame;
    private final OTController controller; 
    
    public ChangePasswordImage(final MultiBitFrame mainFrame, final OTController controller){
        this.mainFrame = mainFrame;
        this.controller = controller;
        
        super.setBackground(ColorAndFontConstants.OT_BACKGROUND_COLOR);
        super.applyComponentOrientation(ComponentOrientation.getOrientation(controller.getLocaliser().getLocale()));
        
        this.initUI();
    }
    
    private void initUI() {
        super.setLayout(new BorderLayout());
        
        final JPanel mainPanel = new JPanel(); 
        mainPanel.setMinimumSize(new Dimension(550,160));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);
        mainPanel.applyComponentOrientation(ComponentOrientation.getOrientation(controller.getLocaliser().getLocale()));
        
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
        mainPanel.add(this.createPathPanel(),constraints);
        
        super.add(mainPanel, BorderLayout.CENTER);
    }
    
    private JPanel createPathPanel()
    {
        final TitledJPanel titledJPanel = new TitledJPanel(super.getComponentOrientation());
        titledJPanel.setTitle("Test Test 1000");
        return titledJPanel;
    }

    @Override
    public void displayView(DisplayHint displayHint) {
        // If it is a wallet transaction change no need to update.
        if (DisplayHint.WALLET_TRANSACTIONS_HAVE_CHANGED == displayHint) {
        }
    }

    @Override
    public Icon getViewIcon() {
        return ImageLoader.createImageIcon(ImageLoader.CHANGE_PASSWORD_ICON_FILE);
    }

    @Override
    public View getViewId() {
        return View.CHANGE_PASSWORD_VIEW;
    }

    @Override
    public String getViewTitle() {
        return this.controller.getLocaliser().getString("ot.changePasswordImage.title");
    }

    @Override
    public String getViewTooltip() {
        return this.controller.getLocaliser().getString("ot.changePasswordImage.tooltip");
    }

    @Override
    public void navigateAwayFromView() {
    }
    
}
