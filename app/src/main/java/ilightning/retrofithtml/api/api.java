package ilightning.retrofithtml.api;

import ilightning.retrofithtml.model.StatusPost;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

//
///**
// * Created by Hoàng on 6/17/2016.
// */

public interface api {

    @GET("/home/tnc")
    Call<ResponseBody> getResponseBodyCall();

    @Headers({
            "ft-api-key : cNiEhlP1qiDgxlUOqPFmFwfZ6SM3bsmt",
            "cache-control : no-cache",
            "postman-token : d5337a20-49ef-0a71-2c6a-98c28bcd0b86",
            "content-type : application/x-www-form-urlencoded"
    })

    @POST("/api/feedback")
    Call<StatusPost> getStatusFeedback(@Body RequestBody requestBody);

    @Headers({
            "ft-api-key : FXc6VZCsEmCziTMdBXwkrBbhz1jUlUiu",
            "cache-control : no-cache",
            "postman-token : 85f8bc5d-36f5-d8f9-5230-db1b2c570611",
            "content-type : application/x-www-form-urlencoded"
    })
    @POST("/api/searchnp")
    Call<ResponseBody> getFollow(@Body RequestBody requestBody);

//    // request /get?testArg=...
//    @GET("/get")
//    Call<HttpBinResponse> getWithArg(@Query("testArg") String arg);
//
//    // POST form encode with form field params
//    @FormUrlEncoded
//    @POST("/post")
//    Call<HttpBinResponse> postWithFormParams(
//            @Field("field1") String field1
//    );
//
//    // POST with a JSON body
//    @POST("/post")
//    Call<HttpBinResponse> postWithJson(
//            @Body LoginData loginData
//    );
//

}
