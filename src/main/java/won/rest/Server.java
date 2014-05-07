package won.rest;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Stateless
public class Server implements IServer{
    @Resource
    WebServiceContext context;

    @Override
    public String serverInfo(String dc, String hc, String server) {

        MessageContext mc = context.getMessageContext();   // This is line that causes the NullPointer exception
        HttpServletRequest request = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);

        return request.getParameter("dc");
    }

}
