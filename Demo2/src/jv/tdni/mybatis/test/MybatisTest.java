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


    private void test1() {
        try {
            Employee employee = sqlSession.selectOne("jv.tdni.mybatis.EmployeeMapper.selectEmployee", 1);
            System.out.println(employee);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    /***
     * 1. 通过 sqlSession.getMapper 获取 IEmployeeMapper 接口的代理对象
     * 2. 通过代理对象执行接口方法查询数据
     */
    private void test2() {
        try {
            IEmployeeMapper employeeMapper = sqlSession.getMapper(IEmployeeMapper.class);
            Employee employee = employeeMapper.getEmployeeById(1);
            System.out.println(employee);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        MybatisTest mybatisTest = new MybatisTest();
        mybatisTest.getSqlSession();
        mybatisTest.test1();
        mybatisTest.test2();
        mybatisTest.closeSqlSession();
    }

}
