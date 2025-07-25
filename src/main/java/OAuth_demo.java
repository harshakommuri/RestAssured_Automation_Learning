

import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.ReusableMethods;
import pojoClassesDemos.Api;
import pojoClassesDemos.GetCourses;
import pojoClassesDemos.WebAutomation;

public class OAuth_demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String response1 = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		formParam("grant_type", "client_credentials").
		formParam("scope", "trust").when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
//		System.out.println(response1);
		
		JsonPath js = ReusableMethods.rawToJson(response1);
		String accessToken = js.getString("access_token");
		GetCourses response2 = given().queryParam("access_token", accessToken).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);
		
//		System.out.println(response2);
		System.out.println(response2.getLinkedIn());
		System.out.println(response2.getInstructor()); 
		System.out.println("Finding course title using index");
		System.out.println(response2.getCourses().getApi().get(1).getCourseTitle());
		System.out.println("Finding course title (dynamical element) to get price of cource");
		List<Api> apiCourses = response2.getCourses().getApi();
		for(int i=0; i<apiCourses.size(); i++) {
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
		System.out.println("Get me the all cource titles of WebAutomation");
		List<WebAutomation> WebAutomationCourses = response2.getCourses().getWebAutomation();
		for(int i=0; i<WebAutomationCourses.size(); i++) {
			System.out.println(WebAutomationCourses.get(i).getCourseTitle());
		}
//		String WebAutomationCourses = response2.getCourses().getWebAutomation().get(1).getCourseTitle();
		

	}

}
