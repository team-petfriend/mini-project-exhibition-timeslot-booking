
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

const fetchTodos = () => {}

const { data, isLoading, isError, isSuccess, status, error } = useQuery({
  queryKey: ["todos"],  
  queryFn: fetchTodos,    
  staleTime: 1000 * 60,   
});

console.log(data);

const createTodo = () => {
  return fetch("/todo");
};

const queryClient = useQueryClient();

const { mutate, isPending } = useMutation({
  mutationFn: createTodo,                 
  onSuccess: () => {
    queryClient.invalidateQueries({
      queryKey: ["todos"], 
    })
  }
});

console.log(mutate);