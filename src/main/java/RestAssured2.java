import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload.AddBody;
import payload.ReusableMethods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


public class RestAssured2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";

//////////////////////////////////////////////////////////////
		
//coping response to string variable

		String response = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json") 
				.body(AddBody.addData())
				.when()
				.post("/maps/api/place/add/json")
				.then()
				//.log().all()
				.assertThat().statusCode(200)
				.body("scope", equalTo("APP")).extract().response().asString(); 

		System.out.println(response);

		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);

////////////////////////////////////////////////////////////////////
//PUT Method

	given().log().all().queryParams("key", "qaclicl123")
	.headers("Content-Type", "application/json")
	.body("{\r\n"
		+ "\"place_id\":\""+placeId+"\",\r\n"
		+ "\"address\":\"70 Summer walk, USA\",\r\n"
		+ "\"key\":\"qaclick123\"\r\n"
		+ "}\r\n"
		+"")
	.when().put("maps/api/place/update/json")
	.then().log().all().assertThat().statusCode(200);

//GET Method
		String response1 = given().log().all()
		.queryParams("key", "qaclick123")
		.queryParams("place_id",placeId)
		.header("Content-Type", "application/json")
		.when().get("maps/api/place/get/json")
		.then()
		//.log().all()
		.assertThat().statusCode(200)
		.extract().response().asString();
		
		//System.out.println(response1);
		JsonPath js2 = ReusableMethods.rawToJson(response1);
		String newAddress = js2.getString("address");
		System.out.println(newAddress);
		Assert.assertEquals(newAddress, "70 Summer walk, USA");

	}
}
