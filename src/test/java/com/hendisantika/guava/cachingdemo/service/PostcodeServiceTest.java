package com.hendisantika.guava.cachingdemo.service;

import com.hendisantika.guava.cachingdemo.repository.PostcodeRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.47
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class PostcodeServiceTest {

    @InjectMocks
    PostcodeService service;

    @Mock
    PostcodeRepo postcodeRepo;

    @Test
    public void getPostCodeQueriesDao() {
        service.getPostcode("5941ED");
        verify(postcodeRepo).findByCode("5941ED");
    }


}