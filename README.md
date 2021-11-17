# cat-facts: Alexa Skill Building Test Code
>  This is a Java repository for Andy Nutt and I's capstone project for General Assembly's Software Engineer Immersive: Java program. The repository to our capstone project can be found [here](https://git.generalassemb.ly/matthompson/paypal-java-capstone-project).

***Prerequisites***  
• An [Amazon Web Services (AWS)](https://aws.amazon.com/) account  
• An [Alexa Skill Builder](https://developer.amazon.com/en-US/alexa)

## Getting Started
We started our capstone project by following the tutorial [Alexa Skills Kit SDK for Java](https://developer.amazon.com/en-US/docs/alexa/alexa-skills-kit-sdk-for-java/overview.html). We received responses from Alexa that showed the code *somewhat* worked, but it was not fully functional. 
  
We decided to start from scratch and routinely tested between our IntelliJ IDE, Alexa Developer Console, and AWS Lambda to ensure functionality. We have added the minimum requirements needed to receive a custom response from Alexa, including pulling data from an Open API.  

### Basic Alexa Skill Setup

__1.__ Add Alexa dependencies to the pom.xml file:
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

__2.__ The following dependency was added to help us format API information to the JSON format:
>

        <dependency>
            <groupId>com.cedarsoftware</groupId>
            <artifactId>json-io</artifactId>
            <version>4.5.0</version>
        </dependency>


        
