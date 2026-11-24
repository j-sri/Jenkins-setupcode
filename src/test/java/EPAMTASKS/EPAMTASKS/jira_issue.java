package EPAMTASKS.EPAMTASKS;


import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class jira_issue {

    static String baseUrl = System.getenv("JIRA_BASE_URL");
    static String email = System.getenv("JIRA_EMAIL");
    static String token = System.getenv("JIRA_API_TOKEN");
    static String projectKey = System.getenv("JIRA_PROJECT_KEY");

    static String firstIssueId;
    static String firstIssueKey;
    static String secondIssueId;
    static String secondIssueKey;

    // Change this to an existing local document
    static String filePath = "C:/JiraFiles/test-document.pdf";

    @BeforeTest
    public void authorization(){

        if (baseUrl == null || email == null ||
            token == null || projectKey == null) {
            throw new IllegalStateException(
                "Configure Jira environment variables first");
        }

        RestAssured.baseURI = baseUrl;
    }

    // Common authentication
    static io.restassured.specification.RequestSpecification
    request() {

        return given()
                .auth()
                .preemptive()
                .basic(email, token)
                .header("Accept", "application/json");
    }

    @Test
    public static void createDefect() {

        String requestBody =
        "{"
        + "\"fields\":{"
        + "\"project\":{\"key\":\"" + projectKey + "\"},"
        + "\"summary\":\"Automation defect created using Rest Assured\","
        + "\"description\":{"
        +   "\"type\":\"doc\","
        +   "\"version\":1,"
        +   "\"content\":[{"
        +     "\"type\":\"paragraph\","
        +     "\"content\":[{"
        +       "\"type\":\"text\","
        +       "\"text\":\"This defect is created through Jira REST API automation.\""
        +     "}]"
        +   "}]"
        + "},"
        + "\"issuetype\":{\"name\":\"Bug\"},"
        + "\"priority\":{\"name\":\"Medium\"}"
        + "}"
        + "}";

        Response response =
            request()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/rest/api/3/issue");

        response.then().statusCode(201);

        firstIssueId = response.jsonPath().getString("id");
        firstIssueKey = response.jsonPath().getString("key");

        System.out.println("STEP 1 - Defect Created");
        System.out.println("Issue ID: " + firstIssueId);
        System.out.println("Issue Key: " + firstIssueKey);
    }

   @Test
    public static void updateDefect() {

        String updateBody =
        "{"
        + "\"fields\":{"
        + "\"summary\":\"Updated Jira defect using issue ID\","
        + "\"description\":{"
        +   "\"type\":\"doc\","
        +   "\"version\":1,"
        +   "\"content\":[{"
        +     "\"type\":\"paragraph\","
        +     "\"content\":[{"
        +       "\"type\":\"text\","
        +       "\"text\":\"Defect updated successfully using Rest Assured.\""
        +     "}]"
        +   "}]"
        + "}"
        + "}"
        + "}";

        request()
        .contentType(ContentType.JSON)
        .body(updateBody)
        .when()
        .put("/rest/api/3/issue/" + firstIssueId)
        .then()
        .statusCode(204);

        System.out.println("STEP 2 - Defect Updated");
    }

    @Test
    public static void searchDefect() {

        Response response =
            request()
            .queryParam("jql", "key = " + firstIssueKey)
            .queryParam("fields", "summary,status,issuetype")
            .queryParam("maxResults", 10)
            .when()
            .get("/rest/api/3/search/jql");

        response.then().statusCode(200);

        int issueCount =
            response.jsonPath().getInt("issues.size()");

        if (issueCount == 0) {
            throw new AssertionError("Created defect not found");
        }

        System.out.println("STEP 3 - Defect Found");
        System.out.println(
            response.jsonPath().getString("issues[0].key"));

        System.out.println(
            response.jsonPath().getString("issues[0].fields.summary"));
    }
@Test
    // STEP 4: Add attachment
    public static void addAttachment(String issueId) {

        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException(
                "Attachment file not found: " + file.getAbsolutePath());
        }

        Response response =
            request()
            .header("X-Atlassian-Token", "no-check")
            .multiPart("file", file)
            .when()
            .post("/rest/api/3/issue/" + issueId + "/attachments");

        response.then().statusCode(200);

        System.out.println("STEP 4 - Attachment Added");
    }
@Test
    // STEP 5: Delete first defect
    public static void deleteDefect(String issueId) {

        request()
        .when()
        .delete("/rest/api/3/issue/" + issueId)
        .then()
        .statusCode(204);

        System.out.println("STEP 5 - Defect Deleted");
    }
@Test
    // STEP 6: Create defect and attach document
    public static void createDefectWithAttachment() {

        String requestBody =
        "{"
        + "\"fields\":{"
        + "\"project\":{\"key\":\"" + projectKey + "\"},"
        + "\"summary\":\"Defect created with attached document\","
        + "\"description\":{"
        +   "\"type\":\"doc\","
        +   "\"version\":1,"
        +   "\"content\":[{"
        +     "\"type\":\"paragraph\","
        +     "\"content\":[{"
        +       "\"type\":\"text\","
        +       "\"text\":\"Defect created with a document attachment.\""
        +     "}]"
        +   "}]"
        + "},"
        + "\"issuetype\":{\"name\":\"Bug\"}"
        + "}"
        + "}";

        Response response =
            request()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/rest/api/3/issue");

        response.then().statusCode(201);

        secondIssueId = response.jsonPath().getString("id");
        secondIssueKey = response.jsonPath().getString("key");

        System.out.println("STEP 6 - Second Defect Created");
        System.out.println("Issue Key: " + secondIssueKey);

        // Upload document to the newly created issue
        addAttachment(secondIssueId);

        System.out.println(
            "Defect created successfully with document attachment.");
    }
}
