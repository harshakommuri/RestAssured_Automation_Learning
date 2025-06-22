import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.AddBody;
import payload.ReusableMethods;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJsonLibreryAPI {

	@Test(dataProvider = "BooksData")
	public static void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response1 = given().header("Content-Type", "application/json")
		.body(AddBody.AddBook("abcs", "25"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println("Below is the response\n");
		System.out.println(AddBody.AddBook(isbn, aisle));
		System.out.println(response1);
		
		JsonPath js = ReusableMethods.rawToJson(response1);
		String id = js.get("ID");
		System.out.println(id);
//		js.get("Msg");
		
	}
	
	@DataProvider(name = "BooksData")
	
	public Object[][] getdata(){
		return new Object[][] {{"ajhy", "32"}, {"kias", "78"}};
	}
}
