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
package org.multibit.controller.ot;

import java.awt.Dialog;
import java.net.URI;
import javax.swing.JDialog;
import org.multibit.controller.AbstractController;
import org.multibit.controller.AbstractEventHandler;
import org.multibit.controller.core.CoreController;
import org.multibit.model.ot.OTModel;
import org.multibit.viewsystem.View;
import org.multibit.viewsystem.swing.core.actions.ExitAction;
import org.opentransactions.otapi.OTCallback;
import org.opentransactions.otapi.OTPassword;
import org.opentransactions.otjavalib.Load.IJavaPath;
import org.opentransactions.otjavalib.Load.IPasswordImage;
import org.opentransactions.otjavalib.util.Utility;

/**
 *
 * @author Cameron Garnham <da2ce7@gmail.com>
 */
public class OTController extends AbstractController<CoreController> {

    private final OTController.EventHandler eventHandler;
    private OTModel model;
    
    private final PasswordImageCallback passwordImageCallback = new PasswordImageCallback();
    private final OTPathCallback otPathCallback = new OTPathCallback();
    
    private OTPasswordCallback otPasswordCallback = null;
    
    
    

    public OTController(CoreController coreController) {
        super(coreController);

        this.eventHandler = new EventHandler(this);
        super.addEventHandler(this.getEventHandler());
    }

    @Override
    public final AbstractEventHandler getEventHandler() {
        return this.eventHandler;
    }

    @Override
    public OTModel getModel() {
        return model;
    }

    public void setModel(OTModel model) {
        this.model = model;
    }

    public IJavaPath getJavaPathCallback() {
        return this.otPathCallback;
    }

    private class OTPathCallback implements IJavaPath {

        private transient Boolean userCancelled = false;
        
        @Override
        public Boolean GetIfUserCancelled() {
            return this.userCancelled;
        }

        @Override
        public String GetJavaPathFromUser(String message) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public final Boolean UpdatePasswordImagePath(String message)
    {
        String passwordImagePath = this.getModel().getPasswordImagePath();
        if (null == passwordImagePath) super.displayView(View.OT_CHANGE_PASSWORD_IMAGE);
        return true;
    }
    
    
    public IPasswordImage getPasswordImageCallback() {
        return this.passwordImageCallback;
    }

    private class PasswordImageCallback implements IPasswordImage {

        private transient Boolean userCancelled = false;
        
        @Override
        public Boolean GetIfUserCancelled() {
            return this.userCancelled;
        }

        @Override
        public String GetPasswordImageFromUser(String message) {
            if (!UpdatePasswordImagePath(message))
            {
                this.userCancelled = true;
                return getModel().getPasswordImagePath();
            }
            
            {
                final String passwordImagePath = getModel().getPasswordImagePath();
                if (null != passwordImagePath) {
                    return passwordImagePath;
                }
            }
            // if we get here we need to get the path from the users.
            return null;
            
        }

        @Override
        public boolean SetPasswordImage(String path) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public OTCallback getOTCallback() {
        if (null == this.otPasswordCallback) {
            this.otPasswordCallback = new OTPasswordCallback();
        }
        return this.otPasswordCallback;
    }

    private class OTPasswordCallback extends OTCallback {

        @Override
        public void runOne(String szDisplay, OTPassword theOutput) {
            if (null == theOutput) {
                System.out.println("JavaCallback.runOne: Failure: theOutput variable (for password to be returned) is null!");
                return;
            }
            if (!Utility.VerifyStringVal(szDisplay)) {
                System.out.println("JavaCallback.runOne: Failure: strDisplay string (telling you what password to type) is null!");
                return;
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void runTwo(String szDisplay, OTPassword theOutput) {
            if (null == theOutput) {
                System.out.println("JavaCallback.runTwo: Failure: theOutput variable (for password to be returned) is null!");
                return;
            }
            if (!Utility.VerifyStringVal(szDisplay)) {
                System.out.println("JavaCallback.runOne: Failure: strDisplay string (telling you what password to type) is null!");
                return;
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class EventHandler extends AbstractEventHandler<OTController> {

        /**
         * Multiple threads will write to this variable so require it to be
         * volatile to ensure that latest write is what gets read
         */
        private volatile URI rawBitcoinURI = null;

        public EventHandler(OTController controller) {
            super(controller);
        }

        @Override
        public void handleOpenURIEvent(URI rawBitcoinURI) {
            // do nothing
        }

        @Override
        public void handleQuitEvent(ExitAction exitAction) {
            // do nothing
        }
    }
}
