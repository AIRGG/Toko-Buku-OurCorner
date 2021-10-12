/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydao;

//import com.mysql.cj.Session;
//import javax.transaction.Transaction;
//import com.mysql.cj.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mypojo.HibUtil;
import mypojo.TblUser;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AIRGG
 */
public class DAOLogin {
    
    public List<TblUser> getBy(String uName, String uPass) {
        Session sess = HibUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<TblUser> lstTblUsr = new ArrayList();
        try {
         tx = sess.beginTransaction();
        Query query = sess.createQuery("from TblUser where username=:uName and password=:uPass");
        query.setString("uName", uName);
        query.setString("uPass", uPass);
        lstTblUsr = query.list();
         tx.commit();
      } catch (Exception e) {
          System.out.println("Err disini: "+e);
      } finally {
         sess.close(); 
      }
        return lstTblUsr;
    }
    
    public void listEmployeesEntity( ){
//      Session session = factory.openSession();
      Session sess = HibUtil.getSessionFactory().openSession();
      Transaction tx = null;
      
      try {
         tx = sess.beginTransaction();
//         String sql = "SELECT * FROM tbl_user";
//         SQLQuery query = sess.createSQLQuery(sql);
//         query.addEntity(TblUser.class);
        Query query = sess.createQuery("from TblUser where username=:uName");
        query.setString("uName", "gg");
         List employees = query.list();

         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            TblUser tblusr = (TblUser) iterator.next();
             System.out.println(tblusr.getUsername());
         }
         tx.commit();
      } catch (Exception e) {
          System.out.println("Err disini: "+e);
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
      } finally {
         sess.close(); 
      }
   }
    
    public static void main(String[] args) {
        DAOLogin lg = new DAOLogin();
//        lg.listEmployeesEntity();
        System.out.println(lg.getBy("gg", "123"));
    }
}
