package com.sharedshopper.chris.sharedshopper;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import utility.util.RandomColour;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class RandomColourTest {

    public static int[] colours = {R.color.color1,R.color.color2,R.color.color3,R.color.color4,
            R.color.color5,R.color.color6,R.color.color7,R.color.color8};

    @Test
    public void getColour_Check(){
        int colour = RandomColour.getColour();
        boolean inArray = false;

        for (int chosenColour : colours) {
            if(colour == chosenColour){
                inArray = true;
            }
        }

        assertThat("Check colour", inArray, is(true));
    }
}