package br.com.jardson.mscalculate.web;

import br.com.jardson.mscalculate.entity.Rules;
import br.com.jardson.mscalculate.exception.ResourceNotFoundException;
import br.com.jardson.mscalculate.repository.RulesRepository;
import br.com.jardson.mscalculate.service.RulesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class RulesServiceTest {

    @Mock
    private RulesRepository repository;

    @InjectMocks
    private RulesService service;

    @Test
    public void testCreate() {
        Rules rules = new Rules();
        rules.setCategory("Test Category");
        rules.setParity(10);

        when(repository.save(any(Rules.class))).thenReturn(rules);

        Rules savedRules = service.create(rules);

        verify(repository, times(1)).save(rules);
        assertEquals("Test Category", savedRules.getCategory());
        assertEquals(10, savedRules.getParity());
    }

    @Test
    public void testGetRulesById() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(new Rules(id, "Category", 10)));

        Rules foundRules = service.getRulesById(id);

        verify(repository, times(1)).findById(id);

        assertEquals("Category", foundRules.getCategory());
        assertEquals(10, foundRules.getParity());
    }

    @Test
    public void testUpdate() {
        Long id = 1L;

        Rules existingRules = new Rules(id, "Category", 10);
        Rules updatedRules = new Rules(id, "Updated Category", 20);

        when(repository.getReferenceById(id)).thenReturn(existingRules);
        when(repository.save(any(Rules.class))).thenReturn(updatedRules);

        Rules savedRules = service.update(id, updatedRules);

        verify(repository, times(1)).getReferenceById(id);
        verify(repository, times(1)).save(existingRules);

        assertEquals("Updated Category", savedRules.getCategory());
        assertEquals(20, savedRules.getParity());
    }

    @Test
    public void testFindAll() {
        List<Rules> rulesList = new ArrayList<>();
        rulesList.add(new Rules(1L, "Category 1", 10));
        rulesList.add(new Rules(2L, "Category 2", 20));
        when(repository.findAll()).thenReturn(rulesList);

        List<Rules> foundRules = service.findAll();

        verify(repository, times(1)).findAll();

        assertEquals(2, foundRules.size());
        assertEquals("Category 1", foundRules.get(0).getCategory());
        assertEquals(20, foundRules.get(1).getParity());
    }
}


