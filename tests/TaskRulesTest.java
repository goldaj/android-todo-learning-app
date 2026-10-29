import com.example.myapplication.domain.Task;
import com.example.myapplication.domain.TaskState;
import com.example.myapplication.domain.TaskRules;
import java.util.Arrays;
import java.util.List;

public class TaskRulesTest {
    private static void check(boolean b,String msg) { if(!b)throw new AssertionError(msg); }
    public static void main(String[] args) {
        Task a=new Task(1L,"Acheter du pain",TaskState.TODO);
        Task b=new Task(2L,"Acheter du pain",TaskState.DONE);
        Task c=new Task(3L,"Réviser le Java",TaskState.TODO);
        List<Task> tasks=Arrays.asList(a,b,c);
        check(a.getId()!=b.getId(),"distinct duplicate titles must have distinct IDs");
        check(TaskRules.select(tasks,"acheter",null).size()==2,"search finds both duplicates");
        check(TaskRules.select(tasks,"",TaskState.TODO).size()==2,"todo filter");
        check(TaskRules.select(tasks,"",TaskState.DONE).size()==1,"done filter");
        List<Task> ordered=TaskRules.select(tasks,"",null);
        check(ordered.get(0).getId()==3 && ordered.get(1).getId()==1 && ordered.get(2).getId()==2,
                "todo-first and descending ID sort");
        check(TaskRules.normalizeTitle("  nettoyer   salon  ").equals("nettoyer salon"),"normalize title");
        boolean bad=false;try{TaskRules.normalizeTitle("   ");}catch(IllegalArgumentException e){bad=true;}
        check(bad,"reject blank");
        bad=false;try{TaskRules.normalizeTitle("a".repeat(141));}catch(IllegalArgumentException e){bad=true;}
        check(bad,"reject long");
        check(TaskRules.select(tasks,"RÉVISER",null).size()==1,"case-insensitive Unicode");
        System.out.println("8 règles TaskRules validées.");
    }
}
