package jv.tdni.mybatis.dao;

import jv.tdni.mybatis.beans.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IEmployeeMapper2 {

    public Employee getEmployeeById(Integer id);

    public Employee getEmployeeByIdStep(Integer id);

    public Employee getEmpAndDeptById(Integer id);

}
