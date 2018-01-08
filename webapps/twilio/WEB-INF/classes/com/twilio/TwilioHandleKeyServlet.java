package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Dial;
 
public class TwilioHandleKeyServlet extends HttpServlet {
 
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	// Get the number that the user entered
    	String digitString = request.getParameter("Digits");
    	int digits = 0;
    	
    	if (digitString != null) {
    		digits = Integer.parseInt(digitString);
    	}
        TwiMLResponse twiml = new TwiMLResponse();
        
        try {
	        // Begin playing the FizzBuzz game
	        for (int i = 1; i <= digits; i++) {
	        	// Read out output of the FizzBuzz game depending on current number being read
	        	if (i % 3 == 0 || i % 5 == 0) {
	        		if (i % 3 == 0) {
	        			twiml.append(new Say("Fizz"));
	        		}
	        		if (i % 5 == 0) {
	        			twiml.append(new Say("Buzz"));
	        		}
	        	} else {
	        		twiml.append(new Say(i + ""));
	        	}
	        }
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}
