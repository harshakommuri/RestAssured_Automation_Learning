import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import payload.AddBody;

public class ComplexJsonParse {

	public static void main(String[] args) {
		JsonPath js2 = new JsonPath(AddBody.CoursesJson());
		
		//no.of courses
		System.out.println("No of courses: "+js2.getInt("courses.size()"));
		
		//print purchase amount
		int purAamm = js2.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase ammount is: "+purAamm);
		
		//print the title of the first course
		String title = js2.getString("courses[0].title");
		System.out.println("Title of the first course is: "+title);
		
		//print all course titles with its prices
		int i=0;
		for(i=0; i<js2.getInt("courses.size()"); i++) {
			String course_Title = js2.get("courses["+i+"].title");
			int course_fee = js2.getInt("courses["+i+"].price");
			System.out.println("course "+i+" title is "+course_Title+" and its fee is: "+course_fee);
		}
		
		//No of RPF course copies sold:
		int j=0;
		for(j=0; j<js2.getInt("courses.size()"); j++) {
			String course_Title = js2.get("courses["+j+"].title");
			//System.out.println(course_Title);
			int copies = js2.getInt("courses["+j+"].copies");
			if(course_Title.equals("RPA")) {
				System.out.println("copies sold: "+copies);
			}
		}
		
		//some of all prices
		int k=0;
		int course_price=0;
		for(k=0; k<js2.getInt("courses.size()"); k++) {
			int price = js2.getInt("courses["+k+"].price");
			int No_of_copies = js2.getInt("courses["+k+"].copies");
			course_price = course_price+price*No_of_copies;
		}
		System.out.println("sum of all courses prices: "+course_price);
		
		int purchase_amount = js2.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(purchase_amount, course_price);
	}
}
