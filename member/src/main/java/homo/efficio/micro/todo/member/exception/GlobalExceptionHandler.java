package homo.efficio.micro.todo.member.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author homo.efficio@gmail.com
 *         Created on 2017-06-17.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final String DEFAULT_ERROR_VIEW_NAME = "common/error";

    @ExceptionHandler({MemberNotFoundException.class})
    public ModelAndView handleMemberNotFoundException(MemberNotFoundException e,
                                                      HttpServletRequest req) {
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        log.error("url: {}, message: {}", req.getRequestURI(), e.getMessage());

        return mv;
    }
}
