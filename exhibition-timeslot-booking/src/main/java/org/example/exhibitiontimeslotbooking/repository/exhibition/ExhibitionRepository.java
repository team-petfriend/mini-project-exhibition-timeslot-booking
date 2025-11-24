package org.example.exhibitiontimeslotbooking.repository.exhibition;

import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
}
