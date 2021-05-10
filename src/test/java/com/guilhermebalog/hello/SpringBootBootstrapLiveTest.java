package com.guilhermebalog.hello;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.guilhermebalog.hello.persistence.model.Book;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SpringBootBootstrapLiveTest {
    private static final String API_ROOT = "http://localhost:8081/api/books";

    private Book createRandomBook() {
        final Book book = new Book();
        book.setTitle(randomAlphabetic(10));
        book.setAuthor(randomAlphabetic(10));

        return book;
    }

    private String createBookAsUri(Book book) {
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);

        return API_ROOT + "/" + response.jsonPath().get("id");
    }

    // GET

    @Test
    public void whenGetAllBooks_thenOK() {
        final Response response = RestAssured.get(API_ROOT);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetBooksByTitle_thenOK() {
        final Book book = createRandomBook();
        createBookAsUri(book);

        final Response response = RestAssured.get(API_ROOT + "/title/" + book.getTitle());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class).size() > 0);
    }

    @Test
    public void whenCreatedBookById_thenOK() {
        Book book = createRandomBook();
        String location = createBookAsUri(book);
        Response response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(book.getTitle(), response.jsonPath().get("title"));
    }

    @Test
    public void whenGetNotExistsBookById_thenNotFound() {
        Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // POST

    @Test
    public void whenCreateNewBook_thenCreated() {
        Book book = createRandomBook();
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);

        assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }

    @Test
    public void whenInvalidBook_thenError(){
        Book book = createRandomBook();
        book.setAuthor(null);

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    // PUT

    @Test
    public void whenUpdateCreatedBook_thenUpdated(){
        Book book = createRandomBook();
        String location = createBookAsUri(book);

        book.setId(Long.parseLong(location.split(API_ROOT + "/")[1]));
        book.setAuthor(("newAuthor"));

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .put(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("newAuthor", response.jsonPath().get("author"));

    }

    // DELETE

    @Test
    public void whenDeleteCreatedBook_thenOK(){
        Book book = createRandomBook();
        String location = createBookAsUri(book);

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
}
