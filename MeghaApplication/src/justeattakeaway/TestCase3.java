package justeattakeaway;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestCase3 {

    private String baseURI = "https://restful-booker.herokuapp.com";
    
    //Create the booking request
    @Test(priority = 1)
    public void createBookingTest() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Peter\",\n" +
                "    \"lastname\" : \"Sam\",\n" +
                "    \"totalprice\" : 300,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2020-02-03\",\n" +
                "        \"checkout\" : \"2020-02-05\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        given().
            contentType("application/json").
            body(requestBody).
        when().
            post(baseURI + "/booking").
        then().
            assertThat().
            statusCode(200).
            body("bookingid", notNullValue()).
            body("booking.firstname", equalTo("Peter")).
            body("booking.lastname", equalTo("Sam"));
    }

    //Update the booking request
    @Test(priority = 2)
    public void updateBookingTest() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Peter\",\n" +
                "    \"lastname\" : \"Sam\",\n" +
                "    \"totalprice\" : 350,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2020-02-04\",\n" +
                "        \"checkout\" : \"2020-02-06\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        int bookingId = createBooking();
        given().
            contentType("application/json").
            body(requestBody).
        when().
            put(baseURI + "/booking/" + bookingId).
        then().
            assertThat().
            statusCode(200).
            body("firstname", equalTo("Peter")).
            body("lastname", equalTo("Sam")).
            body("totalprice", equalTo(350)).
            body("bookingdates.checkin", equalTo("2020-02-04")).
            body("bookingdates.checkout", equalTo("2020-02-06"));
    }
    
   //Read the booking request
    @Test(priority = 3)
    public void getBookingTest() {
        int bookingId = createBooking();
        given().
        when().
            get(baseURI + "/booking/" + bookingId).
        then().
            assertThat().
            statusCode(200).
            body("firstname", notNullValue()).
            body("lastname", notNullValue());
    }

    //Delete the booking request
    @Test(priority = 4)
    public void deleteBookingTest() {
        int bookingId = createBooking();
        given().
        when().
            delete(baseURI + "/booking/" + bookingId).
        then().
            assertThat().
            statusCode(201);
    }

} 