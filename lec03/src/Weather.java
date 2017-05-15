import java.io.IOException;

/**
 * Created by cdh on 5/15/17.
 */
public class Weather extends Page {

    private String condition;
    private int temperature;

    public Weather(String zipcode)throws IOException{
        super("http://weather.yahooapis.com/forecastrss?p="+zipcode);
    }

    public String getCondition(){
        return condition;
    }

    public int getTemperature(){
        return temperature;
    }

    protected void download()throws IOException{
        super.download();
        String yweather=Match.between(this.getContent(),"<yweather:condition","/>");
        this.condition=Match.between(content,"text=\"","\"");
        this.temperature=Integer.valueOf(Match.between(content,"temp\"","\""));

        Page cachedPage=getPageFromCache(this.url);
        if(cachedPage instanceof Weather){
            Weather cachedWeather=(Weather) cachedPage;
            this.condition=((Weather) cachedPage).getCondition();
            this.temperature=((Weather) cachedPage).getTemperature();
        }
        else{}
    }




}
