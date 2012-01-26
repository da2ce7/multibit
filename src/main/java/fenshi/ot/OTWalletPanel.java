/*
 * The MIT License
 *
 * Copyright 2012 shinitei.
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
package fenshi.ot;

import com.google.bitcoin.core.Wallet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.multibit.viewsystem.swing.view.BlinkLabel;
import org.multibit.viewsystem.swing.view.yourwallets.RoundedPanel;

/**
 *
 * @author shinitei
 */
public class OTWalletPanel extends RoundedPanel implements ActionListener{
    
    protected OT_Controller Controller;
    protected LocalWallet LW;
    
    private JLabel walletFilenameLabel;
    private JTextField walletDescriptionTextField;
    private Border walletDescriptionTextFieldBorder;
    
    private JButton OpenWalletButton;
    
    public static final int MINIMUM_WALLET_WIDTH = 220;
    public static final int MINIMUM_WALLET_HEIGHT = 90;
    
    private static final Color BACKGROUND_COLOR_NORMAL = new Color(0x40B840);
    private static final Color BACKGROUND_COLOR_FOCUSED = new Color(0x206420);
    private static final Color BACKGROUND_COLOR_DATA_HAS_CHANGED = new Color(0xff, 0xff, 0xff);
    
    private static final Dimension ABOVE_BASELINE_LEADING_CORNER_PADDING = new Dimension(5, 12);
    private static final Dimension BELOW_BASELINE_TRAILING_CORNER_PADDING = new Dimension(9, 12);
    
    public OTWalletPanel(OT_Controller C, LocalWallet localWallet)
    {
        super(C.getLocale());
        Controller = C;  
        LW = localWallet;
        
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(MINIMUM_WALLET_WIDTH, MINIMUM_WALLET_HEIGHT));
        setPreferredSize(new Dimension(MINIMUM_WALLET_WIDTH, MINIMUM_WALLET_HEIGHT));
        setOpaque(false);
        setFocusable(true);
        setBackground(BACKGROUND_COLOR_NORMAL);
        
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel filler1 = new JLabel();
        filler1.setMinimumSize(ABOVE_BASELINE_LEADING_CORNER_PADDING);
        filler1.setPreferredSize(ABOVE_BASELINE_LEADING_CORNER_PADDING);
        filler1.setMaximumSize(ABOVE_BASELINE_LEADING_CORNER_PADDING);

        filler1.setOpaque(false);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.04;
        constraints.weighty = 0.04;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        add(filler1, constraints);

        walletFilenameLabel = new JLabel();
        walletFilenameLabel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));

        String walletFilename = LW.getName();

        File walletFile = new File(walletFilename);
        if (walletFile != null) {
            String walletFilenameFull = walletFile.getName();
            String walletFilenameShort = walletFilenameFull.replaceAll("\\.xml", "");
            walletFilenameLabel.setText(walletFilenameShort);
            walletFilenameLabel.setToolTipText(walletFilename);
        }
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.92;
        constraints.weighty = 0.09;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        add(walletFilenameLabel, constraints);

        walletDescriptionTextField = new JTextField(LW.getName());
        //walletDescriptionTextField.setFocusable(true);
        //walletDescriptionTextField.addActionListener(this);
        //walletDescriptionTextField.addFocusListener(this);
        walletDescriptionTextFieldBorder = walletDescriptionTextField.getBorder();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 0.92;
        constraints.weighty = 3.5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(walletDescriptionTextField, constraints);

        OpenWalletButton = new JButton();
        OpenWalletButton.setBackground(BACKGROUND_COLOR_NORMAL);
        OpenWalletButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3));
        OpenWalletButton.setText("Open");
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.weightx = 0.92;
        constraints.weighty = 0.5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
        add(OpenWalletButton, constraints);
        OpenWalletButton.addActionListener(this);

        JPanel filler4 = new JPanel();
        filler4.setMinimumSize(BELOW_BASELINE_TRAILING_CORNER_PADDING);
        filler4.setPreferredSize(BELOW_BASELINE_TRAILING_CORNER_PADDING);
        filler4.setMaximumSize(BELOW_BASELINE_TRAILING_CORNER_PADDING);
        filler4.setOpaque(false);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.weightx = 0.02;
        constraints.weighty = 0.02;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
        add(filler4, constraints);

        setSelected(false);
        
        applyComponentOrientation(ComponentOrientation.getOrientation(Controller.getLocale()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InsideWallet insideWallet = new InsideWallet(LW);
        insideWallet.setTitle(LW.getName());
        insideWallet.setLocationRelativeTo(this);
        insideWallet.setVisible(true);
    }
    
    protected boolean focused = false;
    public void focus()
    {
        focused = true;
        setBackground(BACKGROUND_COLOR_FOCUSED);
    }
    
    public void unfocus()
    {
        focused = false;
        setBackground(BACKGROUND_COLOR_NORMAL);
    }
    
    @Override
    public void addMouseListener(MouseListener L)
    {
        super.addMouseListener(L);
        walletFilenameLabel.addMouseListener(L);
        OpenWalletButton.addMouseListener(L);
        walletDescriptionTextField.addMouseListener(L);
    }
    
    public boolean isFocused()
    {
        return focused;
    }
}
