package utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Response {

    default <T> void response(HttpServletResponse resp, T load, Integer status) throws IOException {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(status);
            resp.getWriter().write(new ObjectMapper().writeValueAsString(load));
        } catch (RuntimeException e){
            throw new RuntimeException();
        }
    }
}
