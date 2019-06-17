package jv.tdni.mybatis.test;

import jv.tdni.mybatis.beans.Employee;
import jv.tdni.mybatis.dao.IEmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/***
 * 1. 准备 SQL 映射文件并注册到全局配置文件中
 * 2. 根据全局配置文件 mybatis-config.xml 创建一个 SqlSessionFactory 对象
 * 3. 通过 SqlSessionFactory 开启一个 SqlSession，这个 SqlSession 用于进行数据库会话，用完则关闭
 * 4. SqlSession 通过 SQL 映射文件中定义的 namespace + id 来定位要执行的 SQL 语句
 * 5. SqlSession 执行 SQL 查询语句返回一个 Java Bean 对象
 * @throws IOException
 */

public class MybatisTest {

    private InputStream inputStream = null;
    private SqlSession sqlSession = null;

    private void getSqlSession() {

        if(sqlSession != null) return;

        try {
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void closeSqlSession() {
        if(sqlSession != null) sqlSession.close();
    }

    /***
     * 1. 通过 sqlSession.getMapper 获取 IEmployeeMapper 接口的代理对象
     * 2. 通过代理对象执行接口方法操作数据
     * 3. 默认获取到的 SqlSession 不会自动提交，需要手动提交
     */
    private void testAdd() {
        Employee employee = new Employee();
        employee.setLastName("Foo");
        employee.setGender("F");
        employee.setEmail("foo@123.com");
        try {
            IEmployeeMapper employeeMapper = sqlSession.getMapper(IEmployeeMapper.class);
            employeeMapper.addEmployee(employee);
            sqlSession.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void testGet() {
        try {
            IEmployeeMapper employeeMapper = sqlSession.getMapper(IEmployeeMapper.class);
            Employee employee = employeeMapper.getEmployeeById(2);
            System.out.println(employee);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }



//    private void testAdd() {
//        try {
//            IEmployeeMapper employeeMapper = sqlSession.getMapper(IEmployeeMapper.class);
//            Employee employee = employeeMapper.addEmployee(1);
//            System.out.println(employee);
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
//    }



    public static void main(String[] args) throws IOException {
        MybatisTest mybatisTest = new MybatisTest();
        mybatisTest.getSqlSession();
        mybatisTest.testGet();
        mybatisTest.testAdd();
        mybatisTest.closeSqlSession();
    }

}
