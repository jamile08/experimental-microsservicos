package com.process.resources;


import com.process.resources.dto.RequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/calc")
public class CalcResource {

    @PostMapping
    public ResponseEntity<List<Integer>> calc(@RequestBody RequestDto numbers) {
        System.out.println("somando...");

        List<Integer> thirdVector = new ArrayList<>();

        for (int i = 0; i < numbers.array1().size(); i++) {
            thirdVector.add(numbers.array1().get(i) + numbers.array2().get(i));
            System.out.println(thirdVector.get(i));
        }

        return ResponseEntity.ok(thirdVector);
    }

}
