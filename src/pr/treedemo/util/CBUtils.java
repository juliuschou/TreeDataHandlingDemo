package pr.treedemo.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class CBUtils {

	public static <T,R> LinkedHashMap<R, List<T>> groupingBy(
			List<T> list,
			Function<T, R> classifier) {
		return list.stream().collect(
			Collectors.groupingBy( classifier, 
				LinkedHashMap::new,
				Collectors.mapping(Function.identity(), Collectors.toList())
			)
		);
	}
	
	public static <T, K, U> Collector<T, ?, Map<K, U>> toLinkedMap(
			Function<? super T, ? extends K> keyMapper,
			Function<? super T, ? extends U> valueMapper) {
		return Collectors.toMap(
			keyMapper, 
			valueMapper, 
			(u, v) -> {
				throw new IllegalStateException(String.format("Duplicate key %s", u));
			}, 
			LinkedHashMap::new
		);
	}
	
	public static <T,RK,RV> Map<RK, RV> toLinkedMap( List<T> list, Function<T, RK> keyMapper, Function<T, RV> valueMapper) {
		return list.stream().collect(toLinkedMap( keyMapper, valueMapper ));
	}
	
	public static <T,RK> Map<RK, T> toLinkedMap( List<T> list, Function<T, RK> keyMapper) {
		return toLinkedMap(list, keyMapper, e->e);
	}
	
	public static <T, RK> List<RK> toPropertyList(List<T> list, Function<T, RK> keyMapper) {
		return list.stream().map(keyMapper).collect(Collectors.toList());
	}
	
	public static <T, RK> List<RK> toPropertyLinkedList(List<T> list, Function<T, RK> keyMapper) {
		return list.stream().map(keyMapper).collect(Collectors.toCollection(LinkedList::new));
	}
	
	public static <T> T getNext(List<T> list, T ele) {
	    int idx = list.indexOf(ele);
	    if (idx < 0 || idx+1 == list.size()) return null;
	    return list.get(idx + 1);
	}

	public static <T> T getPrevious(List<T> list, T ele) {
	    int idx = list.indexOf(ele);
	    if (idx <= 0) return null;
	    return list.get(idx - 1);
	}
	
	
	public static <T> Stream<List<T>> batches(List<T> source, int length) {
		if (length <= 0)
			throw new IllegalArgumentException("length = " + length);
		int size = source.size();
		if (size <= 0)
			return Stream.empty();
		int fullChunks = (size - 1) / length;
		return IntStream.range(0, fullChunks + 1)
				.mapToObj(n -> source.subList(n * length, n == fullChunks ? size : (n + 1) * length));
	}
	
	public static <T, K1, K2> Map<K1, Map<K2, List<T>>> toNestedGroupingBy(List<T> list, Function<T, K1> classifier1, Function<T, K2> classifier2) {
	    return list.stream().collect(
	        Collectors.groupingBy(
	            classifier1,
	            LinkedHashMap::new,
	            Collectors.groupingBy(
	                classifier2,
	                LinkedHashMap::new,
	                Collectors.mapping(Function.identity(), Collectors.toList())
	            )
	        )
	    );
	  }
}
