package com.twitterapitest.statuses;

import com.twitterapitest.common.RestUtilities;
import com.twitterapitest.constants.EndPoints;
import com.twitterapitest.constants.Path;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


import static org.hamcrest.Matchers.hasItem;

public class UserTimeTest {
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void setup() {
        reqSpec = RestUtilities.getRequestSpecification(); // this will intialize my request specification from Rest Utilities Class
        reqSpec.queryParam("user_id", "apiautomation");
        reqSpec.basePath(Path.STATUSES);

        resSpec = RestUtilities.getResponseSpecification();
    }

    @Test
    public void readTweets1() {
        given()
            .spec(RestUtilities.createQueryParam(reqSpec, "count", "1"))
        .when()
            .get(EndPoints.STATUSES_USER_TIMELINE)
        .then()
            .log()
            .all()
            .spec(resSpec)
            .body("user.screen_name", hasItem("apiautomation"));
    }
}
