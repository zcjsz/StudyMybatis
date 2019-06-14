package jv.tdni.mybatis.dao;

import jv.tdni.mybatis.beans.Employee;

public interface IEmployeeMapper {
    public Employee getEmployeeById(Integer id);
}
