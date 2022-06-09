package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
    @Cacheable(cacheNames = "employees")
	public List<Employee> retrieveEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

    @Cacheable(cacheNames = "employees", key = "#employeeId")
	public Employee getEmployee(Long employeeId) {
		Optional<Employee> optEmp = employeeRepository.findById(employeeId);
		return optEmp.get();
	}

	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@CacheEvict(cacheNames = "employees", key = "#employeeId", allEntries = false)
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

    @CachePut(cacheNames = "employees", key = "#employeeId")
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}