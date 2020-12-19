import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class TestAPI {
    private JSONObject jsonObject;

    @Test
    public void sendData() throws JSONException {
        RestAssured.baseURI = "https://run.mocky.io/v3/9bc98948-b3d4-455c-812b-0dba22622c48";
        Response response = null;

        addBody();

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonObject).post();

        if(response.getStatusCode() == 200){
            System.out.println("API OK");
            JsonPath jsonPathEvaluator = response.jsonPath();
            String url = jsonPathEvaluator.get("url");
            String code = jsonPathEvaluator.get("code");
            Assert.assertEquals("STATUS_CREATED",code);
            checkBody(url);
        }else if(response.getStatusCode() == 400){
            System.out.println("Bad Request");
        }else if(response.getStatusCode() == 404){
            System.out.println("Not Found");
        }else if(response.statusCode() == 500){
            System.out.println("Internal Server Error");
        }
    }

    public void checkBody(String url) throws JSONException {
        RestAssured.baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");

        if ((response.getStatusCode() == 200) || (response.getStatusCode() == 201)) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            String prefix = jsonPathEvaluator.get("prefix");
            Assert.assertEquals(prefix,jsonObject.getString("prefix"));

            String name = jsonPathEvaluator.get("name");
            Assert.assertEquals(name,jsonObject.getString("name"));

            String suffix = jsonPathEvaluator.get("suffix");
            Assert.assertEquals(suffix,jsonObject.getString("suffix"));

            String industry_id = jsonPathEvaluator.get("industry_id");
            Assert.assertEquals(industry_id,jsonObject.getString("industry_id"));

            String employee_size = jsonPathEvaluator.get("employee_size");
            Assert.assertEquals(employee_size,jsonObject.getString("employee_size"));

            String street = jsonPathEvaluator.get("street");
            Assert.assertEquals(street,jsonObject.getString("street"));

            String place = jsonPathEvaluator.get("place");
            Assert.assertEquals(place,jsonObject.getString("place"));

            int geograph_id = jsonPathEvaluator.get("geograph_id");
            Boolean result = geograph_id == jsonObject.getInt("geograph_id");
            Assert.assertTrue(result);

            String phone = jsonPathEvaluator.get("phone");
            Assert.assertEquals(phone,jsonObject.getString("phone"));
        }
    }

    public void addBody() throws JSONException {
        jsonObject = new JSONObject();

        jsonObject.put("prefix","PT");
        jsonObject.put("name","Sejahtera");
        jsonObject.put("suffix","Tbk");
        jsonObject.put("industry_id","1");
        jsonObject.put("employee_size","500");
        jsonObject.put("street","Jl.Sudirman kav. 21");
        jsonObject.put("place","Sudirman Tower");
        jsonObject.put("geograph_id",100);
        jsonObject.put("phone","08561290092");
    }
}
