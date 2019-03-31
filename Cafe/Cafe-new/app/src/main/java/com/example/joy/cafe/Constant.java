package com.example.joy.cafe;

public class Constant {
    public static String baseUrl="https://droidkothon.com/cafe/";
    public String getplaceURL(){
        return baseUrl.concat("get-place-test.php");
    }
    public String getplaceSearchURL(){
        return baseUrl.concat("get-place-test.php");
    }
    public String gettransurl(){
        return baseUrl.concat("transjection.php");
    }
    public String gettransurl_dummy(){
        return baseUrl.concat("transjection-dummy.php");
    }
    public String addReviewUrl(){
        return  baseUrl.concat("add-review.php");
    }
    public String addReviewUrlCk(){
        return  baseUrl.concat("add-review-ck.php");
    }
    public String updateReview(){
        return  baseUrl.concat("update-review.php");
    }
    public String getReviewUrl(){
        return  baseUrl.concat("get-review.php");
    }
}
