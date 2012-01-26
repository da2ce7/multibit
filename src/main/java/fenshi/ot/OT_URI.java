/*
 * The MIT License
 *
 * Copyright 2012 java_dev.
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

/**
 *
 * @author java_dev
 */
public class OT_URI
{
    public String
            Address,
            Asset,
            Amount,
            Label;
    
    private static String PREFIX = "ot:";
    
    public OT_URI(){}
    
    public OT_URI(String Address, String Asset, String Amount, String Label)
    {
        this.Address = Address;
        this.Asset = Asset;
        this.Amount = Amount;
        this.Label = Label;
    }
    
    public static String URIToString(OT_URI U)
    {
        StringBuilder S = new StringBuilder();
        S.append(PREFIX);
        S.append(U.Address);
        
        char mark = '?';
        if(U.Asset != null)
        {
            S.append(mark);
            mark = '&';
            S.append("asset=").append(U.Asset);
        }
        if(U.Amount!= null)
        {
            S.append(mark);
            mark = '&';
            S.append("amount=").append(U.Amount);
        }
        if(U.Label != null)
        {
            S.append(mark);
            S.append("label=").append(U.Label);
        } 
        return S.toString();
    }
    
    
    public static OT_URI StringToURI(String S)
    {
        OT_URI U = new OT_URI();
        
        if(S.startsWith(PREFIX))
        {
            S = S.substring(PREFIX.length());
        }
        else return null;
        
        U.Address = S.substring(0, S.indexOf('?'));
        S = S.substring(S.indexOf('?'));
        
        if(S.startsWith("asset="))
        {
            S = S.substring("asset=".length());
            U.Asset = S.substring(0, S.indexOf('&'));
        }
                    
        return null;
    }
}
