import java.io.IOException;

/**
 * Created by cdh on 5/12/17.
 */
public class Test {
    public static void main(String[] args){

        try{
            Page page=new Page("http://www.nbu.edu.cn");

            System.out.println(page.getContent());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
