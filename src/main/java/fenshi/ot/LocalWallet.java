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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shinitei
 */
public class LocalWallet
{
    protected List<Server> Servers = new ArrayList<Server>();
    protected List<Nym> Nyms = new ArrayList<Nym>();
    protected List<Asset> Assets = new ArrayList<Asset>();
    protected String Name;
    
    public String getName(){
        return Name;
    }
    public void addServer(Server S)
    {
        Servers.add(S);
    }
    
    public void addNym(Nym N){
        Nyms.add(N);
    }
    public void addAsset(Asset A){
        Assets.add(A);
    }
    
    public List <Server> getServers(){
        return Servers;
    }
    
    public List <Nym> getNyms(){
        return Nyms;
    }
    
    public List <Asset> getAssets(){
        return Assets;
    }
}
