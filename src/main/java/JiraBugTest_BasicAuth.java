import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
//here we have created a bug in jira using rest assured script. Here we have used
//API Basic authentication(we used jira api token, converted token to BASE64 formate.) 
//And we have atteched screan shot to our bug using issue id. 
//We can find all related links here:
//1. https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-issue-types/#api-rest-api-3-issuetype-post
//2. https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-issue-attachments/#api-rest-api-3-issue-issueidorkey-attachments-post
//3. POSTMAN project links - https://www.postman.com/cryosat-physicist-32974441/workspace/myworkspace/collection/32076023-048c47f4-261a-418e-b43a-98f382d52dad?action=share&source=copy-link&creator=32076023
public class JiraBugTest_BasicAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://kommuriharsha110.atlassian.net";
		
		String jiraBug = given().log().all()
		.header("Content-Type", "application/json")
		//jira api token in BASE64 formate
		.header("Authorization", "Basic a29tbXVyaWhhcnNoYTExMEBnbWFpbC5jb206QVRBVFQzeEZmR0Ywc181N0N3X05LdEJBSmtWdkFINkRMLVdnT1pTWHVMdEp1WXpMaHozd1dxV0hjZ1RnVXVyeHNuc0xpckotcGhXQmFka2V1ODdaUy10WWJ6cG15Rzg2TExnYU1Cc3B3MGRYV2VubUYwTjVQdWZyMjR4YnhDQjdHLVlFLVpWZXdWTFVnbjdlOXNmZnpfWWxCaE9yaXZwbHpYZE5DeEpJZGhZOHZDN0VGUlRIa0lBPTcwQUU1RDlG")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Logins are not working\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "")
		.when().post("/rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(jiraBug);
		String issueID = js.get("id");
		System.out.println(issueID);
		
		given().log().all()
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization", "Basic a29tbXVyaWhhcnNoYTExMEBnbWFpbC5jb206QVRBVFQzeEZmR0Ywc181N0N3X05LdEJBSmtWdkFINkRMLVdnT1pTWHVMdEp1WXpMaHozd1dxV0hjZ1RnVXVyeHNuc0xpckotcGhXQmFka2V1ODdaUy10WWJ6cG15Rzg2TExnYU1Cc3B3MGRYV2VubUYwTjVQdWZyMjR4YnhDQjdHLVlFLVpWZXdWTFVnbjdlOXNmZnpfWWxCaE9yaXZwbHpYZE5DeEpJZGhZOHZDN0VGUlRIa0lBPTcwQUU1RDlG")
		.multiPart("file", new File("C:/Users/kommu/Desktop/Screenshot 2022-09-15 165144.jpg"))
		.when().post("//rest/api/3/issue/"+issueID+"/attachments")
		.then().log().all().assertThat().statusCode(200);
	}
	
	

}
