package jv.tdni.mybatis.dao;

import jv.tdni.mybatis.beans.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IEmployeeMapper {

    public boolean addEmployee(Employee employee);

    public boolean deleteEmployeeById(Integer id);

    public int updateEmployee(Employee employee);

    public Employee getEmployeeById(Integer id);

    public Employee getEmployeeByIdAndName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmployeeByMapData(HashMap<String, String> map);

    public List<Employee> getEmployeeByLikeNameRetList(String lastName);

    public Map<String, Object> getEmployeePropMapById(Integer id);

    // 多条记录封装成一个 Map 数据类型，MapKey 是记录的主键，MapVal 是记录封装后的 JavaBean
    // 用注解的方式告诉 Mybatis 封装这个 Map 的时候使用哪个属性作为 MapKey
    @MapKey("id")
    public Map<Integer, Employee> getEmployeeByLikeNameRetMap(String lastName);
}
