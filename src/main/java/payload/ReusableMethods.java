package payload;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	public static JsonPath rawToJson(String response) {
		JsonPath raw2js = new JsonPath(response);
		return raw2js;
	}
}

