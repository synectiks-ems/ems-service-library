package com.synectiks.library.service.impl;

import com.synectiks.library.service.IssueBookService;
import com.synectiks.library.domain.IssueBook;
import com.synectiks.library.repository.IssueBookRepository;
import com.synectiks.library.service.dto.IssueBookDTO;
import com.synectiks.library.service.mapper.IssueBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link IssueBook}.
 */
@Service
@Transactional
public class IssueBookServiceImpl implements IssueBookService {

    private final Logger log = LoggerFactory.getLogger(IssueBookServiceImpl.class);

    private final IssueBookRepository issueBookRepository;

    private final IssueBookMapper issueBookMapper;

    public IssueBookServiceImpl(IssueBookRepository issueBookRepository, IssueBookMapper issueBookMapper) {
        this.issueBookRepository = issueBookRepository;
        this.issueBookMapper = issueBookMapper;
    }

    @Override
    public IssueBookDTO save(IssueBookDTO issueBookDTO) {
        log.debug("Request to save IssueBook : {}", issueBookDTO);
        IssueBook issueBook = issueBookMapper.toEntity(issueBookDTO);
        issueBook = issueBookRepository.save(issueBook);
        return issueBookMapper.toDto(issueBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueBookDTO> findAll() {
        log.debug("Request to get all IssueBooks");
        return issueBookRepository.findAll().stream()
            .map(issueBookMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<IssueBookDTO> findOne(Long id) {
        log.debug("Request to get IssueBook : {}", id);
        return issueBookRepository.findById(id)
            .map(issueBookMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete IssueBook : {}", id);
        issueBookRepository.deleteById(id);
    }
}
