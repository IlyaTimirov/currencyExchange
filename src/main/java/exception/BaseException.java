package exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;

public class BaseException extends RuntimeException{
    ObjectMapper objectMapper = new ObjectMapper();

    public void sendError(HttpServletResponse response, AppException error){
        response.setStatus(error.error.getStatus());
        writeJson(response, error.error.getMessage(), error.error.getStatus());
    }
    private <T> void writeJson(HttpServletResponse resp, T load, Integer status) {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(status);
            resp.getWriter().write(objectMapper.writeValueAsString(load));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сервера");
        }
    }
}
