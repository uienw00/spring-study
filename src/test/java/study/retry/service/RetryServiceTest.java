package study.retry.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/spring/application*" })
public class RetryServiceTest {

    @Autowired
    RetryService retryService;

    @Test
    public void testRetry() throws Exception{
        boolean result = retryService.retryCall("error");
        assertFalse(result);
        result = retryService.retryCall("success");
        assertTrue(result);
    }

    @Test
    public void testRetry2() throws Exception {
        boolean result = retryService.retryTemplateCall("error");
        assertFalse(result);
        result = retryService.retryTemplateCall("success");
        assertTrue(result);
    }

}
