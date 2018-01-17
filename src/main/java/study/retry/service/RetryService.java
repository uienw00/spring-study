package study.retry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by uienw00 on 2017. 8. 3..
 */
@Service
public class RetryService {
    @Retryable(value = IllegalArgumentException.class, backoff = @Backoff(delay = 100, maxDelay = 101), maxAttempts = 3)
    public boolean retryCall(String str){
        if(str.equals("error")){
            throw new IllegalArgumentException("errorMessage");
        }
        return true;
    }

    @Recover
    public boolean recoverCall(IllegalArgumentException e, String str){
        System.out.println("recoverCall:"+str);
        return false;
    }

    @Autowired
    RetryTemplate retryTemplate;

    public boolean retryTemplateCall(String str){
        boolean result = false;
        try{
            result = retryTemplate.execute((retryContext) -> {
                System.out.println("getRetryCount:"+retryContext.getRetryCount());
                if(str.equals("error")){
                    throw new IllegalArgumentException("errorMessage");
                }
                return true;
            });
        }catch (Exception e){
            return false;
        }
        return result;
    }
}
