/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydao;

import java.util.ArrayList;
import java.util.List;
import mypojo.HibUtil;
import mypojo.TblPenulis;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AIRGG
 */
public class DAOPenulis {
    
    private final static String namaTbl = "TblPenulis";
    private final static String idTbl = "id_penulis";
    
    public void add(TblPenulis tblnya) {
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
    public void upd(TblPenulis tblnya) {
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
            TblPenulis datanow = (TblPenulis) sess.load(TblPenulis.class, new String(idnya));
            sess.delete(datanow);
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene(Del): "+e);
        }
    }
    public List<TblPenulis> getById(String idnya) {
        List<TblPenulis> lst = new ArrayList();
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            
            String sql = "from "+ namaTbl +" where 1=1 ";
            if(idnya != null) sql += " and "+ idTbl +"=:vId";
            Query q = sess.createQuery(sql);
            if(idnya != null) q.setString("vId", idnya);
            
            lst = q.list();
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene(getById): "+e);
        }
        return lst;
    }
    public List<TblPenulis> getAll() {
        return getById(null);
    }
    
    
    public static void main(String[] args) {
//        DAOPenulis dao = new DAOPenulis();
//        System.out.println(dao.getById("1").get(0).getNama());
//        TblPenulis nulis = new TblPenulis();
//        nulis.setIdPenulis("11");
//        nulis.setNama("APAPUN");
//        nulis.setAlamat("a");
//        nulis.setEmail("a");
//        nulis.setNoTelp("a");
//        dao.add(nulis);
    }
}
