package com.demo.security01.controller;

import lombok.val;
import org.apache.el.stream.Stream;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.security.core.parameters.P;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.CharacterIterator;
import java.util.*;
import java.util.stream.IntStream;

public class IntTest {

//    정수 배열 arr와 2차원 정수 배열 queries이 주어집니다. queries의 원소는 각각 하나의 query를 나타내며, [s, e, k] 꼴입니다.
//
//    각 query마다 순서대로 s ≤ i ≤ e인 모든 i에 대해 k보다 크면서 가장 작은 arr[i]를 찾습니다.
//
//    각 쿼리의 순서에 맞게 답을 저장한 배열을 반환하는 solution 함수를 완성해 주세요.
//    단, 특정 쿼리의 답이 존재하지 않으면 -1을 저장합니다.


//    arr	                queries                 	result
//  [0, 1, 2, 4, 3]	[[0, 4, 2],[0, 3, 2],[0, 2, 2]]	[3, 4, -1]


    public int[] solution(int[] arr, int[][] queries) {
        List<Integer> answer = new ArrayList<>();

        for (int[] query : queries) {
            int s = query[0];
            int e = query[1];
            int k = query[2];

            List<Integer> list = new ArrayList<>();
            for (int i = s; i <= e; i++) {
                if (arr[i] > k) {
                    list.add(arr[i]);
                }
            }

            if (!list.isEmpty()) {
                Collections.sort(list);
                answer.add(list.get(0));
            } else {
                answer.add(-1);
            }
        }

        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

//return IntStream.range(0, included.length).map(idx -> included[idx]?a+(idx*d):0).sum();


        public static void main(String[] args) {

            String str = "wsdawsdassw";
            String res = "wsddawsddasdsdwasd";
//            int[][] num_log = [0, 3],[1, 2],[1, 4];
           IntTest intTest = new IntTest();
//           String asd = intTest.solution(num_log);
//            System.out.println("asd = " + asd);
        }

}
