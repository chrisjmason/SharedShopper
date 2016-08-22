package testsuite.pojotests;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import utility.pojo.Item;
import utility.util.CodeUtil;
import utility.util.DateUtil;

@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class ItemTest {
    private static final String TEST_TITLE = "test title";
    private static final String TEST_DESC = "test description";
    private static final String TEST_DESC_NULL = null;
    private static final int TEST_COLOUR = 100;
    private static final String TEST_DATE = DateUtil.getDate();
    private static final long TEST_TIMESTAMP = DateUtil.getTimestamp();
    private static final String TEST_CODE = CodeUtil.getCode();

    @Test
    public void Item_createAndCheck(){
        Item item = new Item(TEST_TITLE,TEST_DESC,TEST_COLOUR,TEST_DATE,TEST_TIMESTAMP,TEST_CODE,null);

        assertThat("Check title", item.getTitle(), is(TEST_TITLE));
        assertThat("Check desc", item.getDescription(), is(TEST_DESC));
        assertThat("Check colour", item.getColour(), is(TEST_COLOUR));
        assertThat("Check date", item.getDate(), is(TEST_DATE));
        assertThat("Check timestamp", item.getTimestamp(), is(TEST_TIMESTAMP));
        assertThat("Check code", item.getCode(), is(TEST_CODE));
    }

    @Test
    public void Item_createAndCheckNullDesc(){
        Item item = new Item(TEST_TITLE,TEST_DESC_NULL,TEST_COLOUR,TEST_DATE,TEST_TIMESTAMP,TEST_CODE,null);

        assertThat("Check title", item.getTitle(), is(TEST_TITLE));
        assertThat("Check colour", item.getColour(), is(TEST_COLOUR));
        assertThat("Check date", item.getDate(), is(TEST_DATE));
        assertThat("Check timestamp", item.getTimestamp(), is(TEST_TIMESTAMP));
        assertThat("Check code", item.getCode(), is(TEST_CODE));
    }
}
