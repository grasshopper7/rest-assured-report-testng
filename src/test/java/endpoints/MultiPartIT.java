package endpoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import tech.grasshopper.filter.ExtentRestAssuredFilter;

public class MultiPartIT {

	@Test
	public void multiPartPost() throws Exception {

		File file = new File("src/test/resources/multi.txt");
		InputStream stream = new FileInputStream(file);

		Path path = Paths.get("src/test/resources/multi.txt");
		byte[] data = Files.readAllBytes(path);

		String longStuff = "Hello How are you? What is happening with you? Hello How are you? What is happening with you? Hello How are you? What is happening with you? Hello How are you? What is happening with you? Hello How are you? What is happening with you? Hello How are you? What is happening with you? Hello How are you? What is happening with you? ";

		given().contentType(ContentType.MULTIPART).param("hello", "hello").multiPart("name", "Jane Doe")
				.multiPart("occupation", "automation").multiPart("long one", longStuff).multiPart(file).multiPart(
						"stream", "multistream.txt", stream)
				.multiPart("bytes", "multibyte.txt", data)/* .log().all() */
				.filter(new ExtentRestAssuredFilter()).when()
				.post("https://run.mocky.io/v3/e2ba1326-1863-40da-9666-ff70661b2111").then().log().all()
				.statusCode(200);
	}

	@Test
	public void paramPost() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Jane Doe");
		map.put("age", "50");
		map.put("sex", "female");

		given().contentType(ContentType.JSON)/* .body(map) */.param("hello", "hello").formParam("form", "form")
				.queryParam("query", "query")./* log().all(). */filter(new ExtentRestAssuredFilter()).when()
				.post("https://run.mocky.io/v3/e2ba1326-1863-40da-9666-ff70661b2111").then().log().all()
				.statusCode(200);
	}
}
