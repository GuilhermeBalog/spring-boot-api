package com.guilhermebalog.hello;

import com.guilhermebalog.hello.external.Developer;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestTemplateTest {

    @Test
    public void onePlusOne_thenTwo(){
        int sum = 1 + 1;
        int expected = 2;

        assertEquals(expected, sum);
    }

    @Test
    public void getUser_thenCorrectLogin(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.github.com/users/guilhermebalog";
        Developer dev = restTemplate.getForObject(resourceUrl, Developer.class);

        assertEquals("GuilhermeBalog", dev.getLogin());
    }

    @Test
    public void getUser_thenCorrectHeader(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.github.com/users/guilhermebalog";
        HttpHeaders headers = restTemplate.headForHeaders(resourceUrl);

        assertTrue(headers.getContentType().includes(MediaType.APPLICATION_JSON));
    }
}
