package com.hendisantika.guava.cachingdemo.controller;

import com.hendisantika.guava.cachingdemo.entity.PostCode;
import com.hendisantika.guava.cachingdemo.service.PostcodeService;
import com.hendisantika.guava.cachingdemo.service.SharesService;
import com.hendisantika.guava.cachingdemo.service.TemperatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : caching-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/12/17
 * Time: 08.25
 * To change this template use File | Settings | File Templates.
 */

@RestController
@RequestMapping("/")
public class GuavaController {
    private static Logger LOGGER = LoggerFactory.getLogger(GuavaController.class);
    @Autowired
    PostcodeService postcodeService;

    @Autowired
    TemperatureService temperatureService;

    @Autowired
    SharesService shareService;

    @RequestMapping(value = "/temperature/{postcode}", method = RequestMethod.GET)
    public ResponseEntity<String> getTemperature(@PathVariable(value = "postcode") String postcode) {
        LOGGER.info("GET temperature for postcode {}", postcode);
        PostCode pcode = postcodeService.getPostcode(postcode);
        if (pcode == null)
            return new ResponseEntity<>("Unknown postcode", HttpStatus.NOT_FOUND);
        float temperatureForCoordinate = temperatureService
                .getTemperatureForCoordinate(pcode.getCoordinate());
        return new ResponseEntity<>("temperature: " + Float.toString(temperatureForCoordinate), HttpStatus.OK);
    }

    @RequestMapping(value = "/share/{share}", method = RequestMethod.GET)
    public ResponseEntity<String> getSharePrice(@PathVariable(value = "share") String share) {
        try {
            float value = shareService.getValue(share);
            return new ResponseEntity<>(share + "= " + Float.toString(value), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
