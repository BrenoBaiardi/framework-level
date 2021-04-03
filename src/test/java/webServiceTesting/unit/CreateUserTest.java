package webServiceTesting.unit;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import webServiceTesting.CreateUser;


/**
 * Uses {@link JSONAssert} Library to validate JSON resuts
 * <p>
 * The tests bellow uses the {@link JSONCompareMode#LENIENT} parameter so it only tests
 * the presence of the required fields and therefore does not fail if more than the provided fields exists</p>
 * <p>The use of Lenient comparison provides the possibility of growth without breaking the existing tests
 * </p>
 */
public class CreateUserTest {

  CreateUser createUser = new CreateUser();
  String jsonWithUserAndJob = "{\n" +
      "    \"name\": \"testName\",\n" +
      "    \"job\": \"testJob\"\n" +
      "}";

  String jsonWithUserNameSurnameAndJob = "{\n" +
      "    \"name\": \"testName autoSurname\",\n" +
      "    \"job\": \"testJob\"\n" +
      "}";

  @Before
  public void setup() {
    createUser.setName("testName");
    createUser.setSurName("autoSurname");
    createUser.setJob("testJob");
  }

  @Test
  public void buildBody_validUserAndJob_shouldReturnJsonWithUserAndJob() throws JSONException {
    JSONAssert.assertEquals(jsonWithUserAndJob, createUser.buildBody().toString(), JSONCompareMode.LENIENT);
  }

  @Test
  public void buildBodyWithSurname_validUserAndJob_shouldReturnJsonWithUserNameSurnameAndJob() throws JSONException {
    JSONAssert.assertEquals(jsonWithUserNameSurnameAndJob, createUser.buildBodyWithSurname().toString(), JSONCompareMode.LENIENT);
  }

}