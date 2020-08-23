package hw2;

 public class Lab {

    private final String name;
    private final int capacity;
    private String currentGroupName="Available";
    private int numberOfStudents=0;

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

     public String getCurrentGroupName() {
         return currentGroupName;
     }

     public void setCurrentGroupName(String currentGroupName) {
         this.currentGroupName = currentGroupName;
     }

     public void increaseNumber_of_students() {
        this.numberOfStudents++;
    }
     public void decreaseNumberOfStudents(){
        this.numberOfStudents--;
    }

    public  Lab(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

}
