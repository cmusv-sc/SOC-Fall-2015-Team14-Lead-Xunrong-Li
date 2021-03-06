/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import java.util.Iterator;
import java.util.Map.Entry;

import models.User;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import util.Constants;
import util.APICall;
import util.APICall.ResponseType;
import views.html.climate.*;
import views.html.sns.createUserSuccess;
import views.html.sns.login;
import views.html.sns.signup;

public class Application extends Controller {

    final static Form<User> userForm = Form.form(User.class);

    private static User user = null;
    private static String userId;

    public static class Login {

        public String username;
        public String password;

        public String validate() {
            ObjectNode jsonData = Json.newObject();
            jsonData.put("userName", username);
            jsonData.put("password", password);
            System.out.println(username + "***************" + password);
            // POST Climate Service JSON data
            JsonNode response = APICall.postAPI(Constants.NEW_BACKEND
                    + Constants.IS_USER_VALID, jsonData);
            if (response.get("success") == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result home() {
        return ok(home.render("", "", ""));
    }

    public static Result login() {
        return ok(login.render(Form.form(Login.class)));
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Application.login());
    }

    public static Result createSuccess(){
        return ok(createUserSuccess.render());
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        System.out.println("*********1");


        if (loginForm.hasErrors()) {
            System.out.println("*********2");
            for(String error : loginForm.errors().keySet()) {
                System.out.println(loginForm.errors().get(error));
            }
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("userName", loginForm.get().username);

            user = User.getUserByUserName(loginForm.get().username);
            return redirect(routes.MainController.main());
        }
    }

    public static void flashMsg(JsonNode jsonNode){
        Iterator<Entry<String, JsonNode>> it = jsonNode.fields();
        while (it.hasNext()) {
            Entry<String, JsonNode> field = it.next();
            flash(field.getKey(),field.getValue().asText());
        }
    }

    public static Result signup() {

        return ok(signup.render(userForm));
    }


    public static Result createNewUser(){
        Form<User> nu = userForm.bindFromRequest();
        ObjectNode jsonData = Json.newObject();

        try{
            jsonData.put("userName", nu.get().getUserName());
            jsonData.put("firstName", nu.get().getFirstName());
            jsonData.put("middleInitial", nu.get().getMiddleInitial());
            jsonData.put("lastName", nu.get().getLastName());
            jsonData.put("password", nu.get().getPassword());
            jsonData.put("affiliation", nu.get().getAffiliation());
            jsonData.put("title", nu.get().getTitle());
            jsonData.put("email", nu.get().getEmail());
            jsonData.put("mailingAddress", nu.get().getMailingAddress());
            jsonData.put("phoneNumber", nu.get().getPhoneNumber());
            jsonData.put("faxNumber", nu.get().getFaxNumber());
            jsonData.put("researchInterests", nu.get().getResearchInterests());
            jsonData.put("highestDegree", nu.get().getHighestDegree());
            jsonData.put("hasFrontLayerPhoto", false);

            JsonNode response = APICall.postAPI(Constants.NEW_BACKEND + Constants.ADD_USER, jsonData);

            // flash the response message
            Application.flashMsg(response);

            return redirect(routes.Application.createSuccess());

        }catch (IllegalStateException e) {
            e.printStackTrace();
            Application.flashMsg(APICall
                    .createResponse(ResponseType.CONVERSIONERROR));
        } catch (Exception e) {
            e.printStackTrace();
            Application.flashMsg(APICall
                    .createResponse(ResponseType.UNKNOWN));
        }
        return ok(signup.render(nu));
    }

    public static Result isEmailExisted() {
        JsonNode json = request().body().asJson();
        String email = json.path("email").asText();

        ObjectNode jsonData = Json.newObject();
        JsonNode response = null;
        try {
            jsonData.put("email", email);
            response = APICall.postAPI(Constants.NEW_BACKEND + Constants.IS_EMAIL_EXISTED, jsonData);
            Application.flashMsg(response);
        }catch (IllegalStateException e) {
            e.printStackTrace();
            Application.flashMsg(APICall
                    .createResponse(ResponseType.CONVERSIONERROR));
        } catch (Exception e) {
            e.printStackTrace();
            Application.flashMsg(APICall
                    .createResponse(ResponseType.UNKNOWN));
        }
        return ok(response);
    }

    public static Result isUserNameExisted() {
        JsonNode json = request().body().asJson();
        String userName = json.path("userName").asText();

        ObjectNode jsonData = Json.newObject();
        JsonNode response = null;

        try {
            jsonData.put("userName", userName);
            response = APICall.postAPI(Constants.NEW_BACKEND + Constants.IS_USERNAME_EXISTED, jsonData);
            Application.flashMsg(response);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Application.flashMsg(APICall.createResponse(ResponseType.CONVERSIONERROR));
        } catch (Exception e) {
            e.printStackTrace();
            Application.flashMsg(APICall
                    .createResponse(ResponseType.UNKNOWN));
        }

        return ok(response);
    }
}