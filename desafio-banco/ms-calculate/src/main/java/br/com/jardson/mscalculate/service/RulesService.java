package br.com.jardson.mscalculate.service;

import br.com.jardson.mscalculate.entity.Rules;
import br.com.jardson.mscalculate.repository.RulesRepository;
import br.com.jardson.mscalculate.web.dto.mapper.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulesService {

    @Autowired
    RulesRepository repository;

    public Rules create(Rules rules) {
        return repository.save(rules);
    }


    public Rules getRulesById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Rules update(Long id, Rules rules) {
        Rules entity = repository.getReferenceById(id);
        updateData(entity, rules);
        return repository.save(entity);
    }

    public void delete(Long id) {
        Rules entity = repository.getReferenceById(id);
        repository.delete(entity);
    }

    public List<Rules> findAll() {
        return DozerMapper.parseListObjects(repository.findAll(), Rules.class);
    }


    private void updateData(Rules entity, Rules rules) {
        entity.setCategory(rules.getCategory());
        entity.setParity(rules.getParity());
    }
}


