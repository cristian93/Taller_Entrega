package com.i053114.taller.Parser;

import com.i053114.taller.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 13/10/2017.
 */

public class JsonUser {

    public  static List<User> getData (String content) throws JSONException {
        JSONArray jsonArray  = new JSONArray( content);
        List<User> userList = new ArrayList<>();
        for ( int i=0; i<jsonArray.length(); i++){
            JSONObject item  = jsonArray.getJSONObject(i);

            JSONObject Adrressitem = item.getJSONObject("address");
            JSONObject CompanyItem = item.getJSONObject("company");
            User user = new User();

            user.setId(item.getInt ("id"));
            user.setName(item.getString("name"));
            user.setUsername(item.getString("username"));

            user.setAddress(Adrressitem.getString("city"));

            user.setCompany(CompanyItem.getString("name"));










            //JSONObject company = new JSONObject("company");
          //  user.setCompany(company.getString("name"));

            userList.add(user);
        }
        return userList;
    }
}
