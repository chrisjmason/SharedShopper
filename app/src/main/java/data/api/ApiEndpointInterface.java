package data.api;

import java.util.List;

import data.api.responsepojo.ItemListResponse;
import data.api.responsepojo.LoginResponse;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import utility.pojo.DataId;
import utility.pojo.Item;
import utility.pojo.User;

public interface ApiEndpointInterface {

    @POST("register")
    Observable<LoginResponse> registerUser(@Body User user);

    @POST("login")
    Observable<LoginResponse> loginUser(@Body User user);

    @POST("updatedataid")
    Observable<LoginResponse> changeDataid(@Body DataId dataId);

    @GET("getdata")
    Observable<ItemListResponse> getItemList();

    @POST("additem")
    Observable<Response<ResponseBody>> addItemApi(@Body Item item);

    @POST("addlist")
    Observable<Response<ResponseBody>> addItemListApi(@Body List<Item> itemListAdd);

    @DELETE("delete/{code}")
    Observable<Response<ResponseBody>> deleteItemApi(@Path("code") String code);

    @POST("updatelist")
    Observable<Response<ResponseBody>> deleteItemListApi(@Body List<Item> itemList);
}
