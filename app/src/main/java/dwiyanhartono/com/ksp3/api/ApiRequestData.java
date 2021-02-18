package dwiyanhartono.com.ksp3.api;

import dwiyanhartono.com.ksp3.model.ReqBodyApplyAssignment;
import dwiyanhartono.com.ksp3.model.ReqBodyAssignment;
import dwiyanhartono.com.ksp3.model.ReqBodyAssignmentcari;
import dwiyanhartono.com.ksp3.model.ReqBodyKunjungan;
import dwiyanhartono.com.ksp3.model.ReqBodyLogoutoffline;
import dwiyanhartono.com.ksp3.model.ReqBodyPlanVisit;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.RequestActionplan;
import dwiyanhartono.com.ksp3.model.RequestChangepsd;
import dwiyanhartono.com.ksp3.model.RequestKunjungan;
import dwiyanhartono.com.ksp3.model.RequestLocation;
import dwiyanhartono.com.ksp3.model.RequestLogin;
import dwiyanhartono.com.ksp3.model.RequestLupapassword;
import dwiyanhartono.com.ksp3.model.Requestcifloanid;
import dwiyanhartono.com.ksp3.model.Requestcontact;
import dwiyanhartono.com.ksp3.model.Requestgetimage;
import dwiyanhartono.com.ksp3.model.Requestimage;
import dwiyanhartono.com.ksp3.model.Requestimagelist;
import dwiyanhartono.com.ksp3.model.Requestimagelistupdate;
import dwiyanhartono.com.ksp3.model.Requestupcontact;
import dwiyanhartono.com.ksp3.model.RespondModelHistorical;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import dwiyanhartono.com.ksp3.model.ResponsModelAP;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelContact;
import dwiyanhartono.com.ksp3.model.ResponsModelDatauser;
import dwiyanhartono.com.ksp3.model.ResponsModelDetailAccount;
import dwiyanhartono.com.ksp3.model.ResponsModelFasilitas;
import dwiyanhartono.com.ksp3.model.ResponsModelFs;
import dwiyanhartono.com.ksp3.model.ResponsModelJaminan;
import dwiyanhartono.com.ksp3.model.ResponsModelKunjungan;
import dwiyanhartono.com.ksp3.model.ResponsModelLocation;
import dwiyanhartono.com.ksp3.model.ResponsModelLupapassword;
import dwiyanhartono.com.ksp3.model.ResponsModelPTP;
import dwiyanhartono.com.ksp3.model.ResponsModelPlan;
import dwiyanhartono.com.ksp3.model.ResponsModelRs;
import dwiyanhartono.com.ksp3.model.ResponsModelSettlement;
import dwiyanhartono.com.ksp3.model.ResponsModelSp;
import dwiyanhartono.com.ksp3.model.ResponsModelSub1;
import dwiyanhartono.com.ksp3.model.ResponsModelSub3;
import dwiyanhartono.com.ksp3.model.ResponsModelSub4;
import dwiyanhartono.com.ksp3.model.ResponsModelSub6;
import dwiyanhartono.com.ksp3.model.ResponsModelSub7;
import dwiyanhartono.com.ksp3.model.ResponsModelSub8;
import dwiyanhartono.com.ksp3.model.ResponsModelTesimage;
import dwiyanhartono.com.ksp3.model.ResponsModelTesimageupdate;
import dwiyanhartono.com.ksp3.model.ResponsModelTunggakan;
import dwiyanhartono.com.ksp3.model.ResponsModelTunggakanDownload;
import dwiyanhartono.com.ksp3.model.ResponsModelcedownload;
import dwiyanhartono.com.ksp3.model.ResponsModelceklog;
import dwiyanhartono.com.ksp3.model.ResponsModelcontactupdate;
import dwiyanhartono.com.ksp3.model.ResponsModelcountassignment;
import dwiyanhartono.com.ksp3.model.ResponsModelimage;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public interface ApiRequestData {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendBiodata(@Field("nama") String nama,
                                   @Field("usia") String usia,
                                   @Field("domisili") String domisili);

    // done
    @Headers("Content-Type: application/json")
    @POST("viewassignment")
    Call<ResponsModel> getassignment(@Body ReqBodyAssignment reqBodyAssignment);

    // done
    @Headers("Content-Type: application/json")
    @POST("viewassignmentcari")
    Call<ResponsModel> getassignmentcari(@Body ReqBodyAssignmentcari reqBodyAssignmentcari);

    // done
    @Headers("Content-Type: application/json")
    @POST("viewassignmentnotvisit")
    Call<ResponsModel> getassignmentnotvisit(@Body ReqBodyAssignment reqBodyAssignment);

    // done
    @Headers("Content-Type: application/json")
    @POST("viewassignmentptp")
    Call<ResponsModel> getassignmentptp(@Body ReqBodyAssignment reqBodyAssignment);

    // done
    @Headers("Content-Type: application/json")
    @POST("applyassignment")
    Call<ResponsModel> sendassignment(@Body ReqBodyApplyAssignment reqBodyApplyAssignment);

    @Headers("Content-Type: application/json")
    @POST("viewplanvisit")
    Call<ResponsModelPlan> sendPlanvisit(@Body ReqBodyPlanVisit reqBodyPlanVisit);

    // done
    @Headers("Content-Type: application/json")
    @POST("viewptp")
    Call<ResponsModelPTP> sendPTP(@Body ReqBodyPlanVisit reqBodyPlanVisit);
 // done
    @Headers("Content-Type: application/json")
    @POST("viewsettlement")
    Call<ResponsModelSettlement> settlement(@Body ReqBodyPlanVisit reqBodyPlanVisit);

    // done
    @Headers("Content-Type: application/json")
    @POST("viewptptoday")
    Call<ResponsModelPTP> sendPTPtoday(@Body ReqBodyPlanVisit reqBodyPlanVisit);

    @Headers("Content-Type: application/json")
    @POST("viewsub1")
    Call<ResponsModelSub1> sendsub1(@Body ReqBodySubHal1 reqBodySubHal1);

    @Headers("Content-Type: application/json")
    @POST("viewsub3")
    Call<ResponsModelSub3> sendsub3(@Body ReqBodySubHal1 reqBodySubHal1);

    @Headers("Content-Type: application/json")
    @POST("viewsub4")
    Call<ResponsModelSub4> sendsub4(@Body ReqBodySubHal1 reqBodySubHal1);

    @Headers("Content-Type: application/json")
    @POST("viewsub6")
    Call<ResponsModelSub6> sendsub6(@Body ReqBodySubHal1 reqBodySubHal1);

    @Headers("Content-Type: application/json")
    @POST("viewsub7")
    Call<ResponsModelSub7> sendsub7(@Body ReqBodySubHal1 reqBodySubHal1);

    @Headers("Content-Type: application/json")
    @POST("viewsub8")
    Call<ResponsModelSub8> sendsub8(@Body ReqBodySubHal1 reqBodySubHal1);

    @Headers("Content-Type: application/json")
    @POST("getselfcured")
    Call<ResponsModelSub8> selfcured(@Body ReqBodySubHal1 reqBodySubHal1);


    @Headers("Content-Type: application/json")
    @POST("getkp")
    Call<ResponsModelSub6> getkp(@Body ReqBodySubHal1 reqBodySubHal1);

    @Headers("Content-Type: application/json")
    @POST("getbp")
    Call<ResponsModelSub6> getbp(@Body ReqBodySubHal1 reqBodySubHal1);

    // done
    @Headers("Content-Type: application/json")
    @POST("downloadplanvisit")
    Call<ResponsModel> DownloadPlanvisit(@Body ReqBodyPlanVisit reqBodyPlanVisit);

    @Multipart
    @POST("image")
    Call<ResponsModel> sendimage(@Part MultipartBody.Part Photo,
                                 @Part("f_cif") RequestBody f_cif,
                                 @Part("f_type") RequestBody f_type,
                                 @Part("f_code") RequestBody f_code,
                                 @Part("f_tgl") RequestBody f_tgl);

    @Multipart
    @POST("imageinputkunjungan")
    Call<ResponsModel> sendimageik(@Part MultipartBody.Part Photo,
                                   @Part("f_cif") RequestBody f_cif,
                                   @Part("f_loanid") RequestBody f_loanid,
                                   @Part("f_type") RequestBody f_type,
                                   @Part("f_code") RequestBody f_code,
                                   @Part("f_tgl") RequestBody f_tgl,
                                   @Part("f_keterangan") RequestBody f_keterangan);

    @Headers("Content-Type: application/json")
    @POST("viewdetail")
    Call<ResponsModelDetailAccount> sendDetail(@Body Requestcontact requestcontact);

    @Headers("Content-Type: application/json")
    @POST("viewkunjungan")
    Call<RespondModelHistorical> sendHistory(@Body Requestcifloanid requestcontact);

    // done
    @Headers("Content-Type: application/json")
    @POST("login")
    Call<ResponsModelDatauser> sendLogin(@Body RequestLogin requestLogin);

    // done
    @Headers("Content-Type: application/json")
    @POST("ceklogin")
    Call<ResponsModelceklog> cekLogin(@Body RequestLogin requestLogin);

    // done
    @Headers("Content-Type: application/json")
    @POST("cekdownload")
    Call<ResponsModelcedownload> cekDownload(@Body RequestLogin requestLogin);

    @Headers("Content-Type: application/json")
    @POST("cekpendingaproval")
    Call<ResponsModelcedownload> cekPendingApproval(@Body RequestLogin requestLogin);


    ///////////////Download///////////////////////////////////
    @Headers("Content-Type: application/json")
    @POST("downloadacount")
    Call<ResponsModelDetailAccount> downloadacount(@Body RequestLogin requestLogin);

    @Headers("Content-Type: application/json")
    @POST("downloadtunggakan")
    Call<ResponsModelTunggakanDownload> downloadtunggakan(@Body RequestLogin requestLogin);
// done
    @Headers("Content-Type: application/json")
    @POST("updateacount")
    Call<ResponsModelcedownload> updateacount(@Body RequestLogin requestLogin);

    @Headers("Content-Type: application/json")
    @POST("downloadfasilitas")
    Call<ResponsModelcedownload> downloadfasilitas(@Body RequestLogin requestLogin);

    @Headers("Content-Type: application/json")
    @POST("downloadjaminan")
    Call<ResponsModelcedownload> downloadjaminan(@Body RequestLogin requestLogin);

    @Headers("Content-Type: application/json")
    @POST("downloadsp")
    Call<ResponsModelcedownload> downloadsp(@Body RequestLogin requestLogin);

    @Headers("Content-Type: application/json")
    @POST("downloadcontact")
    Call<ResponsModelcedownload> downloadcontact(@Body RequestLogin requestLogin);

    // done
    @Headers("Content-Type: application/json")
    @POST("logout")
    Call<ResponsModelChangePswd> sendLogout(@Body ReqBodySubHal1 reqBodySubHal1);

    // done
    @Headers("Content-Type: application/json")
    @POST("logoutofline")
    Call<ResponsModelChangePswd> sendLogoutoffline(@Body ReqBodyLogoutoffline reqBodyLogoutoffline);

    // done
    @Headers("Content-Type: application/json")
    @POST("lupapassword")
    Call<ResponsModelLupapassword> sendlupapassword(@Body RequestLupapassword requestLupapassword);

    // done
    @Headers("Content-Type: application/json")
    @POST("changepassword")
    Call<ResponsModelChangePswd> sendChangepsd(@Body RequestChangepsd requestChangepsd);


    @Headers("Content-Type: application/json")
    @POST("location")
    Call<ResponsModel> sendLocation(@Body RequestLocation requestLocation);

    @Headers("Content-Type: application/json")
    @POST("viewcontact")
    Call<ResponsModelContact> viewcontact(@Body Requestcontact requestcontact);

    @Headers("Content-Type: application/json")
    @POST("viewlocation")
    Call<ResponsModelLocation> viewlocation(@Body Requestcontact requestcontact);


    ///////////////////////////// Tes Image
    @Headers("Content-Type: application/json")
    @POST("viewimagehk")
    Call<ResponsModelTesimage> viewtesimage(@Body Requestimage requestimage);

    @Headers("Content-Type: application/json")
    @POST("viewimagehklist")
    Call<ResponsModelTesimage> viewtesimagelist(@Body Requestimagelist requestimagelist);

    @Headers("Content-Type: application/json")
    @POST("updateimage")
    Call<ResponsModelTesimageupdate> updateimage(@Body Requestimagelistupdate requestimagelistupdate);
///////////////////////////////////////

    @Headers("Content-Type: application/json")
    @POST("getimage")
    Call<ResponsModelimage> getiamge(@Body Requestgetimage requestgetimage);


    @Headers("Content-Type: application/json")
    @POST("viewactionplan")
    Call<ResponsModelAP> viewAP(@Body RequestActionplan requestActionplan);

    @Headers("Content-Type: application/json")
    @POST("getparameter")
    Call<ResponsModelAP> getparam(@Body RequestActionplan requestActionplan);

    @Headers("Content-Type: application/json")
    @POST("getkunjunganall")
    Call<ResponsModelKunjungan> getkunjungan(@Body ReqBodyKunjungan reqBodyKunjungan);


    @Headers("Content-Type: application/json")
    @POST("viewRS")
    Call<ResponsModelRs> viewrs(@Body Requestcontact requestcontact);

    @Headers("Content-Type: application/json")
    @POST("viewSP")
    Call<ResponsModelSp> viewsp(@Body Requestcontact requestcontact);

    @Headers("Content-Type: application/json")
    @POST("countassignment")
    Call<ResponsModelcountassignment> viewcountassignment(@Body ReqBodyPlanVisit reqBodyPlanVisit);

    @Headers("Content-Type: application/json")
    @POST("viewFS")
    Call<ResponsModelFs> viewfs(@Body Requestcontact requestcontact);


    @Headers("Content-Type: application/json")
    @POST("viewjaminan")
    Call<ResponsModelJaminan> viewjaminan(@Body Requestcontact requestcontact);

    @Headers("Content-Type: application/json")
    @POST("viewfasilitas")
    Call<ResponsModelFasilitas> viewfasilitas(@Body Requestcontact requestcontact);

    @Headers("Content-Type: application/json")
    @POST("viewtunggakan")
    Call<ResponsModelTunggakan> viewtunggakan(@Body Requestcifloanid requestcifloanid);

    @Headers("Content-Type: application/json")
    @POST("upcontact")
    Call<ResponsModelcontactupdate> Updatecontact(@Body Requestupcontact requestupcontact);

    @Multipart
    @POST("inserthasilkunjunagan.php")
    Call<ResponsModel> inserthasilkunjungan(@Part("f_cif") RequestBody f_cif,
                                            @Part("f_loan") RequestBody f_loanid,
                                            @Part("f_s_almtrm") RequestBody f_s_almtrm,
                                            @Part("f_e_almtrm") RequestBody f_e_almtrm,
                                            @Part("f_s_almtush") RequestBody f_s_almtush,
                                            @Part("f_e_almtush") RequestBody f_e_almtush,
                                            @Part("f_s_tlpn") RequestBody f_s_tlpn,
                                            @Part("f_s_email") RequestBody f_s_email,
                                            @Part("f_e_email") RequestBody f_e_email,
                                            @Part("f_hasilkunjungan") RequestBody f_hasilkunjungan,
                                            @Part("f_tgl_ptp") RequestBody f_tgl_ptp,
                                            @Part("f_bertemunasabah") RequestBody f_bertemunasabah,
                                            @Part("f_other") RequestBody f_other,
                                            @Part("f_lokasibertemu") RequestBody f_lokasibertemu,
                                            @Part("f_karakternasabah") RequestBody f_karakternasabah,
                                            @Part("f_negatifissue") RequestBody f_negatifissue,
                                            @Part("f_actionplan") RequestBody f_actionplan,
                                            @Part("f_resumenasabah") RequestBody f_resumenasabah,
                                            @Part("f_totalbayar") RequestBody f_totalbayar,
                                            @Part("f_tglvisit") RequestBody f_tglvisit,
                                            @Part("f_tujuan") RequestBody f_tujuan,
                                            @Part MultipartBody.Part Photo,
                                            @Part MultipartBody.Part photo2);

    @Headers("Content-Type: application/json")
    @POST("inputkunjungan")
    Call<ResponsModelChangePswd> insertkunjungan2(@Body RequestKunjungan requestKunjungan);

    @Multipart
    @POST("hasilkunjungan")
    Call<ResponsModelcontactupdate> insertkunjungan(@Part("f_tujuan") RequestBody f_tujuan,
                                                    @Part("f_nama_nasabah") RequestBody f_nama_nasabah,
                                                    @Part("f_cif") RequestBody f_cif,
                                                    @Part("f_hasilkunjungan") RequestBody f_hasilkunjungan,
                                                    @Part("f_keterangan_hasilkunjungan") RequestBody f_keterangan_hasilkunjungan,
                                                    @Part("f_tanggal_ptp") RequestBody f_tanggal_ptp,
                                                    @Part("f_bertemu") RequestBody f_bertemu,
                                                    @Part("f_keterangan_bertemu") RequestBody f_keterangan_bertemu,
                                                    @Part("f_lokasi_bertemu") RequestBody f_lokasi_bertemu,
                                                    @Part("f_keterangan_lokasi") RequestBody f_keterangan_lokasi,
                                                    @Part("f_karakter") RequestBody f_karakter,
                                                    @Part("f_keterangan_karakter") RequestBody f_keterangan_karakter,
                                                    @Part("f_negatif_issue") RequestBody f_negatif_issue,
                                                    @Part("f_actionplan") RequestBody f_actionplan,
                                                    @Part("f_resumenasabah") RequestBody f_resumenasabah,
                                                    @Part("f_total_tunggakan") RequestBody f_total_tunggakan,
                                                    @Part("f_total_bayar") RequestBody f_total_bayar,
                                                    @Part("f_perkiraan") RequestBody f_perkiraan,
                                                    @Part("f_tglvisit") RequestBody f_tglvisit,
                                                    @Part("f_target_date_plan") RequestBody f_target_date_plan,
                                                    @Part("f_total_bayar_ap") RequestBody f_total_bayar_ap,
                                                    @Part("f_agent") RequestBody f_agent,
                                                    @Part("f_lat") RequestBody f_lat,
                                                    @Part("f_lng") RequestBody f_lng,
                                                    @Part("f_loanid") RequestBody f_loanid,
                                                    @Part("f_code_image") RequestBody f_code_image);


    @GET("read.php")
    Call<ResponsModel> getBiodata();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponsModel> updateData(@Field("id") String id,
                                  @Field("nama") String nama,
                                  @Field("usia") String usia,
                                  @Field("domisili") String domisili);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponsModel> deleteData(@Field("id") String id);
}
