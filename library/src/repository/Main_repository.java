package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main_repository<T> {

    //final is set beacause once assigned it cannot be changed
    private final Map<String,T> data_map;

    public Main_repository()
    {
        this.data_map= new HashMap<>();
    }

    public void add(String id,T item)
    {

        data_map.put(id,item);
    }

    public T get_id(String id)
    {
        return data_map.get(id);
    }

    public void remove(String id)
    {
        data_map.remove(id);
    }

    public List<T> get_all_data()
    {
        return new ArrayList<>(data_map.values());
    }

    public List<T> search(Predicate<T> condition) {
        return data_map.values().stream()
                .filter(condition)
                .collect(Collectors.toList());
    }



}
