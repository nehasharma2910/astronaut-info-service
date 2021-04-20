package com.suncorp.astronautinfo.core;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @SneakyThrows
    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException  {

        if(httpResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new BusinessException(ErrorCodes.ERROR_101);
        } else if (httpResponse.getStatusCode().series() == SERVER_ERROR) {
            throw new TechnicalException(ErrorCodes.ERROR_103 + ": " + httpResponse.getStatusText());
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            throw new BusinessException(ErrorCodes.ERROR_104);
        }
    }
}
