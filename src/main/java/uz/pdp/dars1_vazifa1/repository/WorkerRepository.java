package uz.pdp.dars1_vazifa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dars1_vazifa1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
}
