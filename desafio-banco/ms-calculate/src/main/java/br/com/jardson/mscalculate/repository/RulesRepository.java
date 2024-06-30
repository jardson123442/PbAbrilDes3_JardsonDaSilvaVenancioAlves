package br.com.jardson.mscalculate.repository;

import br.com.jardson.mscalculate.entity.Rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulesRepository extends JpaRepository<Rules, Long> {

}
