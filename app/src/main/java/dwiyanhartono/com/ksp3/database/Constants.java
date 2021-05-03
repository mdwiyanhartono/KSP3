package dwiyanhartono.com.ksp3.database;

public class Constants {
    //data user TBUSER
    static final String ROW_IDUSR="id";
    static final String ROW_NAMAUSR="username";
    static final String ROW_AREA="area";
    static final String ROW_PASSUSR="pass";
    static final String ROW_STATUSUSR="status";
    static final String ROW_IDUSUSRSRV="iduser_server";
    //version6
    static final String ROW_BRANCH="id_branch";


    ////////////////////////////////////////////////////////////////////////////////
    //data location TBLOCATION
    static final String ROW_IDLOC="id";
    static final String ROW_USER="user";
    static final String ROW_LAT="lt";
    static final String ROW_LONG="lng";
    static final String ROW_STATUS="status";
    static final String ROW_DATETIME="datetime";
    static final String ROW_ID_USER="id_user";
    static final String ROW_COS_LAT_RAD= "cos_lat_rad";
    static final String ROW_SIN_LAT_RAD= "sin_lat_rad";
    static final String ROW_COS_LON_RAD= "cos_lon_rad";
    static final String ROW_SIN_LON_RAD= "sin_lon_rad";

    ////////////////////////////////////////////////////////////////////////////////
    //inventory collect TBINVENTORYCOLLECTION
    static final String ROW_ID="id";
    static final String NAME = "name";
    static final String No_Loan = "No_Loan ";
    static final String Cycle = "Cycle";
    static final String Os = "Os";
    static final String Total_Kewajiban = "Total_Kewajiban";
    static final String Alamat = "Alamat";


    //upgrade version 4
    static final String TANGGALASIGNi = "tanggalasign";
    static final String TANGGALPROSESi = "tanggalproses";
    static final String STATUSi = "status";


    ////////////////////////////////////////////////////////////////////////////////
    //hasil inventory collect TBHASILINVENTORYCOLLECT
    static final String ROW_ID2="id";
    static final String NAME2 = "name";
    static final String No_Loan2 = "No_Loan ";
    static final String Cycle2 = "Cycle";
    static final String Os2 = "Os";
    static final String Total_Kewajiban2 = "Total_Kewajiban";
    static final String Alamat2 = "Alamat";
    static final String anggota_BAYAR = "anggotabayar";
    static final String BERTEMU = "bertemu ";
    static final String NOMINAL_BAYAR = "nominal_bayar";
    static final String LOKASIBERTEMU = "lokasibertemmu";
    static final String KARAKTERanggota = "karakteranggota";
    static final String RESUME = "resume";
    static final String BUKTIBAYAR = "buktibayar";
    static final String IMAGE = "photo";
    static final String FULLPART = "full_part";

    static final String STATUSSATTLEMENT = "statussattlement";
    //upgrade version 5
    static final String TANGGALASIGN = "tanggalasign";
    static final String TANGGALPROSES = "tanggalproses";
    static final String STATUS = "status";


    ////////////////////////////////////////////////////////////////////////////////
    //inventory survey TBINVENTORYSURVEY
    static final String ROW_IDIV="id";
    static final String NAMEIV = "name";
    static final String No_LoanIV = "No_Loan ";
    static final String CycleIV = "Cycle";
    static final String OsIV = "Os";
    static final String TanggalIV = "Tanggal";
    static final String AlamatIV = "Alamat";


    //upgrade version 5
    static final String TANGGALASIGNs = "tanggalasign";
    static final String TANGGALPROSESs = "tanggalproses";
    static final String PERFORMANCE = "Performance";


    ////////////////////////////////////////////////////////////////////////////////
    //inventory survey TBHASILINVENTORYSURVEY
    static final String ROW_IDIVH="id";
    static final String NAMEIVH = "name";
    static final String No_LoanIVH = "No_Loan ";
    static final String CycleIVH = "Cycle";
    static final String OsIVH = "Os";
    static final String TanggalIVH = "Tanggal";
    static final String AlamatIVH = "Alamat";

    static final String BERTEMUDGN = "bertemu_dengan ";
    static final String KEBERADAAN_ASET = "keberadaan_aset";
    static final String PKERJAAN_anggota = "pekerjaan_anggota";
    static final String TANGGALGAJIAN = "tanggal_gajian";
    static final String POSISI_JENISUSAHA = "posisi_jenisusaha";
    static final String KONDISIJAMINAN = "kondisi_jaminan";
    static final String DOCUMENT1 = "document1";
    static final String DOCUMENT2 = "document2";
    static final String DOCUMENT3 = "document3";
    static final String DOCUMENT4 = "document4";


    //upgrade version 5
    static final String TANGGALASIGNhs = "tanggalasign";
    static final String TANGGALPROSEShs = "tanggalproses";
    static final String STATUShs = "status";



    ////////////////////////////////////////////////////////////////////////////////
    //tb NETWORK
    static final String id = "id";
    static final String ipaddress = "ipaddress";
    static final String port = "port";


    /////////////////////////////////////////////////////////////////////////////////
    //tb TIMER
    static final String SISid = "sis_id";
    static final String STR = "str_value";
    static final String NUM = "num_value";



    ////////////////////////////////////////////////////////////////////////////////
    //tb collection list
    static final String ID = "id";
    static final String CIF = "cif";
    static final String ACCTNO = "acctno";
    static final String LOAN = "loan";
    static final String NAMA = "nama";
    static final String PRODID = "produkid";
    static final String TENOR = "tenor";
    static final String DUEDATE = "duedate";
    static final String ALAMATRUMAH = "alamatrumah";
    static final String ALAMATKANTOR = "alamatkantor";
    static final String KOTA = "kota";
    static final String POS = "pos";
    static final String BISNISAREA = "bisnisarea";
    static final String RECORDOWNER = "recordowner";
    static final String DPD = "dpd";
    static final String CYCLE = "cycle";
    static final String AGENTID = "agantid";
    static final String PPOKOK = "pinjaman_pokok";
    static final String TAGIHANPOKOK = "tagihanpokok";
    static final String BUNGA = "bunga";
    static final String DENDA = "denda";
    static final String OS = "os";
    static final String TOTALKEWAJIBAN = "totalkewajiban";
    static final String STATUS1 = "status";
    static final String INSTALLMENTDATE = "installmentdate";

////////////////////////////////////////////////////////////////////////
    ////tbl haasilkunjungan
    static final String tujuan = "tujuan";
    static final String nama = "nama";
    static final String cif = "cif";
    static final String hasilkunjungan = "hasilkunjungan";
    static final String ketkunjungan = "ketkunjungan";
    static final String tanggalptp = "tanggalptp";
    static final String bertemu = "bertemu";
    static final String ketbertemu = "ketbertemu";
    static final String lokasibertemu = "lokasibertemu";
    static final String ketlokasi = "ketlokasi";
    static final String karakter = "karakter";
    static final String ketkarakter = "ketkarakter";
    static final String negatifissue = "negatifissue";
    static final String actionplan = "actionplan";
    static final String resumeanggota = "resumeanggota";
    static final String totaltunggakan = "totaltunggakan";
    static final String totalbayar = "totalbayar";
    static final String perkiraan = "perkiraan";
    static final String tanggalvisit = "tanggalvisit";
    static final String photodoc = "photodoc";
    static final String photojaminan = "photojaminan";


    /////////////////////////////////////////////////////////////////////////
    ///////////list dropdown
    static final String id2 = "id";
    static final String deskription = "deskription";
    static final String type = "type";
    static final String code = "code";

    //////////////////////////////////////////////////////////
    ///////////// detailinventory
    static final String kelamin = "kelamin";
    static final String almtrm = "almtrm";
    static final String almtush = "almtush";
    static final String norek = "norek";
    static final String aoname = "aoname";
    static final String cabang = "cabang";
    static final String actionplanpuk = "actionplanpuk";
    static final String email = "email";
    static final String bucket = "bucket";
    static final String bucketeom = "bucketeom";

    ////////////////////////////////////////////////////
    /////// fasilitas
    static final String fasilitas = "fasilitas";
    static final String plafond = "plafond";
    static final String os = "os";
    static final String booked = "booked";
    static final String expproces = "expproces";
    static final String expdate = "expdate";
    static final String flagprobiz = "flagprobiz";


    ////////////////////////////////////////
    ////////// contact
    static final String contact1 = "contact1";
    static final String contact2= "contact2";
    static final String contact3= "contact3";
    static final String contact4= "contact4";
    static final String contact5= "contact5";
    static final String contact6= "contact6";
    static final String contact7= "contact7";



    ////////////////////////////////////////////////////////////////////////////////
    //DB PROPERTIES
    static final String DB_NAME="DBC24SYSTEM";
    static final String TB_NAME="TBINVENTORYCOLLECT";
    static final String TB_NAME2="TBHASILINVENTORYCOLLECT";
    static final String TB_NAME3="TBUSER";
    static final String TB_NAME4="TBLOCATION";
    static final String TB_NAME5="TBINVENTORYSURVEY";
    static final String TB_NAME6="TBHASILINVENTORYSURVEY";
    static final String TB_NAME7="TBNETWORK";
    static final String TB_NAME8="TBTIMER";
    static final String TB_NAME9="TBCOLLECTIONLIST";
    static final String TB_NAME10="TBLONGLAT";
    static final String TB_NAME11="TBPERFORMANCE";
    static final String TB_NAME12="TBHASILINVENTORYCOLLECTnew";
    static final String TB_NAME13="TBKUNJUNGAN";
    static final String TB_NAME14="TBLISTDROPDOWN";
    static final String TB_NAME15="TBCONTACT";
    static final String TB_NAME16="TBFASILITAS";
    static final String TB_NAME17="TBDETAILINVENTORY";


    static final int DB_VERSION = 3 ;

    //CREATE TABLE
    static final String CREATE_TB="CREATE TABLE TBINVENTORYCOLLECT(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT NOT NULL,"+"No_Loan TEXT NOT NULL , "+"Cycle TEXT NOT NULL, "+" Os TEXT NOT NULL,"+"Total_Kewajiban TEXT NOT NULL" +
            ",Alamat TEXT NOT NULL,tanggalasign TEXT );";

    static final String CREATE_TB2 ="CREATE TABLE TBHASILINVENTORYCOLLECT(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT,"+"No_Loan TEXT,"+"Cycle TEXT,"+" Os TEXT,"+"Total_Kewajiban TEXT,"+"Alamat TEXT ," +
            "anggotabayar TEXT,"+"bertemu TEXT ,"+"nominal_bayar INTEGER,"+"lokasibertemmu TEXT ," +
            "karakteranggota TEXT,"+"resume TEXT,"+"buktibayar TEXT,"+"photo TEXT ,"+"tanggalasign TEXT ," +
            ""+"tanggalproses TEXT ,"+"status TEXT ,"+" statussattlement INTEGER  );";

    static final String CREATE_TB3="CREATE TABLE "+TB_NAME3+" ("+ROW_IDUSR+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ROW_NAMAUSR+" TEXT NOT NULL,"+ROW_AREA+" TEXT NOT NULL,"+ROW_PASSUSR+" TEXT NOT NULL ,"+ROW_STATUSUSR+" TEXT NOT NULL,"+ROW_IDUSUSRSRV+" TEXT NOT NULL,"+ROW_BRANCH+" TEXT NOT NULL);";

    static final String CREATE_TB4="CREATE TABLE TBLOCATION(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"user TEXT NOT NULL,"+"lt TEXT NOT NULL,"+"lng TEXT NOT NULL,"+"status TEXT NOT NULL,"+" datetime TEXT NOT NULL, id_user TEXT NOT NULL);";

    static final String CREATE_TB5="CREATE TABLE TBINVENTORYSURVEY(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT NOT NULL,"+"No_Loan TEXT NOT NULL , "+"Cycle TEXT NOT NULL, "+" Os TEXT NOT NULL,"+"Tanggal TEXT NOT NULL,Alamat TEXT NOT NULL,tanggalasign TEXT );";

    static final String CREATE_TB6="CREATE TABLE TBHASILINVENTORYSURVEY(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT NOT NULL,"+"No_Loan TEXT NOT NULL , "+"Cycle TEXT NOT NULL, "+" Os TEXT NOT NULL,"+"Tanggal TEXT NOT NULL,"+"Alamat TEXT NOT NULL," +
            ""+"bertemu_dengan TEXT NOT NULL,"+"keberadaan_aset TEXT NOT NULL,"+"pekerjaan_anggota TEXT NOT NULL,"+"tanggal_gajian TEXT NOT NULL,"+"posisi_jenisusaha TEXT NOT NULL,"+"kondisi_jaminan TEXT NOT NULL,"+"document1 TEXT NOT NULL,"+"document2 TEXT NOT NULL,"+"document3 TEXT NOT NULL,"+"document4 TEXT NOT NULL," +
            ""+"tanggalasign TEXT ,"+"tanggalproses TEXT ,"+"status TEXT, photo TEXT);";

    static final String CREATE_TB7="CREATE TABLE TBNETWORK(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"ipaddress TEXT NOT NULL,port TEXT);";

    static final String CREATE_TB8="CREATE TABLE "+TB_NAME8+"("+SISid+" TEXT ,"+STR+" TEXT ,"+NUM+" INTEGER);";

    static final String CREATE_TB9="CREATE TABLE "+TB_NAME9+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT " +
            ","+CIF+" TEXT " +
            ","+ACCTNO+" TEXT " +
            ","+LOAN+" TEXT " +
            ","+NAMA+" TEXT " +
            ","+PRODID+" TEXT " +
            ","+TENOR+" TEXT " +
            ","+DUEDATE+" TEXT " +
            ","+INSTALLMENTDATE+" TEXT " +
            ","+ALAMATRUMAH+" TEXT " +
            ","+ALAMATKANTOR+" TEXT " +
            ","+KOTA+" TEXT " +
            ","+POS+" TEXT " +
            ","+BISNISAREA+" TEXT " +
            ","+RECORDOWNER+" TEXT " +
            ","+DPD+" TEXT " +
            ","+CYCLE+" TEXT " +
            ","+AGENTID+" TEXT " +
            ","+PPOKOK+" TEXT " +
            ","+TAGIHANPOKOK+" TEXT " +
            ","+BUNGA+" TEXT " +
            ","+DENDA+" TEXT " +
            ","+OS+" TEXT " +
            ","+TOTALKEWAJIBAN+" TEXT " +
            ","+STATUS1+" INTEGER " +
            ","+TANGGALASIGNs+" TEXT);";

    static final String CREATE_TB11="CREATE TABLE "+TB_NAME11+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT " +
            ","+PERFORMANCE+" TEXT);";
    static final String CREATE_TB10="CREATE TABLE "+TB_NAME10+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT " +
            ","+ROW_BRANCH+" TEXT " +
            ","+CIF+" TEXT " +
            ","+ACCTNO+" TEXT " +
            ","+ROW_LONG+" TEXT " +
            ","+ROW_LAT+" TEXT "+
            ","+ROW_COS_LAT_RAD+" REAL " +
            ","+ROW_SIN_LAT_RAD+" REAL " +
            ","+ROW_COS_LON_RAD+" REAL " +
            ","+ROW_SIN_LON_RAD+" REAL);";

    static final String CREATE_TB12 ="CREATE TABLE TBHASILINVENTORYCOLLECTnew(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"No_Loan TEXT,"+"acctno TEXT,"+" cif TEXT,"+"bertemuanggota TEXT ,"+"bertemudengan TEXT ,"+"anggotabayar TEXT,"+"nominal_bayar INTEGER,"+"lokasibertemmu TEXT ," +
            "karakteranggota TEXT,"+"resume TEXT,"+"buktibayar TEXT,"+"photo TEXT ,"+"tanggalasign TEXT ," +
            ""+"tanggalproses TEXT ,"+"status TEXT );";

    static final String CREATE_TB13="CREATE TABLE "+TB_NAME13+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT " +
            ","+tujuan+" TEXT " +
            ","+nama+" TEXT " +
            ","+cif+" TEXT " +
            ","+hasilkunjungan+" TEXT " +
            ","+ketkunjungan+" TEXT " +
            ","+tanggalptp+" TEXT "+
            ","+bertemu+" TEXT "+
            ","+ketbertemu+" TEXT "+
            ","+lokasibertemu+" TEXT "+
            ","+ketlokasi+" TEXT "+
            ","+karakter+" TEXT "+
            ","+ketkarakter+" TEXT "+
            ","+negatifissue+" TEXT "+
            ","+actionplan+" TEXT "+
            ","+resumeanggota+" TEXT "+
            ","+totaltunggakan+" TEXT "+
            ","+totalbayar+" TEXT "+
            ","+perkiraan+" TEXT "+
            ","+tanggalvisit+" TEXT "+
            ","+photodoc+" TEXT "+
            ","+photojaminan+" TEXT "+
            ","+STATUS+" TEXT);";


    static final String CREATE_TB14="CREATE TABLE "+TB_NAME14+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT " +
            ","+deskription+" TEXT " +
            ","+type+" TEXT " +
            ","+code+" TEXT " +
            ","+STATUS+" TEXT );";

    static final String CREATE_TB15="CREATE TABLE "+TB_NAME15+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT " +
            ","+cif+" TEXT " +
            ","+contact1+" TEXT " +
            ","+contact2+" TEXT " +
            ","+contact3+" TEXT " +
            ","+contact4+" TEXT " +
            ","+contact5+" TEXT " +
            ","+contact6+" TEXT " +
            ","+contact7+" TEXT );";

    static final String CREATE_TB16="CREATE TABLE "+TB_NAME16+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT " +
            ","+cif+" TEXT " +
            ","+fasilitas+" TEXT " +
            ","+plafond+" TEXT " +
            ","+os +" TEXT " +
            ","+booked+" TEXT " +
            ","+expproces+" TEXT " +
            ","+expdate+" TEXT " +
            ","+flagprobiz+" TEXT );";

    static final String CREATE_TB17="CREATE TABLE "+TB_NAME17+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT " +
            ","+STATUS+" INTEGER " +
            ","+cif+" TEXT " +
            ","+nama+" TEXT " +
            ","+kelamin+" TEXT " +
            ","+almtrm +" TEXT " +
            ","+almtush+" TEXT " +
            ","+norek+" TEXT " +
            ","+aoname+" TEXT " +
            ","+cabang+" TEXT " +
            ","+actionplanpuk+" TEXT " +
            ","+AGENTID+" TEXT " +
            ","+OS+" TEXT " +
            ","+totaltunggakan+" TEXT " +
            ","+DPD+" TEXT " +
            ","+bucket+" TEXT " +
            ","+bucketeom+" TEXT " +
            ","+LOAN+" TEXT " +
            ","+email+" TEXT );";

}
