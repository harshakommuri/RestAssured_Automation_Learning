import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClassesDemos.AddPlace_session12;
import pojoClassesDemos.Location;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
AddPlace_session12 ad = new AddPlace_session12();
ad.setAccuracy(50);
ad.setName("Harsha");
ad.setAddress("29, side layout, cohen 09");
ad.setLanguage("Telugu");
ad.setWebsite("my.website.com");
ad.setPhone_number("9182385793");
List<String> list1 = new ArrayList<String>();
list1.add("shoe park");
list1.add("shop");
ad.setTypes(list1);
Location l1 = new Location();
l1.setLng(32.333);
l1.setLat(-32.333);
ad.setLocation(l1);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

//		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		RequestSpecification reqreq = given().spec(req).body(ad);
		
		Response response = reqreq.when().post("/maps/api/place/add/json")
		.then().spec(res).extract().response();
		
//		String res = response.asString();
		System.out.println("Response String: " + response.asString());
	}

}
