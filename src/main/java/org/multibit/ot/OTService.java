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
package org.multibit.ot;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.multibit.controller.ot.OTController;
import org.opentransactions.otapi.OTCallback;
import org.opentransactions.otapi.OTCaller;
import org.opentransactions.otjavalib.Load;

/**
 *
 * @author Cameron Garnham <da2ce7@gmail.com>
 */
public class OTService {

    private final OTController controller;
    
    private final OTCaller javaPasswordCaller = new OTCaller();
    private final OTCallback javaPasswordCallback;

    public OTService(OTController controller) {
        this.controller = controller;
        this.javaPasswordCallback = this.controller.getOTCallback();

        if (controller == null) {
            throw new IllegalStateException("controller cannot be null");
        }

        if (controller.getModel() == null) {
            throw new IllegalStateException("controller.getModel() cannot be null");
        }

        if (controller.getApplicationDataDirectoryLocator() == null) {
            throw new IllegalStateException("controller.getApplicationDataDirectoryLocator() cannot be null");
        }
        try {
            // if we get passed here, we have a fully loaded native API
            LoadOTAPIJ();
        } catch (Load.LoadingOpenTransactionsFailure ex) {
            Logger.getLogger(OTService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadOTAPIJ() throws Load.LoadingOpenTransactionsFailure {
        
        Load.It().InitNative(this.controller.getJavaPathCallback(), null);
        Load.It().Init();
        Load.It().SetupPasswordImage(this.controller.getPasswordImageCallback());
        Load.It().SetupPasswordCallback(javaPasswordCaller, javaPasswordCallback);
        Load.It().LoadWallet();
    }
}
