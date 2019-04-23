package com.xu.miniapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CharleyXu Created on 2019/4/23.
 */
@RestController
@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
public class HomeController {

  @RequestMapping
  public String home() {
    String str =
        "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
            + //
            "<html>\r\n" + //
            "<head>\r\n" + //
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
            + //
            "<title>Monitor Module Interfaces</title>\r\n" + //
            "</head>\r\n" + //
            "<body>" + //
            "	<table>\r\n" + //
            "		<tr>\r\n" + //
            "			<td>REST APIs:</td>\r\n" + //
            "			<td><a href=\"swagger-ui.html\" target=\"_blank\">swagger-ui.html</a></td>\r\n"
            + //
            "		</tr>\r\n" + //
            "	</table>"//
            + "</body>\r\n" + //
            "</html>";

    return str;
  }

}
