package com.i053114.taller.Parser;

import com.i053114.taller.Models.Coment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 13/10/2017.
 */

public class JsonComment {
    public static List<Coment> getData(String content) throws JSONException{
        JSONArray jsonArray = new JSONArray(content);
        List<Coment>  comentList = new ArrayList<>();
        for (int i=0; i<jsonArray.length();i++){
            JSONObject item = jsonArray.getJSONObject(i);

            Coment coment= new Coment();
            coment.setPostId(item.getInt("postId"));
            coment.setId(item.getInt("id"));
            coment.setEmail(item.getString("email"));
            coment.setBody(item.getString("body"));


            comentList.add(coment);
        }
        return   comentList;
    }
}
