package endpoints;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

public class XMLMockyTestIT {

	@Test
	public void responseXMLHttpGET() {
		given().filter(new AllureRestAssured()).when()
				.get("https://run.mocky.io/v3/22da6638-907e-4723-b00b-e1b96f0134ed").then().statusCode(200);
	}

	@Test
	public void responseNOHttpDELETE() {
		given().filter(new AllureRestAssured()).when()
				.delete("https://run.mocky.io/v3/4428f30c-19a7-4cf7-b08d-45df0f7f05b8").then().statusCode(204);
	}

	@Test
	public void responseXMLHttpPOSTCode200() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Jane Doe");
		map.put("age", "50");
		map.put("sex", "female");

		given().contentType(ContentType.JSON).body(map).filter(new AllureRestAssured()).when()
				.post("https://run.mocky.io/v3/e2ba1326-1863-40da-9666-ff70661b2111").then().statusCode(200);
	}

	@Test
	public void responseTextHttpPOSTCode201() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Jane Doe");
		map.put("age", "50");
		map.put("sex", "female");

		given().contentType(ContentType.JSON).body(map).filter(new AllureRestAssured()).when()
				.post("https://run.mocky.io/v3/26415f31-fd26-439f-9c86-cc43bffa3297").then().statusCode(201);
	}

	@Test
	public void responseXmlHttpPUTCode200() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", "1");
		map.put("name", "John Doe");
		map.put("age", "40");
		map.put("sex", "male");

		given().contentType(ContentType.JSON).body(map).filter(new AllureRestAssured()).when()
				.put("https://run.mocky.io/v3/c0dbd82a-2f49-49c9-922a-ff050a6c5e92").then().statusCode(200);
	}

	@Test
	public void responseXmlHttpPATCHCode200() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", "1");
		map.put("age", "45");

		given().contentType(ContentType.JSON).body(map).filter(new AllureRestAssured()).when()
				.patch("https://run.mocky.io/v3/8c9163ff-9c06-4aae-ac09-2dd2df03bf20").then().statusCode(200);
	}
}
