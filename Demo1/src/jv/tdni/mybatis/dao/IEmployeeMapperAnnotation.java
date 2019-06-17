package jv.tdni.mybatis.dao;

import jv.tdni.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Select;

public interface IEmployeeMapperAnnotation {

    @Select("select id, last_name lastName, gender, email from employee where id = #{id}")
    public Employee getEmployeeById(Integer id);
}
