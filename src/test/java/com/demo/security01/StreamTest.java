package com.demo.security01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.InitBinder;

import javax.persistence.PrePersist;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class StreamTest {
    public List<Member> createMember(){
        log.info(" ==================================");
        log.info(" ============  createMember ==========");
        List<Member> members = new ArrayList<>();
        members.add(new Member("Kim", 20, false));
        members.add(new Member("Ahn", 40, true));
        members.add(new Member("Lee", 60, false));
        log.info("members ={}", members);
        log.info(" =====================================");
        return members;
    }

    @Test
    public void filterTest(){

        // filter : 특정 조건을 만족하는 데이터만 걸러내는데 사용
        // Predicate 에 true 를 반환하는 data 만 존재하는 stream 리턴

        List<Member> members = createMember();
        /*members.add(new Member("Kim", 20, false));
        members.add(new Member("Ahn", 40, true));
        members.add(new Member("Lee", 60, false));*/

        // filter 를 사용해 나이가 30 이상인 회원 조회
        List<Member> ageFilteredList = members.stream()
                .filter(x -> x.age >= 30)
                .collect(Collectors.toList());
        Assertions.assertThat(ageFilteredList.size()).isEqualTo(2);
        log.info("ageFilteredList ={}", ageFilteredList);
    }

    @Test
    public void mapTest(){
        List<Member> members = createMember();
        // Map : 데이터 변형시 사용
        // 데이터에 해당 함수가 적용된 결과물을 제공하는 stream 리턴

        // Map 을 사용해 Member List 를 나이를 가진 Integer 리스트로 변형
        List<Integer> ageList = members.stream()
//                .map(p -> p.getAge())// 람다식 표현
                .map(Member::getAge)// 메서드 리퍼런스
                .collect(Collectors.toList());

        log.info("ageList = {}", ageList);
    }

    @Test
    public void sortedTest(){
        List<Member> members = createMember();

        // 데이터가 순서대로 정렬된 stream 을 리턴
        // 데이터의 종류에 따라 Comparator 가 필요할 수 있음

        // 이름 기준으로 알파벳 오름차순 정렬한 이후 이름 추출
        List<String> nameAscList = members.stream()
                .sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
//                .map(p -> p.getFirstName())
                .map(Member::getFirstName)
                .collect(Collectors.toList());

        log.info("nameAscList ={}", nameAscList);

    }

    @Setter
    @Getter
    @ToString
    class Member {
        String firstName;
        int age;
        Boolean isVerified;

        public Member(String firstName, int age, Boolean isVerified) {
            this.firstName = firstName;
            this.age = age;
            this.isVerified = isVerified;
        }
    }
}




