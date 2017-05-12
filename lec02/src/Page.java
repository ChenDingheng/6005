import java.awt.datatransfer.DataFlavor;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cdh on 5/12/17.
 */
public class Page {
    private static final Page[] cache=new Page[100];
    private static int cachePointer=0;//index of next page to replace in the cache

    private URL url;
    private String content;

    public Page(String urlString) throws MalformedURLException,IOException{
        this.url=new URL(urlString);
        Page p=getPageFromCache(url);
        if(p!=null)
            this.content=p.content;
        else{
            this.content=Web.fetch(this.url);
            putPageInCache(this);
        }

    }

    public URL getURL() {
        return this.url;
    }

    public String getContent(){
        return this.content;
    }

    /**
     * @param url
     * @return the cached Page object for url or null if no such Page in the cache.
     */
    private Page getPageFromCache(URL url){
        for(Page p:cache){
            if(p!=null&&p.getURL().equals(url))
                return p;
        }
        return null;//page not found
    }

    /**
     * store page in the cache.
     * @param page
     */
    private void putPageInCache(Page page){
        cache[cachePointer]=page;
        cachePointer++;
        if(cachePointer>=cache.length)
            cachePointer=0;
    }
}
