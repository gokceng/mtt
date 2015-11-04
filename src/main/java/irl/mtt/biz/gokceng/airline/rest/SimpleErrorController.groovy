package irl.mtt.biz.gokceng.airline.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.ErrorAttributes
import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.ServletRequestAttributes

import javax.servlet.http.HttpServletRequest

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
@RestController
@RequestMapping("/error")
class SimpleErrorController implements ErrorController {
	@Autowired
	private ErrorAttributes errorAttributes;

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping
	public Map<String, Object> error(HttpServletRequest aRequest) {
		Map<String, Object> body = getErrorAttributes(aRequest, getTraceParameter(aRequest));
		return body;
	}

	private boolean getTraceParameter(HttpServletRequest request) {
		String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}
		return !"false".equals(parameter.toLowerCase());
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest aRequest, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);
		return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}
}
