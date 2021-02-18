package dwiyanhartono.com.ksp3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context ctx) {
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
    public long adduser(String username, String area, String pass, String status, String iduser, String id_branch) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.ROW_NAMAUSR, username);
            cv.put(Constants.ROW_AREA, area);
            cv.put(Constants.ROW_PASSUSR, pass);
            cv.put(Constants.ROW_STATUSUSR, status);
            cv.put(Constants.ROW_IDUSUSRSRV, iduser);
            cv.put(Constants.ROW_BRANCH, id_branch);
            db.delete(Constants.TB_NAME3, Constants.ROW_NAMAUSR + " = ?", new String[]{username});
            return db.insert(Constants.TB_NAME3, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addnetwork(String ip, String port) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.ipaddress, ip);
            cv.put(Constants.port, port);
            return db.insert(Constants.TB_NAME7, Constants.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addperformance(String month) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.PERFORMANCE, month);
            return db.insert(Constants.TB_NAME11, Constants.ID, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addloc(String user, String lt, String lng, String status, String datetime, String iduser) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.ROW_USER, user);
            cv.put(Constants.ROW_LAT, lt);
            cv.put(Constants.ROW_LONG, lng);
            cv.put(Constants.ROW_STATUS, status);
            cv.put(Constants.ROW_DATETIME, datetime);
            cv.put(Constants.ROW_ID_USER, iduser);
            return db.insert(Constants.TB_NAME4, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long adcontact(String c1, String c2, String c3, String c4, String c5, String c6, String c7) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.contact1, c1);
            cv.put(Constants.contact2, c2);
            cv.put(Constants.contact3, c3);
            cv.put(Constants.contact4, c4);
            cv.put(Constants.contact5, c5);
            cv.put(Constants.contact6, c6);
            cv.put(Constants.contact7, c7);
            return db.insert(Constants.TB_NAME15, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addlocnasabah(String branch, String cif, String acctno, String long1, String lat) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.ROW_BRANCH, branch);
            cv.put(Constants.CIF, cif);
            cv.put(Constants.ACCTNO, acctno);
            cv.put(Constants.ROW_LONG, long1);
            cv.put(Constants.ROW_LAT, lat);
            cv.put(Constants.ROW_COS_LAT_RAD, Math.cos(deg2rad(Double.parseDouble(lat))));
            cv.put(Constants.ROW_SIN_LAT_RAD, Math.sin(deg2rad(Double.parseDouble(lat))));
            cv.put(Constants.ROW_COS_LON_RAD, Math.cos(deg2rad(Double.parseDouble(long1))));
            cv.put(Constants.ROW_SIN_LON_RAD, Math.sin(deg2rad(Double.parseDouble(long1))));
            return db.insert(Constants.TB_NAME10, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long add(String name, String loan, String cycle, String os, String totalkewajiban, String alamat, String tglsign) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.NAME, name);
            cv.put(Constants.No_Loan, loan);
            cv.put(Constants.Cycle, cycle);
            cv.put(Constants.Os, os);
            cv.put(Constants.Total_Kewajiban, totalkewajiban);
            cv.put(Constants.Alamat, alamat);
            cv.put(Constants.TANGGALASIGNi, tglsign);
            return db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long add2(String name, String loan, String cycle, String os, String totalkewajiban, String alamat
            , String nasabahbayar, String bertemu, String nominal_bayar, String lokasibertemmu, String karakternasabah, String resume, String buktibayar, String photo, String tglasign, String tglproses, String status, Integer statussattlement) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.NAME2, name);
            cv.put(Constants.No_Loan2, loan);
            cv.put(Constants.Cycle2, cycle);
            cv.put(Constants.Os2, os);
            cv.put(Constants.Total_Kewajiban2, totalkewajiban);
            cv.put(Constants.Alamat2, alamat);
            cv.put(Constants.NASABAH_BAYAR, nasabahbayar);/*
            cv.put(Constants.FULLPART,fullpart );*/
            cv.put(Constants.BERTEMU, bertemu);
            cv.put(Constants.NOMINAL_BAYAR, nominal_bayar);
            cv.put(Constants.LOKASIBERTEMU, lokasibertemmu);
            cv.put(Constants.KARAKTERNASABAH, karakternasabah);
            cv.put(Constants.RESUME, resume);
            cv.put(Constants.BUKTIBAYAR, buktibayar);
            cv.put(Constants.IMAGE, photo);
            cv.put(Constants.TANGGALASIGN, tglasign);
            cv.put(Constants.TANGGALPROSES, tglproses);
            cv.put(Constants.STATUS, status);
            cv.put(Constants.STATUSSATTLEMENT, statussattlement);
            return db.insert(Constants.TB_NAME2, Constants.ROW_ID2, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addinventorysurvey(String name, String loan, String cycle, String os, String tanggal, String alamat) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.NAMEIV, name);
            cv.put(Constants.No_LoanIV, loan);
            cv.put(Constants.CycleIV, cycle);
            cv.put(Constants.OsIV, os);
            cv.put(Constants.TanggalIV, tanggal);
            cv.put(Constants.AlamatIV, alamat);
            return db.insert(Constants.TB_NAME5, Constants.ROW_IDIV, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addhasilinventorysurvey(String name, String loan, String cycle, String os, String tanggal, String alamat
            , String bertemu, String aset, String krjaannasbah, String tglgajian, String posisi, String kondisijaminan, String dokument1, String dokument2, String dokument3, String dokument4, String tanggalasign, String tanggalproses, String status, String image) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.NAMEIVH, name);
            cv.put(Constants.No_LoanIVH, loan);
            cv.put(Constants.CycleIVH, cycle);
            cv.put(Constants.OsIVH, os);
            cv.put(Constants.TanggalIVH, tanggal);
            cv.put(Constants.AlamatIVH, alamat);
            cv.put(Constants.BERTEMUDGN, bertemu);
            cv.put(Constants.KEBERADAAN_ASET, aset);
            cv.put(Constants.PKERJAAN_NASABAH, krjaannasbah);
            cv.put(Constants.TANGGALGAJIAN, tglgajian);
            cv.put(Constants.POSISI_JENISUSAHA, posisi);
            cv.put(Constants.KONDISIJAMINAN, kondisijaminan);
            cv.put(Constants.DOCUMENT1, dokument1);
            cv.put(Constants.DOCUMENT2, dokument2);
            cv.put(Constants.DOCUMENT3, dokument3);
            cv.put(Constants.DOCUMENT4, dokument4);
            cv.put(Constants.TANGGALASIGN, tanggalasign);
            cv.put(Constants.TANGGALPROSES, tanggalproses);
            cv.put(Constants.IMAGE, image);
            cv.put(Constants.STATUS, status);

            return db.insert(Constants.TB_NAME6, Constants.ROW_IDIVH, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long addcollectionlist(String cif, String acctno, String no_loan, String nama, String prodid, String tenor, String duedate, String alamatR, String alamatk, String kota, String pos, String bisnisarea, String recordowner, String dpd, String cycle, String id_agent, String pinjamanapokok, String tagihanpokok, String bunga, String denda, String os, String totalkewajiban, String status, String tglsign, String installmentdate) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.CIF, cif);
            cv.put(Constants.ACCTNO, acctno);
            cv.put(Constants.LOAN, no_loan);
            cv.put(Constants.NAMA, nama);
            cv.put(Constants.PRODID, prodid);
            cv.put(Constants.TENOR, tenor);
            cv.put(Constants.DUEDATE, duedate);
            cv.put(Constants.ALAMATRUMAH, alamatR);
            cv.put(Constants.ALAMATKANTOR, alamatk);
            cv.put(Constants.KOTA, kota);
            cv.put(Constants.POS, pos);
            cv.put(Constants.BISNISAREA, bisnisarea);
            cv.put(Constants.RECORDOWNER, recordowner);
            cv.put(Constants.DPD, dpd);
            cv.put(Constants.CYCLE, cycle);
            cv.put(Constants.AGENTID, id_agent);
            cv.put(Constants.PPOKOK, pinjamanapokok);
            cv.put(Constants.TAGIHANPOKOK, tagihanpokok);
            cv.put(Constants.BUNGA, bunga);
            cv.put(Constants.DENDA, denda);
            cv.put(Constants.OS, os);
            cv.put(Constants.TOTALKEWAJIBAN, totalkewajiban);
            cv.put(Constants.STATUS1, status);
            cv.put(Constants.TANGGALASIGNs, tglsign);
            cv.put(Constants.INSTALLMENTDATE, tglsign);

            db.insert(Constants.TB_NAME9, Constants.ID, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }




    public long adddatahasilkunjungan(String tujuan, String nama, String cif, String hasilkunjungan, String ketkunjungan, String tanggalptp, String bertemu, String ketbertemu,
                                      String lokasibertemu, String ketlokasi, String karakter, String ketkarakter, String negatifissue,
                                      String actionplan, String resumenasabah, String totaltunggakan, String totalbayar, String perkiraan, String tanggalvisit,
                                      String photodoc, String photojaminan, String STATUS) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.tujuan, tujuan);
            cv.put(Constants.nama, nama);
            cv.put(Constants.cif, cif);
            cv.put(Constants.hasilkunjungan, hasilkunjungan);
            cv.put(Constants.ketkunjungan, ketkunjungan);
            cv.put(Constants.tanggalptp, tanggalptp);
            cv.put(Constants.bertemu, bertemu);
            cv.put(Constants.ketbertemu, ketbertemu);
            cv.put(Constants.lokasibertemu, lokasibertemu);
            cv.put(Constants.ketlokasi, ketlokasi);
            cv.put(Constants.karakter, karakter);
            cv.put(Constants.ketkarakter, ketkarakter);
            cv.put(Constants.negatifissue, negatifissue);
            cv.put(Constants.actionplan, actionplan);
            cv.put(Constants.resumenasabah, resumenasabah);
            cv.put(Constants.totaltunggakan, totaltunggakan);
            cv.put(Constants.totalbayar, totalbayar);
            cv.put(Constants.perkiraan, perkiraan);
            cv.put(Constants.tanggalvisit, tanggalvisit);
            cv.put(Constants.photodoc, photodoc);
            cv.put(Constants.photojaminan, photojaminan);
            cv.put(Constants.STATUS, STATUS);

            db.insert(Constants.TB_NAME13, Constants.ID, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }


    public long adddatacus(String cif, String nama, String kelamin, String almtrm, String almtush, String norek, String aoname,
                           String cabang, String actionplanpuk, String AGENTID, String OS, String totaltunggakan,
                           String bucket, String bucketeom, String email, String loan, String DPD, int status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.cif, cif);
            cv.put(Constants.nama, nama);
            cv.put(Constants.kelamin, kelamin);
            cv.put(Constants.almtrm, almtrm);
            cv.put(Constants.almtush, almtush);
            cv.put(Constants.norek, norek);
            cv.put(Constants.aoname, aoname);
            cv.put(Constants.cabang, cabang);
            cv.put(Constants.actionplanpuk, actionplanpuk);
            cv.put(Constants.AGENTID, AGENTID);
            cv.put(Constants.OS, OS);
            cv.put(Constants.totaltunggakan, totaltunggakan);
            cv.put(Constants.bucket, bucket);
            cv.put(Constants.bucketeom, bucketeom);
            cv.put(Constants.email, email);
            cv.put(Constants.LOAN, loan);
            cv.put(Constants.DPD, DPD);
            cv.put(Constants.STATUS, status);

            db.insert(Constants.TB_NAME17, Constants.ID, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addlist(String desc,String type,String code,String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.deskription, desc);
            cv.put(Constants.type, type);
            cv.put(Constants.code, code);
            cv.put(Constants.STATUS, status);
            db.insert(Constants.TB_NAME14, Constants.ID, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GET DATA
    public Cursor getAllPlayers() {
        String[] columns = {Constants.ROW_ID, Constants.NAME, Constants.No_Loan, Constants.Cycle, Constants.Os, Constants.Total_Kewajiban, Constants.Alamat, Constants.TANGGALASIGNi};

        return db.query(Constants.TB_NAME, columns, null, null, null, null, null);
    }


    public Cursor getdailyplanvisit(int status) {
        String[] columns = {Constants.norek, Constants.AGENTID, Constants.nama, Constants.bucket, Constants.bucketeom, Constants.cif, Constants.Os, Constants.totaltunggakan,
                Constants.almtrm, Constants.DPD, Constants.almtush, Constants.LOAN, Constants.email};

        return db.query(Constants.TB_NAME17, columns, Constants.STATUS + " = ?", new String[]{String.valueOf(status)}, null, null, null);

    }

    public Cursor getAllcountlist(String tglsign) {

        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," +
                Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," +
                Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," +
                Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," +
                Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + " " + "FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) = ?" + "ORDER BY " + Constants.DPD + " DESC", new String[]{tglsign});
    }

    public Cursor getAllcountrepot() {
        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," +
                Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," +
                Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," +
                Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," +
                Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + " " + "FROM " + Constants.TB_NAME9 + " where " + Constants.STATUS1 + " =?", new String[]{"1"});
    }

    public Cursor getAllcountrepot2(String pilih) {
        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," +
                Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," +
                Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," +
                Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," +
                Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + " " + "FROM " + Constants.TB_NAME9 + " Where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) = ? AND " + Constants.STATUS1 + "=?", new String[]{pilih, "1"});
    }

    public Cursor getAllcountlistOS(String tglsign) {
        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," +
                Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," +
                Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," +
                Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," +
                Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + " " + "FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) = ?" + "ORDER BY " + Constants.DPD + " ASC", new String[]{tglsign});
    }

    public Cursor getAllcountrecord(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =?", new String[]{tglsign});

        //return db.query(Constants.TB_NAME9,null ,null,null,null,null,null);
    }

    public Cursor getAllcountlistsattlement(String tglsign, String loan, String os) {
        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," + Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," + Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," + Constants.PPOKOK + "," +
                Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," + Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + " " +
                "FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) = ? AND " + Constants.LOAN + " = ? AND " + Constants.OS + "=?", new String[]{tglsign, loan, os});
    }

    public Cursor getAllcountlistmaps(String cif, String acctno) {
        String[] columns = {Constants.ID, Constants.CIF, Constants.ACCTNO, Constants.LOAN, Constants.NAMA, Constants.PRODID, Constants.TENOR, Constants.DUEDATE, Constants.ALAMATRUMAH
                , Constants.ALAMATKANTOR, Constants.KOTA, Constants.POS, Constants.BISNISAREA, Constants.RECORDOWNER, Constants.DPD, Constants.CYCLE, Constants.AGENTID, Constants.PPOKOK, Constants.TAGIHANPOKOK, Constants.BUNGA, Constants.DENDA, Constants.OS, Constants.TOTALKEWAJIBAN, Constants.STATUS1, Constants.TANGGALASIGNs};

        return db.query(Constants.TB_NAME9, columns, Constants.CIF + " = ? AND " + Constants.ACCTNO + " = ? ", new String[]{cif, acctno}, null, null, null);
    }

    public Cursor getlonglattrack(String cif, String acctno) {
        String[] columns = {Constants.ROW_LONG, Constants.ROW_LAT};

        return db.query(Constants.TB_NAME10, columns, Constants.CIF + " = ? AND " + Constants.ACCTNO + " = ? ", new String[]{cif, acctno}, null, null, null);
    }

    public Cursor getAllcountlistloc() {
        String[] columns = {Constants.ID, Constants.CIF, Constants.ACCTNO, Constants.LOAN, Constants.NAMA, Constants.PRODID, Constants.TENOR, Constants.DUEDATE, Constants.ALAMATRUMAH
                , Constants.ALAMATKANTOR, Constants.KOTA, Constants.POS, Constants.BISNISAREA, Constants.RECORDOWNER, Constants.DPD, Constants.CYCLE, Constants.AGENTID, Constants.PPOKOK, Constants.TAGIHANPOKOK, Constants.BUNGA, Constants.DENDA, Constants.OS, Constants.TOTALKEWAJIBAN, Constants.STATUS1};

        return db.query(Constants.TB_NAME9, columns, null, null, null, null, null);
    }

    public Cursor getcounternv(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) = ? AND " + Constants.STATUS1 + " = ? AND " + Constants.DPD + " > ?", new String[]{tglsign, "0", "0",});

        //return db.query(Constants.TB_NAME9,null ,Constants.STATUS1+" = ? AND "+Constants.DPD+" > ?",new String[]{"0","0"},null,null,null);
    }

    public Cursor getcountercontact(String cif) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME15 + " where " + Constants.cif + " =?", new String[]{cif});

        // return db.query(Constants.TB_NAME2,null ,Constants.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getcounterfp(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME2 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.NASABAH_BAYAR + " = ?", new String[]{tglsign, "Full"});

        // return db.query(Constants.TB_NAME2,null ,Constants.NASABAH_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }

    public Cursor getcounterpp(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME2 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.NASABAH_BAYAR + " = ?", new String[]{tglsign, "Partial"});

        // return db.query(Constants.TB_NAME2,null ,Constants.NASABAH_BAYAR+" = ?",new String[]{"Partial"},null,null,null);
    }

    public Cursor getcounternp(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME2 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.NOMINAL_BAYAR + " = ?", new String[]{tglsign, ""});

        //return db.query(Constants.TB_NAME2,null ,Constants.NOMINAL_BAYAR+" = ?",new String[]{""},null,null,null);
    }

    public Cursor getpp(String tglsign) {
        return db.rawQuery("SELECT " + Constants.ROW_ID2 + "," + Constants.NAME2 + "," + Constants.No_Loan2 + "," + Constants.Cycle2 + "," +
                Constants.Os2 + "," + Constants.Total_Kewajiban2 + "," + Constants.Alamat2 + "," + Constants.NASABAH_BAYAR + "," +
                Constants.BERTEMU + "," + Constants.NOMINAL_BAYAR + "," + Constants.LOKASIBERTEMU + "," + Constants.KARAKTERNASABAH + "," +
                Constants.RESUME + "," + Constants.BUKTIBAYAR + "," + Constants.IMAGE + "," + Constants.TANGGALASIGN + "," + Constants.TANGGALPROSES + "," + Constants.STATUS +
                " FROM " + Constants.TB_NAME2 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.NASABAH_BAYAR + " = ? ", new String[]{tglsign, "Partial"});

        /*return db.rawQuery("SELECT "+"a."+Constants.ID+","+"a."+Constants.CIF+","+"a."+Constants.ACCTNO+","+"b."+Constants.No_Loan+","+"b."+Constants.NAME+","+"a."+Constants.PRODID+","+"a."+Constants.TENOR+","+"a."+Constants.DUEDATE+","+"b."+Constants.NOMINAL_BAYAR+","+"a."+
                Constants.ALAMATKANTOR+","+"a."+Constants.KOTA+","+"a."+Constants.POS+","+"a."+Constants.BISNISAREA+","+"a."+Constants.RECORDOWNER+","+"a."+Constants.DPD+","+"a."+Constants.CYCLE+","+"a."+Constants.AGENTID+","+"a."+Constants.PPOKOK+","+"a."+Constants.TAGIHANPOKOK+","+"a."+Constants.BUNGA+","+"a."+Constants.DENDA+","+"a."+Constants.OS+","+"a."+Constants.TOTALKEWAJIBAN+","+"a."+Constants.STATUS1+","+"a."+Constants.TANGGALASIGNs+","+"a."+Constants.INSTALLMENTDATE+" " +
                "FROM "+Constants.TB_NAME9+ " a " +" INNER JOIN " + Constants.TB_NAME2 +" b "
                + " ON " +"a."+ Constants.LOAN + " = " +"b."+ Constants.No_Loan +" where SUBSTR("+"b."+Constants.TANGGALASIGN+",1,7)=?  AND "+"a."+Constants.DPD+" > ?"+" AND "+"a."+Constants.STATUS1+" = ?"+" AND "+"b."+Constants.NASABAH_BAYAR +" = ?" ,new String[]{tglsign,"0",String.valueOf( 1 ),"Partial"});
    */
    }

    public Cursor getnp(String tglsign) {
        return db.rawQuery("SELECT " + "a." + Constants.ID + "," + "a." + Constants.CIF + "," + "a." + Constants.ACCTNO + "," + "b." + Constants.No_Loan + "," + "a." + Constants.NAMA + "," + "a." + Constants.PRODID + "," + "a." + Constants.TENOR + "," + "a." + Constants.DUEDATE + "," + "b." + Constants.NOMINAL_BAYAR + "," + "a." +
                Constants.ALAMATKANTOR + "," + "a." + Constants.KOTA + "," + "a." + Constants.POS + "," + "a." + Constants.BISNISAREA + "," + "a." + Constants.RECORDOWNER + "," + "a." + Constants.DPD + "," + "a." + Constants.CYCLE + "," + "a." + Constants.AGENTID + "," + "a." + Constants.PPOKOK + "," + "a." + Constants.TAGIHANPOKOK + "," + "a." + Constants.BUNGA + "," + "a." + Constants.DENDA + "," + "a." + Constants.OS + "," + "a." + Constants.TOTALKEWAJIBAN + "," + "a." + Constants.STATUS1 + "," + "a." + Constants.TANGGALASIGNs + "," + "a." + Constants.INSTALLMENTDATE + " " +
                "FROM " + Constants.TB_NAME9 + " a " + " INNER JOIN " + Constants.TB_NAME2 + " b "
                + " ON " + "a." + Constants.LOAN + " = " + "b." + Constants.No_Loan + " where SUBSTR(" + "a." + Constants.INSTALLMENTDATE + ",1,7) = ? AND SUBSTR(" + "a." + Constants.TANGGALASIGN + ",1,7) = ?  AND a." + Constants.DPD + " > ?" + " AND a." + Constants.STATUS1 + " = ?" + " AND b." + Constants.NOMINAL_BAYAR + " = ?", new String[]{tglsign, tglsign, "0", String.valueOf(1), ""});
    }

    public Cursor getcounterci(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) = ? AND " + Constants.DPD + " <= ?", new String[]{tglsign, tglsign, "0"});
    }

    public Cursor getAllcountlistci() {
        String[] columns = {Constants.ID, Constants.CIF, Constants.ACCTNO, Constants.LOAN, Constants.NAMA, Constants.PRODID, Constants.TENOR, Constants.DUEDATE, Constants.ALAMATRUMAH
                , Constants.ALAMATKANTOR, Constants.KOTA, Constants.POS, Constants.BISNISAREA, Constants.RECORDOWNER, Constants.DPD, Constants.CYCLE, Constants.AGENTID, Constants.PPOKOK, Constants.TAGIHANPOKOK, Constants.BUNGA, Constants.DENDA, Constants.OS, Constants.TOTALKEWAJIBAN, Constants.STATUS1};

        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," + Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH
                + "," + Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," + Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," + Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," + Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + " " +
                "FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) = SUBSTR(" + Constants.TANGGALASIGN + ",1,7) AND " + Constants.DPD + " <= " + 0 + "", null);
    }

    public Cursor getcounterss(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME6 + " a " + " INNER JOIN " + Constants.TB_NAME9 + " b " + " ON " + "a." + Constants.No_Loan + " = " + "b." + Constants.LOAN + " where SUBSTR(" + "b." + Constants.INSTALLMENTDATE + ",1,7) = ? AND SUBSTR(" + "b." + Constants.TANGGALASIGN + ",1,7) = ? AND " + "b." + Constants.STATUS + "= ? AND " + "b." + Constants.DPD + "<= ?", new String[]{tglsign, tglsign, "1", "0"});
    }

    public Cursor getcounterbs(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) = ? AND SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.DPD + " <= ? AND " + Constants.STATUS1 + " = ?", new String[]{tglsign, tglsign, "0", "0"});
    }

    public Cursor getcountercl(String tglsign) {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) < ? AND SUBSTR(" + Constants.TANGGALASIGN + ",1,7) = ? AND " + Constants.DPD + " <= ?", new String[]{tglsign, tglsign, "0"});

    }
//    public  Cursor a (String lat){
//        //String rawQuery=("Select nome, id as _id, " + "( " + latitude + " - lat) * ( " + latitude +"- lat) + ( " + longitude + "- lon) * ( " + longitude + "- lon) * " + fudge + " as distanza "	+ " from cliente "+ " order by distanza asc", null);
//        //return  db.rawQuery( "SELECT *,("+Constants.ROW_LONG+") FROM ",null )
//        //String rawQuery= ("SELECT ",(" + sin_lat_rad + "*" sin_lat_rad" + " + cos_lat_rad + "*"cos_lat_rad"*(" + sin_lon_rad + "*"sin_lon_rad"+" + cos_lon_rad + "*"cos_lon_rad")) AS "distance_acos" FROM parish WHERE ("+sin_lat_rad+" * "sin_lat_rad" +"+ cos_lat_rad +"* "cos_lat_rad" * (+"+sin_lon_rad +"* "sin_lon_rad" + "+cos_lon_rad +"* "cos_lon_rad")) >"+dis+ " ORDER BY "distance_acos" DESC ", null);
//
//    }

    public Cursor getmapslonglat(double latOwn, double longOwn) {
        String rawQuery = "SELECT " + Constants.ROW_BRANCH + ",a." + Constants.CIF + ",a." + Constants.ACCTNO + "," + Constants.ROW_LONG + "," + Constants.ROW_LAT + "," + Constants.STATUS
                + " FROM " + Constants.TB_NAME9 + " a " + " INNER JOIN " + Constants.TB_NAME10 + " b "
                + " ON " + "a." + Constants.CIF + " = " + "b." + Constants.CIF;
        return db.rawQuery(rawQuery, null);
    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public Cursor getmapslonglatWithDistance(double latitude, double longitude) {
        double cos_lat_radOwn = Math.cos(deg2rad(latitude));
        double sin_lat_radOwn = Math.sin(deg2rad(latitude));
        double cos_lon_radOwn = Math.cos(deg2rad(longitude));
        double sin_lon_radOwn = Math.sin(deg2rad(longitude));

        Cursor cursor = db.rawQuery("SELECT " + Constants.ROW_BRANCH + ",a." + Constants.CIF + ",a." + Constants.ACCTNO + "," + Constants.ROW_LONG + "," + Constants.ROW_LAT + "," + Constants.STATUS
                + " ,(" + sin_lat_radOwn + "* sin_lat_rad +" + cos_lat_radOwn + "* cos_lat_rad *("
                + sin_lon_radOwn + " * sin_lon_rad + " + cos_lon_radOwn + " * cos_lon_rad)) AS distance_acos "
                + " FROM " + Constants.TB_NAME9 + " a " + " INNER JOIN " + Constants.TB_NAME10 + " b "
                + " ON " + "a." + Constants.CIF + " = " + "b." + Constants.CIF
                + " ORDER BY distance_acos DESC", null);
        return cursor;
    }

    ///////untuk visit
    public Cursor getAllcountlist2(String tglsign) {
        String[] columns = {Constants.ID, Constants.CIF, Constants.ACCTNO, Constants.LOAN, Constants.NAMA, Constants.PRODID, Constants.TENOR, Constants.DUEDATE, Constants.ALAMATRUMAH
                , Constants.ALAMATKANTOR, Constants.KOTA, Constants.POS, Constants.BISNISAREA, Constants.RECORDOWNER, Constants.DPD, Constants.CYCLE, Constants.AGENTID, Constants.PPOKOK, Constants.TAGIHANPOKOK, Constants.BUNGA, Constants.DENDA, Constants.OS, Constants.TOTALKEWAJIBAN, Constants.STATUS1, Constants.TANGGALASIGNs};

        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," +
                Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," +
                Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," +
                Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," +
                Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + "," + Constants.TANGGALASIGNs + " FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) = ? AND " + Constants.DPD + " > ? AND " + Constants.STATUS1 + " = ?", new String[]{tglsign, "0", "0"});
        //return db.query(Constants.TB_NAME9,columns,Constants.DPD +" > ? AND "+ Constants.STATUS1+" = ? ",new String[]{"0","0"},null,null,null,null);
    }

    public Cursor getfp(String full, String tglsign) {
        return db.rawQuery("SELECT " + Constants.ROW_ID2 + "," + Constants.NAME2 + "," + Constants.No_Loan2 + "," + Constants.Cycle2 + "," +
                Constants.Os2 + "," + Constants.Total_Kewajiban2 + "," + Constants.Alamat2 + "," + Constants.NASABAH_BAYAR + "," +
                Constants.BERTEMU + "," + Constants.NOMINAL_BAYAR + "," + Constants.LOKASIBERTEMU + "," + Constants.KARAKTERNASABAH + "," +
                Constants.RESUME + "," + Constants.BUKTIBAYAR + "," + Constants.IMAGE + "," + Constants.TANGGALASIGN + "," + Constants.TANGGALPROSES + "," + Constants.STATUS +
                " FROM " + Constants.TB_NAME2 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.NASABAH_BAYAR + " = ? ", new String[]{tglsign, full});

        /*return db.rawQuery("SELECT "+"a."+Constants.ID+","+"a."+Constants.CIF+","+"a."+Constants.ACCTNO+","+"b."+Constants.No_Loan+","+"a."+Constants.NAMA+","+"a."+Constants.PRODID+","+"a."+Constants.TENOR+","+"a."+Constants.DUEDATE+","+"b."+Constants.NOMINAL_BAYAR+","+"a."+
                Constants.ALAMATKANTOR+","+"a."+Constants.KOTA+","+"a."+Constants.POS+","+"a."+Constants.BISNISAREA+","+"a."+Constants.RECORDOWNER+","+"a."+Constants.DPD+","+"a."+Constants.CYCLE+","+"a."+Constants.AGENTID+","+"a."+Constants.PPOKOK+","+"a."+Constants.TAGIHANPOKOK+","+"a."+Constants.BUNGA+","+"a."+Constants.DENDA+","+"a."+Constants.OS+","+"a."+Constants.TOTALKEWAJIBAN+","+"a."+Constants.STATUS1+","+"a."+Constants.TANGGALASIGNs+","+"a."+Constants.INSTALLMENTDATE+" " +
                "FROM "+Constants.TB_NAME9+ " a " +" INNER JOIN " + Constants.TB_NAME2 +" b "
                + " ON " +"a."+ Constants.LOAN + " = " +"b."+ Constants.No_Loan +" where "+"a."+Constants.DPD+" > ?"+" AND "+"a."+Constants.STATUS1+" = ?"+" AND "+"b."+Constants.NASABAH_BAYAR +" = ? AND "+"a."+Constants.TANGGALASIGN+"= ?"  ,new String[]{"0","1",full,tglsign});
   */
    }

    //////untuk survey
    public Cursor getAllcountlist3(String tglsign) {
        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + ","
                + Constants.NAMA + "," + Constants.PRODID + "," + Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + ","
                + Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," + Constants.RECORDOWNER + ","
                + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," + Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + ","
                + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," + Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + ","
                + Constants.TANGGALASIGNs + "," + Constants.INSTALLMENTDATE + " FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) = ? AND " + Constants.DPD + " <= ? AND " + Constants.STATUS1 + "=?", new String[]{tglsign, "0", "0"});
        //return db.query(Constants.TB_NAME9,columns,Constants.DPD +" <= ? AND " + Constants.STATUS1 +" = ? " ,new String[]{"0","0"} ,null,null,null,null);
    }

    public Cursor getci(String tglsign, String loan) {
        return db.rawQuery("SELECT " + Constants.STATUS1 + "" +
                " FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) < ? AND " + Constants.LOAN + "=?", new String[]{tglsign, loan});
        //return db.query(Constants.TB_NAME9,columns,Constants.DPD +" <= ? AND " + Constants.STATUS1 +" = ? " ,new String[]{"0","0"} ,null,null,null,null);
    }

    public Cursor getbs(String tglsign) {
        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," + Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," + Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," + Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," + Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + "," + Constants.TANGGALASIGNs + "," + Constants.INSTALLMENTDATE +
                " FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) = ? AND SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.DPD + " <= ? AND " + Constants.STATUS1 + " = ?", new String[]{tglsign, tglsign, "0", "0"});
    }

    public Cursor getcl(String tglsign) {
        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," + Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," + Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," + Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," + Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + "," + Constants.TANGGALASIGNs + "," + Constants.INSTALLMENTDATE +
                " FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) != ? AND SUBSTR(" + Constants.TANGGALASIGN + ",1,7) = ? AND " + Constants.DPD + " <= ? ", new String[]{tglsign, tglsign, "0"});
    }

    public Cursor getss(String tglsign) {
        return db.rawQuery("SELECT " + Constants.ID + "," + Constants.CIF + "," + Constants.ACCTNO + "," + Constants.LOAN + "," + Constants.NAMA + "," + Constants.PRODID + "," + Constants.TENOR + "," + Constants.DUEDATE + "," + Constants.ALAMATRUMAH + "," +
                Constants.ALAMATKANTOR + "," + Constants.KOTA + "," + Constants.POS + "," + Constants.BISNISAREA + "," + Constants.RECORDOWNER + "," + Constants.DPD + "," + Constants.CYCLE + "," + Constants.AGENTID + "," + Constants.PPOKOK + "," + Constants.TAGIHANPOKOK + "," + Constants.BUNGA + "," + Constants.DENDA + "," + Constants.OS + "," + Constants.TOTALKEWAJIBAN + "," + Constants.STATUS1 + "," + Constants.TANGGALASIGNs + "," + Constants.INSTALLMENTDATE +
                " FROM " + Constants.TB_NAME9 + " where SUBSTR(" + Constants.INSTALLMENTDATE + ",1,7) = ? AND  SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.DPD + " <= ? AND " + Constants.STATUS1 + " = ?", new String[]{tglsign, tglsign, "0", "1"});
    }

    ////untuk menampilkan detail
    public Cursor getAllcountlist1(int id) {
        String[] columns = {Constants.ID, Constants.CIF, Constants.ACCTNO, Constants.LOAN, Constants.NAMA, Constants.PRODID, Constants.TENOR
                , Constants.DUEDATE, Constants.ALAMATRUMAH, Constants.ALAMATKANTOR, Constants.KOTA, Constants.POS, Constants.BISNISAREA
                , Constants.RECORDOWNER, Constants.DPD, Constants.CYCLE, Constants.AGENTID, Constants.PPOKOK, Constants.TAGIHANPOKOK
                , Constants.BUNGA, Constants.DENDA, Constants.OS, Constants.TOTALKEWAJIBAN, Constants.STATUS1};

        return db.query(Constants.TB_NAME9, columns, Constants.ID + " =?", new String[]{String.valueOf(id)}, null, null, null, null);
    }

    public Cursor getAllcountlist1(String loan) {
        String[] columns = {Constants.ID, Constants.CIF, Constants.ACCTNO, Constants.LOAN, Constants.NAMA, Constants.PRODID, Constants.TENOR
                , Constants.DUEDATE, Constants.ALAMATRUMAH, Constants.ALAMATKANTOR, Constants.KOTA, Constants.POS, Constants.BISNISAREA
                , Constants.RECORDOWNER, Constants.DPD, Constants.CYCLE, Constants.AGENTID, Constants.PPOKOK, Constants.TAGIHANPOKOK
                , Constants.BUNGA, Constants.DENDA, Constants.OS, Constants.TOTALKEWAJIBAN, Constants.STATUS1};

        return db.query(Constants.TB_NAME9, columns, Constants.LOAN + " =?", new String[]{loan}, null, null, null, null);
    }

    public Cursor getAllinventorysurvey() {
        String[] columns = {Constants.ROW_IDIV, Constants.NAMEIV, Constants.No_LoanIV, Constants.CycleIV, Constants.OsIV, Constants.TanggalIV, Constants.AlamatIV, Constants.TANGGALASIGNs};

        return db.query(Constants.TB_NAME5, columns, null, null, null, null, null);
    }

    //hasil inventory survey
    public Cursor getAllhasilinventorysurvey() {
        String[] columns = {Constants.ROW_IDIVH, Constants.NAMEIVH, Constants.No_LoanIVH, Constants.CycleIVH, Constants.OsIVH
                , Constants.TanggalIVH, Constants.AlamatIVH, Constants.BERTEMUDGN, Constants.KEBERADAAN_ASET, Constants.PKERJAAN_NASABAH
                , Constants.TANGGALGAJIAN, Constants.POSISI_JENISUSAHA, Constants.KONDISIJAMINAN, Constants.DOCUMENT1, Constants.DOCUMENT2
                , Constants.DOCUMENT3, Constants.DOCUMENT4, Constants.TANGGALASIGNhs, Constants.TANGGALPROSEShs, Constants.STATUShs, Constants.IMAGE};

        return db.query(Constants.TB_NAME6, columns, Constants.STATUShs + "=?", new String[]{"0"}, null, null, null);
    }

    public Cursor getAllhasilinventorysurveyrecord() {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME6 + " where " + Constants.STATUShs + " = ? ", new String[]{"0"});

        //return db.query(Constants.TB_NAME6,columns,Constants.STATUShs + "=?",new String[]{"0"},null,null,null);
    }

    public Cursor getAllhasilinventorysurveyss(String tglsign, String loan) {
        /*String[] columns={Constants.ROW_IDIVH,Constants.NAMEIVH,Constants.No_LoanIVH,Constants.CycleIVH,Constants.OsIVH
                ,Constants.TanggalIVH,Constants.AlamatIVH,Constants.BERTEMUDGN,Constants.KEBERADAAN_ASET,Constants.PKERJAAN_NASABAH
                ,Constants.TANGGALGAJIAN,Constants.POSISI_JENISUSAHA,Constants.KONDISIJAMINAN,Constants.DOCUMENT1,Constants.DOCUMENT2
                ,Constants.DOCUMENT3,Constants.DOCUMENT4,Constants.TANGGALASIGNhs,Constants.TANGGALPROSEShs,Constants.STATUShs,Constants.IMAGE};
*/
        //return db.query(Constants.TB_NAME6,columns,Constants.No_LoanIVH+ "=?",new String[]{loan},null,null,null);
        return db.rawQuery(" SELECT " + Constants.ROW_IDIVH + "," + Constants.NAMEIVH + "," + Constants.No_LoanIVH + "," + Constants.CycleIVH + "," + Constants.OsIVH + "," +
                Constants.TanggalIVH + "," + Constants.AlamatIVH + "," + Constants.BERTEMUDGN + "," + Constants.KEBERADAAN_ASET + "," + Constants.PKERJAAN_NASABAH + "," +
                Constants.TANGGALGAJIAN + "," + Constants.POSISI_JENISUSAHA + "," + Constants.KONDISIJAMINAN + "," + Constants.DOCUMENT1 + "," + Constants.DOCUMENT2 + "," +
                Constants.DOCUMENT3 + "," + Constants.DOCUMENT4 + "," + Constants.TANGGALASIGNhs + "," + Constants.TANGGALPROSEShs + "," + Constants.STATUShs + "," + Constants.IMAGE + " FROM " + Constants.TB_NAME6 + " where SUBSTR(" + Constants.TANGGALASIGNhs + ",1,7) =? AND " + Constants.No_LoanIVH + " =?", new String[]{tglsign, loan});
    }

    public Cursor getAllhasilinventoryvisit() {

        String[] columns = {Constants.ROW_ID2, Constants.NAME2, Constants.No_Loan2, Constants.Cycle2, Constants.Os2
                , Constants.Total_Kewajiban2, Constants.Alamat2, Constants.NASABAH_BAYAR, Constants.BERTEMU, Constants.NOMINAL_BAYAR
                , Constants.LOKASIBERTEMU, Constants.KARAKTERNASABAH, Constants.RESUME, Constants.BUKTIBAYAR, Constants.IMAGE
                , Constants.TANGGALASIGN, Constants.TANGGALPROSES, Constants.STATUS};

        return db.query(Constants.TB_NAME2, columns, Constants.STATUS + "=?", new String[]{"0"}, null, null, null);
    }

    public Cursor getAllhasilinventoryvisitrecord() {
        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME2 + " where " + Constants.STATUS + " = ? ", new String[]{"0"});

        //return db.query(Constants.TB_NAME2,columns,Constants.STATUS + "=?",new String[]{"0"},null,null,null);
    }

    public Cursor getAllhasilinventoryvisit2(String tglsign) {

        String[] columns = {Constants.ROW_ID2, Constants.NAME2, Constants.No_Loan2, Constants.Cycle2, Constants.Os2
                , Constants.Total_Kewajiban2, Constants.Alamat2, Constants.NASABAH_BAYAR, Constants.BERTEMU, Constants.NOMINAL_BAYAR
                , Constants.LOKASIBERTEMU, Constants.KARAKTERNASABAH, Constants.RESUME, Constants.BUKTIBAYAR, Constants.IMAGE
                , Constants.TANGGALASIGN, Constants.TANGGALPROSES, Constants.STATUS};

        return db.rawQuery("SELECT " + Constants.ROW_ID2 + "," + Constants.NAME2 + "," + Constants.No_Loan2 + "," + Constants.Cycle2 + "," +
                Constants.Os2 + "," + Constants.Total_Kewajiban2 + "," + Constants.Alamat2 + "," + Constants.NASABAH_BAYAR + "," +
                Constants.BERTEMU + "," + Constants.NOMINAL_BAYAR + "," + Constants.LOKASIBERTEMU + "," + Constants.KARAKTERNASABAH + "," +
                Constants.RESUME + "," + Constants.BUKTIBAYAR + "," + Constants.IMAGE + "," + Constants.TANGGALASIGN + "," + Constants.TANGGALPROSES + "," + Constants.STATUS +
                " FROM " + Constants.TB_NAME2 + " where SUBSTR(" + Constants.TANGGALASIGN + ",1,7) =? AND " + Constants.NOMINAL_BAYAR + " != ? AND " + Constants.STATUSSATTLEMENT + " = ?", new String[]{tglsign, "", "0"});
        //return db.query(Constants.TB_NAME2,columns,Constants.NOMINAL_BAYAR + " != ? AND " +Constants.STATUSSATTLEMENT+"= ?",new String[]{"",String.valueOf( 0 )},null,null,null);
    }

    public Cursor getAllhasilinventoryvisitpayment(String loan, String os) {

        String[] columns = {Constants.ROW_ID2, Constants.NAME2, Constants.No_Loan2, Constants.Cycle2, Constants.Os2
                , Constants.Total_Kewajiban2, Constants.Alamat2, Constants.NASABAH_BAYAR, Constants.BERTEMU, Constants.NOMINAL_BAYAR
                , Constants.LOKASIBERTEMU, Constants.KARAKTERNASABAH, Constants.RESUME, Constants.BUKTIBAYAR, Constants.IMAGE
                , Constants.TANGGALASIGN, Constants.TANGGALPROSES, Constants.STATUS};

        return db.query(Constants.TB_NAME2, columns, Constants.No_Loan2 + "= ? AND " + Constants.OS + " = ? ", new String[]{loan, os}, null, null, null);
    }

    public Cursor getUser() {
        String[] columns = {Constants.ROW_NAMAUSR, Constants.ROW_AREA, Constants.ROW_PASSUSR, Constants.ROW_STATUSUSR, Constants.ROW_IDUSUSRSRV};

        return db.query(Constants.TB_NAME3, columns, Constants.ROW_STATUSUSR + " = ?", new String[]{"1"}, null, null, null);
    }

    public Cursor getnetwork() {
        String[] columns = {Constants.ipaddress, Constants.port};

        return db.query(Constants.TB_NAME7, columns, null, null, null, null, null);
    }

    public Cursor getstatussattelemnt(String tglsign1, String loan, String os) {
        return db.rawQuery("SELECT " + Constants.STATUSSATTLEMENT + " FROM " + Constants.TB_NAME2 + " WHERE SUBSTR(" + Constants.TANGGALASIGNs + ",1,7) =? AND " + Constants.No_Loan + "=? AND " + Constants.OS + "=?", new String[]{tglsign1, loan, os});
    }

    public Cursor gettglsign() {
        /*String[] columns={Constants.PERFORMANCE};*/

        //return db.query(Constants.TB_NAME11,columns,null,null,null,null,null);
        return db.rawQuery("SELECT SUBSTR(" + Constants.PERFORMANCE + ",1,7) FROM " + Constants.TB_NAME11 + "", null);
    }

    public Cursor gettimer() {
        String[] columns = {Constants.SISid, Constants.STR, Constants.NUM};

        return db.query(Constants.TB_NAME8, columns, null, null, null, null, null);
    }

    public Cursor getlocation() {
        String[] columns = {Constants.ROW_USER, Constants.ROW_LAT, Constants.ROW_LONG, Constants.ROW_DATETIME, Constants.ROW_ID_USER, Constants.ROW_IDLOC};

        return db.query(Constants.TB_NAME4, columns, Constants.ROW_STATUS + " = ?", new String[]{"BELUM TERKIRIM"}, null, null, null);
    }

    public Cursor getlist(String code,String type){

        return db.rawQuery("SELECT COUNT(*) FROM " + Constants.TB_NAME14 + " where "+ Constants.code + " = ? AND " + Constants.type + "=?", new String[]{code, type});

    }
    public Cursor getlistap(String type, String status){
        String[] columns={Constants.deskription};

        return db.query(Constants.TB_NAME14, columns, Constants.STATUS + " = ? AND " + Constants.type + " = ? ", new String[]{status,type}, null, null, null);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //UPDATE
    public long UPDATE(int id, String name, String loan, String cycle, String os, String total, String alamat, String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.NAME, name);
            cv.put(Constants.No_Loan, loan);
            cv.put(Constants.Cycle, cycle);
            cv.put(Constants.Os, os);
            cv.put(Constants.Total_Kewajiban, total);
            cv.put(Constants.Alamat, alamat);

            return db.update(Constants.TB_NAME, cv, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public long updatecontact(String c1, String c2, String c3, String c4, String c5, String c6, String c7, String cif) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.contact1, c1);
            cv.put(Constants.contact2, c2);
            cv.put(Constants.contact3, c3);
            cv.put(Constants.contact4, c4);
            cv.put(Constants.contact5, c5);
            cv.put(Constants.contact6, c6);
            cv.put(Constants.contact7, c7);

            return db.update(Constants.TB_NAME15, cv, Constants.ROW_ID + " =?", new String[]{String.valueOf(cif)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long UPDATEcollectionlist(int id, String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.STATUS1, status);

            return db.update(Constants.TB_NAME9, cv, Constants.ID + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public long UPDATEperformance(String month) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.PERFORMANCE, month);

            return db.update(Constants.TB_NAME11, cv, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public long UPDATEhasilinventorysurvey(int id, String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.STATUS1, status);

            db.update(Constants.TB_NAME6, cv, Constants.ID + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }


    public long updatelist(String desc,String code) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.deskription, desc);
            db.update(Constants.TB_NAME14,cv,Constants.code + "=?",new String[]{code});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public long UPDATEhasilinventoryvisit(int id, String status) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.STATUS, status);

            db.update(Constants.TB_NAME2, cv, Constants.ID + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public long updatesattlement(String status, String loan, String os) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.STATUSSATTLEMENT, status);

            db.update(Constants.TB_NAME2, cv, Constants.No_Loan2 + "=? AND " + Constants.Os2 + "=?", new String[]{loan, os});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DELETE
    public long Delete(int id) {
        try {

            return db.delete(Constants.TB_NAME, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long Deleteinventorysurvey(int id) {
        try {

            return db.delete(Constants.TB_NAME5, Constants.ROW_IDIV + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long Deleteuser() {
        try {

            return db.delete(Constants.TB_NAME3, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long deletnetwork() {
        try {

            return db.delete(Constants.TB_NAME7, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long deletperformance() {
        try {

            return db.delete(Constants.TB_NAME11, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long DeleteLocation(int id) {
        try {
            return db.delete(Constants.TB_NAME4, Constants.ROW_IDLOC + " =?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
