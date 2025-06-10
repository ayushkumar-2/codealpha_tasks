//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GradeBook gb = new GradeBook(100);
        gb.addStudent("mustafa",45);
        gb.addStudent("ayush",56);
        gb.addStudent("hamza",5);

        System.out.println("Students in the gradebook");
        gb.displayStudents();

        String searchName = "ayush";
        int grade = gb.searchStudent(searchName);
        if (grade!=-1){
            System.out.println(searchName+"'s Grade: "+grade);
        }else {
            System.out.println(searchName + " Not found in the grade book");
        }
    }
}