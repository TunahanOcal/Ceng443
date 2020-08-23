package hw2;

import java.util.concurrent.locks.*;

public class  StudyGroup {

    private final String name;
    private final Lab lab;
    private int studentNumber=0;
    private Lock lock = new ReentrantLock();
    private Condition labIsFull = lock.newCondition();

    public StudyGroup(String name, Lab lab) {
        this.name = name;
        this.lab = lab;
    }

    public String getName() {
        return name;
    }

    public Lab getLab() {
        return lab;
    }

    public void startStudyingWith(){
        lock.lock();
        try{
        while(this.lab.getNumberOfStudents()>=this.lab.getCapacity() || this.studentNumber!=this.lab.getNumberOfStudents() ){
            labIsFull.signalAll();
            labIsFull.await();
        }
            this.lab.increaseNumber_of_students();
            this.lab.setCurrentGroupName(this.name);
            this.studentNumber++;

        }catch (InterruptedException e) {
        e.printStackTrace();}
        finally{

            lock.unlock();
        }
    }
    public void stopStudyingWith(){
        lock.lock();
        try {

            this.lab.decreaseNumberOfStudents();
            this.studentNumber--;

        }finally {
            labIsFull.signalAll();
            lock.unlock();
        }
    }
}
