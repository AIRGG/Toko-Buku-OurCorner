/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydao;

import java.util.ArrayList;
import java.util.List;
import mypojo.HibUtil;
import mypojo.TblPelanggan;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AIRGG
 */
public class DAOPelanggan {

    public void addPelanggan(TblPelanggan plg) {
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            sess.save(plg);
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene: "+e);
        }
    }
    
    public void updPelanggan(TblPelanggan plg) {
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            sess.update(plg);
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene: "+e);
        }
    }
    
    public void delPelanggan(String idplg) {
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            TblPelanggan plg = (TblPelanggan) sess.load(TblPelanggan.class, new String(idplg));
            sess.delete(plg);
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene: "+e);
        }
    }
    
    public List<TblPelanggan> getById(String idplg) {
        List<TblPelanggan> lstplg = new ArrayList();
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            Query q = sess.createQuery("from TblPelanggan where id_pelanggan=:vIdplg");
            q.setString("vIdplg", idplg);
            lstplg = q.list();
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene: "+e);
        }
        return lstplg;
    }
    
    public List<TblPelanggan> getTblPelanggan() {
        List lstplg = new ArrayList();
        Transaction tx = null;
        Session sess = HibUtil.getSessionFactory().openSession();
        try {
            tx = sess.beginTransaction();
            Query q = sess.createQuery("from TblPelanggan");
            lstplg = q.list();
            tx.commit();
        }catch(Exception e) {
            System.out.println("Error kene: "+e);
        }
        return lstplg;
    }
    
    public static void main(String[] args) {
        DAOPelanggan plg = new DAOPelanggan();
        List<TblPelanggan> isi = plg.getTblPelanggan();
        System.out.println(isi.get(0).getAlamat());
        System.out.println(isi);
    }
    
}
