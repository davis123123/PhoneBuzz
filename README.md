# PhoneBuzz
# Author: Davis Wong
PhoneBuzz game for LendUp

Ufortunately I only had enough time to complete Phases 1, 2, and 3. 

Setup: 

1. Need to have ngrok downloaded.

2. Run ngrok: type in command line -> ngrok http 8080
  
3. 
	TwilioServlet.java:
		Phone number (line 34), twilio ID (line 19), twilio token (line 20), ngrok URL (line 35) might need to be changed in order to run

4. To compile java file

 - cd ~/PhoneBuzz/webapps/twilio
 - Execute command: javac -cp WEB-INF/lib/servlet-api.jar:WEB-INF/lib/twilio-with-dependencies.jar:$CLASSPATH WEB-INF/classes/com/twilio/TwilioServlet.java

5. To start up tomcat in order to run server
  - cd ~/PhoneBuzz
  - sh bin/startup.sh
  - This runs the apache-tomcat localhost 8080
  
6. Go to "(ngrok generated url)/twilio" on web browser to start the game
