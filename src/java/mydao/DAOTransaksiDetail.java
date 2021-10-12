/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mypojo.HibUtil;
import mypojo.TblDetTrx;
import mypojo.TblDetTrxId;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AIRGG
 */
public class DAOTransaksiDetail {
    private final static String namaTbl = "TblDetTrx";
    private final static String idTbl = "trx_id";
    
    public void add(TblDetTrx tblnya) {
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            sess.save(tblnya);
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene(Add): "+e);
        }
    }
    public void upd(TblDetTrx tblnya) {
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            sess.update(tblnya);
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene(Upd): "+e);
        }
    }
    public void del(TblDetTrxId idnya) {
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
//            TblDetTrx datanow = (TblDetTrx) sess.load(TblDetTrx.class, new String(idnya));
//            sess.delete(datanow);
//            String sql = "DELETE FROM tbl_det_trx WHERE 1=1 ";
//            
//            if(idnya != null) sql += " and "+ idTbl +"=:vId";
//            Query q = sess.createSQLQuery(sql);
//            if(idnya != null) q.setString("vId", idnya);

            Query query = sess.createQuery("delete from TblDetTrx where id = :prm ");
            query.setParameter("prm", idnya);
            query.executeUpdate();
            
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene(Del): "+e);
        }
    }
    public List<Map<String, Object>> getById(String idnya) {
        List<Map<String, Object>> rtr = new ArrayList<>();
        List lst = new ArrayList();
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            
            String sql = "SELECT "
                    + "a.*, b.judul, c.tanggal, d.nama nama_penulis, e.nama nama_penerbit, f.nama nama_genre, f.keterangan keterangan, g.nama nama_pelanggan "
                    + "FROM tbl_det_trx a "
                    + "INNER JOIN tbl_buku b ON a.buku_id=b.id_buku "
                    + "INNER JOIN tbl_trx c ON a.trx_id=c.id_trx "
                    + "INNER JOIN tbl_penulis d ON b.penulis_id=d.id_penulis "
                    + "INNER JOIN tbl_penerbit e ON b.penerbit_id=e.id_penerbit "
                    + "INNER JOIN tbl_genre f ON b.genre_id=f.id_genre "
                    + "INNER JOIN tbl_pelanggan g ON c.pelanggan_id=g.id_pelanggan"
                    + " WHERE 1=1 ";
            
            if(idnya != null) sql += " and "+ idTbl +"=:vId";
            Query q = sess.createSQLQuery(sql);
            if(idnya != null) q.setString("vId", idnya);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            
            // https://www.tutorialspoint.com/hibernate/hibernate_native_sql.htm
//            lst = q.list();
            for(Object object : q.list()) {
                Map row = (Map)object;
                rtr.add(row);
            }
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene(getById): "+e);
        }
        return rtr;
    }
    public List<Map<String, Object>> getAll() {
        return getById(null);
    }
    public static void main(String[] args) {
        DAOTransaksiDetail dao = new DAOTransaksiDetail();
        System.out.println(dao.getAll());
    }
}
