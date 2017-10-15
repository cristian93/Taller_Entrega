package com.i053114.taller.Parser;

import com.i053114.taller.Models.Posts;
import com.i053114.taller.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 14/10/2017.
 */

public class JsonPost {
    public  static List<Posts> getData (String content) throws JSONException {
        JSONArray jsonArray  = new JSONArray( content);
        List<Posts> postsList = new ArrayList<>();
        for ( int i=0; i<jsonArray.length(); i++){
            JSONObject item  = jsonArray.getJSONObject(i);

            Posts posts = new Posts();
            posts.setUserId(item.getInt("userId"));
            posts.setId(item.getInt("id"));
            posts.setTitle(item.getString("title"));
            posts.setBody(item.getString("body"));;

            postsList.add(posts);
        }
        return postsList;
    }
}
