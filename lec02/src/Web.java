import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cdh on 5/12/17.
 */
public class Web {


    public static String fetch(String urlString) throws MalformedURLException, IOException {
        URL url=new URL(urlString);

        return fetch(url);
    }
    public static String fetch(URL url) throws MalformedURLException,IOException{
        //open a stream from the web server
        InputStream input=url.openStream();
        InputStreamReader reader=new InputStreamReader(input);

        //create a stream that appends data together into a string
        StringWriter writer=new StringWriter();

        //copy from the web server stream to the string stream
        //define in a few slides
        copyStream(reader,writer);

        //return the string we created
        return writer.toString();
    }

    /**
     * Copies all data from the "from" stream to
     * the "to" stream, then closes both streams.
     * Throws IOException if any error occurs.
     * @param from
     * @param to
     * @throws IOException
     */
    public static void copyStream(Reader from, Writer to) throws IOException{
        char[] buffer=new char[10000];

        //any size buffer would work, but bigger performs better
        while(true){
            int n=from.read(buffer);//if the stream is larger than sizeof buffer, only read len in a iteration
            if(n==-1)
                break;//"from" stream is done
            to.write(buffer,0,n);
        }
        from.close();
        to.close();
    }
}
