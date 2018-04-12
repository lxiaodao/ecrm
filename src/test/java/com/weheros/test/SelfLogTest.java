
package com.weheros.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SelfLogTest extends GenericJunitDataInDBTest {
   
    protected Logger log = LoggerFactory.getLogger(getClass());
    @Test
    public void logself(){
        log.info("-----------------self4JJJ------------------");
    }
}
