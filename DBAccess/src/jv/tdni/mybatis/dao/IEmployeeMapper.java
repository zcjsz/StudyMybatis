package jv.tdni.mybatis.dao;

import jv.tdni.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface IEmployeeMapper {

    public boolean addEmployee(Employee employee);

    public boolean deleteEmployeeById(Integer id);

    public int updateEmployee(Employee employee);

    public Employee getEmployeeById(Integer id);

    public Employee getEmployeeByIdAndName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmployeeByMapData(HashMap<String, String> map);
}
