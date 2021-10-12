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
import mypojo.TblTrx;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AIRGG
 */
public class DAOTransaksi {
    private final static String namaTbl = "TblTrx";
    private final static String idTbl = "a.id_trx";
    
    public void add(TblTrx tblnya) {
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
    public void upd(TblTrx tblnya) {
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
    public void del(String idnya) {
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            TblTrx datanow = (TblTrx) sess.load(TblTrx.class, new String(idnya));
            sess.delete(datanow);
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
            
            String sql = "SELECT *, "
                    + "(SELECT COUNT(*) FROM tbl_det_trx z WHERE z.trx_id=a.id_trx) total_buku, "
                    + "(SELECT SUM(harga) FROM tbl_det_trx z WHERE z.trx_id=a.id_trx) total_harga "
                    + "FROM tbl_trx a "
                    + "INNER JOIN tbl_pelanggan b ON a.pelanggan_id=b.id_pelanggan "
                    + "WHERE 1=1 " ;
            
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
        DAOTransaksi dao = new DAOTransaksi();
        System.out.println(dao.getAll());
    }
}
