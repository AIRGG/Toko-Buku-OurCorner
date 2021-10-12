/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydao;

import java.util.ArrayList;
import java.util.List;
import mypojo.HibUtil;
import mypojo.TblUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AIRGG
 */
public class DAOUser {
    private final static String namaTbl = "TblUser";
    private final static String idTbl = "username";
    
    public void add(TblUser tblnya) {
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
    public void upd(TblUser tblnya) {
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
            // Agak Beda disini, INTEGER karna id_login
            TblUser datanow = (TblUser) sess.load(TblUser.class, new Integer(idnya));
            sess.delete(datanow);
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene(Del): "+e);
        }
    }
    public List<TblUser> getById(String idnya) {
        List<TblUser> lst = new ArrayList();
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
    public List<TblUser> getAll() {
        return getById(null);
    }
    
    
    public static void main(String[] args) {
        DAOUser usr = new DAOUser();
        System.out.println(usr.getById("gg").get(0).getPassword());
    }
}
