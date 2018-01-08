
package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import java.io.IOException;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Gather;
 
public class TwilioServlet extends HttpServlet {
 
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
        TwiMLResponse twiml = new TwiMLResponse();
        
        // Prompt user to enter a number
        Gather gather = new Gather();
        gather.setAction("handle-key");
        gather.setFinishOnKey("#");
        gather.setTimeout(100);
        gather.setMethod("POST");
        Say sayInGather = new Say("Please enter a number and press pound");
        try {
        	gather.append(sayInGather);
            twiml.append(gather);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
        
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}
