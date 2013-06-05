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

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import org.multibit.viewsystem.swing.core.ColorAndFontConstants;

/**
 *
 * @author Cameron Garnham <da2ce7@gmail.com>
 */
class TransGradientPanel extends JPanel {
    
    private static final double PROPORTION_TO_FILL = 0.618; // golden ratio
    private static final Color TRANSPARENT_WHITE = new Color(255,255,255,0);
    
    private final ComponentOrientation componentOrientation;
    
    private Color gradientColor =  ColorAndFontConstants.VERY_LIGHT_BACKGROUND_COLOR;
    private Direction direction = Direction.HORIZONTAL; 
    
    TransGradientPanel(final ComponentOrientation componentOrientation) {
        this.componentOrientation = componentOrientation;
        super.setComponentOrientation(componentOrientation);
        super.setOpaque(false);
    }
    
    public void setGradientColor(Color color) {
        this.gradientColor = color;
        super.repaint();
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
        super.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Dimension d = this.getSize();
        Boolean reverse = false;
        
        Point origin = new Point(0,0);
        Point destination = null;
        
        switch (this.direction) {
            case VERTICAL:
                destination = new Point(0, (int) (d.height * PROPORTION_TO_FILL));
                break;
            case HORIZONTAL:
                if (ComponentOrientation.RIGHT_TO_LEFT == componentOrientation) {
                    destination = new Point((int) (d.width * (1 - PROPORTION_TO_FILL)), 0);
                    reverse = true;
                } else {
                    destination = new Point((int) (d.width * PROPORTION_TO_FILL), 0);
                }
                break;
        }
        
        GradientPaint gradientPaint = null;
        if (!reverse) {
            gradientPaint = new GradientPaint(origin, this.gradientColor, destination, TRANSPARENT_WHITE);
        }
        else {
            gradientPaint = new GradientPaint(destination, TRANSPARENT_WHITE, origin, this.gradientColor);
        }
        
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, d.width , d.height);

        super.paintComponent(g);
    }
    
    public enum Direction {
            VERTICAL,
            HORIZONTAL
           }
    
}
