package com.twilio;

import com.twilio.twiml.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;

import java.net.URI;

public class TwilioServlet extends HttpServlet {

    public static final String ACCOUNT_SID = "AC3f62ab3d77c2be3625c84a5bb566a9e8"; //TWILIO ID
    public static final String AUTH_TOKEN = "b9789ed87f2c1f594ad79e1f7efbbc98"; //TWILIO TOKEN

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //PHASE 2
        if (request.getParameter("button1") != null) {
            TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();
            PhoneNumber to = new PhoneNumber(request.getParameter("PhoneNumber"));
            PhoneNumber from = new PhoneNumber("8327260937"); // TWILIO NUMBER
            URI uri = URI.create("http://d22f779c.ngrok.io/twilio/twiml"); //replace (ngrok url)/twilio/twiml

            //PHASE 3
            if (request.getParameter("seconds") != null) {
                String sec = request.getParameter("seconds");
                try {
                    Thread.sleep(1000 * Integer.parseInt(sec));
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            Call call = new CallCreator(to, from, uri).create(client);
            return;
        }
        //PHASE 2

        VoiceResponse.Builder builder = new VoiceResponse.Builder();

        String digits = request.getParameter("Digits");
        if (digits != null) {

            int number = Integer.parseInt(digits);
            if (number < 1) {
                appendGather(builder);
            } else {
                String result = this.fizzBuzzSequence(number);

                builder.say(new Say.Builder(result).build());
            }

        } else {
            appendGather(builder);
        }

        response.setContentType("application/xml");
        try {
            response.getWriter().print(builder.build().toXml());
        } catch (TwiMLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void appendGather(VoiceResponse.Builder builder) {
        builder.gather(new Gather.Builder()
                .timeout(5)
                .finishOnKey("#")
                .say(new Say.Builder("Enter a number and press hashtag to play Fizz Buzz.").build())
                .build()
        )
                .redirect(new Redirect.Builder().url("/twiml").build());
    }

    private String fizzBuzzSequence (int num) {
        String sentence = "";

        for (int i = 1; i <= num; i += 1) {
          if (i % 3 == 0) {
                sentence += "Fizz ";
            }
          if (i % 5 == 0) {
                sentence += "Buzz ";
            }
        }

        return sentence;
    }

}
