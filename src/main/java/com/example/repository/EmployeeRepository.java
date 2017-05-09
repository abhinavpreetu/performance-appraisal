/**
 * 
 */
package com.example.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Designation;
import com.example.entities.Employee;
import com.example.entities.ProjectEntity;

/**
 * @author abhinav.preetu@darkhorseboa.com
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/**
	 * @param string
	 */
	void findByFname(String string);

	/**
	 * @param employeeId
	 * @return 
	 */
	Employee findByEmpId(Long employeeId);

	//List<Employee> findByDesignationDesg(String desg);
	List<Employee>findByProjectIdIsNullAndDesignationDesgNotIn(String desg);

	Employee findByEmail(String employee);

	List<Employee> findByProjectId(ProjectEntity project);

	Employee findByContact(Long contact);

}
