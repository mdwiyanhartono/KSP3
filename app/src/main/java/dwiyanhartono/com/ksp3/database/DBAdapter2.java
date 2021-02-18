package dwiyanhartono.com.ksp3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dwiyanhartono.com.ksp3.api.Retroserver;


public class DBAdapter2 {
    Context c;
    Retroserver d;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter2(Context ctx) {
        this.c = ctx;
        helper = new DBHelper(c);
    }

    //OPEN DB
    public void openDB() {
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CLOSE
    public void close() {
        try {
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //INSERT DATA TO DB
    public long adduser(String username, String area, String jabatan, String status, String iduser, String id_branch, String count, String imei, String datetime) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.nama, username);
            cv.put(Constant.area, area);
            cv.put(Constant.status, status);
            cv.put(Constant.count, count);
            cv.put(Constant.nik, iduser);
            cv.put(Constant.branch, id_branch);
            cv.put(Constant.jabatan, jabatan);
            cv.put(Constant.datetime, datetime);
            cv.put(Constant.imei, imei);
            db.delete(Constant.TB_NAME1, Constant.nik + " = ?", new String[]{iduser});
            return db.insert(Constant.TB_NAME1, null, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    ///// Downlaod //////////////

    public long addacount(String ld, String cif, String norekening, String namadebitur, String alamatrumah, String alamatusaha, String notlp,
                          String email, String cabang, String aoname, String actionplanpuk, String agentid, String bucket, String bucketeom,
                          String os, String totaltunggakan, String approved, String dpd, String loanid, String angsuran, Integer status,String datetime) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.ld, ld);
            cv.put(Constant.cif, cif);
            cv.put(Constant.norekening, norekening);
            cv.put(Constant.namadebitur, namadebitur);
            cv.put(Constant.alamatrumah, alamatrumah);
            cv.put(Constant.alamatusaha, alamatusaha);
            cv.put(Constant.notlp, notlp);
            cv.put(Constant.email, email);
            cv.put(Constant.cabang, cabang);
            cv.put(Constant.aoname, aoname);
            cv.put(Constant.actionplanpuk, actionplanpuk);


            cv.put(Constant.agentid, agentid);
            cv.put(Constant.bucket, bucket);
            cv.put(Constant.bucketeom, bucketeom);
            cv.put(Constant.os, os);
            cv.put(Constant.totaltunggakan, totaltunggakan);
            cv.put(Constant.approved, approved);
            cv.put(Constant.dpd, dpd);
            cv.put(Constant.loanid, loanid);
            cv.put(Constant.angsuran, angsuran);
            cv.put(Constant.datetime, datetime);
            cv.put(Constant.status, status);

//            db.delete(Constant.TB_NAME3, Constant.nik + " = ?", new String[]{iduser});
            db.insert(Constant.TB_NAME3, Constant.id, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public long addtunggakan(String cif, String idt, String totaltunggakan, String installmentdate, String duepr, String duein, String duech,
                          String nodaysod) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.idt, idt);
            cv.put(Constant.totaltunggakan, totaltunggakan);
            cv.put(Constant.installmentdate, installmentdate);
            cv.put(Constant.due_pr, duepr);
            cv.put(Constant.due_in, duein);
            cv.put(Constant.due_ch, duech);
            cv.put(Constant.no_days_do, nodaysod);
            db.insert(Constant.TB_NAME4, Constant.id, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    ///// End Download //////////////
    public long addlogout(String nik, String date, String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.nik, nik);
            cv.put(Constant.datetime, date);
            cv.put(Constant.status_kirim, status);
            db.insert(Constant.TB_NAME11, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addlist(String desc,String val, String type, String code, String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.desc, desc);
            cv.put(Constant.value, val);
            cv.put(Constant.type, type);
            cv.put(Constant.code, code);
            cv.put(Constant.status, status);
            db.insert(Constant.TB_NAME2, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addkunjungan(String iddata, String cif, String codeimage, String tujuan, String hasilkunjungan, String kethasilkunjungan,
                             String namadebitur, String statusactionplan, String bertemu, String ketbertemu, String lokasibertemu,
                             String ketlokasi, String karakter, String negatifissue, String actionplan, String dateactionplan,
                             String resume, String totaltunggakan, String totalbayar, String perkiraan, String tgvisit, String lat, String lng, String sts,String edit_email,String edit_alamat,String edit_alamatusaha,String pihakbank,String ketkarakter,String notif) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.iddata, iddata);
            cv.put(Constant.cif, cif);
            cv.put(Constant.codeimage, codeimage);
            cv.put(Constant.tujuan, tujuan);
            cv.put(Constant.hasilkunjungan, hasilkunjungan);
            cv.put(Constant.kethasilkunjungan, kethasilkunjungan);
            cv.put(Constant.namadebitur, namadebitur);
            cv.put(Constant.statusactionplan, statusactionplan);
            cv.put(Constant.bertemu, bertemu);
            cv.put(Constant.ketbertemu, ketbertemu);
            cv.put(Constant.lokasibertemu, lokasibertemu);
            cv.put(Constant.ketlokasi, ketlokasi);
            cv.put(Constant.karakter, karakter);
            cv.put(Constant.ketkarakter, ketkarakter);
            cv.put(Constant.negatifissue, negatifissue);
            cv.put(Constant.actionplan, actionplan);
            cv.put(Constant.dateactionplan, dateactionplan);
            cv.put(Constant.resume, resume);
            cv.put(Constant.totaltunggakan, totaltunggakan);
            cv.put(Constant.totalbayar, totalbayar);
            cv.put(Constant.perkiraan, perkiraan);
            cv.put(Constant.tgvisit, tgvisit);
            cv.put(Constant.lat, lat);
            cv.put(Constant.lng, lng);
            cv.put(Constant.edit_email, edit_email);
            cv.put(Constant.edit_alamat, edit_alamat);
            cv.put(Constant.edit_alamatusaha, edit_alamatusaha);
            cv.put(Constant.pihakbank, pihakbank);
            cv.put(Constant.notif, notif);
            cv.put(Constant.status_kirim, sts);
//            db.delete(Constant.TB_NAME12, null,null);
//            return
           return db.insert(Constant.TB_NAME12, null, cv);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addselfcured(String cif, String nama, String tanggal) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.namadebitur, nama);
            cv.put(Constant.datetime, tanggal);
            db.insert(Constant.TB_NAME13, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public long addkp(String cif, String nama, String tanggal, String bertemu, String nominal, String dateprocess, String lokasibertemu, String actionplan, String hasilkunjungan) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.namadebitur, nama);
            cv.put(Constant.dateactionplan, tanggal);
            cv.put(Constant.dateprocess, dateprocess);
            cv.put(Constant.lokasibertemu, lokasibertemu);
            cv.put(Constant.bertemu, bertemu);
            cv.put(Constant.totalbayar, nominal);
            cv.put(Constant.hasilkunjungan, hasilkunjungan);
            cv.put(Constant.actionplan, actionplan);
            cv.put(Constant.kp_bp, "KP");
            db.insert(Constant.TB_NAME14, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addbp(String cif, String nama, String tanggal, String bertemu, String nominal, String dateprocess, String lokasibertemu, String actionplan, String hasilkunjungan) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.namadebitur, nama);
            cv.put(Constant.dateactionplan, tanggal);
            cv.put(Constant.dateprocess, dateprocess);
            cv.put(Constant.lokasibertemu, lokasibertemu);
            cv.put(Constant.bertemu, bertemu);
            cv.put(Constant.totalbayar, nominal);
            cv.put(Constant.hasilkunjungan, hasilkunjungan);
            cv.put(Constant.actionplan, actionplan);
            cv.put(Constant.kp_bp, "BP");
            db.insert(Constant.TB_NAME14, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public long addimage(String cif, String loanid, String codeimage, String filepath, String keterangan, String tanggal, String typefile, String statuskirim) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.loanid, loanid);
            cv.put(Constant.code_image, codeimage);
            cv.put(Constant.file_path, filepath);
            cv.put(Constant.keterangan, keterangan);
            cv.put(Constant.tanggal, tanggal);
            cv.put(Constant.typefile, typefile);
            cv.put(Constant.status_kirim, statuskirim);

            return db.insert(Constant.TB_NAME8, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public long adcontact(String cif, String c1, String c2, String c3, String c4, String c5, String c6, String c7) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.contact_1, c1);
            cv.put(Constant.contact_2, c2);
            cv.put(Constant.contact_3, c3);
            cv.put(Constant.contact_4, c4);
            cv.put(Constant.contact_5, c5);
            cv.put(Constant.contact_6, c6);
            cv.put(Constant.contact_7, c7);
            cv.put(Constant.status_kirim, "0");
            return db.insert(Constant.TB_NAME9, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addloc(String lt, String lng, String status, String datetime, String iduser) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.id_user, iduser);
            cv.put(Constant.lat, lt);
            cv.put(Constant.lng, lng);
            cv.put(Constant.status_kirim, status);
            cv.put(Constant.datetime, datetime);
            return db.insert(Constant.TB_NAME10, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addkunjungan(String cif, String nama, String tanggal, String bertemu, String nominal, String dateprocess, String lokasibertemu, String actionplan, String hasilkunjungan) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.namadebitur, nama);
            cv.put(Constant.dateactionplan, tanggal);
            cv.put(Constant.dateprocess, dateprocess);
            cv.put(Constant.lokasibertemu, lokasibertemu);
            cv.put(Constant.bertemu, bertemu);
            cv.put(Constant.totalbayar, nominal);
            cv.put(Constant.hasilkunjungan, hasilkunjungan);
            cv.put(Constant.actionplan, actionplan);
            cv.put(Constant.kp_bp, "BP");
            db.insert(Constant.TB_NAME14, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GET DATA

    public Cursor getLogout() {

        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME11 + " where " + Constant.status_kirim + " = ?", new String[]{String.valueOf(0)});
    }

    public Cursor getUser() {
        String[] columns = {Constant.nama, Constant.area, Constant.jabatan, Constant.status, Constant.nik, Constant.count, Constant.imei,"SUBSTR( "+Constant.datetime + ",1,10)" };

        return db.query(Constant.TB_NAME1, columns, Constant.status + " = ?", new String[]{"1"}, null, null, null);
    }

    public Cursor getUserexpired(String date2) {
        String[] columns = {Constant.nama, Constant.area, Constant.jabatan, Constant.status, Constant.nik, Constant.count, Constant.imei,"SUBSTR( "+Constant.datetime + "1,10)"};

        return db.query(Constant.TB_NAME1, columns, "SUBSTR(" +Constant.datetime +",1,9+) = ?", new String[]{date2}, null, null, null);
    }

    public Cursor gettimer(String m, String code, String sts) {
        String[] columns = {Constant.desc};

        return db.query(Constant.TB_NAME2, columns, Constant.status + " = ? AND " + Constant.type + "= ?", new String[]{sts, code}, null, null, null);
    }

    public Cursor gettimeral(String m, String code, String sts) {
        String[] columns = {Constant.desc};

        return db.query(Constant.TB_NAME2, columns, Constant.status + " = ? AND " + Constant.type + "= ?", new String[]{sts, code}, null, null, null);
    }

    public Cursor getlist(String code, String type) {

        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME2 + " where " + Constant.code + " = ? AND " + Constant.type + "=?", new String[]{code, type});

    }

    public Cursor getkunjungan(String iddata, String cif) {

        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME12 + " where " + Constant.iddata + " = ? AND " + Constant.cif + "=?", new String[]{iddata, cif});

    }

    public Cursor getkunjunganvisit() {

        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME12, null);

    }

    public Cursor getkunjunganpending() {

        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME12+ " where " + Constant.status_kirim+ " = ? ", new String[]{"0"});

    }

    public Cursor getkunjungancontacted() {

        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME12 + " where " + Constant.hasilkunjungan + " = ? ", new String[]{"Contacted"});

    }

    public Cursor getkunjungannocontacted() {

        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME12 + " where " + Constant.hasilkunjungan + " = ? ", new String[]{"No Contacted"});

    }

    public Cursor getptptoday() {

        String Date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME12 + " where " + Constant.dateactionplan + " = ? ", new String[]{Date});

    }

    public Cursor getselfcured(String cif) {

        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME13 + " where " + Constant.cif + " = ? ", new String[]{cif});

    }

    public Cursor getselfcuredcount() {

        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME13, null);

    }

    public Cursor getkp(String cif) {

//        + Constant.cif + " = ? AND "
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME14 + " where " + Constant.kp_bp + " = ?", new String[]{"KP"});

    }

    public Cursor getkpcount() {

//        + Constant.cif + " = ? AND "
        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME14 + " where " + Constant.kp_bp + " = ?", new String[]{"KP"});

    }

    public Cursor getbpall(String cif) {

//        + Constant.cif + " = ? AND "
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME14 + " where " + Constant.kp_bp + " = ?", new String[]{"BP"});

    }

    public Cursor getbp(String cif) {

//        + Constant.cif + " = ? AND "
        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME14 + " where " + Constant.kp_bp + " = ?", new String[]{"BP"});

    }

    public Cursor getbpcount() {

//        + Constant.cif + " = ? AND "
        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME14 + " where " + Constant.kp_bp + " = ?", new String[]{"BP"});

    }

    public Cursor getselfcuredall() {
//        +Constant.ID+","+Constant.CIF+","+ Constant.ACCTNO+",
        return db.rawQuery("SELECT " + Constant.cif + "," + Constant.namadebitur + "," + Constant.datetime + " FROM " + Constant.TB_NAME13 ,null);

//        String[] columns={Constant.cif,Constant.namadebitur,Constant.datetime};

//        return db.query(Constant.TB_NAME13,columns,Constant.datetime+" = ?",new String[]{date},null,null,null);

    }

    public Cursor getcontactedall(String contacted) {
//        +Constant.ID+","+Constant.CIF+","+ Constant.ACCTNO+",
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME12 + " where " + Constant.hasilkunjungan + "= ? ", new String[]{contacted});

//        String[] columns={Constant.cif,Constant.namadebitur,Constant.datetime};

//        return db.query(Constant.TB_NAME13,columns,Constant.datetime+" = ?",new String[]{date},null,null,null);

    }

    public Cursor getvisitall() {
//        +Constant.ID+","+Constant.CIF+","+ Constant.ACCTNO+",
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME12, null);

//        String[] columns={Constant.cif,Constant.namadebitur,Constant.datetime};

//        return db.query(Constant.TB_NAME13,columns,Constant.datetime+" = ?",new String[]{date},null,null,null);

    }

    public Cursor getnocontactedall(String contacted) {
//        +Constant.ID+","+Constant.CIF+","+ Constant.ACCTNO+",
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME12 + " where " + Constant.hasilkunjungan + "= ? ", new String[]{contacted});

//        String[] columns={Constant.cif,Constant.namadebitur,Constant.datetime};

//        return db.query(Constant.TB_NAME13,columns,Constant.datetime+" = ?",new String[]{date},null,null,null);

    }

    public Cursor getptptoday(String date, String date2) {
//        +Constant.ID+","+Constant.CIF+","+ Constant.ACCTNO+",
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME12 + " where  SUBSTR(" + Constant.tgvisit + ",1,7) = ? AND " + Constant.dateactionplan + "= ?", new String[]{date2, date});

//        String[] columns={Constant.cif,Constant.namadebitur,Constant.datetime};

//        return db.query(Constant.TB_NAME13,columns,Constant.datetime+" = ?",new String[]{date},null,null,null);

    }


    public Cursor getlistap(String type, String status) {
        String[] columns = {Constant.desc};

        return db.query(Constant.TB_NAME2, columns, Constant.status + " = ? AND " + Constant.type + " = ? ", new String[]{status, type}, null, null, null);
    }
    public Cursor gettunggakan(String cif) {
        String[] columns = {Constant.id,
                Constant.idt,
                Constant.cif,
                Constant.totaltunggakan ,
                Constant.installmentdate,
                Constant.due_pr,
                Constant.due_in,
                Constant.due_ch,
                Constant.no_days_do};

        return db.query(Constant.TB_NAME4, columns, Constant.cif + " = ?  ", new String[]{cif},null, null, null);

    }

    public Cursor getcountercontact(String cif) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constant.TB_NAME9 + " where " + Constant.cif + " =?", new String[]{cif});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getcontact(String cif) {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME9 + " where " + Constant.cif + " =?", new String[]{cif});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getimage(String cif, String codeimg, String type) {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME8 + " where " + Constant.cif + " = ? AND " + Constant.code_image + "=? AND " + Constant.typefile + "=?", new String[]{cif, codeimg, type});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getimage2(String cif, String codeimg, String ststus, String type) {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME8 + " where " + Constant.cif + " = ? AND " + Constant.code_image + "=? AND " + Constant.status_kirim + "=? AND " + Constant.typefile + "= ?", new String[]{cif, codeimg, ststus, type});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getimage3(String cif, String codeimg, String ststus, String type) {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME8 + " where " + Constant.cif + " = ? AND " + Constant.code_image + "=? AND " + Constant.status_kirim + "=? AND " + Constant.typefile + "= ?", new String[]{cif, codeimg, ststus, type});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getimage4(String cif, String codeimg, String ststus) {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME8 + " where " + Constant.cif + " = ? AND " + Constant.code_image + "=? AND " + Constant.status_kirim + "=?", new String[]{cif, codeimg, ststus});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }


    public Cursor getimageall() {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME8 + " where " + Constant.status_kirim + "=?", new String[]{"0"});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getcontactall(String cif, String status) {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME9 + " where " + Constant.cif + " = ? AND " + Constant.status_kirim + "=? ", new String[]{cif, status});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getcontactallpending() {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME9 + " where " + Constant.status_kirim + "=? ", new String[]{"0"});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getkunjunganpendingall() {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME12 + " where " + Constant.status_kirim + "=? ", new String[]{"0"});

        // return db.query(Constant.TB_NAME2,null ,Constant.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getlocation() {
        //String[] columns = {Constants.ROW_USER, Constants.ROW_LAT, Constants.ROW_LONG, Constants.ROW_DATETIME, Constants.ROW_ID_USER, Constants.ROW_IDLOC};

        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME10 + " where " + Constant.status_kirim + "=?", new String[]{"BELUM TERKIRIM"});

    }

    public Cursor getdailyplanvisit(String agenntid) {
        String[] columns = {Constant.norekening,
                Constant.agentid,
                Constant.namadebitur,
                Constant.bucket,
                Constant.bucketeom,
                Constant.cif,
                Constant.os,
                Constant.totaltunggakan,
                Constant.alamatrumah,
                Constant.dpd,
                Constant.alamatusaha,
                Constant.loanid,
                Constant.email,
                Constant.approved,
                Constant.angsuran,
                Constant.notlp};

        return db.query(Constant.TB_NAME3, columns, Constant.agentid + "=?", new String[]{agenntid}, null, null, null);

    }




    ///////untuk visit


    //////untuk survey

    ////untuk menampilkan detail

    //hasil inventory survey

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //UPDATE

    public long updatelist(String desc, String code, String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.desc, desc);
            cv.put(Constant.status, status);
            db.update(Constant.TB_NAME2, cv, Constant.code + "=?", new String[]{code});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public long updatelistpending(String id) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.status_kirim, "1");
            return db.update(Constant.TB_NAME12, cv, Constant.id + "=?", new String[]{id});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long updatelistpendingsenddata(String codeimage) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.status_kirim, "1");
            return db.update(Constant.TB_NAME12, cv, Constant.codeimage + "=?", new String[]{codeimage});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long updatekunjungan(String iddata, String codeimage, String tujuan, String hasilkunjungan, String kethasilkunjungan,
                                String namadebitur, String statusactionplan, String bertemu, String ketbertemu, String lokasibertemu,
                                String ketlokasi, String karakter, String negatifissue, String actionplan, String dateactionplan,
                                String resume, String totaltunggakan, String totalbayar, String perkiraan, String tgvisit, String lat, String lng) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.codeimage, codeimage);
            cv.put(Constant.tujuan, tujuan);
            cv.put(Constant.hasilkunjungan, hasilkunjungan);
            cv.put(Constant.kethasilkunjungan, kethasilkunjungan);
            cv.put(Constant.namadebitur, namadebitur);
            cv.put(Constant.statusactionplan, statusactionplan);
            cv.put(Constant.bertemu, bertemu);
            cv.put(Constant.ketbertemu, ketbertemu);
            cv.put(Constant.lokasibertemu, lokasibertemu);
            cv.put(Constant.ketlokasi, ketlokasi);
            cv.put(Constant.karakter, karakter);
            cv.put(Constant.negatifissue, negatifissue);
            cv.put(Constant.actionplan, actionplan);
            cv.put(Constant.dateactionplan, dateactionplan);
            cv.put(Constant.resume, resume);
            cv.put(Constant.totaltunggakan, totaltunggakan);
            cv.put(Constant.totalbayar, totalbayar);
            cv.put(Constant.perkiraan, perkiraan);
            cv.put(Constant.tgvisit, tgvisit);
            cv.put(Constant.lat, lat);
            cv.put(Constant.lng, lng);
            db.update(Constant.TB_NAME12, cv, Constant.iddata + "=?", new String[]{iddata});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long updateselfcured(String cif, String nama, String tanggal) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.namadebitur, nama);
            cv.put(Constant.datetime, tanggal);
            db.update(Constant.TB_NAME13, cv, Constant.cif + "= ?", new String[]{cif});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }


    public long updatekp(String cif, String nama, String tanggal, String bertemu, String nominal, String dateprocess, String lokasibertemu, String actionplan, String hasilkunjungan) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.namadebitur, nama);
            cv.put(Constant.dateactionplan, tanggal);
            cv.put(Constant.dateprocess, dateprocess);
            cv.put(Constant.lokasibertemu, lokasibertemu);
            cv.put(Constant.bertemu, bertemu);
            cv.put(Constant.totalbayar, nominal);
            cv.put(Constant.hasilkunjungan, hasilkunjungan);
            cv.put(Constant.actionplan, actionplan);
            cv.put(Constant.kp_bp, "KP");
            db.update(Constant.TB_NAME14, cv, Constant.cif + "= ? AND " + Constant.kp_bp + "= ?", new String[]{cif, "KP"});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public long updatebp(String cif, String nama, String tanggal, String bertemu, String nominal, String dateprocess, String lokasibertemu, String actionplan, String hasilkunjungan) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.cif, cif);
            cv.put(Constant.namadebitur, nama);
            cv.put(Constant.dateactionplan, tanggal);
            cv.put(Constant.dateprocess, dateprocess);
            cv.put(Constant.lokasibertemu, lokasibertemu);
            cv.put(Constant.bertemu, bertemu);
            cv.put(Constant.totalbayar, nominal);
            cv.put(Constant.hasilkunjungan, hasilkunjungan);
            cv.put(Constant.actionplan, actionplan);
            cv.put(Constant.kp_bp, "BP");
            db.update(Constant.TB_NAME14, cv, Constant.cif + "= ? AND " + Constant.kp_bp + "= ?", new String[]{cif, "BP"});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public long updatecontact(String c1, String c2, String c3, String c4, String c5, String c6, String c7, String cif) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.contact_1, c1);
            cv.put(Constant.contact_2, c2);
            cv.put(Constant.contact_3, c3);
            cv.put(Constant.contact_4, c4);
            cv.put(Constant.contact_5, c5);
            cv.put(Constant.contact_6, c6);
            cv.put(Constant.contact_7, c7);
            return db.update(Constant.TB_NAME9, cv, Constant.cif + " =?", new String[]{cif});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long updatestscontact(String cif, String sts) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.status_kirim, sts);
            return db.update(Constant.TB_NAME9, cv, Constant.cif + " =?", new String[]{cif});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long updateimage(String id, String ket) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.keterangan, ket);

            return db.update(Constant.TB_NAME8, cv, Constant.id + "=?", new String[]{id});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public long updatestsimage(String id, String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.status_kirim, status);

            return db.update(Constant.TB_NAME8, cv, Constant.id + "=?", new String[]{id});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DELETE

    public long Deleteuser() {
        try {

            return db.delete(Constant.TB_NAME1, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long deletekunjungan(String date) {
        try {

            return db.delete(Constant.TB_NAME12, Constant.tgvisit + "< ?", new String[]{date});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public long deletekunjunganall(String sts) {
        try {

            return db.delete(Constant.TB_NAME12, Constant.status_kirim + "= ?", new String[]{"1"});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long deletekunjunganstatus(String sts) {
        try {

            return db.delete(Constant.TB_NAME12, Constant.status_kirim + "= ?", new String[]{sts});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long deleteselfcured(String date) {
        try {

            return db.delete(Constant.TB_NAME13, Constant.datetime + "< ?", new String[]{date});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long deleteselfcuredall() {
        try {

            return db.delete(Constant.TB_NAME13, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long deletekp(String date) {
        try {

            return db.delete(Constant.TB_NAME14, "SUBSTR(" + Constant.dateprocess + " 1,6 ) < ?", new String[]{date});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long deletekpall() {
        try {

            return db.delete(Constant.TB_NAME14, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long DeleteLocation(int id) {
        try {
            return db.delete(Constant.TB_NAME10, Constant.id + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long deleteparameter() {
        try {
            return db.delete(Constant.TB_NAME2, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long deleteaccount(String date) {
        try {
            return db.delete(Constant.TB_NAME3, Constant.datetime +" <> ?", new String[]{date});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long deleteaccountpercif(String date) {
        try {
            return db.delete(Constant.TB_NAME3, Constant.cif+" =?", new String[]{date});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public long deletetunggakan(String date) {
        try {
            return db.delete(Constant.CREATE_TB4, Constant.cif +" =?", new String[]{date});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long deleteaccountall() {
        try {
            return db.delete(Constant.TB_NAME3,null,null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long deletetunggakan() {
        try {
            return db.delete(Constant.TB_NAME4,null,null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public long deleteimage(String status) {
        try {
            return db.delete(Constant.TB_NAME8,null,null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }



}
