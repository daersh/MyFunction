package com.daersh.daersh_project.board;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>specification</h1>
 * DB 쿼리 조건을 작성하기 위한 것이지만 QueryDSL 방식을 더 추천한다는 글을 보고 사용하지 않을 예정
 * */


public class BoardSpecification {

    public static Specification<Board> getBoards(BoardFilter filter) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 1. 좋아요
            if (filter.getLikes() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("likes"), filter.getLikes()));
            }
            // 2. 조회수
            if (filter.getHits() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("hits"), filter.getHits()));
            }
            // 검색어
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
                        // 아직 연관관계 설정하지 않아 username은 추후 개선 예정
                        break;
                    default:
                        break;
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
