package brave.spring.webmvc;

import brave.servlet.HttpServletAdapter;
import javax.servlet.http.HttpServletRequest;

class WebMVCAdapter extends HttpServletAdapter {
  // redefined from HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE as doesn't exist until Spring 3
  static final String BEST_MATCHING_PATTERN_ATTRIBUTE =
      "org.springframework.web.servlet.HandlerMapping.bestMatchingPattern";

  @Override public String templateFromRequest(HttpServletRequest request) {
    Object result = request.getAttribute(BEST_MATCHING_PATTERN_ATTRIBUTE);
    return result != null ? result.toString() : null;
  }
}
