

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.ReusableMethods;

public class OAuth_demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String response1 = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		formParam("grant_type", "client_credentials").
		formParam("scope", "trust").when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(response1);
		
		JsonPath js = ReusableMethods.rawToJson(response1);
		String accessToken = js.getString("access_token");
		String response2 = given().queryParam("access_token", accessToken).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		
		System.out.println(response2);

	}

}
