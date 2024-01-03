package com.company.libraryservice.client;

import com.company.libraryservice.exception.BookNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try(InputStream body = response.body().asInputStream()){
            message = new ExceptionMessage((String) response.headers().get("date").toArray()[0],
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());

        }catch (IOException ex){
            return new Exception(ex.getMessage());
        }

        switch (response.status()){
            case 404:
//                throw new BookNotFoundException(message);
                throw new BookNotFoundException(message.message() != null ? message.message() : "Not found");
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }


}
