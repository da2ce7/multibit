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

import fenshi.otapi.OTApi;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.multibit.controller.MultiBitController;

/**
 *
 * @author shinitei
 */
public class OT_Controller {
    //public static JFLogger L;
    static PrintStream FW;
    protected MultiBitController multicontroller;
    protected LocalWallet LW;
    
    public void init()
    {
        OTApi.OT_API_Init("./wallets");
        loadWallet("default.xml");
        
    }
    public OT_Controller(MultiBitController multiC){
        multicontroller = multiC;
    }
    
    //Obsługa błedów bez konsoli
    static{
        try
        {
            File F = new File("jflog.txt");
            F.createNewFile();
            FW = new PrintStream(F);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getClass().getName()+"\n"+ex.getMessage());
        }
        
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                JOptionPane.showMessageDialog(null, e.getClass().getName()+"\n"+e.getMessage());
                e.printStackTrace(FW);
                FW.flush();
            }
        });
    }
    
    /**
     * 
     * @param name
     * @param t by rozróźnić funkcję
     * @return 
     */
    public LocalWallet loadWallet(String name, boolean t)
    {
        LocalWallet LW = new LocalWallet();
        LW.Name = name;
        return LW;
    }
    
    public LocalWallet loadWallet(String name)
    {
        OTApi.OT_API_LoadWallet(name);
        LW = new LocalWallet();
        LW.Name = name;
        //Załaduj serwery
        int serverCount = OTApi.OT_API_GetServerCount();
        for(int i = 0; i<serverCount; i++)
        {
            Server S = new Server();
            S.ID = OTApi.OT_API_GetServer_ID(i);
            S.Name = OTApi.OT_API_GetServer_Name(S.ID);
            LW.addServer(S);
        }
        
        //Załaduj nymy
        int nymCount = OTApi.OT_API_GetNymCount();
        for(int i = 0; i<nymCount; i++)
        {
            Nym N = new Nym();
            N.ID = OTApi.OT_API_GetNym_ID(i);
            N.Name = OTApi.OT_API_GetNym_Name(N.ID);
            LW.addNym(N);
        }
        
        //Załaduj assety
        int assetCount = OTApi.OT_API_GetAssetTypeCount();
        for(int i = 0; i<assetCount; i++)
        {
            Asset A = new Asset();
            A.ID = OTApi.OT_API_GetAssetType_ID(i);
            A.Name = OTApi.OT_API_GetAssetType_Name(A.ID);
            LW.addAsset(A);
        }
        return LW;
    }
    
    public OTWalletsPanel createPanel(){
        return new OTWalletsPanel(this, multicontroller);
    }
    
    public Locale getLocale(){
        return Locale.ENGLISH;
    }
}
