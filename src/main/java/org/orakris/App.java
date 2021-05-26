package org.orakris;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory sf = metadata.getSessionFactoryBuilder().build();
        Session session = sf.openSession();

//        Transaction t = session.beginTransaction();
//        // store data
//        Student student1= new Student();
//        student1.setName("abc");
//
//        Student student2 = new Student();
//        student2.setName("def");
//
//        College college = new College();
//        college.setName("mit");
//
//        student1.setCollege(college);
//        student2.setCollege(college);
//
//        session.persist(student1);
//        session.persist(student2);
//        t.commit();

        // fetch data
        TypedQuery query = session.createQuery("from Student");
        List<Student> list = query.getResultList();

        Iterator<Student> itr = list.iterator();
        while (itr.hasNext()) {
            Student student = itr.next();
            System.out.println(student.getId() + " " + student.getName());
            College college = student.getCollege();
            System.out.println(college.getId() + " " + college.getName());
        }
        session.close();
        System.out.println("success");
    }
}
