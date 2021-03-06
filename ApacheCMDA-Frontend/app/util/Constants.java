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

package util;

public class Constants {

    // API 1.3 URL
    public static final String NEW_API_URL = "http://localhost:9033/";

    // API Call format
    public static final String FORMAT = "json";

    // climate service
    public static final String NEW_GET_CLIMATE_SERVICE = "getAllClimateServices/";
    public static final String NEW_ADD_CLIMATE_SERVICE = "addClimateService";
    public static final String NEW_DELETE_CLIMATE_SERVICE = "climateService/deleteClimateService/id/";
    public static final String NEW_EDIT_CLIMATE_SERVICE = "updateClimateService";

    //service log
    public static final String NEW_GET_A_SERVICE_LOG = "getServiceExecutionLogs/";

    public static final String GET_DATASETLIST ="getDatasetList/";

    //New backend API (MySQL database)
    //public static final String NEW_BACKEND = "http://localhost:9034";
    public static final String NEW_BACKEND = "http://einstein.sv.cmu.edu:9073";
    //New service execution log stuff
    public static final String SERVICE_EXECUTION_LOG =	"serviceExecutionLog/";
    public static final String SERVICE_EXECUTION_LOG_QUERY =	"queryServiceExecutionLogs";
    public static final String SERVICE_EXECUTION_LOG_GET= "getServiceExecutionLog/";
    public static final String NEW_GET_ALL_SERVICE_LOG = "getAllServiceExecutionLog";

    //ServiceConfigItem
    public static final String CONFIG_ITEM =	"serviceConfigurationItem/";
    public static final String GET_CONFIG_ITEMS_BY_CONFIG= "serviceConfigurationItemByServiceConfig/";


    // login & signup
    public static final String URL_HOST = "http://localhost";
    public static final String CMU_BACKEND_PORT = ":9034";
    public static final String IS_USER_VALID = "/users/isUserValid";
    public static final String ADD_USER = "/users/add";
    public static final String IS_EMAIL_EXISTED = "/users/isEmailExisted";
    public static final String IS_USERNAME_EXISTED = "/users/isUserNameExisted";

    //sns
    public static final String GET_USER_BY_USERNAME = "/users/username/";
    public static final String GET_ALL_USERS = "/users/getAllUsers/json";
    public static final String GET_ALL_POSTS = "/posts/getAllPosts/json";
    public static final String GET_USER_POSTS = "/posts/userId/";
    public static final String GET_FOLLOWING_POSTS = "/users/getFollowingPosts/";
    public static final String GET_POPULAR_POSTS = "/posts/getMostPopularPosts";

    public static final String UPLOAD_IMAGE = "/users/uploadPhoto/";
    public static final String GET_FRONTLAYER_FRANCE = "/users/getFrontLayerPhoto/France";

    public static final String USER_API = "/users/";
    public static final String ADD_POST = "/posts/add";
    public static final String ADD_COMMENT = "/comments/add";
    public static final String GET_FOLLOWING_USERS = "/users/getFollowingUsers/";
    public static final String GET_FOLLOWED_USERS = "/users/getFollowers/";
    public static final String ADD_LIKE = "/posts/addLike";
    public static final String ADD_SHARE = "/posts/addSharedUser";
    public static final String DELETE_POST = "/posts/delete/";
    public static final String GET_SHARED_POST = "/posts/getSharedPosts/";
    public static final String ADD_FOLLOWEE = "/users/addFollowee";
    public static final String FUZZY_SEARCH = "/users/fuzzySearch/";
    public static final String EXACT_SEARCH = "/users/exactSearch";

}