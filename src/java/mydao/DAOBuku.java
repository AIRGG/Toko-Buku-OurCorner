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
import mypojo.TblBuku;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AIRGG
 */
public class DAOBuku {
    private final static String namaTbl = "TblBuku";
    private final static String idTbl = "id_buku";
    
    public void add(TblBuku tblnya) {
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
    public void upd(TblBuku tblnya) {
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
            TblBuku datanow = (TblBuku) sess.load(TblBuku.class, new String(idnya));
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
            
            String sql = "SELECT "
                    + "a.*, b.nama nama_penulis, c.nama nama_penerbit, d.nama nama_genre, d.keterangan "
                    + "FROM tbl_buku a "
                    + "INNER JOIN tbl_penulis b ON a.penulis_id=b.id_penulis "
                    + "INNER JOIN tbl_penerbit c ON a.penerbit_id=c.id_penerbit "
                    + "INNER JOIN tbl_genre d ON a.genre_id=d.id_genre "
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
    
    public List cobaJoin() {
        List<Map<String, Object>> rtr = new ArrayList<>();
        List lst = new ArrayList();
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            
            String sql = "SELECT "
                    + "a.*, b.nama nama_penulis, c.nama nama_penerbit, d.nama nama_genre, d.keterangan "
                    + "FROM tbl_buku a "
                    + "INNER JOIN tbl_penulis b ON a.penulis_id=b.id_penulis "
                    + "INNER JOIN tbl_penerbit c ON a.penerbit_id=c.id_penerbit "
                    + "INNER JOIN tbl_genre d ON a.genre_id=d.id_genre "
                    + "WHERE 1=1 AND id_buku='BK01'";
            Query q = sess.createSQLQuery(sql);
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
    
    
    public static void main(String[] args) {
        DAOBuku dao = new DAOBuku();
        List<Map<String, Object>> isi = dao.getById("BK01");
//        List<Map<String, Object>> rtr = new ArrayList<>();
//        for(Object object : isi) {
//            Map row = (Map)object;
//            System.out.println(row);
//            rtr.add(row);
////            System.out.print("First Name: " + row.get("nama_penulis")); 
////            System.out.println(", Salary: " + row.get("judul")); 
//         }
        
        System.out.println(isi);
    }
}
