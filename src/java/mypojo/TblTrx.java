package mypojo;
// Generated Sep 24, 2021 7:49:16 AM by Hibernate Tools 4.3.1

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import mydao.DAOBuku;
import mydao.DAOTransaksi;
import mydao.DAOTransaksiDetail;

/**
 * TblTrx generated by hbm2java
 */
@ManagedBean
public class TblTrx implements java.io.Serializable {

    private String idTrx;
    private Date tanggal;
    private String pelangganId;

    private List listBuku = new ArrayList();
    private String strBuku;
    private Map<String, List> BukuQty;

    private Boolean error = false;
    private String errorMsg = "";

    private List<Map<String, Object>> listBuku2;
    private List stokBuku2;
    private List lstIdTrx;
    
    private Map<String, Object> trxNow;
    public TblTrx() {
//        List kosong = new ArrayList();
//        BukuQty.put("buku", kosong);
//        BukuQty.put("stok", kosong);
    }

    public TblTrx(String idTrx, Date tanggal, String pelangganId) {
        this.idTrx = idTrx;
        this.tanggal = tanggal;
        this.pelangganId = pelangganId;
    }

    public Map<String, Object> getTrxNow() {
        return trxNow;
    }

    public void setTrxNow(Map<String, Object> trxNow) {
        this.trxNow = trxNow;
    }

    public List getLstIdTrx() {
        return lstIdTrx;
    }

    public void setLstIdTrx(List lstIdTrx) {
        this.lstIdTrx = lstIdTrx;
    }

    public List getListBuku2() {
        return listBuku2;
    }

    public void setListBuku2(List listBuku2) {
        this.listBuku2 = listBuku2;
    }

    public List getStokBuku2() {
        return stokBuku2;
    }

    public void setStokBuku2(List stokBuku2) {
        this.stokBuku2 = stokBuku2;
    }

    public Map<String, List> getBukuQty() {
        return BukuQty;
    }

    public void setBukuQty(Map<String, List> BukuQty) {
        this.BukuQty = BukuQty;
    }

    public String getStrBuku() {
        return strBuku;
    }

    public void setStrBuku(String strBuku) {
        this.strBuku = strBuku;
    }

    public List getListBuku() {
        return listBuku;
    }

    public void setListBuku(List listBuku) {
        this.listBuku = listBuku;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getIdTrx() {
        return this.idTrx;
    }

    public void setIdTrx(String idTrx) {
        this.idTrx = idTrx;
    }

    public Date getTanggal() {
        return this.tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getPelangganId() {
        return this.pelangganId;
    }

    public void setPelangganId(String pelangganId) {
        this.pelangganId = pelangganId;
    }

    public String addData() {

//        System.out.println(listBuku2);
        DAOTransaksi dao = new DAOTransaksi();
        DAOTransaksiDetail dao2 = new DAOTransaksiDetail();
        List isi = dao.getById(idTrx);
        if (isi.isEmpty()) {
            this.tanggal = new Date();
            dao.add(this);
            listBuku2.forEach((s) -> {

                if (!s.get("get").equals("0")) {
                    System.out.println(s);
                    TblDetTrx tblDet = new TblDetTrx();
                    TblDetTrxId tblIdDet = new TblDetTrxId();

                    Integer qtynya = Integer.parseInt(s.get("get").toString());
                    Double harga = Double.parseDouble(s.get("harga").toString());
                    Double total = harga * qtynya;

                    tblIdDet.setTrxId(idTrx);
                    tblIdDet.setBukuId(s.get("id_buku").toString());

                    tblDet.setId(tblIdDet);
                    tblDet.setTrxId(idTrx);
                    tblDet.setHarga(total);
                    tblDet.setQty(qtynya);

                    dao2.add(tblDet);
                }
//                if(s.get("get")){
            });

            idTrx = "";
            tanggal = new Date();
            pelangganId = "";
            return "/admin/transaksi?faces-redirect=true";
        }
        error = true;
        errorMsg = "ID Has been Taken.!";
        return "";
    }

    public String updData() {
        DAOTransaksi dao = new DAOTransaksi();
        DAOTransaksiDetail dao2 = new DAOTransaksiDetail();
        List isi = dao.getById(idTrx);
        this.tanggal = new Date();
        dao.upd(this);
        listBuku2.forEach((s) -> {
            TblDetTrxId tmpTrxDetId = new TblDetTrxId();
            tmpTrxDetId.setTrxId(idTrx);
            tmpTrxDetId.setBukuId(s.get("id_buku").toString());
            
            dao2.del(tmpTrxDetId);
            System.out.print(s);
            System.out.println(" <-- isinya");
            if (!s.get("get").equals("0")) {
                System.out.println(s);
                TblDetTrx tblDet = new TblDetTrx();
                TblDetTrxId tblIdDet = new TblDetTrxId();

                Integer qtynya = Integer.parseInt(s.get("get").toString());
                Double harga = Double.parseDouble(s.get("harga").toString());
                Double total = harga * qtynya;

                tblIdDet.setTrxId(idTrx);
                tblIdDet.setBukuId(s.get("id_buku").toString());

                tblDet.setId(tblIdDet);
                tblDet.setTrxId(idTrx);
                tblDet.setHarga(total);
                tblDet.setQty(qtynya);

                dao2.add(tblDet);
            }
        });

        idTrx = "";
        tanggal = new Date();
        pelangganId = "";
        return "/admin/transaksi?faces-redirect=true";
    }

    public String delData(Map<String, Object> idnya) {
        DAOTransaksi dao = new DAOTransaksi();
        DAOTransaksiDetail dao2 = new DAOTransaksiDetail();
        String idtrxnya = idnya.get("id_trx").toString();
//        System.out.println(idnya.get("id_trx").toString());
//        System.out.println(" <-- apa");
//        return "";
//        dao.del(idnya.get("id_trx").toString());
//        List<Map<String, Object>> dtnow = getDataBukuEdit(idnya.get("id_trx").toString());
        List<Map<String, Object>> dtnow = dao2.getById(idtrxnya);
        System.out.print(idnya);
        System.out.println(" <-- isinya");
        System.out.print(dtnow);
        System.out.println(" <-- isinya2");
        dtnow.forEach((s) -> {
            TblDetTrxId tmpTrxDetId = new TblDetTrxId();
            System.out.print(s);
            System.out.println(" <-- sini...");
            tmpTrxDetId.setBukuId(strBuku);
            tmpTrxDetId.setTrxId(idtrxnya);
            tmpTrxDetId.setBukuId(s.get("buku_id").toString());
            
            dao2.del(tmpTrxDetId);
        });
        dao.del(idtrxnya);
        return "/admin/transaksi?faces-redirect=true";
    }

    public List<Map<String, Object>> getAllData() {
        DAOTransaksi dao = new DAOTransaksi();
        dao.getAll().forEach((v) -> {
            System.out.println(v);
//            lstIdTrx.add(v.get("id_trx"));
        });
        System.out.println(lstIdTrx);
        return dao.getAll();
    }
    public List<Map<String, Object>> getAllDataDet(Map<String, Object> idnya) {
        DAOTransaksiDetail dao = new DAOTransaksiDetail();
        return dao.getById(idnya.get("id_trx").toString());
    }

    public String showEdit(String idnya) {
        DAOTransaksi dao = new DAOTransaksi();
        List<Map<String, Object>> isi = dao.getById(idnya);
        idTrx = isi.get(0).get("id_trx").toString();
        tanggal = new Date(isi.get(0).get("tanggal").toString());
        pelangganId = isi.get(0).get("pelanggan_id").toString();
        return "transaksi-edit.xhtml";
    }

    public String showEdit2(Map<String, Object> idnya) {
        DAOTransaksi dao = new DAOTransaksi();
        List<Map<String, Object>> isi = dao.getById(idnya.get("id_trx").toString());
        System.out.print(isi);
        System.out.println(" <-- ABIS DAO");
        idTrx = isi.get(0).get("id_trx").toString();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date tmpDate = sdf.parse(isi.get(0).get("tanggal").toString());
        try {
            date = sdf.parse(isi.get(0).get("tanggal").toString());
        } catch (Exception e) {
            System.out.print(e);
            System.out.println(" <-- Error TGL");
        }
        tanggal = date;
        pelangganId = isi.get(0).get("pelanggan_id").toString();
        return "transaksi-edit.xhtml";
//        System.out.print(idnya.get("id_trx"));
//        System.out.println(" <-- INI IDNYA");
//        return "";
    }
    public String showDetail(Map<String, Object> idnya) {
        DAOTransaksi dao = new DAOTransaksi();
        List<Map<String, Object>> isi = dao.getById(idnya.get("id_trx").toString());
        trxNow = isi.get(0);
        return "transaksi-detail.xhtml";
    }

    public void cobaPrint() {
//              System.out.println("Printing selected options :");
//              if(listBuku != null) {
//                  System.out.println(listBuku);
//                  listBuku.forEach((s) -> {
//                      System.out.println("Entity with ID : " + s + " is selected");
//                  });
//              }
//            listBuku2.forEach((s) -> {
//                System.out.println("Entity with ID : " + s + " is selected");
//            });
    }

    public List<Map<String, Object>> getDataBuku() {
        DAOBuku dao = new DAOBuku();
        List<Map<String, Object>> lst = new ArrayList();
        List tmpStok = new ArrayList();

        dao.getAll().forEach((s) -> {
            s.put("qtynya", 0);
            lst.add(s);
        });
        listBuku2 = lst;
        stokBuku2 = tmpStok;
        return lst;
    }

    public List<Map<String, Object>> getDataBukuEdit(String idnya) {
        DAOBuku dao = new DAOBuku();
        List<Map<String, Object>> lst = new ArrayList();
        List tmpStok = new ArrayList();

        List<Map<String, Object>> tmpData = dao.getAll();
        dao.getAll().forEach((s) -> {
            DAOTransaksiDetail daoTrx = new DAOTransaksiDetail();
            List<Map<String, Object>> isi = daoTrx.getById(idnya);
            s.put("qtynya", 0);
            isi.forEach((v) -> {
                if (s.get("id_buku").equals(v.get("buku_id")) && !v.get("qty").equals("0")) {
                    System.out.println("MASUK GK??");
                    System.out.println(v);
                    s.put("qtynya", v.get("qty"));
                }
            });
            lst.add(s);
        });
        listBuku2 = lst;
        stokBuku2 = tmpStok;
        return lst;
    }

}