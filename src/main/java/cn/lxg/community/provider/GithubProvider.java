package cn.lxg.community.provider;

import cn.lxg.community.dto.AccessTokenDTO;
import cn.lxg.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String accessToken= response.body().string().split("&")[0].split("=")[1];
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/user?access_token="+accessToken).build();
        try {
            Response response = client.newCall(request).execute();
            return JSON.parseObject(response.body().string(), GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
