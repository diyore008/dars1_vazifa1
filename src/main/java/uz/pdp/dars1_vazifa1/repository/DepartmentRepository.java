package uz.pdp.dars1_vazifa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dars1_vazifa1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
