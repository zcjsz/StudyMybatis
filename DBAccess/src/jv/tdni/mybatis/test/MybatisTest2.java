package jv.tdni.mybatis.test;

import jv.tdni.mybatis.beans.Employee;
import jv.tdni.mybatis.dao.IEmployeeMapper2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 1. 准备 SQL 映射文件并注册到全局配置文件中
 * 2. 根据全局配置文件 mybatis-config.xml 创建一个 SqlSessionFactory 对象
 * 3. 通过 SqlSessionFactory 开启一个 SqlSession，这个 SqlSession 用于进行数据库会话，用完则关闭
 * 4. SqlSession 通过 SQL 映射文件中定义的 namespace + id 来定位要执行的 SQL 语句
 * 5. SqlSession 执行 SQL 查询语句返回一个 Java Bean 对象
 * @throws IOException
 */

public class MybatisTest2 {

    private InputStream inputStream = null;
    private SqlSession sqlSession = null;

    private void getSqlSession() {

        if(sqlSession != null) return;

        try {
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession(true);
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
     * 4. Mybatis 允许 增删改 有以下类型的返回值: Integer, Long, Boolean. 只需要在接口方法上定义返回类型就可以。
     */

    private void testGetEmployeeById2() {
        try {
            IEmployeeMapper2 employeeMapper = sqlSession.getMapper(IEmployeeMapper2.class);
            Employee employee = employeeMapper.getEmployeeById2(2);
            System.out.println(employee);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        MybatisTest2 mybatisTest2 = new MybatisTest2();
        mybatisTest2.getSqlSession();
        mybatisTest2.testGetEmployeeById2();
        mybatisTest2.closeSqlSession();
    }

}
