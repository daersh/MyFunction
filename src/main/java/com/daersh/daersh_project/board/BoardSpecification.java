package com.daersh.daersh_project.board;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class BoardSpecification {

    public static Specification<Board> getBoards(BoardFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getLikes() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("likes"), filter.getLikes()));
            }

            if (filter.getHits() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("hits"), filter.getHits()));
            }

            if (filter.getSearch() != null && !filter.getSearch().isEmpty()) {
                switch (filter.getCategory()) {
                    case 0:
                        predicates.add(criteriaBuilder.or(
                                criteriaBuilder.like(root.get("title"), "%" + filter.getSearch() + "%"),
                                criteriaBuilder.like(root.get("content"), "%" + filter.getSearch() + "%")
                        ));
                        break;
                    case 1:
                        predicates.add(criteriaBuilder.like(root.get("title"), "%" + filter.getSearch() + "%"));
                        break;
                    case 2:
                        predicates.add(criteriaBuilder.like(root.get("content"), "%" + filter.getSearch() + "%"));
                        break;
                    case 3:
                        // Assuming userCode maps to a username in another table, you would need to join and filter by username
                        // This is a placeholder for the actual join logic
                        break;
                    default:
                        break;
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
