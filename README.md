# cat-facts: Alexa Skill Building Test Code
>  This is a Java repository for Andy Nutt and I's capstone project for General Assembly's Software Engineer Immersive: Java program. The repository to our capstone project can be found [here](https://git.generalassemb.ly/matthompson/paypal-java-capstone-project).

***Prerequisites***  
• An [Amazon Web Services (AWS)](https://aws.amazon.com/) account  
• An [Alexa Skill Builder](https://developer.amazon.com/en-US/alexa) account

## Getting Started
We started our capstone project by following the tutorial [Alexa Skills Kit SDK for Java](https://developer.amazon.com/en-US/docs/alexa/alexa-skills-kit-sdk-for-java/overview.html). We received responses from Alexa that showed the code *somewhat* worked, but it was not fully functional. 
  
We decided to start from scratch and routinely tested between our IntelliJ IDE, Alexa Developer Console, and AWS Lambda to ensure functionality. We have added the minimum requirements needed to receive a custom response from Alexa, including pulling data from an Open API.  

### Basic Alexa Skill Setup

__1.Add Alexa dependencies to the pom.xml file:__
> 

        <dependency>
            <groupId>com.amazon.alexa</groupId>
            <artifactId>ask-sdk</artifactId>
            <version>2.41.0</version>
        </dependency>

        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-lambda-java-core</artifactId>
            <version>1.2.1</version>
        </dependency>

__2. The following dependency was added to help us format API information to the JSON format:__
>

        <dependency>
            <groupId>com.cedarsoftware</groupId>
            <artifactId>json-io</artifactId>
            <version>4.5.0</version>
        </dependency>

__3. Create the following additional packages in the com.example package:__  
• controller  
• handlers  
• service  

__4. Let's get started with our Alexa handlers! The handlers are responsible for ensuring Alexa provides the proper response to an *Intent*. Intents are defined in each separate class and are referenced in the Alexa Developer Console.__  
  
***Please note that many of the handlers provided in the Alexa Tutorial are pre-built in each skill. Each handler is setup the same way with the ability to customize.***  
  
The following is a template and example to setup a handler class:
>  

    import com.amazon.ask.dispatcher.request.handler.HandlerInput;
    import com.amazon.ask.dispatcher.request.handler.RequestHandler;
    import com.amazon.ask.model.Response;

    import java.util.Optional;

    import static com.amazon.ask.request.Predicates.intentName;

    public class WelcomeRequestHandler implements RequestHandler {
    
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("WelcomeIntent"));
      }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech("Welcome")
                .build();
      }
    }
Place your Alexa Skill's Intent Name as a String in the parenthesis after "intentName".  
Place your desired Alexa's response as a String in the parenthesis after ".withSpeech".  
We will explore customizing a request handler later!  

__5. Create a Java Class in the Service package. In this example, we named it "OpenAPIService."__  
This is where we access an open API providing cat facts. We used [Cat Facts API Documentation](https://alexwohlbruck.github.io/cat-facts/docs/). Alexa's method "withSpeech" in the HandlerInput class requires a String value. We decided to return Strings through our various methods in order to easily provide Alexa with the information we want returned.  

  - Create a method to retrieve information from the API:
  >
  
        public String getApiRequest() throws IOException, JSONException {

        URL getUrl = new URL("https://cat-fact.herokuapp.com/facts/random");

        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        
        // Set request method
        connection.setRequestMethod("GET");

        // Getting response code
        int responseCode = connection.getResponseCode();

        // If responseCode is 200 means we get data successfully
        if (responseCode == HttpURLConnection.HTTP_OK) {
            
        // This isolates a specific key and value in the returned JSON data
        JSONObject json = readJsonFromUrl(getUrl.toString());
        String factReturn = (String) json.get("text");

            // Print result in string format
            return factReturn;
        } else {
            return String.valueOf(responseCode);
        }
    }  
    
Originally, we had created a BufferedReader inside the "if" statement. We ran into multiple issues as it did not turn the data into a JSONObject. We created two helper methods to assist isolating the desired information:
  
  > 
  
    private static String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(bufferedReader);
            return new JSONObject(jsonText);
        }
    }
This required a lot of research and testing. A large majority of our time was used debugging and testing to make this code work exactly how we wanted. Our main takeaway was to instantiate the information as a JSONObject ASAP. You need to do this in order to return a key's value. The JSONParser Class and many related methods have be __depreciated.__  
