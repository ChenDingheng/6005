package lights;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Created by cdh on 5/15/17.
 */
public class ColoredLightTest {
    @Test
    public void getColoredLightColor() {
        ColoredLight coloredLight = new ColoredLight(Color.cyan);

        Color color=coloredLight.getColor();
        Assert.assertEquals(Color.cyan,color);
    }

}
