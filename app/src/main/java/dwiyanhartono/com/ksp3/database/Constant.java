package dwiyanhartono.com.ksp3.database;

public class Constant {

    static final int DB_VERSION = 1;
    static final String DB_NAME = "Colsys_v1.0";


    static final String id = "id";
    static final String nama = "nama";
    static final String area = "area";
    static final String status = "status";
    static final String nik = "nik";
    static final String count = "count";
    static final String jabatan = "jabatan";
    static final String branch = "branch";
    static final String datetime = "datetime";
    static final String imei = "imei";
    static final String value = "value";


    static final String os = "os";
    static final String agentid = "agentid";
    static final String bucket = "bucket";
    static final String bucketeom = "bucketeom";
    static final String totaltunggakan = "totaltunggakan";
    static final String approved = "approved";
    static final String dpd = "dpd";

    static final String TB_NAME1 = "t_user";

    static final String CREATE_TB1 = "CREATE TABLE " + TB_NAME1 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + nama + " TEXT,"
            + area + " TEXT ,"
            + status + " TEXT ,"
            + count + " TEXT ,"
            + nik + " TEXT ,"
            + branch + " TEXT ,"
            + datetime + " TEXT ,"
            + imei + " TEXT ,"
            + jabatan + " TEXT );";


    static final String type = "type";
    static final String code = "code";
    static final String desc = "desc";

    static final String TB_NAME2 = "t_parameter";

    static final String CREATE_TB2 = "CREATE TABLE " + TB_NAME2 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + type + " TEXT NOT NULL,"
            + code + " TEXT NOT NULL,"
            + desc + " TEXT NOT NULL,"
            + value + " TEXT,"
            + status + " TEXT NOT NULL);";


    static final String ld = "ld";
    static final String cif = "cif";
    static final String namadebitur = "namadebitur";
    static final String alamatrumah = "alamatrumah";
    static final String notlp = "notlp";
    static final String email = "email";
    static final String alamatusaha = "alamatusaha";
    static final String norekening = "norekening";
    static final String aoname = "aoname";
    static final String cabang = "cabang";
    static final String actionplanpuk = "actionplanpuk";
    static final String loanid = "loanid";
    static final String angsuran = "angsuran";

    static final String TB_NAME3 = "t_account";

    static final String CREATE_TB3 = "CREATE TABLE " + TB_NAME3 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ld + " TEXT ,"
            + cif + " TEXT ,"
            + norekening + " TEXT,"
            + namadebitur + " TEXT ,"
            + alamatrumah + " TEXT ,"
            + alamatusaha + " TEXT ,"
            + notlp + " TEXT ,"
            + email + " TEXT ,"
            + cabang + " TEXT ,"
            + aoname + " TEXT ,"
            + actionplanpuk + " TEXT ,"
            + agentid + " TEXT,"
            + bucket + " TEXT,"
            + bucketeom + " TEXT,"
            + os + " TEXT,"
            + totaltunggakan + " TEXT,"
            + approved + " TEXT,"
            + dpd + " TEXT,"
            + loanid + " TEXT,"
            + angsuran + " TEXT,"
            + datetime + " TEXT,"
            + status + " INTEGER );";


    static final String idt = "idt";
    static final String installmentdate = "installmentdate";
    static final String due_pr = "due_pr";
    static final String due_in = "due_in";
    static final String due_ch = "due_ch";
    static final String no_days_do = "no_days_do";

    static final String TB_NAME4 = "t_deatail_tunggakan";

    static final String CREATE_TB4 = "CREATE TABLE " + TB_NAME4 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + cif + " TEXT ,"
            + idt + " TEXT ,"
            + totaltunggakan + " TEXT ,"
            + installmentdate + " TEXT ,"
            + due_pr + " TEXT ,"
            + due_in + " TEXT ,"
            + due_ch + " TEXT ,"
            + no_days_do + " TEXT );";


    static final String jenisjaminan = "jenisjaminan";
    static final String alamat = "alamat";
    static final String markevalue = "markevalue";

    static final String TB_NAME5 = "t_jaminan";

    static final String CREATE_TB5 = "CREATE TABLE " + TB_NAME5 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + cif + " TEXT NOT NULL,"
            + idt + " TEXT NOT NULL,"
            + jenisjaminan + " TEXT NOT NULL,"
            + desc + " TEXT NOT NULL,"
            + alamat + " TEXT NOT NULL,"
            + markevalue + " TEXT NOT NULL);";


    static final String facility_type = "facility_type";
    static final String plafond = "plafond";
    static final String booked = "booked";
    static final String expired_promise = "expired_promise";
    static final String expired_date = "expired_date";
    static final String flag_probiz = "flag_probiz";

    static final String TB_NAME6 = "t_facility";

    static final String CREATE_TB6 = "CREATE TABLE " + TB_NAME6 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + cif + " TEXT NOT NULL,"
            + loanid + " TEXT NOT NULL,"
            + facility_type + " TEXT NOT NULL,"
            + plafond + " TEXT NOT NULL,"
            + os + " TEXT NOT NULL,"
            + booked + " TEXT NOT NULL,"
            + expired_promise + " TEXT NOT NULL,"
            + expired_date + " TEXT NOT NULL,"
            + flag_probiz + " TEXT NOT NULL);";


    static final String sp = "sp";
    static final String tanggal_sp = "tanggal_sp";
    static final String tanggal_kirim = "tanggal_kirim";

    static final String TB_NAME7 = "t_sp";

    static final String CREATE_TB7 = "CREATE TABLE " + TB_NAME7 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + cif + " TEXT ,"
            + sp + " TEXT ,"
            + tanggal_sp + " TEXT ,"
            + tanggal_kirim + " TEXT );";


    static final String code_image = "code_image";
    static final String file_path = "file_path";
    static final String status_kirim = "status_kirim";
    static final String keterangan = "keterangan";
    static final String tanggal = "tanggal";
    static final String typefile = "typefile";

    static final String TB_NAME8 = "t_image";

    static final String CREATE_TB8 = "CREATE TABLE " + TB_NAME8 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + cif + " TEXT ,"
            + loanid + " TEXT ,"
            + code_image + " TEXT ,"
            + file_path + " TEXT ,"
            + keterangan + " TEXT ,"
            + tanggal + " TEXT ,"
            + typefile + " TEXT ,"
            + status_kirim + " TEXT );";


    static final String contact_1 = "contact_1";
    static final String contact_2 = "contact_2";
    static final String contact_3 = "contact_3";
    static final String contact_4 = "contact_4";
    static final String contact_5 = "contact_5";
    static final String contact_6 = "contact_6";
    static final String contact_7 = "contact_7";




    static final String TB_NAME9 = "t_contact";

    static final String CREATE_TB9 = "CREATE TABLE " + TB_NAME9 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + cif + " TEXT ,"
            + loanid + " TEXT ,"
            + contact_1 + " TEXT ,"
            + contact_2 + " TEXT ,"
            + contact_3 + " TEXT ,"
            + contact_4 + " TEXT ,"
            + contact_5 + " TEXT ,"
            + contact_6 + " TEXT ,"
            + contact_7 + " TEXT ,"
            + status_kirim + " TEXT );";


    static final String lng = "lng";
    static final String lat = "lat";
    static final String id_user = "id_user";

    static final String TB_NAME10 = "t_longlat";

    static final String CREATE_TB10 = "CREATE TABLE " + TB_NAME10 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + id_user + " TEXT ,"
            + lng + " TEXT ,"
            + lat + " TEXT ,"
            + datetime + " TEXT ,"
            + status_kirim + " TEXT NOT NULL);";



    static final String TB_NAME11 = "t_logout";

    static final String CREATE_TB11 = "CREATE TABLE " + TB_NAME11 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + nik + " TEXT NOT NULL,"
            + datetime + " TEXT ,"
            + status_kirim + " TEXT NOT NULL);";


    static final String iddata = "iddata";
    static final String codeimage = "codeimage";
    static final String statusactionplan = "statusactionplan";
    static final String tujuan = "tujuan";
    static final String hasilkunjungan = "hasilkunjungan";
    static final String kethasilkunjungan = "kethasilkunjungan";
    static final String bertemu = "bertemu";
    static final String ketbertemu = "ketbertemu";
    static final String lokasibertemu = "lokasibertemu";
    static final String ketlokasi = "ketlokasi";
    static final String karakter = "karakter";
    static final String ketkarakter = "ketkarakter";
    static final String negatifissue = "negatifissue";
    static final String actionplan = "actionplan";
    static final String dateactionplan = "dateactionplan";
    static final String resume = "resume";
    static final String totalbayar = "totalbayar";
    static final String perkiraan = "perkiraan";
    static final String tgvisit = "tgvisit";
    static final String edit_email = "edit_email";
    static final String edit_alamat = "edit_alamat";
    static final String edit_alamatusaha = "edit_alamatusaha";
    static final String pihakbank = "pihakbank";
    static final String notif = "notifikasi";

    static final String TB_NAME12 = "t_kunjungan";

    static final String CREATE_TB12 = "CREATE TABLE " + TB_NAME12 + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT," //0
            + iddata + " TEXT ," //1
            + lat + " TEXT ," //2
            + lng + " TEXT ," //3
            + cif + " TEXT ," //4
            + loanid + " TEXT ," //5
            + codeimage + " TEXT ," //6
            + namadebitur + " TEXT ," //7
            + statusactionplan + " TEXT ," //8
            + tujuan + " TEXT ," //9
            + hasilkunjungan + " TEXT ," //10
            + kethasilkunjungan + " TEXT ," //11
            + bertemu + " TEXT ," //12
            + ketbertemu + " TEXT ," //13
            + lokasibertemu + " TEXT ," //14
            + ketlokasi + " TEXT ," //15
            + karakter + " TEXT ," //16
            + ketkarakter + " TEXT ," //17
            + negatifissue + " TEXT ," //18
            + actionplan + " TEXT ," //19
            + dateactionplan + " TEXT ," //20
            + resume + " TEXT ," //21
            + totaltunggakan + " TEXT ," //22
            + totalbayar + " TEXT ," //23
            + perkiraan + " TEXT ," //24
            + tgvisit + " TEXT ," //25
            + edit_email + " TEXT ," //26
            + edit_alamat + " TEXT ," //27
            + edit_alamatusaha + " TEXT ," //28
            + pihakbank + " TEXT ," //29
            + notif + " TEXT ," //30
            + angsuran + " TEXT ," //31
            + norekening + " TEXT ," //32
            + status_kirim + " TEXT );" ;//33


    static final String TB_NAME13 = "t_selfcured";
    static final String CREATE_TB13 = "CREATE TABLE " + TB_NAME13 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + cif + " TEXT ,"
            + namadebitur + " TEXT ,"
            + datetime + " TEXT);";


    static final String dateprocess = "dateprocess";
    static final String kp_bp = "kp_bp";

    static final String TB_NAME14 = "t_kp_bp";
    static final String CREATE_TB14 = "CREATE TABLE " + TB_NAME14 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + cif + " TEXT ,"
            + namadebitur + " TEXT ,"
            + actionplan + " TEXT ,"
            + hasilkunjungan + " TEXT ,"
            + lokasibertemu + " TEXT ,"
            + bertemu + " TEXT ,"
            + totalbayar + " TEXT ,"
            + dateprocess + " TEXT ,"
            + kp_bp + " TEXT ,"
            + dateactionplan + " TEXT);";

}
