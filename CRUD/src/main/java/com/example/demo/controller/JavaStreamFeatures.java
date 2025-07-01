package com.example.demo.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.hibernate.query.criteria.internal.expression.function.SubstringFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

@RestController
public class JavaStreamFeatures {
	
	@RequestMapping(value="/streams", method= RequestMethod.GET)
	public void streamFeatures() {
			List<String> names = Arrays.asList("a", null, "b", null, "c", "d",null);
		
		//1. Stream.ofNullable
			List<String> res = names.stream().filter(name-> null!=name).collect(Collectors.toList());
//			Object res1 = names.stream().map(Stream::).collect(Collectors.toList());
			System.out.println(res);
			
		//2. Stream.iterate(seed, f) starts with seed: 0 , and preform the function with previous value 
			Stream.iterate(0, n->n+2).limit(5).forEach(System.out::println);
			
			//output: 0, 2,4,6,8
			
		//3. Collectors.collectingAndThen(downstream, finisher)
			List<User> users = Stream.of(new User(1,"abc"),new User(2,"def"),new User(3,"ghi")).collect(Collectors.toList());
		Long avg = users.stream().mapToDouble(User::getId).boxed()
				.collect(Collectors.collectingAndThen(Collectors.averagingDouble(Double::doubleValue), Math::round));
		List<User> sample = users.stream().filter(u-> (u.getId()!=1 && u.getId()!=2 )).collect(Collectors.toList());
		System.out.println(avg);
		
		//4.	Stream.takeWhile and Stream.dropWhile
		List<Integer> numbers = List.of(1,2,3,4,5,6,7,8);
		List<Integer> resList = numbers.stream().takeWhile(num->num<3).collect(Collectors.toList());
		System.out.println(resList);
		
		List<Integer> resList1 = numbers.stream().dropWhile(num->num<3).collect(Collectors.toList());
		System.out.println(resList1);
		
		List<Integer> newRes = numbers.stream().dropWhile(num-> num<3).takeWhile(num->num<7).collect(Collectors.toList());
		System.out.println(newRes);
		
		//5. Collectors.teeing(downstream1, downstream2, merger) introduced in JDK 12
		// It allows two collectors in parallel and combine there result
		
		Map<String,Integer> minMaxRes = numbers.stream().collect(Collectors.teeing(Collectors.maxBy(Integer::compareTo),
				Collectors.minBy(Integer::compareTo), 
				(e1,e2)->Map.of("maxVal",e1.get(),"min",e2.get())));
		
		System.out.println(minMaxRes);
		
		// 6. Stream.concat(a, b). two streams can concat
		Stream<Integer> a = Stream.of(1,2,3);
		Stream<Integer> b = Stream.of(4,5,6,7);
		Stream<Integer> concatstream = Stream.concat(a, b);
//		List<Integer> resStream = Stream.concat(a, b).toList();
		System.out.println(concatstream);
		int sum = concatstream.mapToInt(Integer::intValue).sum();
		System.out.println(sum);
		
		// 7. Collections.partitioning. introduced in java 8
		// It will partition the stream into two groups based on the boolean condition
		Map<Boolean, List<Integer>> mapRes = numbers.stream().collect(Collectors.partitioningBy(num->num%2==0));
		System.out.println(mapRes);
		System.out.println("Even Num:"+ mapRes.get(Boolean.TRUE));
		System.out.println("Odd Num:"+ mapRes.get(Boolean.FALSE));
		
		//8. IntStream for range
		// ranage will exclude the last value, rangeClose will include last value
		
		List<Integer> range = IntStream.range(10, 20).boxed().collect(Collectors.toList());
		List<Integer> rangeClosed = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
		System.out.println(range);
		System.out.println(rangeClosed);
	}
	
	@RequestMapping(value="/collectionFeatures", method = RequestMethod.GET)
	public void collectionFeatures() {
		
		//1. Collections.nCopies(n, o). it will return the immutable(can't Modify) list containing n no of copies
		List<String> names = Collections.nCopies(5, "sampleText");
		System.out.println(names);
		
		try {
			names.set(0, "testData");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 2. Collections.frequency(c, o) 
		List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,2,2,2);
		int freq = Collections.frequency(numbers, 2);
		System.out.println(freq);
		//find the occurrence of the each element in the list
		
		Map<Object, Object> mapRes = numbers.stream().collect(Collectors.toMap(number -> number,
				number -> Collections.frequency(numbers, number), (existingValue, newValue) -> existingValue));
		System.out.println(mapRes);
		
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public void test() {
		
		BigDecimal rating = BigDecimal.valueOf(2.45);
		String res = rating.setScale(1, RoundingMode.HALF_UP).toString();
		
		String str="'pour '&amp;Left (ToText({?Année}), 5)";
		
		//"pour" + substring($P{Année},5)
		str=str.substring(1, 5);
		str=str.replace("'pour '","\"pour\" + substring" );
		str=str.replace("&amp;Left ","" );
		str=str.replace("ToText({?Année})","$P{Année},1" );
		System.out.println(str);
	}

}
