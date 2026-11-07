package com.hendisantika.guava.cachingdemo.service;

import com.hendisantika.guava.cachingdemo.repository.PostcodeRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
class PostcodeServiceTest {

    @InjectMocks
    PostcodeService service;

    @Mock
    PostcodeRepo postcodeRepo;

    @Test
    void getPostCodeQueriesDao() {
        service.getPostcode("5941ED");
        verify(postcodeRepo).findByCode("5941ED");
    }


}