public class Student {
    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

    class GradeBook {
        private Student[] students;
        private int size;

        public GradeBook(int capacity) {
            students = new Student[capacity];
            this.size = 0;
        }

        public void addStudent(String name, int grade) {
            Student newStudent = new Student(name,grade);
            int pos = 0;
            for (int i = 0 ; i  < size ; i++){
                if(compareStrings(students[i].name,name)>0)
                {
                break;
                }
                pos = i+1;
            }
            for (int i = size ; i >pos;i--){
                students[i] = students[i-1];
            }
            students[pos] = newStudent;
            size++;
        }

        private int compareStrings(String str1, String str2) {
            int minlength = Math.min(str1.length(),str2.length());
            for (int i = 0 ; i < minlength;i++){
                if (str1.charAt(i)!=str2.charAt(i)){
                    return str1.charAt(i) - str2.charAt(i);
                }

            }
            return str1.length() - str2.length();
        }
        public int searchStudent(String name)
        {
            int lb = 0;
            int ub = size-1;
            while (lb<=ub){
                int mid = (lb+ub)/2;
                int cmp = compareStrings(students[mid].name,name);
                if(cmp==0){
                    return students[mid].grade;
                } else if (cmp<0) {
                    lb = mid + 1;
                }else {
                    ub = mid - 1;
                }
            }
            return -1;
        }
        public void displayStudents(){
            for (int i = 0 ; i < size ; i++){
                System.out.println("Name:" + students[i].name + ",Grade:"+students[i].grade);
            }
        }
    }