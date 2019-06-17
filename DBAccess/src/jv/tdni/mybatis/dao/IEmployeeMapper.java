package jv.tdni.mybatis.dao;

import jv.tdni.mybatis.beans.Employee;

public interface IEmployeeMapper {

    public void addEmployee(Employee employee);

    public void deleteEmployeeById(Integer id);

    public void updateEmployee(Employee employee);

    public Employee getEmployeeById(Integer id);
}
