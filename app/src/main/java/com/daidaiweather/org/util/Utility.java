package com.daidaiweather.org.util;

import android.text.TextUtils;

import com.daidaiweather.org.db.City;
import com.daidaiweather.org.db.County;
import com.daidaiweather.org.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/5/22.
 */

public class Utility {

    /**
     * 解析处理服务器返回的省级数据
     * @param response 服务器返回的数据
     * @return 是否成功
     */
    public static boolean handleProvinceResponse(String response)
    {
        if (!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i=0; i<allProvinces.length(); i++)
                {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 解析和处理服务器返回的市级数据
     * @param response 服役器返回的数据
     * @param provinceId 所属的省的ID
     * @return 是否处理成功
     */
    public static boolean handleCityResponse(String response, int provinceId)
    {
        if (!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i=0; i<allCities.length(); i++)
                {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}



































