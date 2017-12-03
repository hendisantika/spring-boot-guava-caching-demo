package com.hendisantika.guava.cachingdemo.repository;

import com.hendisantika.guava.cachingdemo.entity.PostCode;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.13
 * To change this template use File | Settings | File Templates.
 */
public interface PostcodeRepo extends CrudRepository<PostCode, Long> {
    PostCode findByCode(String code);
}
