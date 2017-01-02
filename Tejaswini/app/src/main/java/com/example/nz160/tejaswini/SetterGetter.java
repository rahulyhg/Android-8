package com.example.nz160.tejaswini;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nz160 on 31-03-2016.
 */
public class SetterGetter implements Parcelable {

    private String description;
    private String webinar_date;
    private String webinar_time;
    private String product_id;
    private String webinar_duration;
    private String product_name;
    private String price;
    private String person_name;
    private String person_rofile;
    private String profile_img;
    private String person_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String title;
    private String party_id;
    private String person_image;
    private String person_desc;
    private String internal_name;
    private String recinternal_name;
    private String recwebtime;
    private String recwebprice;
    private String livwebprice;
    private String livwebdur;
    private String livetime;
    private String livedate;
    private String liveproId;
    private String recproId;
    private String user_name;
    private String user_email;
    private String profile_photo;
    private String user_phone;
    private String user_job_title;
    private String user_job_function;
    private String user_dob;
    private String speaker;
    private String speaker_person_name;
    private String speaker_rec_person_name;
    private String month;
    private SetterGetter search;
    private String search_month ;
    private String category;
    private String category_product_id;
    private String  product_category_id;
    private String category_id;
    private String catgry;
    private String pro_id;
    private String match_pro_id;
    private SetterGetter setter;
    private String pack_of_three_internal_name;
    private String pack_of_three_product_id;
    private String pack_of_four_price;
    private String pack_of_four_internal_name;
    private String pack_of_eight_internal_name;

    public String getScheduled_product_id() {
        return scheduled_product_id;
    }

    public void setScheduled_product_id(String scheduled_product_id) {
        this.scheduled_product_id = scheduled_product_id;
    }
    private String personLastName;
    private String personDesignation;

    public String getPersonDesignation() {
        return personDesignation;
    }
    public void setPersonDesignation(String personDesignation) {
        this.personDesignation = personDesignation;
    }
    public String getPersonLastName() {
        return personLastName;
    }
    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }
    private String scheduled_product_id;

    private String order_complete_date;
    public String getOrder_complete_date() {
        return order_complete_date;
    }
    public void setOrder_complete_date(String order_complete_date) {
        this.order_complete_date = order_complete_date;
    }
    private String order_complete_trans_id;

    public String getOrder_complete_trans_id() {
        return order_complete_trans_id;
    }
    public void setOrder_complete_trans_id(String order_complete_trans_id) {
        this.order_complete_trans_id = order_complete_trans_id;
    }
    private String orderName;

    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    private String card_type;

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_year() {
        return card_year;
    }

    public void setCard_year(String card_year) {
        this.card_year = card_year;
    }

    public String getCard_month() {
        return card_month;
    }

    public void setCard_month(String card_month) {
        this.card_month = card_month;
    }

    public String getCard_cvv() {
        return card_cvv;
    }

    public void setCard_cvv(String card_cvv) {
        this.card_cvv = card_cvv;
    }

    private String pack_of_eight_price;
    private String pack_product_name;
    private String userparty_id;
    private String promoUserEntered;
    private String order_complete_id;

    private String card_num;
    private String card_name;
    private String card_year;
    private String card_month;
    private String card_cvv;

    public String getOrder_complete_qty() {
        return order_complete_qty;
    }
    public void setOrder_complete_qty(String order_complete_qty) {
        this.order_complete_qty = order_complete_qty;
    }
    public String getOrder_complete_unitPrice() {
        return order_complete_unitPrice;
    }
    public void setOrder_complete_unitPrice(String order_complete_unitPrice) {
        this.order_complete_unitPrice = order_complete_unitPrice;
    }
    public String getOrder_complete_id() {
        return order_complete_id;
    }
    public void setOrder_complete_id(String order_complete_id) {
        this.order_complete_id = order_complete_id;
    }
    private String order_complete_qty;
    private String order_complete_unitPrice;
    public String getSpeakerPosition() {
        return speakerPosition;
    }

    public void setSpeakerPosition(String speakerPosition) {
        this.speakerPosition = speakerPosition;
    }

    private String speakerPosition;

    public String getCategoryPosition() {
        return categoryPosition;
    }

    public void setCategoryPosition(String categoryPosition) {
        this.categoryPosition = categoryPosition;
    }

    private String categoryPosition;

    public String getMonthPosition() {
        return monthPosition;
    }
    public String getPromoUserEntered() {
        return promoUserEntered;
    }
    public void setPromoUserEntered(String promoUserEntered) {
        this.promoUserEntered = promoUserEntered;
    }

    public void setMonthPosition(String monthPosition) {
        this.monthPosition = monthPosition;
    }

    private String monthPosition;

    public int getSchedule_hour() {
        return schedule_hour;
    }

    public void setSchedule_hour(int schedule_hour) {
        this.schedule_hour = schedule_hour;
    }

    private int schedule_hour;

    public String getSchedule_AM_PM() {
        return schedule_AM_PM;
    }

    public void setSchedule_AM_PM(String schedule_AM_PM) {
        this.schedule_AM_PM = schedule_AM_PM;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    private int Date;

    public int getSchedule_month() {
        return schedule_month;
    }

    public void setSchedule_month(int schedule_month) {
        this.schedule_month = schedule_month;
    }

    private int schedule_month;

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    private int Year;

    public int getMinutes() {
        return Minutes;
    }

    public void setMinutes(int minutes) {
        Minutes = minutes;
    }

    private int Minutes;
    private String schedule_AM_PM;


    public String getRecently_viewed_product_name() {
        return recently_viewed_product_name;
    }

    public void setRecently_viewed_product_name(String recently_viewed_product_name) {
        this.recently_viewed_product_name = recently_viewed_product_name;
    }
    private String mostPopular_images;


    public String getMostPopular_images() {
        return mostPopular_images;
    }
    public void setMostPopular_images(String mostPopular_images) {
        this.mostPopular_images = mostPopular_images;
    }

    private String recently_viewed_product_name;

    public String getRecently_viewed_party_id() {
        return recently_viewed_party_id;
    }

    public void setRecently_viewed_party_id(String recently_viewed_party_id) {
        this.recently_viewed_party_id = recently_viewed_party_id;
    }

    private String recently_viewed_party_id;

    public String getRecently_viewed_image() {
        return recently_viewed_image;
    }

    public void setRecently_viewed_image(String recently_viewed_image) {
        this.recently_viewed_image = recently_viewed_image;
    }

    private String recently_viewed_image;

    public String getRecently_viewed_webinar_date() {
        return recently_viewed_webinar_date;
    }

    public void setRecently_viewed_webinar_date(String recently_viewed_webinar_date) {
        this.recently_viewed_webinar_date = recently_viewed_webinar_date;
    }

    public String getRecently_viewed_webinar_time() {
        return recently_viewed_webinar_time;
    }

    public void setRecently_viewed_webinar_time(String recently_viewed_webinar_time) {
        this.recently_viewed_webinar_time = recently_viewed_webinar_time;
    }

    public String getRecently_viewed_webinar_duration() {
        return recently_viewed_webinar_duration;
    }

    public void setRecently_viewed_webinar_duration(String recently_viewed_webinar_duration) {
        this.recently_viewed_webinar_duration = recently_viewed_webinar_duration;
    }

    public String getRecently_viewed_webinar_price() {
        return recently_viewed_webinar_price;
    }

    public void setRecently_viewed_webinar_price(String recently_viewed_webinar_price) {
        this.recently_viewed_webinar_price = recently_viewed_webinar_price;
    }

    public int getRecently_viewed_current_date() {
        return recently_viewed_current_date;
    }

    public void setRecently_viewed_current_date(int recently_viewed_current_date) {
        this.recently_viewed_current_date = recently_viewed_current_date;
    }

    public String getRecently_viewed_product_id() {
        return recently_viewed_product_id;
    }

    public void setRecently_viewed_product_id(String recently_viewed_product_id) {
        this.recently_viewed_product_id = recently_viewed_product_id;
    }

    public String getRecently_viewed_imei() {
        return recently_viewed_imei;
    }

    public void setRecently_viewed_imei(String recently_viewed_imei) {
        this.recently_viewed_imei = recently_viewed_imei;
    }

    private String recently_viewed_webinar_date;
    private String recently_viewed_webinar_time;
    private String recently_viewed_webinar_duration;
    private String recently_viewed_webinar_price;
    private int recently_viewed_current_date;
    private String recently_viewed_product_id;
    private String recently_viewed_imei;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public  String images;
    public String getAttendee_name() {
        return attendee_name;
    }
    public void setAttendee_name(String attendee_name) {
        this.attendee_name = attendee_name;
    }
    private String attendee_name;

    public String getAttendee_product_id() {
        return attendee_product_id;
    }
    public void setAttendee_product_id(String attendee_product_id) {this.attendee_product_id = attendee_product_id;}
    private String attendee_product_id;

    public String getAttendee_email() {
        return attendee_email;
    }
    public void setAttendee_email(String attendee_email) {
        this.attendee_email = attendee_email;
    }
    private String attendee_email;

    public String getAttendee_Phone() {
        return attendee_Phone;
    }
    public void setAttendee_Phone(String attendee_Phone) {
        this.attendee_Phone = attendee_Phone;
    }
    private String attendee_Phone;

    public SetterGetter getWeb_pack_setter() {
        return web_pack_setter;
    }
    public void setWeb_pack_setter(SetterGetter web_pack_setter) {this.web_pack_setter = web_pack_setter;}

    private String order_id;
    private String order_date;
    private String order_price;
    private String order_status;

    private String voucher_id;
    public String getVoucher_id() {
        return voucher_id;
    }
    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getVoucher_number() {
        return voucher_number;
    }
    public void setVoucher_number(String voucher_number) {
        this.voucher_number = voucher_number;
    }

    public String getVoucher_amt() {
        return voucher_amt;
    }
    public void setVoucher_amt(String voucher_amt) {
        this.voucher_amt = voucher_amt;
    }

    public String getValid_date_from() {
        return valid_date_from;
    }
    public void setValid_date_from(String valid_date_from) {this.valid_date_from = valid_date_from;}

    public String getValid_date_to() {
        return valid_date_to;
    }
    public void setValid_date_to(String valid_date_to) {
        this.valid_date_to = valid_date_to;
    }

    private String voucher_number;
    private String refundticket;
    public String getRefundticket() {
        return refundticket;
    }
    public void setRefundticket(String refundticket) {
        this.refundticket = refundticket;
    }

    private String voucher_amt;
    private String used_amt;
    public String getUsed_amt() {
        return used_amt;
    }
    public void setUsed_amt(String used_amt) {
        this.used_amt = used_amt;
    }

    private String available_amt;
    public String getAvailable_amt() {
        return available_amt;
    }
    public void setAvailable_amt(String available_amt) {
        this.available_amt = available_amt;
    }
    private String voucher_status;
    public String getVoucher_status() {
        return voucher_status;
    }
    public void setVoucher_status(String voucher_status) {
        this.voucher_status = voucher_status;
    }

    private String valid_date_from;
    private String valid_date_to;

    public String getOrder_status() {
        return order_status;
    }
    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_price() {
        return order_price;
    }
    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    private SetterGetter web_pack_setter;

    public SetterGetter getPack_of_3_arr() {
        return pack_of_3_arr;
    }
    public void setPack_of_3_arr(SetterGetter pack_of_3_arr) {
        this.pack_of_3_arr = pack_of_3_arr;
    }

    private SetterGetter pack_of_3_arr;

    public SetterGetter() {}
    public String getPack_product_name() {return pack_product_name;}
    public void setPack_product_name(String pack_product_name) {this.pack_product_name = pack_product_name;}

    private String pack_of_five_internal_name;
    private String pack_of_five_price;

    public String getPack_of_five_price() {
        return pack_of_five_price;
    }
    public void setPack_of_five_price(String pack_of_five_price) {this.pack_of_five_price = pack_of_five_price;}

    public String getPack_of_five_internal_name() {
        return pack_of_five_internal_name;
    }
    public void setPack_of_five_internal_name(String pack_of_five_internal_name) {
        this.pack_of_five_internal_name = pack_of_five_internal_name;
    }
    private String pack_of_five_product_id;

    public String getPack_of_five_product_id() {
        return pack_of_five_product_id;
    }
    public void setPack_of_five_product_id(String pack_of_five_product_id) {
        this.pack_of_five_product_id = pack_of_five_product_id;
    }

    private String pack_of_six_internal_name;
    private String pack_of_six_price;

    public String getPack_of_six_price() {
        return pack_of_six_price;
    }
    public void setPack_of_six_price(String pack_of_six_price) {
        this.pack_of_six_price = pack_of_six_price;
    }

    public String getPack_of_six_internal_name() {
        return pack_of_six_internal_name;
    }
    public void setPack_of_six_internal_name(String pack_of_six_internal_name) {
        this.pack_of_six_internal_name = pack_of_six_internal_name;
    }

    public String getUserPartyId(){
        return this.userparty_id;
    }
    public void setUserPartyId(String userparty_id){
        this.userparty_id = userparty_id;
    }
    private String pack_of_six_product_id;

    public String getPack_of_six_product_id() {
        return pack_of_six_product_id;
    }

    private String pack_of_seven_internal_name;
    private String pack_of_seven_price;

    public String getPack_of_seven_price() {
        return pack_of_seven_price;
    }

    public void setPack_of_seven_price(String pack_of_seven_price) {
        this.pack_of_seven_price = pack_of_seven_price;
    }
    public String getPack_of_seven_internal_name() {
        return pack_of_seven_internal_name;
    }
    public void setPack_of_seven_internal_name(String pack_of_seven_internal_name) {
        this.pack_of_seven_internal_name = pack_of_seven_internal_name;
    }
    private String pack_of_seven_product_id;

    public String getPack_of_seven_product_id() {
        return pack_of_seven_product_id;
    }
    public void setPack_of_seven_product_id(String pack_of_seven_product_id) {
        this.pack_of_seven_product_id = pack_of_seven_product_id;
    }
    public void setPack_of_eight_price(String pack_of_eight_price) {
        this.pack_of_eight_price = pack_of_eight_price;
    }
    public String getPack_of_eight_internal_name() {
        return pack_of_eight_internal_name;
    }
    public void setPack_of_eight_internal_name(String pack_of_eight_internal_name) {
        this.pack_of_eight_internal_name = pack_of_eight_internal_name;
    }
    public String getPack_of_eight_price() {
        return pack_of_eight_price;
    }
    private String pack_of_ten_internal_name;
    private String pack_of_ten_price;

    public String getPack_of_ten_price() {
        return pack_of_ten_price;
    }
    public void setPack_of_ten_price(String pack_of_ten_price) {
        this.pack_of_ten_price = pack_of_ten_price;
    }
    public String getPack_of_ten_internal_name() {
        return pack_of_ten_internal_name;
    }
    public void setPack_of_ten_internal_name(String pack_of_ten_internal_name) {
        this.pack_of_ten_internal_name = pack_of_ten_internal_name;
    }
    private String pack_of_ten_product_id;

    public String getPack_of_ten_product_id() {
        return pack_of_ten_product_id;
    }
    public void setPack_of_ten_product_id(String pack_of_ten_product_id) {
        this.pack_of_ten_product_id = pack_of_ten_product_id;
    }
    private String pack_of_thirty2_internal_name;
    private String pack_of_thirty2_price;

    public String getPack_of_thirty2_price() {
        return pack_of_thirty2_price;
    }
    public void setPack_of_thirty2_price(String pack_of_thirty2_price) {
        this.pack_of_thirty2_price = pack_of_thirty2_price;
    }
    public String getPack_of_thirty2_internal_name() {
        return pack_of_thirty2_internal_name;
    }
    public void setPack_of_thirty2_internal_name(String pack_of_thirty2_internal_name) {
        this.pack_of_thirty2_internal_name = pack_of_thirty2_internal_name;
    }
    private String pack_of_thirty2_product_id;

    public String getPack_of_thirty2_product_id() {
        return pack_of_thirty2_product_id;
    }
    public void setPack_of_thirty2_product_id(String pack_of_thirty2_product_id) {
        this.pack_of_thirty2_product_id = pack_of_thirty2_product_id;
    }
    private String pack_of_fourty_internal_name;
    private String pack_of_fourty_price;

    public String getPack_of_fourty_price() {
        return pack_of_fourty_price;
    }
    public void setPack_of_fourty_price(String pack_of_fourty_price) {
        this.pack_of_fourty_price = pack_of_fourty_price;
    }
    public String getPack_of_fourty_internal_name() {
        return pack_of_fourty_internal_name;
    }
    public void setPack_of_fourty_internal_name(String pack_of_fourty_internal_name) {
        this.pack_of_fourty_internal_name = pack_of_fourty_internal_name;
    }
    private String pack_of_fourty_product_id;
    private String pack_of_three_price;

    public String getPack_of_fourty_product_id() {
        return pack_of_fourty_product_id;
    }
    public void setPack_of_fourty_product_id(String pack_of_fourty_product_id) {
        this.pack_of_fourty_product_id = pack_of_fourty_product_id;
    }
    private String pack_of_eight_product_id;

    public String getPack_of_eight_product_id() {
        return pack_of_eight_product_id;
    }
    public void setPack_of_eight_product_id(String pack_of_eight_product_id) {
        this.pack_of_eight_product_id = pack_of_eight_product_id;
    }
    public void setPack_of_six_product_id(String pack_of_six_product_id) {
        this.pack_of_six_product_id = pack_of_six_product_id;
    }
    public String getPack_of_three_internal_name() {
        return pack_of_three_internal_name;
    }
    public void setPack_of_three_internal_name(String pack_of_three_internal_name) {
        this.pack_of_three_internal_name = pack_of_three_internal_name;
    }
    public String getPack_of_three_product_price() {
        return pack_of_three_price;
    }
    public void setPack_of_three_product_price(String pack_of_three_price) {
        this.pack_of_three_price = pack_of_three_price;
    }
    public String getPack_of_three_product_id() {
        return pack_of_three_product_id;
    }
    public void setPack_of_three_product_id(String pack_of_three_product_id) {
        this.pack_of_three_product_id = pack_of_three_product_id;
    }

    public String getPack_of_four_price() {
        return pack_of_four_price;
    }
    public void setPack_of_four_price(String pack_of_four_price) {
        this.pack_of_four_price = pack_of_four_price;
    }
    public String getPack_of_four_internal_name() {
        return pack_of_four_internal_name;
    }
    public void setPack_of_four_internal_name(String pack_of_four_internal_name) {
        this.pack_of_four_internal_name = pack_of_four_internal_name;
    }
    private String pack_of_four_product_id;

    public String getPack_of_four_product_id() {
        return pack_of_four_product_id;
    }
    public void setPack_of_four_product_id(String pack_of_four_product_id) {
        this.pack_of_four_product_id = pack_of_four_product_id;
    }
    //Live WEb list
    public String getLiveproductId(){
        return this.product_id; 
    }
    public void setLiveproductId(String product_id){
        this.product_id = product_id;
    }

    public String getLiveWebinarTime(){
        return this.webinar_time;
    }
    public void setLiveWebinarTime(String webinar_time){
        this.webinar_time = webinar_time;
    }

    public String getLiveWebinarDuration(){
        return this.webinar_duration;
    }
    public void setLiveWebinarDuration(String webinar_duration){
        this.webinar_duration = webinar_duration;
    }
    public String getLiveName(){
        return this.product_name;
    }
    public void setLiveName(String product_name){
        this.product_name = product_name;
    }

    public String getLiveDescription(){
        return this.description;
    }
    public void setLiveDescription(String description){
        this.description = description;
    }

    public String getLiveWebinarDate(){
        return this.webinar_date;
    }
    public void setLiveWebinarDate(String webinar_date){
        this.webinar_date = webinar_date;
    }

    public String getLivePrice(){
        return this.price;
    }
    public void setLivePrice(String price){
        this.price = price;
    }

    public String getLivePersonProfileImg(){
        return this.profile_img;
    }
    public void setLivePersonProfileImg(String profile_img){
        this.profile_img = profile_img;
    }

    public String getLivePersonId(){
        return this.person_id;
    }
    public void setLivePersonId(String person_id){
        this.person_id = person_id;
    }

    //Recorded WEb list
    public String getRecordedproductId(){
        return this.product_id;
    }
    public void setRecordedproductId(String product_id){
        this.product_id = product_id;
    }

    public String getRecordedName(){
        return this.product_name;
    }
    public void setRecordedName(String product_name){
        this.product_name = product_name;
    }

    public String getRecordedDescription(){
        return this.description;
    }
    public void setRecordedDescription(String description){
        this.description = description;
    }

   

    public String getRecordedPrice(){
        return this.price;
    }
    public void setRecordedPrice(String price){
        this.price = price;
    }

    public String getRecordedPersonProfileImg(){
        return this.profile_img;
    }
    public void setRecordedPersonProfileImg(String profile_img){
        this.profile_img = profile_img;
    }

    public String getRecordedPersonId(){
        return this.person_id;
    }
    public void setRecordedPersonId(String person_id){
        this.person_id = person_id;
    }

    //product  list
    public String getproductId(){
        return this.product_id;
    }
    public void setproductId(String product_id){
        this.product_id = product_id;
    }

    public String getWebinarTime(){
        return this.webinar_time;
    }
    public void setWebinarTime(String webinar_time){
        this.webinar_time = webinar_time;
    }

    public String getWebinarDuration(){
        return this.webinar_duration;
    }
    public void setWebinarDuration(String webinar_duration){
        this.webinar_duration = webinar_duration;
    }
    public String getName(){
        return this.product_name;
    }
    public void setName(String product_name){
        this.product_name = product_name;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getWebinarDate(){
        return this.webinar_date;
    }
    public void setWebinarDate(String webinar_date){
        this.webinar_date = webinar_date;
    }

    public String getPrice(){
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }

    public String getOverView(){
        return this.price;
    }
    public void setOverView(String price){
        this.price = price;
    }

    public String getPersonName(){
        return this.person_name;
    }
    public void setPersonName(String person_name){

        this.person_name = person_name;
    }
    public String getPersonProfile(){
        return this.person_rofile;
    }
    public void setPersonProfile(String person_rofile){

        this.person_rofile = person_rofile;
    }

    public String getPersonProfileImg(){
        return this.profile_img;
    }
    public void setPersonProfileImg(String profile_img){
        this.profile_img = profile_img;
    }

    public String getPersonId(){
        return this.person_id;
    }
    public void setPersonId(String person_id){
        this.person_id = person_id;
    }

    //Speaker Details
    public String getPersonDetailListFirstName(){
        return this.first_name;
    }
    public void setPersonDetailListFirstName(String first_name){
        this.first_name = first_name;
    }

    public String getPersonDetailListMiddleName(){
        return this.middle_name;
    }
    public void setPersonDetailListMiddleName(String middle_name){
        this.middle_name = middle_name;
    }

    public String getPersonDetailListLastName(){
        return this.last_name;
    }
    public void setPersonDetailListLastName(String last_name){
        this.last_name = last_name;
    }

    public String getPersonDetailListPersonalTitle(){
        return this.title;
    }
    public void setPersonDetailListPersonalTitle(String title){
        this.title = title;
    }

    public String getPersonDetailListPartyId(){
        return this.party_id;
    }
    public void setPersonDetailListPartyId(String party_id){
        this.party_id = party_id;
    }

    public String getPersonDetailListImage(){
        return this.person_image;
    }
    public void setPersonDetailListImage(String person_image){
        this.person_image = person_image;
    }

    public String getPersonDetailListBriefDesc(){
        return this.person_desc;
    }
    public void setPersonDetailListBriefDesc(String person_desc){
        this.person_desc = person_desc;
    }

    //Speaker Details
    public String getPersonListFirstName(){
        return this.first_name;
    }
    public void setPersonListFirstName(String first_name){
        this.first_name = first_name;
    }

    public String getPersonListMiddleName(){
        return this.middle_name;
    }
    public void setPersonListMiddleName(String middle_name){
        this.middle_name = middle_name;
    }

    public String getPersonListLastName(){
        return this.last_name;
    }
    public void setPersonListLastName(String last_name){
        this.last_name = last_name;
    }

    public String getPersonListPersonalTitle(){
        return this.title;
    }
    public void setPersonListPersonalTitle(String title){
        this.title = title;
    }

    public String getPersonListPartyId(){
        return this.party_id;
    }
    public void setPersonListPartyId(String party_id){
        this.party_id = party_id;
    }

    public String getPersonListImage(){
        return this.person_image;
    }
    public void setPersonListImage(String person_image){
        this.person_image = person_image;
    }

    public String getPersonListBriefDesc(){
        return this.person_desc;
    }
    public void setPersonListBriefDesc(String person_desc){
        this.person_desc = person_desc;
    }

    public String getLiveInternalName(){
        return this.internal_name;
    }
    public void setLiveInternalName(String internal_name){
        this.internal_name = internal_name;
    }
    
    public String getRecInternalName(){
        return this.recinternal_name;
    }
    public void setRecInternalName(String recinternal_name){
        this.recinternal_name = recinternal_name;
    }

    public String getRecWebinarDuration(){
        return this.recwebtime;
    }
    public void setRecWebinarDuration(String recwebtime){
        this.recwebtime = recwebtime;
    }

    public String getRecWebinarPrice(){
        return this.recwebprice;
    }
    public void setRecWebinarPrice(String recwebprice){
        this.recwebprice = recwebprice;
    }

    public String getLivWebinaDuration(){
        return this.livwebdur;
    }
    public void setLivWebinaDuration(String livwebdur){
        this.livwebdur = livwebdur;
    }

    public String getLiveWebinarPrice(){
        return this.livwebprice;
    }
    public void setLiveWebinarPrice(String livwebprice){
        this.livwebprice = livwebprice;
    }

    public String getLiveWebinaTime(){
        return this.livetime;
    }
    public void setLiveWebinaTime(String livetime){
        this.livetime = livetime;
    }

    public String getLiveWebinaDate(){
        return this.livedate;
    }
    public void setLiveWebinaDate(String livedate){
        this.livedate = livedate;
    }

    public String getLiveProId(){
        return this.liveproId;
    }
    public void setLiveProId(String liveproId){
        this.liveproId = liveproId;
    }

    public String getRecProId(){
        return this.recproId;
    }
    public void setRecProId(String recproId){
        this.recproId = recproId;
    }

    public String getUserName(){
        return this.user_name;
    }
    public void setUserName(String user_name){
        this.user_name = user_name;
    }

    public String getUserEmail(){
        return this.user_email;
    }
    public void setUserEmail(String user_email){
        this.user_email = user_email;
    }

    public String getProfilePhoto(){
        return this.profile_photo;
    }
    public void setProfilePhoto(String profile_photo){
        this.profile_photo = profile_photo;
    }

    public String getUserPhone(){
        return this.user_phone;
    }
    public void setUserPhone(String user_phone){
        this.user_phone = user_phone;
    }

    public String getUserCompany(){
        return this.user_job_title;
    }
    public void setUserCompany(String user_job_title){
        this.user_job_title = user_job_title;
    }

    public String getUserCountry(){
        return this.user_job_function;
    }
    public void setUserCountry(String user_job_function){
        this.user_job_function = user_job_function;
    }

    public String getUserDOB(){
        return this.user_dob;
    }
    public void setUserDOB(String user_dob){
        this.user_dob = user_dob;
    }

    public String getSpeaker(){
        return this.speaker;
    }
    public void setSpeaker(String speaker){
        this.speaker = speaker;
    }

    public String getLivePersonName(){
        return this.speaker_person_name;
    }
    public void setLivePersonName(String speaker_person_name){
        this.speaker_person_name = speaker_person_name;
    }

    public String getRecordedPersonName(){
        return this.speaker_rec_person_name;
    }
    public void setRecordedPersonName(String speaker_rec_person_name){
        this.speaker_rec_person_name = speaker_rec_person_name;
    }

    public String getMonth(){
        return this.month;
    }
    public void setMonth(String month){
        this.month = month;
    }

    public String getSearchMonth(){
        return this.search_month;
    }
    public void setSearchMonth(String search_month){
        this.search_month = search_month;
    }

    public String getcategory(){
        return this.category;
    }
    public void setcategory(String category){
        this.category = category;
    }

    public String getcategoryProductid(){
        return this.category_product_id;
    }
    public void setcategoryProductid(String category_product_id){
        this.category_product_id = category_product_id;
    }
    public String getProductCategoryid(){
        return this.product_category_id;
    }
    public void setProductCategoryid(String product_category_id){
        this.product_category_id = product_category_id;
    }

    public String getcategoryId(){
        return this.category_id;
    }
    public void setcategoryId(String category_id){
        this.category_id = category_id;
    }

    public String getCatgry(){
        return this.catgry;
    }
    public void setCatgry(String catgry){
        this.catgry = catgry;
    }

    public String getProId(){
        return this.pro_id;
    }
    public void setProId(String pro_id){
        this.pro_id = pro_id;
    }

    public String getMatchProdId(){
        return this.match_pro_id;
    }
    public void setMatchProdId(String match_pro_id){
        this.match_pro_id = match_pro_id;
    }

    public SetterGetter getSetter(){
        return this.setter;
    }
    public void setSetter(SetterGetter setter){
        this.setter = setter;
    }
    private String promoCodeId;

    public String getPromoCodeId() {
        return promoCodeId;
    }
    public void setPromoCodeId(String promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    public String getPromoId() {
        return promoId;
    }
    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getPromoProductId() {
        return promoProductId;
    }
    public void setPromoProductId(String promoProductId) {
        this.promoProductId = promoProductId;
    }

    public String getPromoPartyId() {
        return promoPartyId;
    }
    public void setPromoPartyId(String promoPartyId) {
        this.promoPartyId = promoPartyId;
    }

    private String promoId;
    private String promoAction;

    public String getPromoAction() {
        return promoAction;
    }
    public void setPromoAction(String promoAction) {
        this.promoAction = promoAction;
    }
    private String quantity;

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    private String promoProductId;
    private String promoPartyId;
    private String promoAmount;

    public String getPromoAmount() {
        return promoAmount;
    }
    public void setPromoAmount(String promoAmount) {
        this.promoAmount = promoAmount;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_name);
        dest.writeString(webinar_date);
        dest.writeString(profile_img);
        dest.writeString(person_id);
        dest.writeString(webinar_duration);
        dest.writeString(price);
        dest.writeString(webinar_time);
        dest.writeString(product_id);
    }
    protected SetterGetter(Parcel in) {
        product_name = in.readString();
        webinar_date = in.readString();
        profile_img = in.readString();
        person_id = in.readString();
        webinar_duration = in.readString();
        price = in.readString();
        webinar_time = in.readString();
        product_id = in.readString();
    }
    public static final Creator CREATOR
            = new Creator() {
        public SetterGetter createFromParcel(Parcel in) {
            return new SetterGetter(in);
        }

        public SetterGetter[] newArray(int size) {
            return new SetterGetter[size];
        }
    };
}
