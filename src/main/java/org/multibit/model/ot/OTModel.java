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
package org.multibit.model.ot;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import org.multibit.model.AbstractModel;
import org.multibit.model.ModelEnum;
import org.multibit.model.core.CoreModel;

/**
 *
 * @author Cameron Garnham <da2ce7@gmail.com>
 */
public class OTModel extends AbstractModel<CoreModel> {
    public static final String PROP_PASSWORDIMAGEPATH = "PROP_PASSWORDIMAGEPATH";

    
    private String passwordImagePath = null;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    private final transient VetoableChangeSupport vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);

    public OTModel(CoreModel coreModel) {
        super(coreModel);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        this.vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        this.vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    @Override
    public ModelEnum getModelEnum() {
        return ModelEnum.OPENTRANSACTIONS;
    }

    /**
     * @return the passwordImagePath
     */
    public String getPasswordImagePath() {
        return passwordImagePath;
    }

    /**
     * @param passwordImagePath the passwordImagePath to set
     */
    public void setPasswordImagePath(String passwordImagePath) throws PropertyVetoException {
        java.lang.String oldPasswordImagePath = this.passwordImagePath;
        vetoableChangeSupport.fireVetoableChange(PROP_PASSWORDIMAGEPATH, oldPasswordImagePath, passwordImagePath);
        this.passwordImagePath = passwordImagePath;
        propertyChangeSupport.firePropertyChange(PROP_PASSWORDIMAGEPATH, oldPasswordImagePath, passwordImagePath);
    }

}
