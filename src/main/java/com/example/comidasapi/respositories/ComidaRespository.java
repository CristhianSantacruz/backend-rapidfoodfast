package com.example.comidasapi.respositories;

import com.example.comidasapi.models.ComidaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRespository extends JpaRepository<ComidaModel,Long> {
}
