package com.example.xml_parser_test_withretrofit;


import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "response")
public class dataOPOJ {
    @Element
    Body body;
    @Element
    Header header;

    public Body getBody() {
        return body;
    }

    public Header getHeader() {
        return header;
    }

    @Xml
    public static class Header {
        @PropertyElement
        String resultCode;
        @PropertyElement
        String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }
    }

    @Xml
    public static class Body {
        @Element
        Items items;
        @PropertyElement
        String numOfRows;
        @PropertyElement
        String pageNo;
        @PropertyElement
        String totalCount;

        public Items getItems() {
            return items;
        }

        public String getNumOfRows() {
            return numOfRows;
        }

        public String getPageNo() {
            return pageNo;
        }


        public String getTotalCount() {
            return totalCount;
        }
    }

    @Xml
    public static class Items {
        @Element
        List<Item> item;

        public List<Item> getItem() {
            return item;
        }
    }

    @Xml
    public static class Item {
        @PropertyElement
        String basic;
        @PropertyElement
        String continent;
        @PropertyElement
        String countryEnName;
        @PropertyElement
        String countryName;
        @PropertyElement
        String id;
        @PropertyElement
        String imgUrl;
        @PropertyElement
        String wrtDt;

        public String getBasic() {
            return basic;
        }

        public String getContinent() {
            return continent;
        }

        public String getCountryEnName() {
            return countryEnName;
        }

        public String getCountryName() {
            return countryName;
        }

        public String getId() {
            return id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getWrtDt() {
            return wrtDt;
        }
    }

}
